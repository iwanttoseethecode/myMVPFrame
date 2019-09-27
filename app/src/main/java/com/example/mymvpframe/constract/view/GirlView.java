package com.example.mymvpframe.constract.view;

import com.example.mymvpframe.bean.GirlBean;
import com.example.mymvpframe.constract.BaseView;

import java.util.List;

/**
 * Created by luoling on 2019/9/16.
 * description:
 */
public interface GirlView extends BaseView {

    void showGirlView(List<GirlBean> girls);

}
