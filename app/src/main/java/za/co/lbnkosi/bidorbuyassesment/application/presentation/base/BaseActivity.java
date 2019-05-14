package za.co.lbnkosi.bidorbuyassesment.application.presentation.base;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.airbnb.lottie.LottieAnimationView;

import java.util.ArrayList;
import java.util.Objects;
import javax.inject.Inject;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import za.co.lbnkosi.bidorbuyassesment.application.dependencyinjection.application.AndroidApplication;
import za.co.lbnkosi.bidorbuyassesment.application.presentation.utils.adapters.RecyclerItemClickListener;
import za.co.lbnkosi.bidorbuyassesment.application.presentation.utils.adapters.Adapter;
import za.co.lbnkosi.bidorbuyassesment.application.presentation.utils.network.NetworkHelper;
import za.co.lbnkosi.bidorbuyassesment.application.presentation.utils.sharedPrefs.SharedPreferencesHelper;
import za.co.lbnkosi.bidorbuyassesment.application.presentation.views.home.Constants;
import za.co.lbnkosi.bidorbuyassesment.data.local.api.RetrieveData;
import za.co.lbnkosi.bidorbuyassesment.application.presentation.ViewPresenter;
import za.co.lbnkosi.bidorbuyassesment.data.remote.implementation.Implementation;
import za.co.lbnkosi.bidorbuyassesment.domain.model.TradeModel;

/**
 * Created by Lebogang Nkosi on 26/04/2019.
 */

public abstract class BaseActivity extends AppCompatActivity implements Base {

    @Inject Implementation implementation;
    @Inject SharedPreferencesHelper sharedPreferencesHelper;
    @Inject NetworkHelper networkHelper;

    Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((AndroidApplication)getApplicationContext()).applicationComponent().inject(this);
    }


    /**
     * If there is an internet connection this method calls which retrieves data from the Bid or buy API and populates the RecyclerView
     *
     * @param view The ReyclcerView which is being populated with data
     * @param animationView The animation view which gets displayed whilst the app is loading data
     * @param totalResultsTextView Total number of results returned from the API
     * @param resultsPerPage The results per page returned from the API
     * @param includedKeywords String of keywords to search on, can be empty
     * @param condition A string of either `NEW`, `SECOND_HAND` or `REFURBISHED`
     * @param tradeType A string of either `ENGLISH_AUCTION`, `FIXED_PRICE` or `CLASSIFIED_CONTACT` corresponding to items being Auctions, Buy Nows or Classifieds
     * @param orderBy A string for how results will be ordered. Left out will give default ordering, otherwise the values `Price`, `Ending`, `Opening`, `BidCount` and `Title` are accepted.
     */
    public void buildRecyclerViewFromRemoteData(RecyclerView view, LottieAnimationView animationView, TextView totalResultsTextView, int resultsPerPage,String includedKeywords, String condition, String tradeType, String orderBy) {
        LinearLayoutManager mLayoutManager1 = new LinearLayoutManager(getApplicationContext());
        adapter = new Adapter(getApplicationContext());
        view.setAdapter(adapter);
        view.setHasFixedSize(true);
        view.setLayoutManager(mLayoutManager1);
        implementation.getList(new ViewPresenter.MainView() {
            @Override
            public void mainValidateError() {
                showDialog("No internet connection","Please check your internet connection");
            }

            @Override
            public void showProgressBar() {
                animationView.setVisibility(View.VISIBLE);
            }

            @Override
            public void hideProgressBar() {
                animationView.setVisibility(View.GONE);
            }

            @SuppressLint("SetTextI18n")
            @Override
            public void onSuccess(String totalResults, ArrayList<TradeModel> arrayList) {
                animationView.setVisibility(View.GONE);
                adapter.addList(arrayList);
                totalResultsTextView.setText("Total Results:" +totalResults);
            }
            @Override
            public void onFailed(String error) {
                Toast.makeText(getApplicationContext(), error,
                        Toast.LENGTH_LONG).show();
            }

            @Override
            public boolean checkInternet() {
                return networkHelper.isThereInternetConnection();
            }
        }, getApplicationContext(),Constants.AUTH_ID, Constants.PLATFORM, Constants.CID, resultsPerPage, includedKeywords,condition, tradeType, orderBy);
        recyclerViewClick(view);
    }


    /**
     * If there isn't an internet connection the app checks first if there is some data from a previous session that can be used to display offline items.
     * Granted there is some data, we load that json and if the data isn't available we display a message to the user telling them to run the app with internet
     * for the first time.
     *
     * @param view The ReyclcerView which is being populated with data
     * @param animationView The animation view which gets displayed whilst the app is loading data
     */
    public void buildRecyclerViewFromLocalData(RecyclerView view, LottieAnimationView animationView) {
        LinearLayoutManager mLayoutManager1 = new LinearLayoutManager(getApplicationContext());
        adapter = new Adapter(getApplicationContext());
        view.setAdapter(adapter);
        view.setHasFixedSize(true);
        view.setLayoutManager(mLayoutManager1);
        if (RetrieveData.getResponse(getApplicationContext()) != null){
            animationView.setVisibility(View.GONE);
            adapter.addList(Objects.requireNonNull(RetrieveData.getResponse(getApplicationContext())).getTrade());
            //recyclerViewClick(view, _arrayList);
        }
        else showDialog("Offline data not available","Run the app with internet for the first time");
    }


    /**
     * When the user clicks the RecyclerView we do something. I wanted to take the user to another screen but I didn't want to write too much, extra code
     *
     * @param view The ReyclcerView which is being populated with data
     */
    private void recyclerViewClick(RecyclerView view) {
        view.addOnItemTouchListener(new RecyclerItemClickListener(getApplicationContext(), view, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(getApplicationContext(), String.valueOf(position),
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View view, int position) {
                Toast.makeText(getApplicationContext(), String.valueOf(position),
                        Toast.LENGTH_SHORT).show();
            }
        }));
    }


    /**
     * Called when the user clicks on the search bar (Edit text). Listens for changes to the edit text and updates the recyclerview with the response
     *
     * @param searchBox This is the edittext that the user uses to enter the required keywords
     * @param recyclerView The ReyclcerView which is being populated with data
     * @param animationView The animation view which gets displayed whilst the app is loading data
     * @param totalResultsTextView Total number of results returned from the API
     */
    public void startEditTextListener(EditText searchBox, RecyclerView recyclerView, LottieAnimationView animationView, TextView totalResultsTextView) {
        searchBox.setOnClickListener(v -> searchBox.getText().clear());
        searchBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                if (networkHelper.isThereInternetConnection()) buildRecyclerViewFromRemoteData(recyclerView, animationView, totalResultsTextView,
                        sharedPreferencesHelper.getResultsPerPage(getApplicationContext()),
                        String.valueOf(charSequence),
                        sharedPreferencesHelper.getCondition(getApplicationContext()),
                        sharedPreferencesHelper.getTradeType(getApplicationContext()),
                        sharedPreferencesHelper.getOrderBy(getApplicationContext())
                );
                else showDialog("Network error","Please connect to the internet to use the search function");
            }
        });
    }


    /**
     * Dialog to show
     *
     * @param title Title for the dialog
     * @param message Message for dialog
     */
    private void showDialog(String title, String message) {
        new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("Continue", (dialog, which) -> dialog.dismiss())
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }
}
