package com.yourgains.listitemsretrofit.data.network.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by alexeyshishov
 * on 2/2/19.
 */
public class DataModel {

    @SerializedName("url") private String mUrl;
    @SerializedName("title") private String mTitle;

    public String getUrl() {
        return mUrl;
    }

    public String getTitle() {
        return mTitle;
    }
}
