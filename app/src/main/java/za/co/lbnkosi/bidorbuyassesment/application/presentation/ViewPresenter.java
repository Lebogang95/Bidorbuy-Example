package za.co.lbnkosi.bidorbuyassesment.application.presentation;

import android.content.Context;
import java.util.ArrayList;
import za.co.lbnkosi.bidorbuyassesment.domain.model.TradeModel;

/**
 * Created by Lebogang Nkosi on 26/04/2019.
 */
public interface ViewPresenter {

    interface MainView {
        void mainValidateError();
        void showProgressBar();
        void hideProgressBar();
        void onSuccess(String totalResults, ArrayList<TradeModel> arrayList);
        void onFailed(String error);
        boolean checkInternet();
    }

    interface MainPresenter {
        void getList(ViewPresenter.MainView callback, Context context, String AUTH_ID, int PLATFORM, String CID, int resultsPerPage, String includedKeywords, String condition, String tradeType, String orderBy);
        void onStop();
    }

}
