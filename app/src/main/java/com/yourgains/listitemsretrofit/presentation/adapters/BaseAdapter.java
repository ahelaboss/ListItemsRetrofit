package com.yourgains.listitemsretrofit.presentation.adapters;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

import com.yourgains.listitemsretrofit.data.model.BaseModel;
import com.yourgains.listitemsretrofit.presentation.adapters.listeners.OnItemClickListener;
import com.yourgains.listitemsretrofit.presentation.adapters.viewholders.BaseViewHolder;

import java.util.List;

/**
 * Created by alexeyshishov
 * on 2/3/19.
 */
public abstract class BaseAdapter<T extends BaseModel, H extends BaseViewHolder<T>> extends
        RecyclerView.Adapter<H> {

    protected List<T> mList;
    protected OnItemClickListener<T> mListener;

    public BaseAdapter(List<T> list) {
        mList = list;
    }

    public BaseAdapter(List<T> list, OnItemClickListener<T> listener) {
        mList = list;
        mListener = listener;
    }

    @Override public void onBindViewHolder(@NonNull H viewHolder, int position) {
        T item = getItem(position);
        if (item != null) viewHolder.onBind(getItem(position));
    }

    @Override public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    public List<T> getItems() {
        return mList;
    }

    public void setItems(List<T> list) {
        mList = list;
        notifyDataSetChanged();
    }

    @Nullable protected T getItem(int position) {
        return position > -1 && position < mList.size() ? mList.get(position) : null;
    }

}
