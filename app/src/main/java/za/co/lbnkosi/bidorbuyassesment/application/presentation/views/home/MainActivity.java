package za.co.lbnkosi.bidorbuyassesment.application.presentation.views.home;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import com.airbnb.lottie.LottieAnimationView;
import java.util.Objects;
import javax.inject.Inject;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import za.co.lbnkosi.bidorbuyassesment.R;
import za.co.lbnkosi.bidorbuyassesment.application.dependencyinjection.application.AndroidApplication;
import za.co.lbnkosi.bidorbuyassesment.application.presentation.base.BaseActivity;
import za.co.lbnkosi.bidorbuyassesment.application.presentation.utils.network.NetworkHelper;
import za.co.lbnkosi.bidorbuyassesment.application.presentation.utils.sharedPrefs.SharedPreferencesHelper;
import za.co.lbnkosi.bidorbuyassesment.application.presentation.views.augmentedreality.AugmentedRealityActivity;

public class MainActivity extends BaseActivity implements AdapterView.OnItemSelectedListener {

    @BindView(R.id.recyclerView) RecyclerView recyclerView;
    @BindView(R.id.total_results) TextView totalResultsTextView;
    @BindView(R.id.filter_sort_button) Button filterSortButton;
    @BindView(R.id.loading_animation) LottieAnimationView animationView;
    @BindView(R.id.searchBox) EditText searchBox;
    @BindView(R.id.ar_button) Button augmentedRealityButton;
    @BindView(R.id.swipeRefresh) SwipeRefreshLayout swipeRefreshLayout;

    @Inject Constants constants;
    @Inject NetworkHelper networkHelper;
    @Inject SharedPreferencesHelper sharedPreferencesHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        ((AndroidApplication)getApplicationContext()).applicationComponent().inject(this);
        swipeRefreshLayout.setOnRefreshListener(() -> {
            initializeApplication();
            swipeRefreshLayout.setRefreshing(false);
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        initializeApplication();
        startEditTextListener(searchBox, recyclerView, animationView, totalResultsTextView);
    }

    @Override
    protected void onPause() {
        super.onPause();
        animationView.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        initializeApplication();
    }

    @OnClick(R.id.filter_sort_button)
    public void submit(View view) {
        final Dialog dialog = new Dialog(this);
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.copyFrom(Objects.requireNonNull(dialog.getWindow()).getAttributes());
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;

        dialog.setContentView(R.layout.custom_dialog);

        Spinner orderBySpinner = dialog.findViewById(R.id.order_by_spinner);
        orderBySpinner.setOnItemSelectedListener(MainActivity.this);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, constants.orderBy());
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        orderBySpinner.setAdapter(dataAdapter);

        Spinner conditionSpinner = dialog.findViewById(R.id.condition_spinner);
        conditionSpinner.setOnItemSelectedListener(MainActivity.this);
        ArrayAdapter<String> conditionDataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, constants.condition());
        conditionDataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        conditionSpinner.setAdapter(conditionDataAdapter);

        Spinner tradeTypeSpinner = dialog.findViewById(R.id.trade_type_spinner);
        tradeTypeSpinner.setOnItemSelectedListener(MainActivity.this);
        ArrayAdapter<String> tradeTypeDataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, constants.tradeType());
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tradeTypeSpinner.setAdapter(tradeTypeDataAdapter);

        SeekBar seekBar = dialog.findViewById(R.id.resultsPerPage_seekbar);
        seekBar.setProgress(sharedPreferencesHelper.getResultsPerPage(this));
        TextView textView = dialog.findViewById(R.id.seekBar_text);
        textView.setText(String.valueOf(sharedPreferencesHelper.getResultsPerPage(this)));

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textView.setText(String.valueOf(seekBar.getProgress()));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        Button button = dialog.findViewById(R.id.apply_button);
        button.setOnClickListener(v -> {
            if (seekBar.getProgress() < 25) sharedPreferencesHelper.saveResultsPerPage(this, 25);
            else sharedPreferencesHelper.saveResultsPerPage(this, seekBar.getProgress());
            if (conditionSpinner.getSelectedItem().toString().equals("DEFAULT")) sharedPreferencesHelper.saveCondition(this,"NEW");
            else sharedPreferencesHelper.saveCondition(this, conditionSpinner.getSelectedItem().toString());
            if (tradeTypeSpinner.getSelectedItem().toString().equals("DEFAULT")) sharedPreferencesHelper.saveTradeType(this,"ENGLISH_AUCTION");
            else sharedPreferencesHelper.saveTradeType(this, tradeTypeSpinner.getSelectedItem().toString());
            if (orderBySpinner.getSelectedItem().toString().equals("DEFAULT")) sharedPreferencesHelper.saveOrderBy(this, "");
            else sharedPreferencesHelper.saveOrderBy(this, orderBySpinner.getSelectedItem().toString());
            initializeApplication();
            dialog.dismiss();
        });
        dialog.show();
        dialog.getWindow().setAttributes(layoutParams);
    }


    /**
     * Navigate to the AugmentedReality
     */
    @OnClick(R.id.ar_button)
    public void moveToAugmentedReality() {
        startActivity(new Intent(this, AugmentedRealityActivity.class));
    }


    /**
     *  Initializes the application. Checks if whether if the device has an active internet connection.
     *  If an internet connection is detected then it initializes the remote data repository.
     *  If an internet connection is absent then it initializes the local data repository
     */
    private void initializeApplication(){
        String includedKeywords = String.valueOf(searchBox.getText());
        if (networkHelper.isThereInternetConnection()) buildRecyclerViewFromRemoteData(recyclerView, animationView, totalResultsTextView,
                sharedPreferencesHelper.getResultsPerPage(this),
                includedKeywords,
                sharedPreferencesHelper.getCondition(this),
                sharedPreferencesHelper.getTradeType(this),
                sharedPreferencesHelper.getOrderBy(this)
        );
        else{
            buildRecyclerViewFromLocalData(recyclerView, animationView);
        }
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
