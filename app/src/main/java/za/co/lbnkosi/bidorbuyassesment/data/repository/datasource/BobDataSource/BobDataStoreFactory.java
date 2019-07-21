package za.co.lbnkosi.bidorbuyassesment.data.repository.datasource.BobDataSource;

import android.content.Context;

import za.co.lbnkosi.bidorbuyassesment.data.bobNetworking.BobRetrofit;
import za.co.lbnkosi.bidorbuyassesment.data.bobNetworking.BobRetrofitImpl;

public class BobDataStoreFactory {

    private final Context context;

    public BobDataStoreFactory(Context context) {
        this.context = context.getApplicationContext();
    }

    public BobDataStore create() {
        BobDataStore bobDataStore;
        bobDataStore = createRemoteDataStore();
        return bobDataStore;
    }

    public BobDataStore createRemoteDataStore(){
        final BobRetrofit bobRetrofit = new BobRetrofitImpl();
        return new RemoteBobDataStore(bobRetrofit);
    }

}
