package za.co.lbnkosi.bidorbuyassesment.data.remote.implementation;

import android.content.Context;
import java.util.ArrayList;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import za.co.lbnkosi.bidorbuyassesment.application.presentation.ViewPresenter;
import za.co.lbnkosi.bidorbuyassesment.data.remote.network.ApiClient;
import za.co.lbnkosi.bidorbuyassesment.domain.model.TradeModel;

/**
 * Created by Lebogang Nkosi on 26/04/2019.
 */
public class Implementation implements ViewPresenter.MainPresenter {

    private ArrayList<TradeModel> _arrayList;
    private String totalResults;
    private Disposable disposable;


    /***
     * Returns an Observable<Response<ResponseModel>>
     * We then get the results of that object model and assign the value to _arrayList
     * We then use the callback to populate the Recyclerview
     */
    @Override
    public void getList(ViewPresenter.MainView mainView, Context context, String AUTH_ID, int PLATFORM, String CID, int resultsPerPage, String includedKeywords, String condition, String tradeType, String orderBy) {
        mainView.showProgressBar();
        if(mainView.checkInternet()) {
            disposable = ApiClient.getInstance().getList(AUTH_ID, PLATFORM, CID, resultsPerPage, includedKeywords, condition, tradeType, orderBy)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(responseModelResponse -> {
                        mainView.hideProgressBar();
                        int responseCode = responseModelResponse.code();
                        switch (responseCode) {
                            case 200:
                            case 201:
                            case 202:
                                assert responseModelResponse.body() != null;
                                _arrayList = responseModelResponse.body().getTrade();
                                totalResults = responseModelResponse.body().getTotalResults();
                                mainView.onSuccess(totalResults,_arrayList);
                                break;
                            case 401:
                                break;
                            case 402:
                                break;
                            case 500:
                                break;
                            case 501:
                                break;
                        }
                    }, error -> {
                        mainView.hideProgressBar();
                        mainView.onFailed(error.toString());
                    });
        } else {
            mainView.hideProgressBar();
            mainView.mainValidateError();
        }
    }

    @Override
    public void onStop() {
        if (disposable != null) {
            disposable.dispose();
        }
    }


}
