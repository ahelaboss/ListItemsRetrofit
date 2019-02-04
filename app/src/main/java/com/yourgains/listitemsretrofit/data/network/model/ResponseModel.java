package com.yourgains.listitemsretrofit.data.network.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by alexeyshishov
 * on 2/2/19.
 */
public class ResponseModel {

    @SerializedName("message") private String mMessage;
    @SerializedName("data") private List<DataModel> mListData;

    public String getMessage() {
        return mMessage;
    }

    public List<DataModel> getListData() {
        return mListData;
    }
}
