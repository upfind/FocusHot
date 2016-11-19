package cn.upfinder.focushot.Presenter;

import org.junit.Before;
import org.junit.Test;

import cn.upfinder.focushot.MyApplication;

import static org.junit.Assert.*;

/**
 * Created by Administrator on 2016/11/15 0015.
 */
public class BeautyPresenterTest {

    private BeautyPresenter beautyPresenter;

    @Before
    public void setUp() throws Exception {

        beautyPresenter = new BeautyPresenter(MyApplication.getContext().getApplicationContext(), null);
    }

    @Test
    public void getBeautyData() throws Exception {

        beautyPresenter.getBeautyData(1);
    }

}