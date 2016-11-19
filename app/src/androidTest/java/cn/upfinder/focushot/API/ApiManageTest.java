package cn.upfinder.focushot.API;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by upfinder on 2016/10/28 0028.
 */
public class ApiManageTest {

    ApiManage apiManage;

    @Before
    public void setUp() throws Exception {
        apiManage = ApiManage.getInstence();
    }

    @Test
    public void getZhihuService() throws Exception {
        ZhihuApi zhihuApi = apiManage.getZhihuService();
        Assert.assertNotNull(zhihuApi);
    }

}