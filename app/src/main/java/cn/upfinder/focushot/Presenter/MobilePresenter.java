package cn.upfinder.focushot.Presenter;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;

import cn.upfinder.focushot.API.ApiManage;
import cn.upfinder.focushot.Bean.gank.GankData;
import cn.upfinder.focushot.Contract.MobileContract;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by upfinder on 2016/11/15 0015.
 */

public class MobilePresenter implements MobileContract.Presenter {
    private static final String TAG = MobilePresenter.class.getSimpleName();

    private Context context;
    private MobileContract.View mvpView;

    public MobilePresenter(Context context, MobileContract.View mvpView) {

        this.context = context;
        this.mvpView = mvpView;
    }


    @Override
    public void getAndroidData(String type, int page) {

        ApiManage.getInstence().getGankData(type, page, new Observer<ArrayList<GankData.ResultsBean>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

                Log.e(TAG, "onError: " + e.getMessage());
                mvpView.showError(e.getMessage());
            }

            @Override
            public void onNext(ArrayList<GankData.ResultsBean> resultsBeen) {

                mvpView.showAndroidData(resultsBeen);
            }
        });
    }

    @Override
    public void loadMoreAndroidData(String type, int page) {

        ApiManage.getInstence().getGankData(type, page,
                new Observer<ArrayList<GankData.ResultsBean>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mvpView.showError(e.getMessage());
                    }

                    @Override
                    public void onNext(ArrayList<GankData.ResultsBean> resultsBeen) {

                        mvpView.showMoreAndroidData(resultsBeen);
                    }
                });
    }

    @Override
    public void start() {

    }
}
