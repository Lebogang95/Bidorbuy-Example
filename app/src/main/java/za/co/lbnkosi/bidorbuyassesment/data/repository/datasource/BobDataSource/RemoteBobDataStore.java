package za.co.lbnkosi.bidorbuyassesment.data.repository.datasource.BobDataSource;

import io.reactivex.Observable;
import retrofit2.Response;
import za.co.lbnkosi.bidorbuyassesment.data.bobNetworking.BobRetrofit;
import za.co.lbnkosi.bidorbuyassesment.data.entity.BobEntities.ResponseModelData;

public class RemoteBobDataStore implements BobDataStore {

    private final BobRetrofit bobRetrofit;

    public RemoteBobDataStore(BobRetrofit bobRetrofit){
        this.bobRetrofit = bobRetrofit;
    }

    @Override
    public Observable<Response<ResponseModelData>> getList(String AUTH_ID, int PLATFORM, String CID, int resultsPerPage, String includedKeywords, String condition, String tradeType, String orderBy) {
        return this.bobRetrofit.getList(AUTH_ID, PLATFORM, CID, resultsPerPage, includedKeywords, condition, tradeType, orderBy);
    }
}
