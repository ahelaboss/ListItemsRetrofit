package com.yourgains.listitemsretrofit.presentation.presenter;

import com.yourgains.listitemsretrofit.data.AnimalListener;
import com.yourgains.listitemsretrofit.data.model.AnimalModel;
import com.yourgains.listitemsretrofit.presentation.ui.fragments.PageFragment;
import com.yourgains.listitemsretrofit.presentation.view.PageView;
import com.yourgains.listitemsretrofit.utils.StringUtils;

import java.util.List;

/**
 * Created by alexeyshishov
 * on 2/2/19.
 */
public class PagePresenter extends BasePresenter<PageView> {

    public PagePresenter(PageView view) {
        super(view);
    }

    public void getData(String type) {
        if (StringUtils.isEmpty(type)) return;
        if (PageFragment.BUNDLE_KEY_TYPE_VALUE_CAT.equals(type)) {
            getDataManager().getCatList(mAnimalListener);
        } else {
            getDataManager().getDogList(mAnimalListener);
        }
    }

    private AnimalListener mAnimalListener = new AnimalListener() {
        @Override public void onComplete(List<AnimalModel> list) {
            getView().setItems(list);
        }

        @Override public void onError() {
            getView().showError();
        }
    };
}
