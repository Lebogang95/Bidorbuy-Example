package za.co.lbnkosi.bidorbuyassesment.data.local.api;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.gson.Gson;
import za.co.lbnkosi.bidorbuyassesment.application.presentation.views.home.Constants;
import za.co.lbnkosi.bidorbuyassesment.domain.model.ResponseModel;

/**
 * Created by Lebogang Nkosi on 28/04/2019.
 */

public class StoreData {

    public static void saveResponse(Context mContext, ResponseModel responseModel){
        synchronized (mContext.getApplicationContext()){
            SharedPreferences mPrefs = mContext.getSharedPreferences(Constants.SHARED_PREF_NAME,Context.MODE_PRIVATE);
            SharedPreferences.Editor prefsEditor = mPrefs.edit();
            Gson gson = new Gson();
            String json = gson.toJson(responseModel);
            prefsEditor.putString(Constants.REMOTE_DATA_OBJECT_MODEL_STRING, json);
            prefsEditor.apply();
        }
    }

}
