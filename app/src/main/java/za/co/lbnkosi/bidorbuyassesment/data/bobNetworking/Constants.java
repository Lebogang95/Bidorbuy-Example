package za.co.lbnkosi.bidorbuyassesment.data.bobNetworking;

import java.util.ArrayList;
import java.util.List;

public class Constants {

    public static final String HEADER_AUTH_ID = "X-BOB-AUTHID";

    public static final String HEADER_PLATFORM = "X-BOB-PLATFORM";

    public static final String HEADER_CID = "X-BOB-CID";

    public static final String AUTH_ID = "kfpP9jzHLmoTqRBtzGvxkYYF2GzfWfWhtgHGZVpB";

    public static final int PLATFORM = 4;

    public static final String CID = "987654321";

    public static final String BASE_URL = "https://demo.bidorbuy.co.za/services/v3/";

    public static final String SHARED_PREF_NAME = "DOC";

    public static final String REMOTE_DATA_OBJECT_MODEL_STRING = "REMOTE_DATA_MODEL";

    public static final String RESULTS_PER_PAGE = "resultsPerPage";

    public static final String INCLUDED_KEYWORDS = "IncludedKeywords";

    public static final String CONDITION = "Condition";

    public static final String TRADE_TYPE = "TradeType";

    public static final String ORDER_BY = "OrderBy";

    public static final String GET = "tradesearch";

    List<String> orderBy() {
        List<String> orderBy = new ArrayList<>();
        orderBy.add("DEFAULT");
        orderBy.add("Price");
        orderBy.add("Ending");
        orderBy.add("Opening");
        orderBy.add("BidCount");
        orderBy.add("Title");
        return orderBy;
    }

    List<String> condition() {
        List<String> condition = new ArrayList<>();
        condition.add("DEFAULT");
        condition.add("NEW");
        condition.add("SECOND_HAND");
        condition.add("REFURBISHED");
        return condition;
    }

    List<String> tradeType() {
        List<String> tradeType = new ArrayList<>();
        tradeType.add("DEFAULT");
        tradeType.add("ENGLISH_AUCTION");
        tradeType.add("FIXED_PRICE");
        return tradeType;
    }

}
