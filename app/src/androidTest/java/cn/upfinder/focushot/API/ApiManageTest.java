package cn.upfinder.focushot.API;

import android.util.Log;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import cn.upfinder.focushot.Bean.Jock.QiuBaiGif;
import rx.Observer;

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
    public void getQiuBaiGif() throws Exception {

        apiManage.getQiuBaiGif(2, new Observer<ArrayList<QiuBaiGif>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

                Log.e("testGetQiuBaiGif", "onError: ", e);
            }

            @Override
            public void onNext(ArrayList<QiuBaiGif> qiuBaiGifs) {

                Log.d("testGetQiuBaiGif", "onNext: " + qiuBaiGifs.size());
            }
        });
    }


}