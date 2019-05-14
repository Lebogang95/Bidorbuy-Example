package za.co.lbnkosi.bidorbuyassesment.application.presentation.views.splashscreen;

import android.content.Intent;
import android.os.Bundle;
import com.airbnb.lottie.LottieAnimationView;
import java.util.Timer;
import java.util.TimerTask;
import javax.inject.Inject;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import za.co.lbnkosi.bidorbuyassesment.R;
import za.co.lbnkosi.bidorbuyassesment.application.dependencyinjection.application.AndroidApplication;
import za.co.lbnkosi.bidorbuyassesment.application.presentation.utils.animation.AnimationUtil;
import za.co.lbnkosi.bidorbuyassesment.application.presentation.utils.sharedPrefs.SharedPreferencesHelper;
import za.co.lbnkosi.bidorbuyassesment.application.presentation.views.home.MainActivity;

public class SplashScreenActivity extends AppCompatActivity {

    @Inject AnimationUtil animationUtil;
    @Inject SharedPreferencesHelper sharedPreferencesHelper;

    @BindView(R.id.loading_animation) LottieAnimationView animationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);
        ButterKnife.bind(this);
        ((AndroidApplication)getApplicationContext()).applicationComponent().inject(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        animationUtil.playAnimation(animationView,"loading_animation.json");
        sharedPreferencesHelper.initialisePrefs(getApplicationContext());
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }

    @Override
    protected void onPause() {
        super.onPause();
        animationUtil.pauseAnimation(animationView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        animationUtil.playAnimation(animationView, "loading_animation.json");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
