package za.co.lbnkosi.bidorbuyassesment.application.presentation.utils.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.gson.Gson;
import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.Objects;

import androidx.recyclerview.widget.RecyclerView;
import za.co.lbnkosi.bidorbuyassesment.R;
import za.co.lbnkosi.bidorbuyassesment.domain.model.TradeModel;


/**
 * Created by Lebogang Nkosi on 26/04/2019.
 */

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private ArrayList<TradeModel> dataSet;
    private Context context;

    public Adapter(Context context) {
        this.context = context;
        dataSet = new ArrayList<>();
    }

    @Override @NotNull
    public ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NotNull ViewHolder holder, int position) {
        TradeModel tradeModel = dataSet.get(position);
        holder.aliasView.setText(tradeModel.getUserAlias());
        holder.titleView.setText(tradeModel.getTitle());
        holder.locationView.setText(tradeModel.getLocation());
        holder.breadCrumbView.setText(tradeModel.getCategoryBreadCrumb());
        holder.priceTextView.setText(checkPrice(tradeModel.getAmount()));
        holder.buyTextView.setTextColor(determineStatus(tradeModel.getStatus()));
        Glide.with(context)
                .load(imageUrl(tradeModel))
                .centerCrop()
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public void addList(ArrayList<TradeModel> tradeModel) {
        if (tradeModel != null) {
            dataSet.addAll(tradeModel);
            notifyDataSetChanged();
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView aliasView;
        private TextView titleView;
        private TextView locationView;
        private TextView breadCrumbView;
        private TextView priceTextView;
        private TextView buyTextView;

        private ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.listImageView);
            aliasView = itemView.findViewById(R.id.text_alias);
            titleView = itemView.findViewById(R.id.text_title);
            locationView = itemView.findViewById(R.id.text_location);
            breadCrumbView = itemView.findViewById(R.id.text_breadCrumb);
            priceTextView = itemView.findViewById(R.id.text_price);
            buyTextView = itemView.findViewById(R.id.text_buy);
        }
    }

    private int determineStatus(String status) {
        int color;
        if (status.equals("OPEN")) color = Color.parseColor("#4caf50");
        else color = Color.parseColor("#ac0800");
        return color;
    }

    private String imageUrl(TradeModel tradeModel) {
        String imageUrl = "";
        JSONArray arr = null;
        try {
            arr = new JSONArray(new Gson().toJson(tradeModel.getImages()));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONObject jObj = null;
        try {
            jObj = Objects.requireNonNull(arr).getJSONObject(0);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            imageUrl = Objects.requireNonNull(jObj).getString("image");

        } catch (JSONException e) {
            e.printStackTrace();
        }

        if (imageUrl == null) imageUrl = "https://img.bidorbuy.co.za/image/upload/user_images/site_images/171025130638_bidorbuy-logo.png";

        return imageUrl;
    }

    private String checkPrice(String price){
        String newPrice;
        if (price.contains(".")) newPrice = "R"+price;
        else newPrice = "R"+price+".00";
        return newPrice;
    }

}
