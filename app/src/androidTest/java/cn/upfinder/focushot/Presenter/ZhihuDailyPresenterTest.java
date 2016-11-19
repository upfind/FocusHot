package cn.upfinder.focushot.Presenter;

import org.junit.Before;
import org.junit.Test;

import cn.upfinder.focushot.MyApplication;

import static org.junit.Assert.*;

/**
 * Created by upfinder on 2016/10/28 0028.
 */
public class ZhihuDailyPresenterTest {

    ZhihuDailyPresenter zhihuDailyPresenter;
    @Before
    public void setUp() throws Exception {
        zhihuDailyPresenter = new ZhihuDailyPresenter(MyApplication.getContext().getApplicationContext(),null);
    }

    @Test
    public void getLastZhihuDaily() throws Exception {

        zhihuDailyPresenter.getLastZhihuDaily();
    }

}