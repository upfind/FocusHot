package cn.upfinder.focushot.Presenter;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;

import cn.upfinder.focushot.API.ApiManage;
import cn.upfinder.focushot.Bean.Jock.QiuBaiGif;
import cn.upfinder.focushot.Contract.QiuBaiGifContract;
import rx.Observer;

/**
 * Created by Administrator on 2016/12/6 0006.
 */

public class QiuBaiGifPresenter implements QiuBaiGifContract.Presenter {
    private static final String TAG = QiuBaiGifPresenter.class.getSimpleName();

    private Context context;
    private QiuBaiGifContract.View mvpView;

    public QiuBaiGifPresenter(Context context, QiuBaiGifContract.View mvpView) {
        this.context = context;
        this.mvpView = mvpView;

    }


    @Override
    public void loadGifList(int page) {
        ApiManage.getInstence().getQiuBaiGif(page, new Observer<ArrayList<QiuBaiGif>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

                Log.e(TAG, "onError: ", e);
            }

            @Override
            public void onNext(ArrayList<QiuBaiGif> qiuBaiGifArrayList) {

                mvpView.showGifList(qiuBaiGifArrayList);
            }
        });

    }

    @Override
    public void loadMoreGifList(int page) {

        ApiManage.getInstence().getQiuBaiGif(page, new Observer<ArrayList<QiuBaiGif>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

                Log.e(TAG, "onError: ", e);
            }

            @Override
            public void onNext(ArrayList<QiuBaiGif> qiuBaiGifArrayList) {

                mvpView.showMoreGifList(qiuBaiGifArrayList);
            }
        });
    }

    @Override
    public void start() {

    }
}
