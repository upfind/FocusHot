package cn.upfinder.focushot.Contract;

import java.util.ArrayList;

import cn.upfinder.focushot.Bean.gank.GankData;

/**
 * Created by upfinder on 2016/11/15 0015.
 */

public interface MobileContract {

    interface View extends BaseView<Presenter> {

        void showAndroidData(ArrayList<GankData.ResultsBean> androidList);

        void showMoreAndroidData(ArrayList<GankData.ResultsBean> androidList);
    }

    interface Presenter extends BasePresenter {

        void getAndroidData(String type, int page);

        void loadMoreAndroidData(String type, int page);
    }
}
