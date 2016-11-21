package cn.upfinder.focushot.Contract;

import java.util.ArrayList;

import cn.upfinder.focushot.Bean.gank.GankData;

/**
 * Created by upfinder on 2016/11/21 0021.
 */

public interface VideoContract {

    interface View extends BaseView<Presenter> {

        void showVideoData(ArrayList<GankData.ResultsBean> videos);

        void showMoreVideoData(ArrayList<GankData.ResultsBean> videos);
    }

    interface Presenter extends BasePresenter {

        void getVideoData(int page);

        void getMoreVideoData(int page);
    }
}
