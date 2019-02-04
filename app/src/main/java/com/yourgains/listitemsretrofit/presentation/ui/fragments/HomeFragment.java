package com.yourgains.listitemsretrofit.presentation.ui.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.View;

import com.yourgains.listitemsretrofit.R;
import com.yourgains.listitemsretrofit.presentation.presenter.HomePresenter;
import com.yourgains.listitemsretrofit.presentation.view.HomeView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by alexeyshishov
 * on 2/2/19.
 */
public class HomeFragment extends BaseFragment<HomePresenter> implements HomeView,
        TabLayout.OnTabSelectedListener {

    public static final String TAG = "HomeFragment";
    public static final String TAB_POSITION = "TAB_POSITION";

    private static List<Fragment> sFragmentList = new ArrayList<>();

    @BindView(R.id.f_home_tl) TabLayout mTabLayout;

    public static HomeFragment newInstance() {
        Bundle args = new Bundle();
        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        int position = 0;
        if (savedInstanceState != null) {
            position = savedInstanceState.getInt(TAB_POSITION, 0);
        }
        TabLayout.Tab tab = mTabLayout.getTabAt(position);
        if (tab != null) tab.select();
                if (position > -1 && position < sFragmentList.size()) {
            mPresenter.setTab(position);
        }
    }

    @Override public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(TAB_POSITION, mTabLayout.getSelectedTabPosition());
    }

    @Override protected int getContentView() {
        return R.layout.fragment_home;
    }

    @Override protected int getFragmentLayout() {
        return R.id.f_home_fl;
    }

    @Override protected void init() {
        if (sFragmentList.isEmpty()) {
            sFragmentList.add(PageFragment.newInstance(PageFragment.BUNDLE_KEY_TYPE_VALUE_CAT));
            sFragmentList.add(PageFragment.newInstance(PageFragment.BUNDLE_KEY_TYPE_VALUE_DOG));
        }
        mTabLayout.addOnTabSelectedListener(this);
    }

    @Override protected HomePresenter createPresenter() {
        return new HomePresenter(this);
    }

    @Override public void onTabSelected(TabLayout.Tab tab) {
        mPresenter.setTab(tab.getPosition());
    }

    @Override public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override public void onTabReselected(TabLayout.Tab tab) {

    }

    @Override public void setTab(int position) {
        switch (position) {
            case 0:
                onAddOrReplaceFragment(sFragmentList.get(0), PageFragment.TAG_CATS);
                break;
            case 1:
                onAddOrReplaceFragment(sFragmentList.get(1), PageFragment.TAG_DOGS);
                break;
        }
    }
}
