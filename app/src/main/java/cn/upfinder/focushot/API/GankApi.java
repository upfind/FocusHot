package cn.upfinder.focushot.API;

import cn.upfinder.focushot.Bean.gank.GankData;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by upfinder on 2016/11/15 0015.
 */

public interface GankApi {

    //type : Android | iOS | 前端 | 拓展资源 | App
    @GET("/api/data/{type}/10/{page}")
    Observable<GankData> getGankData(@Path("type") String type, @Path("page") int page);
}
