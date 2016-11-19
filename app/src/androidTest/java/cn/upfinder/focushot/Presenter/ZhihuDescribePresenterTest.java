package cn.upfinder.focushot.Presenter;

import org.junit.Before;
import org.junit.Test;

import cn.upfinder.focushot.MyApplication;

import static org.junit.Assert.*;

/**
 * Created by Administrator on 2016/11/11 0011.
 */
public class ZhihuDescribePresenterTest {

    ZhihuDescribePresenter zhihuDescribePresenter;

    @Before
    public void setUp() throws Exception {

        zhihuDescribePresenter = new ZhihuDescribePresenter(MyApplication.getContext().getApplicationContext(), null);
    }

    @Test
    public void getZhihuStory() throws Exception {

        zhihuDescribePresenter.getZhihuStory("8893968");
    }

}