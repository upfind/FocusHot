package cn.upfinder.focushot.API;


import cn.upfinder.focushot.Bean.zhihu.Daily;
import cn.upfinder.focushot.Bean.zhihu.Story;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by upfinder on 2016/10/28 0028.
 */

public interface ZhihuApi {

    @GET("/api/4/news/latest")
    Observable<Daily> getLastDaily();

    @GET("/api/4/news/before/{date}")
    Observable<Daily> getBeforeDaily(@Path("date") String date);

    @GET("/api/4/news/{id}")
    Observable<Story> getStory(@Path("id") String id);


}
