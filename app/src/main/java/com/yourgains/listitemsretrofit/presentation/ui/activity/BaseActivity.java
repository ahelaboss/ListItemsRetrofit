package com.yourgains.listitemsretrofit.presentation.ui.activity;

import android.os.Bundle;
import android.support.annotation.AnimRes;
import android.support.annotation.AnimatorRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

import com.yourgains.listitemsretrofit.presentation.presenter.BasePresenter;

import butterknife.ButterKnife;


/**
 * Created by alexeyshishov
 * on 2/2/19.
 */
public abstract class BaseActivity<T extends BasePresenter> extends FragmentActivity {

    protected T mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentView());
        ButterKnife.bind(this);

        mPresenter = createPresenter();

        if (mPresenter != null) mPresenter.onFirstAttachView();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mPresenter != null) mPresenter.onAttachView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mPresenter != null) mPresenter.onResume();

    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mPresenter != null) mPresenter.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mPresenter != null) mPresenter.onDetachView();
    }

    @Override
    protected void onDestroy() {
        if (mPresenter != null) mPresenter.onDestroy();
        super.onDestroy();
    }

    @LayoutRes
    protected abstract int getContentView();

    protected abstract T createPresenter();

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

    public void onAddOrReplaceFragment(@NonNull Fragment fragment, @NonNull String tag, boolean isAddToBackStack) {
        if (getFragmentLayout() <= 0) return;
        Fragment tagFragment = getSupportFragmentManager().findFragmentByTag(tag);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (tagFragment == null) {
            if (isAddToBackStack) transaction.addToBackStack(tag);
            transaction.add(getFragmentLayout(), fragment, tag);
        } else {
            transaction.replace(getFragmentLayout(), tagFragment, tag);
        }
        transaction.setCustomAnimations(getAnimEnter(), getAnimExit());
        transaction.commit();
    }
}
