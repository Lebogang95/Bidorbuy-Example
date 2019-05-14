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

    @GET("tradesearch")
    Observable<Response<ResponseModel>>
    getList(@Header(Constants.HEADER_AUTH_ID) String AUTH_ID,
            @Header(Constants.HEADER_PLATFORM) int PLATFORM,
            @Header(Constants.HEADER_CID) String CID,
            @Query("resultsPerPage") int resultsPerPage,
            @Query("IncludedKeywords") String includedKeywords,
            @Query("Condition") String condition,
            @Query("TradeType") String tradeType,
            @Query("OrderBy") String orderBy
    );

}
