package cn.upfinder.focushot.Utils;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import cn.upfinder.focushot.MyApplication;

import static org.junit.Assert.*;

/**
 * Created by upfinder on 2016/10/28 0028.
 */
public class NetWorkUtilTest {


    @Before
    public void setUp() throws Exception {

    }


    @Test
    public void isNetWorkAvailable() throws Exception {
        boolean isNet = NetWorkUtil.isNetWorkAvailable(MyApplication.getContext().getApplicationContext());
        Assert.assertTrue(isNet);
    }

    @Test
    public void isWifiConnected() throws Exception {

    }

    @Test
    public void is3gConnected() throws Exception {

    }

}