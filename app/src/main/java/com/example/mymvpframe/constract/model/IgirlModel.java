package com.example.mymvpframe.constract.model;

import com.example.mymvpframe.bean.GirlBean;
import com.example.mymvpframe.constract.BaseModel;

import java.util.List;

/**
 * Created by luoling on 2019/9/16.
 * description:
 */
public interface IgirlModel extends BaseModel {

    void loadGirlData();

    void loadGirlData(OnLoadListener onLoadListener);

    interface OnLoadListener{
        void onComplete(List<GirlBean> list);
    }


    void useHttpFrame();
}
