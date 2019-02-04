package com.yourgains.listitemsretrofit.presentation.presenter;

import com.yourgains.listitemsretrofit.App;
import com.yourgains.listitemsretrofit.data.DataManager;
import com.yourgains.listitemsretrofit.presentation.view.BaseView;

/**
 * Created by alexeyshishov
 * on 2/2/19.
 */
public abstract class BasePresenter<T extends BaseView> {

    private T mView;
    private DataManager mDataManager;

    public BasePresenter(T view) {
        mView = view;
        mDataManager = App.getDataManager();
    }

    public void onFirstAttachView() {

    }

    public void onAttachView() {

    }

    public void onResume() {

    }

    public void onPause() {

    }

    public void onDetachView() {

    }

    public void onDestroy() {

    }

    protected T getView() {
        return mView;
    }

    protected DataManager getDataManager() {
        return mDataManager;
    }
}
