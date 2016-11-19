package cn.upfinder.focushot.API;

import cn.upfinder.focushot.Bean.Juhe.JuheNews;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by upfinder on 2016/11/18 0018.
 * 聚合数据的Api
 */

public interface JuheApi {

    /*
    * 聚合数据新闻头条接口
    * @type: 类型,,top(头条，默认),shehui(社会),guonei(国内),guoji(国际),yule(娱乐),tiyu(体育)junshi(军事),keji(科技),caijing(财经),shishang(时尚)
    * */
//    @GET("/toutiao/index?type={type}&key=bf17f828f3cce5b67da7a631ad8bc991")
//    Observable<JuheNews> getJuheNews(@Path("type") String type);

    @GET("/toutiao/index")
    Observable<JuheNews> getJuheNews(@Query("type") String type, @Query("key") String key);
}

