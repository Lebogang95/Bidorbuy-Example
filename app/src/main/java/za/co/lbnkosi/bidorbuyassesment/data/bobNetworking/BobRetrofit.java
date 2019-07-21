package za.co.lbnkosi.bidorbuyassesment.data.bobNetworking;

import io.reactivex.Observable;
import retrofit2.Response;
import za.co.lbnkosi.bidorbuyassesment.data.entity.BobEntities.ResponseModelData;

public interface BobRetrofit {

    Observable<Response<ResponseModelData>> getList(String AUTH_ID, int PLATFORM, String CID, int resultsPerPage, String includedKeywords, String condition, String tradeType, String orderBy);

}
