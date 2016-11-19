package cn.upfinder.focushot.Presenter;

import android.content.Context;
import android.util.Log;

import cn.upfinder.focushot.API.ApiManage;
import cn.upfinder.focushot.Bean.zhihu.Story;
import cn.upfinder.focushot.Contract.ZhihuDescribeContract;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by upfinder on 2016/11/8 0008.
 */

public class ZhihuDescribePresenter implements ZhihuDescribeContract.Presenter {
    private static final String TAG = ZhihuDescribePresenter.class.getSimpleName();

    private ZhihuDescribeContract.View mvpView;
    private Context context;

    public ZhihuDescribePresenter(Context context, ZhihuDescribeContract.View mvpView) {
        this.context = context;
        this.mvpView = mvpView;
    }


    @Override
    public void getZhihuStory(String storyId) {
        mvpView.showProgress();

        ApiManage.getInstence().getZhihuStory(storyId, new Observer<Story>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                mvpView.showError(e.getMessage());
                Log.d(TAG, "onNext: " + e.getMessage());
            }

            @Override
            public void onNext(Story story) {
                mvpView.showZhihuStory(story);
                Log.d(TAG, "onNext: " + story.getTitle());
            }
        });
    }

    @Override
    public void start() {

    }
}
