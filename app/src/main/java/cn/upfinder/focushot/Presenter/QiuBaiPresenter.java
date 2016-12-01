package cn.upfinder.focushot.Presenter;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;

import cn.upfinder.focushot.API.ApiManage;
import cn.upfinder.focushot.Bean.Jock.QiuBaiBean;
import cn.upfinder.focushot.Contract.QiuBaiContract;
import rx.Observer;

/**
 * Created by upfinder on 2016/11/30 0030.
 */

public class QiuBaiPresenter implements QiuBaiContract.Presenter {

    private Context context;
    private QiuBaiContract.View mvpView;

    public QiuBaiPresenter(Context context, QiuBaiContract.View mvpView) {
        this.context = context;
        this.mvpView = mvpView;

    }


    @Override
    public void start() {

    }

    @Override
    public void getQiuBaiData(int page) {
        ApiManage.getInstence().getQiuBaiJock(page, new Observer<ArrayList<QiuBaiBean>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                mvpView.showError(e.getMessage());
            }

            @Override
            public void onNext(ArrayList<QiuBaiBean> qiuBaiList) {
                mvpView.showQiuBaiData(qiuBaiList);
            }
        });
    }

    @Override
    public void getMoreQiuBaiData(int page) {
        ApiManage.getInstence().getQiuBaiJock(page, new Observer<ArrayList<QiuBaiBean>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                mvpView.showError(e.getMessage());
            }

            @Override
            public void onNext(ArrayList<QiuBaiBean> qiuBaiList) {
                mvpView.showMoreQiuBaiData(qiuBaiList);
            }
        });

    }
}
