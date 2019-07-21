package za.co.lbnkosi.bidorbuyassesment.data.local.api;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.gson.Gson;
import java.util.Objects;
import za.co.lbnkosi.bidorbuyassesment.application.presentation.views.home.Constants;
import za.co.lbnkosi.bidorbuyassesment.domain.model.ResponseModelDomain;

/**
 * Created by Lebogang Nkosi on 28/04/2019.
 */

public class RetrieveData {

    public static ResponseModelDomain getResponse(Context mContext){
        synchronized (mContext.getApplicationContext()) {
            SharedPreferences mPrefs = mContext.getSharedPreferences(Constants.SHARED_PREF_NAME,Context.MODE_PRIVATE);
            Gson gson = new Gson();
            String json = mPrefs.getString(Constants.REMOTE_DATA_OBJECT_MODEL_STRING, "");
            if(Objects.requireNonNull(json).equalsIgnoreCase("")){
                return null;
            }
            return gson.fromJson(json, ResponseModelDomain.class);
        }
    }

}
