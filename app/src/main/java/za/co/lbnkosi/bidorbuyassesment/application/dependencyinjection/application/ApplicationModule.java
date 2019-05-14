package za.co.lbnkosi.bidorbuyassesment.application.dependencyinjection.application;

import android.content.Context;
import dagger.Module;
import dagger.Provides;
import za.co.lbnkosi.bidorbuyassesment.application.presentation.utils.adapters.Adapter;
import za.co.lbnkosi.bidorbuyassesment.application.presentation.utils.animation.AnimationUtil;
import za.co.lbnkosi.bidorbuyassesment.application.presentation.utils.network.NetworkHelper;
import za.co.lbnkosi.bidorbuyassesment.application.presentation.utils.sharedPrefs.SharedPreferencesHelper;
import za.co.lbnkosi.bidorbuyassesment.application.presentation.views.home.Constants;
import za.co.lbnkosi.bidorbuyassesment.data.remote.implementation.Implementation;

/**
 * Created by Lebogang Nkosi on 28/04/2019.
 */
@Module
class ApplicationModule {

    private Context context;

    ApplicationModule(Context context) {
        this.context = context;
    }

    @Provides
    Implementation bobApiImplementation() {
        return new Implementation();
    }

    @Provides
    SharedPreferencesHelper sharedPreferencesHelper(){
        return new SharedPreferencesHelper();
    }

    @Provides
    NetworkHelper networkHelper() {
        return new NetworkHelper(context);
    }

    @Provides
    Constants constants() {
        return new Constants();
    }

    @Provides
    Adapter remoteDataAdapter() {
        return new Adapter(context);
    }

    @Provides
    AnimationUtil animationUtil() {
        return new AnimationUtil();
    }

}
