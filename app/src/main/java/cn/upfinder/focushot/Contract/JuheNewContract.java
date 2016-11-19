package cn.upfinder.focushot.Contract;

import java.util.ArrayList;

import cn.upfinder.focushot.Bean.Juhe.JuheNews;

/**
 * Created by upfinder on 2016/11/18 0018.
 */

public interface JuheNewContract {

    interface View extends BaseView<Presenter> {

        void showNewsData(ArrayList<JuheNews.ResultBean.DataBean> juheNewsList);

    }

    interface Presenter extends BasePresenter {

        void getJuheNewsData(String newsType);
    }


}
