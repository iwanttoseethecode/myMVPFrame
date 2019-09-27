package com.example.mymvpframe.view.main;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mymvpframe.R;
import com.example.mymvpframe.bean.GirlBean;
import com.example.mymvpframe.constract.view.GirlView;
import com.example.mymvpframe.presenter.GirlPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by luoling on 2019/9/17.
 * description:
 */
public class GirlAdapter extends RecyclerView.Adapter<GirlAdapter.MyViewHolder> {

    private List<GirlBean> mList = new ArrayList<>();
    private Context context;
    private LayoutInflater layoutInflater;
    private GirlPresenter<GirlView> mPresenter;

    public GirlAdapter(Context context, GirlPresenter<GirlView> presenter) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
        mPresenter = presenter;
    }

    public void setData(List<GirlBean> list){
        this.mList = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public GirlAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = layoutInflater.inflate(R.layout.item,viewGroup,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GirlAdapter.MyViewHolder viewHolder, int i) {
        GirlBean girlBean = mList.get(i);
        viewHolder.iv_icon.setImageResource(girlBean.getIcon());
        viewHolder.tv_like.setText(girlBean.getLike());
        viewHolder.tv_style.setText(girlBean.getStyle());

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.exeLoginHttp();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView iv_icon;
        TextView tv_style;
        TextView tv_like;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_icon = itemView.findViewById(R.id.iv_icon);
            tv_style = itemView.findViewById(R.id.tv_style);
            tv_like = itemView.findViewById(R.id.tv_like);
        }
    }

}
