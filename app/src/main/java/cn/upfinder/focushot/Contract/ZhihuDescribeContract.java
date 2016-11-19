package cn.upfinder.focushot.Contract;

import cn.upfinder.focushot.Bean.zhihu.Story;

/**
 * Created by Administrator on 2016/11/8 0008.
 */

public interface ZhihuDescribeContract {


    interface View extends BaseView<Presenter> {

        void showZhihuStory(Story story);

        void showProgress();

    }

    interface Presenter extends BasePresenter {

        //获取Story详情
        void getZhihuStory(String id);

    }
}
