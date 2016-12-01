package cn.upfinder.focushot.Contract;

import java.util.ArrayList;

import cn.upfinder.focushot.Bean.Jock.QiuBaiBean;

/**
 * Created by upfinder on 2016/11/30 0030.
 */

public interface QiuBaiContract {

    interface View extends BaseView<Presenter> {

        void showQiuBaiData(ArrayList<QiuBaiBean> qiuBaiList);

        void showMoreQiuBaiData(ArrayList<QiuBaiBean> qiuBaiList);
    }

    interface Presenter extends BasePresenter {

        void getQiuBaiData(int page);

        void getMoreQiuBaiData(int page);
    }
}
