package za.co.lbnkosi.bidorbuyassesment.data.bobNetworking;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitComponent {

    private static RetrofitComponent INSTANCE;

    BobService bobService;

    private RetrofitComponent(){

        URL url = null;
        try {
            url = new URL(Constants.BASE_URL);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        String serverHostname = Objects.requireNonNull(url).getHost();

        int seconds = 60;
        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                .connectTimeout(seconds, TimeUnit.SECONDS)
                .readTimeout(seconds, TimeUnit.SECONDS)
                .writeTimeout(seconds, TimeUnit.SECONDS)
                .build();

        Retrofit getRetrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        bobService = getRetrofit.create(BobService.class);
    }

    public static RetrofitComponent getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new RetrofitComponent();
        }
        return INSTANCE;
    }

}
