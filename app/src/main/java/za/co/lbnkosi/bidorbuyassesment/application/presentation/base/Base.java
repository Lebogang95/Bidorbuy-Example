package za.co.lbnkosi.bidorbuyassesment.application.presentation.base;

import android.widget.TextView;
import com.airbnb.lottie.LottieAnimationView;
import androidx.recyclerview.widget.RecyclerView;

public interface Base {

    void buildRecyclerViewFromRemoteData(RecyclerView view, LottieAnimationView animationView, TextView totalResultsTextView, int resultsPerPage, String includedKeywords, String condition, String tradeType, String orderBy);

}
