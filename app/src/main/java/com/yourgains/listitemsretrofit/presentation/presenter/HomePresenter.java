package com.yourgains.listitemsretrofit.presentation.presenter;

import com.yourgains.listitemsretrofit.presentation.view.HomeView;

/**
 * Created by alexeyshishov
 * on 2/2/19.
 */
public class HomePresenter extends BasePresenter<HomeView> {

    private int mTabPosition = -1;

    public HomePresenter(HomeView view) {
        super(view);
    }

    public void setTab(int position) {
        getView().setTab(position);
    }
}
