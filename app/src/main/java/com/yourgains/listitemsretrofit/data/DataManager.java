package com.yourgains.listitemsretrofit.data;

import com.yourgains.listitemsretrofit.data.model.AnimalModel;
import com.yourgains.listitemsretrofit.data.network.NetworkManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexeyshishov
 * on 2/2/19.
 */
public class DataManager {

    private static DataManager sDataManager;

    private NetworkManager mNetworkManager;

    private List<AnimalModel> mCatList = new ArrayList<>();
    private List<AnimalModel> mDogList = new ArrayList<>();

    public static DataManager getInstance() {
        if (sDataManager == null) {
            sDataManager = new DataManager();
        }
        return sDataManager;
    }

    private DataManager() {
        mNetworkManager = NetworkManager.getInstance();
    }

    public void getCatList(final AnimalListener listener) {
        if (mCatList.isEmpty()) {
            mNetworkManager.getCatList(new AnimalListener() {
                @Override public void onComplete(List<AnimalModel> list) {
                    mCatList = list;
                    listener.onComplete(list);
                }

                @Override public void onError() {
                    listener.onError();
                }
            });
        } else {
            listener.onComplete(mCatList);
        }
    }

    public void getDogList(final AnimalListener listener) {
        if (mDogList.isEmpty()) {
            mNetworkManager.getDogList(new AnimalListener() {
                @Override public void onComplete(List<AnimalModel> list) {
                    mDogList = list;
                    listener.onComplete(list);
                }

                @Override public void onError() {
                    listener.onError();
                }
            });
        } else {
            listener.onComplete(mDogList);
        }
    }
}
