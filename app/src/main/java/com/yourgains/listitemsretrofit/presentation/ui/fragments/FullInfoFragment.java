package com.yourgains.listitemsretrofit.presentation.ui.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.yourgains.listitemsretrofit.R;
import com.yourgains.listitemsretrofit.data.model.AnimalModel;
import com.yourgains.listitemsretrofit.presentation.presenter.FullInfoPresenter;
import com.yourgains.listitemsretrofit.presentation.view.FullInfoView;

import butterknife.BindView;

/**
 * Created by alexeyshishov
 * on 2/2/19.
 */
public class FullInfoFragment extends BaseFragment<FullInfoPresenter> implements FullInfoView {

    public static final String TAG = "FullInfoFragment";

    private static final String BUNDLE_KEY_MODEL = "ANIMAL_MODEL";

    @BindView(R.id.f_full_info_iv) ImageView mAvatar;
    @BindView(R.id.f_full_info_tv_title) TextView mTitle;
    @BindView(R.id.f_full_info_tv_description) TextView mDescription;

    public static FullInfoFragment newInstance(AnimalModel model) {
        Bundle args = new Bundle();
        args.putParcelable(BUNDLE_KEY_MODEL, model);
        FullInfoFragment fragment = new FullInfoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle bundle = getArguments();
        if (bundle != null) {
            mPresenter.setData(bundle.getParcelable(BUNDLE_KEY_MODEL));
        }
    }

    @Override protected int getContentView() {
        return R.layout.fragment_full_info;
    }

    @Override protected void init() {}

    @Override protected FullInfoPresenter createPresenter() {
        return new FullInfoPresenter(this);
    }

    @Override public void setAvatar(String url) {
        Picasso.get()
                .load(url)
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .into(mAvatar);
    }

    @Override public void setTitle(String title) {
        mTitle.setText(title);
    }

    @Override public void setDescription(String description) {
        mDescription.setText(description);
    }
}
