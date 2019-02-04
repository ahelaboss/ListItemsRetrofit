package com.yourgains.listitemsretrofit.presentation.adapters;

import android.support.annotation.NonNull;
import android.view.ViewGroup;

import com.yourgains.listitemsretrofit.data.model.AnimalModel;
import com.yourgains.listitemsretrofit.presentation.adapters.listeners.OnItemClickListener;
import com.yourgains.listitemsretrofit.presentation.adapters.viewholders.AnimalViewHolder;

import java.util.List;

/**
 * Created by alexeyshishov
 * on 2/3/19.
 */
public class AnimalAdapter extends BaseAdapter<AnimalModel, AnimalViewHolder> {

    public AnimalAdapter(List<AnimalModel> list, OnItemClickListener<AnimalModel> listener) {
        super(list, listener);
    }

    @NonNull @Override
    public AnimalViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        return new AnimalViewHolder(viewGroup, mListener);
    }
}
