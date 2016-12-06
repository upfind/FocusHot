package cn.upfinder.focushot.Contract;

import java.util.ArrayList;

import cn.upfinder.focushot.Bean.Jock.QiuBaiGif;
import rx.Observer;

/**
 * Created by Administrator on 2016/12/6 0006.
 */

public interface QiuBaiGifContract {


    interface View extends BaseView<Presenter> {
        void showGifList(ArrayList<QiuBaiGif> qiuBaiGifArrayList);

        void showMoreGifList(ArrayList<QiuBaiGif> qiuBaiGifArrayList);


    }

    interface Presenter extends BasePresenter {

        void loadGifList(int page);

        void loadMoreGifList(int page);
    }
}
