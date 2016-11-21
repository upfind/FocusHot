package cn.upfinder.focushot.Presenter;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;

import cn.upfinder.focushot.API.ApiManage;
import cn.upfinder.focushot.Bean.gank.GankData;
import cn.upfinder.focushot.Contract.VideoContract;
import rx.Observer;

/**
 * Created by upfinder on 2016/11/21 0021.
 */

public class VideoPresenter implements VideoContract.Presenter {
    private static final String TAG = VideoPresenter.class.getSimpleName();
    private String dataType = "休息视频";

    private Context context;
    private VideoContract.View mvpView;

    public VideoPresenter(Context context, VideoContract.View mvpView) {

        this.context = context;
        this.mvpView = mvpView;
    }

    @Override
    public void start() {

    }

    @Override
    public void getVideoData(int page) {

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
            public void onNext(ArrayList<GankData.ResultsBean> resultsBeen) {

                Log.d(TAG, "onNext:获取视频数量 " + resultsBeen.size());
                mvpView.showVideoData(resultsBeen);
            }
        });
    }

    @Override
    public void getMoreVideoData(int page) {

        ApiManage.getInstence().getGankData(dataType, page, new Observer<ArrayList<GankData.ResultsBean>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

                mvpView.showError(e.getMessage());
            }

            @Override
            public void onNext(ArrayList<GankData.ResultsBean> resultsBeen) {

                mvpView.showMoreVideoData(resultsBeen);
            }
        });
    }
}
