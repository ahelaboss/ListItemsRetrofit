package com.yourgains.listitemsretrofit.presentation.ui.activity;

import android.os.Bundle;

import com.yourgains.listitemsretrofit.R;
import com.yourgains.listitemsretrofit.presentation.presenter.MainPresenter;
import com.yourgains.listitemsretrofit.presentation.ui.fragments.HomeFragment;
import com.yourgains.listitemsretrofit.presentation.view.MainView;

public class MainActivity extends BaseActivity<MainPresenter> implements MainView {

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            onAddOrReplaceFragment(HomeFragment.newInstance(), HomeFragment.TAG,  false);
        }
    }

    @Override protected int getContentView() {
        return R.layout.activity_main;
    }

    @Override protected int getFragmentLayout() {
        return R.id.a_main_fl;
    }

    @Override protected MainPresenter createPresenter() {
        return new MainPresenter(this);
    }

    @Override public void onBackPressed() {
        super.onBackPressed();
    }
}
