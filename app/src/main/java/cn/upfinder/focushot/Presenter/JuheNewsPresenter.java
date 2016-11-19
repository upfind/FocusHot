package cn.upfinder.focushot.Presenter;

import android.content.Context;

import java.util.ArrayList;

import cn.upfinder.focushot.API.ApiManage;
import cn.upfinder.focushot.Bean.Juhe.JuheNews;
import cn.upfinder.focushot.Contract.JuheNewContract;
import rx.Observer;

/**
 * Created by Administrator on 2016/11/18 0018.
 */

public class JuheNewsPresenter implements JuheNewContract.Presenter {

    private JuheNewContract.View mvpView;
    private Context context;

    public JuheNewsPresenter(Context context, JuheNewContract.View mvpView) {
        this.context = context;
        this.mvpView = mvpView;
    }


    @Override
    public void start() {

    }

    @Override
    public void getJuheNewsData(String newsType) {

        ApiManage.getInstence().getJuheNews(newsType, new Observer<ArrayList<JuheNews.ResultBean.DataBean>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

                mvpView.showError(e.getMessage());
            }

            @Override
            public void onNext(ArrayList<JuheNews.ResultBean.DataBean> dataBeen) {

                mvpView.showNewsData(dataBeen);
            }
        });
    }
}
