package com.example.mymvpframe.view;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;


import com.example.mymvpframe.R;
import com.example.mymvpframe.base.BaseActivity;
import com.example.mymvpframe.bean.GirlBean;
import com.example.mymvpframe.constract.view.GirlView;
import com.example.mymvpframe.presenter.GirlPresenter;
import com.example.mymvpframe.simpleDataBus.SimpleDataBus;
import com.example.mymvpframe.utils.DeviderDecoration;
import com.example.mymvpframe.view.main.GirlAdapter;

import java.util.List;

public class MainActivity extends BaseActivity<GirlPresenter<GirlView>,GirlView> implements GirlView {

    private RecyclerView girlRecycleView;
    private  GirlAdapter girlAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        girlRecycleView = findViewById(R.id.girlRecycleListView);
        girlRecycleView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        girlRecycleView.addItemDecoration(new DeviderDecoration(2));
        girlAdapter = new GirlAdapter(MainActivity.this,mPresenter);
        girlRecycleView.setAdapter(girlAdapter);

    }

    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.fetch1();
    }

    @Override
    protected GirlPresenter<GirlView> createPresenter() {
        return new GirlPresenter<GirlView>();
    }

    @Override
    protected void registerDataBus() {
        SimpleDataBus.getInstance().register(mPresenter);
    }

    @Override
    protected void unregisterDataBus() {
        SimpleDataBus.getInstance().unregister(mPresenter);
    }

    @Override
    public void showGirlView(final List<GirlBean> girls) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                girlAdapter.setData(girls);
            }
        });
    }
}
