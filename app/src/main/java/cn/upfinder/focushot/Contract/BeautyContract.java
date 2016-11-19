package cn.upfinder.focushot.Contract;

import java.util.ArrayList;

import cn.upfinder.focushot.Bean.gank.GankData;

/**
 * Created by Administrator on 2016/11/15 0015.
 */

public interface BeautyContract {

    interface View extends BaseView<Presenter> {

        void showBeautyData(ArrayList<GankData.ResultsBean> beauties);

        void showMoreBeautyData(ArrayList<GankData.ResultsBean> beauties);
    }

    interface Presenter extends BasePresenter {

        void getBeautyData(int page);

        void loadMoreBeautyData(int page);
    }
}
