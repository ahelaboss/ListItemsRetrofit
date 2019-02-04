package com.yourgains.listitemsretrofit.data.network;

import android.support.annotation.NonNull;

import com.yourgains.listitemsretrofit.data.AnimalListener;
import com.yourgains.listitemsretrofit.data.model.AnimalModel;
import com.yourgains.listitemsretrofit.data.network.model.DataModel;
import com.yourgains.listitemsretrofit.data.network.model.ResponseModel;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;

/**
 * Created by alexeyshishov
 * on 2/2/19.
 */
public class NetworkManager {

    private static final String LINK = "http://kot3.com/";
    private static final String API_CAT = "cat";
    private static final String API_DOG = "dog";

    private static NetworkManager sNetworkManager;

    private Retrofit mRetrofit;

    public static NetworkManager getInstance() {
        if (sNetworkManager == null) {
            sNetworkManager = new NetworkManager();
        }
        return sNetworkManager;
    }

    private NetworkManager() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(LINK)
                .addConverterFactory(GsonConverterFactory.create())
                .client(new OkHttpClient())
                .build();
    }

    @NonNull private NetworkService getNetworkService() {
        return mRetrofit.create(NetworkService.class);
    }

    private void callList(Call<ResponseModel> call, AnimalListener listener) {
        call.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(@NotNull Call<ResponseModel> call, @NotNull Response<ResponseModel> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<DataModel> listData = response.body().getListData();
                    List<AnimalModel> list = new ArrayList<>();
                    String message = response.body().getMessage();
                    for (DataModel model : listData) {
                        list.add(new AnimalModel(model.getUrl(), model.getTitle(), message + " " + model.getTitle()));
                    }
                    listener.onComplete(list);
                } else {
                    Timber.e("Bad request");
                    listener.onError();
                }
            }

            @Override
            public void onFailure(@NotNull Call<ResponseModel> call, @NotNull Throwable t) {
                Timber.e(t);
                listener.onError();
            }
        });
    }

    public void getCatList(AnimalListener listener) {
        NetworkService service = getNetworkService();
        Call<ResponseModel> call = service.get(API_CAT);
        callList(call, listener);
    }

    public void getDogList(final AnimalListener listener) {
        NetworkService service = getNetworkService();
        Call<ResponseModel> call = service.get(API_DOG);
        callList(call, listener);
    }
}
