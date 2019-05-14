package za.co.lbnkosi.bidorbuyassesment.data.remote.network;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import java.util.concurrent.TimeUnit;
import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import za.co.lbnkosi.bidorbuyassesment.application.presentation.views.home.Constants;
import za.co.lbnkosi.bidorbuyassesment.domain.model.ResponseModel;

/**
 * Created by Lebogang Nkosi on 26/04/2019.
 */

public class ApiClient {

    private static ApiClient INSTANCE;
    private Api api;

    private ApiClient(){
        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .build();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build();

        api = retrofit.create(Api.class);
    }

    public static ApiClient getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ApiClient();
        }
        return INSTANCE;
    }

    public Observable<Response<ResponseModel>> getList(String AUTH_ID, int PLATFORM, String CID, int resultsPerPage, String includedKeywords, String condition, String tradeType, String orderBy){
        return api.getList(AUTH_ID, PLATFORM, CID, resultsPerPage, includedKeywords, condition, tradeType, orderBy);
    }

}
