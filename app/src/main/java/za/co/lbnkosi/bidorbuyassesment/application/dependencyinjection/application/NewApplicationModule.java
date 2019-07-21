package za.co.lbnkosi.bidorbuyassesment.application.dependencyinjection.application;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import za.co.lbnkosi.bidorbuyassesment.data.executor.JobExecutor;
import za.co.lbnkosi.bidorbuyassesment.data.thread.UIThread;

@Module
public class NewApplicationModule {

    private final Application mApplication;

    public NewApplicationModule(Application application) {
        mApplication = application;
    }

    @Singleton
    @Provides
    JobExecutor jobExecutor(){
        return new JobExecutor();
    }

    @Singleton
    @Provides
    UIThread uiThread() {
        return new UIThread();
    }

}
