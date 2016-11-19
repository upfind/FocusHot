package cn.upfinder.focushot;

import android.app.Application;

import com.tencent.smtt.sdk.QbSdk;
import com.tencent.smtt.sdk.TbsDownloader;

/**
 * Created by upfinder on 2016/10/28 0028.
 */

public class MyApplication extends Application {

    public static MyApplication myApplication;

    public static Application getContext() {
        return myApplication;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        myApplication = this;

        TbsDownloader.needDownload(getApplicationContext(), false);
    }
}
