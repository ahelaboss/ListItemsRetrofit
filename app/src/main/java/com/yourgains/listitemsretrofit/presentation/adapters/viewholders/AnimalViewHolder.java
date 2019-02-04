package com.yourgains.listitemsretrofit.presentation.adapters.viewholders;

import android.support.annotation.NonNull;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.yourgains.listitemsretrofit.R;
import com.yourgains.listitemsretrofit.data.model.AnimalModel;
import com.yourgains.listitemsretrofit.presentation.adapters.listeners.OnItemClickListener;

import butterknife.BindView;

/**
 * Created by alexeyshishov
 * on 2/3/19.
 */
public class AnimalViewHolder extends BaseViewHolder<AnimalModel> {

    @BindView(R.id.li_iv) ImageView mAvatar;
    @BindView(R.id.li_tv_title) TextView mTitle;
    @BindView(R.id.li_tv_description) TextView mDescription;

    public AnimalViewHolder(@NonNull ViewGroup parent, OnItemClickListener<AnimalModel> listener) {
        super(parent, R.layout.list_item, listener);
    }

    @Override public void onBind(@NonNull final AnimalModel item) {
        mTitle.setText(item.mTitle);
        mDescription.setText(item.mDescription);
        Picasso.get()
                .load(item.mAvatar)
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .into(mAvatar);

        if (mListener != null) itemView.setOnClickListener(v -> mListener.onItemClick(item, getAdapterPosition()));
    }
}
