package cn.upfinder.focushot.API;

import android.support.annotation.IntRange;

import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by upfinder on 2016/11/30 0030.
 * 糗事百科的API接口
 */

public interface QiuBaiApi {

    @GET("/text/page/{page}/?s=4934975")
    Observable<ResponseBody> getQiuBaiList(@Path("page") @IntRange(from = 2, to = 35) int page);
}
