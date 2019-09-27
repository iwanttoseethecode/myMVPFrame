package com.example.isolation_processor.retrofitPg.Rxretrofit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by luoling on 2019/9/25.
 * description:
 */
public class ScheduleTransformer {
    public static ObservableTransformer io_main(){
        return new ObservableTransformer() {
            @Override
            public ObservableSource apply(Observable upstream) {
                return upstream.subscribeOn(Schedulers.io()).unsubscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
            }
        };
    }
}
