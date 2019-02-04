package com.yourgains.listitemsretrofit.presentation.ui.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.yourgains.listitemsretrofit.R;
import com.yourgains.listitemsretrofit.data.model.AnimalModel;
import com.yourgains.listitemsretrofit.presentation.adapters.AnimalAdapter;
import com.yourgains.listitemsretrofit.presentation.adapters.listeners.OnItemClickListener;
import com.yourgains.listitemsretrofit.presentation.presenter.PagePresenter;
import com.yourgains.listitemsretrofit.presentation.ui.activity.BaseActivity;
import com.yourgains.listitemsretrofit.presentation.view.PageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by alexeyshishov
 * on 2/2/19.
 */
public class PageFragment extends BaseFragment<PagePresenter> implements PageView,
        OnItemClickListener<AnimalModel> {

    public static final String TAG_CATS = "PageFragmentCats";
    public static final String TAG_DOGS = "HomeFragmentDogs";

    private static final String BUNDLE_KEY_TYPE = "TYPE";
    public static final String BUNDLE_KEY_TYPE_VALUE_CAT = "cat";
    public static final String BUNDLE_KEY_TYPE_VALUE_DOG = "dog";
    public static final String STATE = "STATE";
    public static final String COMPLETE = "COMPLETE";

    @BindView(R.id.f_page_rv) RecyclerView mRecyclerView;

    private AnimalAdapter mAdapter;
    private Parcelable mState;
    private boolean isComplete = false;

    public static PageFragment newInstance(String type) {
        Bundle args = new Bundle();
        args.putString(BUNDLE_KEY_TYPE, type);
        PageFragment fragment = new PageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle = getArguments();
        if (savedInstanceState != null) {
            mState = savedInstanceState.getParcelable(STATE);
            isComplete = savedInstanceState.getBoolean(COMPLETE, false);
        }
        if (bundle != null && !isComplete) {
            mPresenter.getData(bundle.getString(BUNDLE_KEY_TYPE, ""));
        }
    }

    @Override public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(STATE, mState);
        outState.putBoolean(COMPLETE, isComplete);
    }

    @Override public void onResume() {
        super.onResume();
        if (mRecyclerView.getLayoutManager() != null)
            mRecyclerView.getLayoutManager().onRestoreInstanceState(mState);
    }

    @Override public void onPause() {
        super.onPause();
        if (mRecyclerView.getLayoutManager() != null)
            mState = mRecyclerView.getLayoutManager().onSaveInstanceState();
    }

    @Override protected int getContentView() {
        return R.layout.fragment_page;
    }

    @Override protected void init() {
        if (mAdapter == null) {
            mAdapter = new AnimalAdapter(new ArrayList<>(), this);
        }
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override protected PagePresenter createPresenter() {
        return new PagePresenter(this);
    }

    @Override public void setItems(List<AnimalModel> list) {
        mAdapter.setItems(list);
    }

    @Override public void showError() {
        if (getContext() == null) return;
        Toast.makeText(getContext(), R.string.error, Toast.LENGTH_SHORT).show();
    }

    @Override public void onItemClick(AnimalModel item, int position) {
        Activity activity = getActivity();
        if (activity instanceof BaseActivity) {
            ((BaseActivity) activity).onAddOrReplaceFragment(FullInfoFragment.newInstance(item), FullInfoFragment.TAG, true);
        }
    }
}
