package com.yourgains.listitemsretrofit.data;

import com.yourgains.listitemsretrofit.data.model.AnimalModel;

import java.util.List;

/**
 * Created by alexeyshishov
 * on 2/3/19.
 */
public interface AnimalListener {

    void onComplete(List<AnimalModel> list);

    void onError();
}
