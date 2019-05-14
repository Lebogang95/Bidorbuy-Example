package za.co.lbnkosi.bidorbuyassesment.data.remote.network;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;
import za.co.lbnkosi.bidorbuyassesment.application.presentation.views.home.Constants;
import za.co.lbnkosi.bidorbuyassesment.domain.model.ResponseModel;

/**
 * Created by Lebogang Nkosi on 26/04/2019.
 */

public interface Api {

    @GET(Constants.GET)
    Observable<Response<ResponseModel>>
    getList(@Header(Constants.HEADER_AUTH_ID) String AUTH_ID,
            @Header(Constants.HEADER_PLATFORM) int PLATFORM,
            @Header(Constants.HEADER_CID) String CID,
            @Query(Constants.RESULTS_PER_PAGE) int resultsPerPage,
            @Query(Constants.INCLUDED_KEYWORDS) String includedKeywords,
            @Query(Constants.CONDITION) String condition,
            @Query(Constants.TRADE_TYPE) String tradeType,
            @Query(Constants.ORDER_BY) String orderBy
    );

}
