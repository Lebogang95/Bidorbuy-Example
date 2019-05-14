package za.co.lbnkosi.bidorbuyassesment.application.dependencyinjection.application;

import android.app.Application;

/**
 * Created by Lebogang Nkosi on 28/04/2019.
 */
public class AndroidApplication extends Application {

    private ApplicationComponent mComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(getApplicationContext()))
                .build();
    }

    public ApplicationComponent applicationComponent() {
        return mComponent;
    }

}

