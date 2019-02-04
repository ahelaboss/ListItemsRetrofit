package com.yourgains.listitemsretrofit;

import android.app.Application;

import com.yourgains.listitemsretrofit.data.DataManager;

/**
 * Created by alexeyshishov
 * on 2/2/19.
 */
public class App extends Application {

    private static DataManager sDataManager;

    @Override public void onCreate() {
        super.onCreate();
        sDataManager = DataManager.getInstance();
    }

    public static DataManager getDataManager() {
        return sDataManager;
    }
}
