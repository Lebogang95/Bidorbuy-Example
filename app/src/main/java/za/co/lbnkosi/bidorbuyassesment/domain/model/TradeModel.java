package za.co.lbnkosi.bidorbuyassesment.domain.model;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

/**
 * Created by Lebogang Nkosi on 26/04/2019.
 */

public class TradeModel {

    @SerializedName("images")
    private ArrayList<ImageModel> images;

    @SerializedName("amount")
    private String amount;

    @SerializedName("title")
    private String title;

    @SerializedName("type")
    private String type;

    @SerializedName("userId")
    private String userId;

    @SerializedName("hotSelling")
    private boolean hotSelling;

    @SerializedName("categoryBreadCrumb")
    private String categoryBreadCrumb;

    @SerializedName("userAlias")
    private String userAlias;

    @SerializedName("closeTime")
    private String closeTime;

    @SerializedName("homeCategoryId")
    private String homeCategoryId;

    @SerializedName("location")
    private String location;

    @SerializedName("openTime")
    private String openTime;

    @SerializedName("tradeId")
    private String tradeId;

    @SerializedName("status")
    private String status;


    public ArrayList<ImageModel> getImages() {
        return images;
    }

    public void setImages(ArrayList<ImageModel> images) {
        this.images = images;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getAmount() {
        return amount;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setHotSelling(boolean hotSelling) {
        this.hotSelling = hotSelling;
    }

    public boolean getHotSelling(boolean hotSelling) {
        return hotSelling;
    }

    public void setCategoryBreadCrumb(String categoryBreadCrumb) {
        this.categoryBreadCrumb = categoryBreadCrumb;
    }

    public String getCategoryBreadCrumb() {
        return categoryBreadCrumb;
    }

    public void setUserAlias(String userAlias) {
        this.userAlias = userAlias;
    }

    public String getUserAlias() {
        return userAlias;
    }

    public void setCloseTime(String closeTime) {
        this.closeTime = closeTime;
    }

    public String getCloseTime() {
        return closeTime;
    }

    public void setHomeCategoryId(String homeCategoryId) {
        this.homeCategoryId = homeCategoryId;
    }

    public String getHomeCategoryId() {
        return homeCategoryId;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocation() {
        return location;
    }

    public void setOpenTime(String openTime) {
        this.openTime = openTime;
    }

    public String getOpenTime() {
        return openTime;
    }

    public void setTradeId(String tradeId) {
        this.tradeId = tradeId;
    }

    public String getTradeId() {
        return tradeId;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
