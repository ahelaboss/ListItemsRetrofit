package com.yourgains.listitemsretrofit.presentation.presenter;

import com.yourgains.listitemsretrofit.data.model.AnimalModel;
import com.yourgains.listitemsretrofit.presentation.view.FullInfoView;


/**
 * Created by alexeyshishov
 * on 2/2/19.
 */
public class FullInfoPresenter extends BasePresenter<FullInfoView> {

    public FullInfoPresenter(FullInfoView view) {
        super(view);
    }

    public void setData(AnimalModel model) {
        getView().setAvatar(model.mAvatar);
        getView().setTitle(model.mTitle);
        getView().setDescription(model.mDescription);
    }
}
