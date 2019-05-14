package za.co.lbnkosi.bidorbuyassesment.domain.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Lebogang Nkosi on 26/04/2019.
 */

public class ImageModel {

    @SerializedName("image")
    private String image;

    @SerializedName("thumbnail")
    private String thumbnail;


    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
}
