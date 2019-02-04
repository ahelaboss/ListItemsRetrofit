package com.yourgains.listitemsretrofit.presentation.adapters.viewholders;

import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.yourgains.listitemsretrofit.data.model.BaseModel;
import com.yourgains.listitemsretrofit.presentation.adapters.listeners.OnItemClickListener;

import butterknife.ButterKnife;

/**
 * Created by alexeyshishov
 * on 2/3/19.
 */
public abstract class BaseViewHolder<T extends BaseModel> extends RecyclerView.ViewHolder {

    protected OnItemClickListener<T> mListener;

    public BaseViewHolder(ViewGroup parent, @LayoutRes int layoutId) {
        super(LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false));
        ButterKnife.bind(this, itemView);
    }

    public BaseViewHolder(ViewGroup parent, @LayoutRes int layoutId, OnItemClickListener<T> listener) {
        super(LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false));
        ButterKnife.bind(this, itemView);
        mListener = listener;
    }

    public abstract void onBind(T item);
}
