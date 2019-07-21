package za.co.lbnkosi.bidorbuyassesment.data.repository.datasource.BobDataSource;

import io.reactivex.Observable;
import retrofit2.Response;
import za.co.lbnkosi.bidorbuyassesment.data.entity.BobEntities.ResponseModelData;

public interface BobDataStore {

    Observable<Response<ResponseModelData>> getList(String AUTH_ID, int PLATFORM, String CID, int resultsPerPage, String includedKeywords, String condition, String tradeType, String orderBy);

}
