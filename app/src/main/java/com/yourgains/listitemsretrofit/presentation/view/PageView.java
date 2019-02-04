package com.yourgains.listitemsretrofit.presentation.view;

import com.yourgains.listitemsretrofit.data.model.AnimalModel;

import java.util.List;

/**
 * Created by alexeyshishov
 * on 2/2/19.
 */
public interface PageView extends BaseView {

    void setItems(List<AnimalModel> list);

    void showError();
}
