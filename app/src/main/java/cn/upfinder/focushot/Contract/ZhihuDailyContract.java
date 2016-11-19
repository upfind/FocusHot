package cn.upfinder.focushot.Contract;


import java.util.ArrayList;

import cn.upfinder.focushot.Bean.zhihu.Daily;
import cn.upfinder.focushot.Bean.zhihu.DailyItem;

/**
 * Created by upfinder on 2016/10/28 0028.
 */

public interface ZhihuDailyContract {

    interface View extends BaseView<Presenter> {

        void showDaily(Daily daily);


        void showMoreDaily(ArrayList<DailyItem> dailys);

    }

    interface Presenter extends BasePresenter {

        //获取知乎Daily数据
        void getLastZhihuDaily();

        //获取更多数据
        void getMoreZhihuDaily(String date);
    }
}
