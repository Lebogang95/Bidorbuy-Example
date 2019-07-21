package za.co.lbnkosi.bidorbuyassesment.domain.repository;

import io.reactivex.Observable;
import retrofit2.Response;
import za.co.lbnkosi.bidorbuyassesment.domain.model.ResponseModelDomain;

public interface BobRepository {

    Observable<Response<ResponseModelDomain>> getList(String AUTH_ID, int PLATFORM, String CID, int resultsPerPage, String includedKeywords, String condition, String tradeType, String orderBy);

}
