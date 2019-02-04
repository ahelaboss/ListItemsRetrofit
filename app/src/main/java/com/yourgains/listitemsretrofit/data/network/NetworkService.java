package com.yourgains.listitemsretrofit.data.network;

import com.yourgains.listitemsretrofit.data.network.model.ResponseModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by alexeyshishov
 * on 2/2/19.
 */
public interface NetworkService {

    @GET("xim/api.php")
    Call<ResponseModel> get(@Query("query") String query);
}
