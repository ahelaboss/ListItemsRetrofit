package com.yourgains.listitemsretrofit.presentation.ui.fragments;

import android.os.Bundle;
import android.support.annotation.AnimRes;
import android.support.annotation.AnimatorRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yourgains.listitemsretrofit.presentation.presenter.BasePresenter;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by alexeyshishov
 * on 2/2/19.
 */
public abstract class BaseFragment<T extends BasePresenter> extends Fragment {

    protected T mPresenter;

    private Unbinder mUnBinder;

    @Nullable @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(getContentView(), container, false);
    }

    @Override public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mUnBinder = ButterKnife.bind(this, view);

        if (mPresenter == null) mPresenter = createPresenter();

        init();
        if (mPresenter != null) mPresenter.onFirstAttachView();
    }

    @LayoutRes
    protected abstract int getContentView();

    protected abstract void init();

    protected abstract T createPresenter();

    @Override
    public void onStart() {
        super.onStart();
        if (mPresenter != null) mPresenter.onAttachView();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mPresenter != null) mPresenter.onResume();

    }

    @Override
    public void onPause() {
        super.onPause();
        if (mPresenter != null) mPresenter.onPause();
    }

    @Override public void onDestroyView() {
        super.onDestroyView();
        if (mPresenter != null) mPresenter.onDetachView();
        if (mUnBinder != null) {
            mUnBinder.unbind();
        }
    }

    @Override
    public void onDestroy() {
        if (mPresenter != null) mPresenter.onDestroy();
        super.onDestroy();
    }

    protected int getFragmentLayout() {
        return 0;
    }

    @AnimRes
    @AnimatorRes
    protected int getAnimEnter() {
        return android.R.anim.fade_in;
    }

    @AnimRes
    @AnimatorRes
    protected int getAnimExit() {
        return android.R.anim.fade_out;
    }

    protected void onAddOrReplaceFragment(Fragment fragment, String tag) {
        if (getFragmentLayout() <= 0) return;
        FragmentManager fragmentManager = getFragmentManager();
        if (fragmentManager == null) return;
        Fragment tagFragment = fragmentManager.findFragmentByTag(tag);
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        if (tagFragment == null) {
            transaction.add(getFragmentLayout(), fragment, tag);
            tagFragment = fragment;
        }
        transaction.replace(getFragmentLayout(), tagFragment, tag);
        transaction.setCustomAnimations(getAnimEnter(), getAnimExit());
        transaction.commit();
    }
}
