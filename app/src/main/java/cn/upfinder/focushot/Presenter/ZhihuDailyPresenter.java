package cn.upfinder.focushot.Presenter;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;

import cn.upfinder.focushot.Contract.ZhihuDailyContract;
import cn.upfinder.focushot.Fragment.ZhihuDailyFragment;
import cn.upfinder.focushot.API.ApiManage;
import cn.upfinder.focushot.Bean.zhihu.Daily;
import cn.upfinder.focushot.Bean.zhihu.DailyItem;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by upfinder on 2016/10/28 0028.
 */

public class ZhihuDailyPresenter implements ZhihuDailyContract.Presenter {
    private static final String TAG = ZhihuDailyPresenter.class.getSimpleName();

    private ZhihuDailyFragment mvpView;
    private Context context;
    private String dataFlag;

    public ZhihuDailyPresenter(Context context, ZhihuDailyContract.View view) {
        this.context = context;
        this.mvpView = (ZhihuDailyFragment) view;
    }

    @Override
    public void getLastZhihuDaily() {
        ApiManage.getInstence().getZhihuLastDailys(new Observer<Daily>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError: " + e.getMessage());
                mvpView.showError(e.getMessage());
            }

            @Override
            public void onNext(Daily daily) {
                Log.d(TAG, "onNext: " + daily.getStories().size());
                mvpView.showDaily(daily);
            }
        });
    }

    @Override
    public void getMoreZhihuDaily(final String date) {
        ApiManage.getInstence().getZhihuBeforDailys(date, new Observer<ArrayList<DailyItem>>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError: " + e.getMessage());
                mvpView.showError(e.getMessage());
            }

            @Override
            public void onNext(ArrayList<DailyItem> dailys) {

                Log.d(TAG, "onNext: " + dailys.size());
                mvpView.showMoreDaily(dailys);
            }
        });
    }

    @Override
    public void start() {

    }
}
