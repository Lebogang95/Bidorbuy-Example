package za.co.lbnkosi.bidorbuyassesment.application.presentation.utils.sharedPrefs;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Lebogang Nkosi on 27/04/2019.
 */
public class SharedPreferencesHelper {

    public void initialisePrefs(Context context) {
        saveResultsPerPage(context, 25);
        saveCondition(context, "NEW");
        saveTradeType(context, "ENGLISH_AUCTION");
        saveOrderBy(context,"");
    }

    public void saveResultsPerPage(Context context, int resultsPerPage) {
        synchronized (context.getApplicationContext()) {
            SharedPreferences pref = context.getSharedPreferences("resultsPerPage", 0);
            SharedPreferences.Editor editor = pref.edit();
            editor.putInt("KEY_RESULTS_PER_PAGE", resultsPerPage);
            editor.apply();
        }
    }

    public int getResultsPerPage(Context context) {
        synchronized (context.getApplicationContext()){
            SharedPreferences pref = context.getSharedPreferences("resultsPerPage", 0);
            return pref.getInt("KEY_RESULTS_PER_PAGE", -1);
        }
    }

    public void saveCondition (Context context, String condition) {
        synchronized (context.getApplicationContext()){
            SharedPreferences pref = context.getSharedPreferences("condition", 0);
            SharedPreferences.Editor editor = pref.edit();
            editor.putString("KEY_CONDITION", condition);
            editor.apply();
        }
    }

    public String getCondition(Context context) {
        synchronized (context.getApplicationContext()){
            SharedPreferences pref = context.getSharedPreferences("condition", 0);
            return pref.getString("KEY_CONDITION", null);
        }
    }

    public void saveTradeType(Context context, String tradeType) {
        synchronized (context.getApplicationContext()){
            SharedPreferences pref = context.getSharedPreferences("tradeType", 0);
            SharedPreferences.Editor editor = pref.edit();
            editor.putString("KEY_TRADE_TYPE", tradeType);
            editor.apply();
        }
    }

    public String getTradeType(Context context) {
        synchronized (context.getApplicationContext()){
            SharedPreferences pref = context.getSharedPreferences("tradeType", 0);
            return pref.getString("KEY_TRADE_TYPE", null);
        }
    }

    public void saveOrderBy(Context context, String orderBy) {
        synchronized (context.getApplicationContext()){
            SharedPreferences pref = context.getSharedPreferences("orderBy", 0);
            SharedPreferences.Editor editor = pref.edit();
            editor.putString("KEY_ORDER_BY", orderBy);
            editor.apply();
        }
    }

    public String getOrderBy(Context context) {
        synchronized (context.getApplicationContext()){
            SharedPreferences pref = context.getSharedPreferences("orderBy", 0);
            return pref.getString("KEY_ORDER_BY", null);
        }
    }

}
