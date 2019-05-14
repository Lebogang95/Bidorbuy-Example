package za.co.lbnkosi.bidorbuyassesment.domain.model;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

/**
 * Created by Lebogang Nkosi on 26/04/2019.
 */

public class ResponseModel {

    @SerializedName("totalResults")
    private String totalResults;

    @SerializedName("pageNumber")
    private String pageNumber;

    @SerializedName("resultsPerPage")
    private String resultsPerPage;

    @SerializedName("trade")
    private ArrayList<TradeModel> trade;


    public ResponseModel(ArrayList<TradeModel> trade) {

    }

    public String getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(String totalResults) {
        this.totalResults = totalResults;
    }

    public String getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(String pageNumber) {
        this.pageNumber = pageNumber;
    }

    public String getResultsPerPage() {
        return resultsPerPage;
    }

    public void setResultsPerPage(String resultsPerPage) {
        this.resultsPerPage = resultsPerPage;
    }

    public ArrayList<TradeModel> getTrade() {
        return trade;
    }

    public void setTrade(ArrayList<TradeModel> trade) {
        this.trade = trade;
    }
}
