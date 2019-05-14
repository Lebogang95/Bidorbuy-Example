package za.co.lbnkosi.bidorbuyassesment.application.dependencyinjection.application;

import dagger.Component;
import za.co.lbnkosi.bidorbuyassesment.application.presentation.base.BaseActivity;
import za.co.lbnkosi.bidorbuyassesment.application.presentation.views.home.MainActivity;
import za.co.lbnkosi.bidorbuyassesment.application.presentation.views.splashscreen.SplashScreenActivity;

/**
 * Created by Lebogang Nkosi on 28/04/2019.
 */

@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {

    void inject(SplashScreenActivity splashScreenActivity);

    void inject(BaseActivity baseActivity);

    void inject(MainActivity mainActivity);

}
