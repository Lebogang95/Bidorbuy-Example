package za.co.lbnkosi.bidorbuyassesment.application.dependencyinjection.presentation;

import android.app.Activity;
import android.content.Context;

import dagger.Module;
import dagger.Provides;
import za.co.lbnkosi.bidorbuyassesment.data.entity.mapper.BobMapper;
import za.co.lbnkosi.bidorbuyassesment.data.executor.JobExecutor;
import za.co.lbnkosi.bidorbuyassesment.data.repository.BobDataRepository;
import za.co.lbnkosi.bidorbuyassesment.data.repository.datasource.BobDataSource.BobDataStoreFactory;
import za.co.lbnkosi.bidorbuyassesment.data.thread.UIThread;
import za.co.lbnkosi.bidorbuyassesment.domain.interactor.BobInteractors.GetList;
import za.co.lbnkosi.bidorbuyassesment.domain.repository.BobRepository;

@Module
public class BobPresentationModule {

    private final Activity activity;

    public BobPresentationModule(Activity activity) {
        this.activity = activity;
    }

    @Provides
    Activity getActivity() {
        return activity;
    }

    @Provides
    Context context(Activity activity) {
        return activity;
    }

    @Provides
    BobMapper bobMapper(){
        return new BobMapper();
    }

    @Provides
    BobDataStoreFactory bobDataStoreFactory(Context context){
        return new BobDataStoreFactory(context);
    }

    @Provides
    BobRepository bobRepository(BobDataStoreFactory bobDataStoreFactory, BobMapper bobMapper) {
        return new BobDataRepository(bobDataStoreFactory, bobMapper);
    }

    @Provides
    GetList getList(BobRepository bobRepository, JobExecutor jobExecutor, UIThread uiThread) {
        return new GetList(bobRepository, jobExecutor, uiThread);
    }

}
