package cn.upfinder.focushot.Presenter;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;

import cn.upfinder.focushot.API.ApiManage;
import cn.upfinder.focushot.Bean.gank.GankData;
import cn.upfinder.focushot.Contract.BeautyContract;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by upfinder on 2016/11/15 0015.
 */

public class BeautyPresenter implements BeautyContract.Presenter {
    private static final String TAG = BeautyPresenter.class.getSimpleName();
    private String dataType = "福利";

    private Context context;
    private BeautyContract.View mvpView;

    public BeautyPresenter(Context context, BeautyContract.View mvpView) {
        this.context = context;
        this.mvpView = mvpView;
    }

    @Override
    public void getBeautyData(int page) {
        ApiManage.getInstence().getGankData(dataType, page, new Observer<ArrayList<GankData.ResultsBean>>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError: " + e.getMessage());
                mvpView.showError(e.getMessage());
            }

            @Override
            public void onNext(ArrayList<GankData.ResultsBean> beauties) {
                Log.d(TAG, "onNext: 获取数据数量》》 " + beauties.size());
                mvpView.showBeautyData(beauties);
            }
        });

    }

    @Override
    public void loadMoreBeautyData(int page) {
        ApiManage.getInstence().getGankData(dataType, page, new Observer<ArrayList<GankData.ResultsBean>>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError: " + e.getMessage());
                mvpView.showError(e.getMessage());
            }

            @Override
            public void onNext(ArrayList<GankData.ResultsBean> beauties) {
                Log.d(TAG, "onNext: 上拉加载数据数量》》 " + beauties.size());
                mvpView.showMoreBeautyData(beauties);
            }
        });
    }

    @Override
    public void start() {

    }
}
