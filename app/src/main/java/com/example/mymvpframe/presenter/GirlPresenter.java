package com.example.mymvpframe.presenter;

import com.example.mymvpframe.bean.GirlBean;
import com.example.mymvpframe.constract.BaseView;
import com.example.mymvpframe.constract.model.IgirlModel;
import com.example.mymvpframe.constract.presenter.AbstractPresenter;
import com.example.mymvpframe.constract.view.GirlView;
import com.example.mymvpframe.model.GirlModel;
import com.example.mymvpframe.simpleDataBus.RegisterDataBus;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by luoling on 2019/9/16.
 * description:
 */
public class GirlPresenter<T extends BaseView> extends AbstractPresenter<T> {

    private IgirlModel igirlModel = new GirlModel();

    public void fetch(){
        igirlModel.loadGirlData(new IgirlModel.OnLoadListener() {
            @Override
            public void onComplete(List<GirlBean> list) {
                T girlView = viewWrf.get();
                if (girlView instanceof GirlView){
                    ((GirlView) girlView).showGirlView(list);
                }
            }
        });
    }

    public void fetch1(){
        igirlModel.loadGirlData();
    }

    @RegisterDataBus()
    public void getShowGirlData(ArrayList<GirlBean> list){
        T girlView = viewWrf.get();
        if (girlView instanceof GirlView){
            ((GirlView) girlView).showGirlView(list);
        }
    }

    public void exeLoginHttp(){
       igirlModel.useHttpFrame();
    }

}
