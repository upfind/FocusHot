package cn.upfinder.focushot.Utils;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 封装一个retrofit集成0kHttp3的抽象基类
 */
public abstract class RetrofitUtils {

    private static Retrofit gankRetrofit;
    private static Retrofit zhihuRetrofit;
    private static Retrofit juheRetrofit;

    private static OkHttpClient gankHttpClient;
    private static OkHttpClient zhihuHttpClient;
    private static OkHttpClient juheHttpClient;

    /**
     * 获取GankRetrofit对象
     */
    public static Retrofit getGankRetrofit() {

        if (null == gankRetrofit) {

            if (null == gankHttpClient) {
                gankHttpClient = OkHttp3Utils.getOkHttpClient();
            }
            //Retrofit2后使用build设计模式
            gankRetrofit = new Retrofit.Builder()
                    //设置服务器路径
                    .baseUrl("http://gank.io")
                    //添加转化库，默认是Gson
                    .addConverterFactory(GsonConverterFactory.create())
                    //添加回调库，采用RxJava
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    //设置使用okhttp网络请求
                    .client(gankHttpClient)
                    .build();
        }
        return gankRetrofit;
    }

    /**
     * 获取GankRetrofit对象
     */
    public static Retrofit getZhihuRetrofit() {

        if (null == zhihuRetrofit) {
            if (null == zhihuHttpClient) {
                zhihuHttpClient = OkHttp3Utils.getOkHttpClient();
            }
            //Retrofit2后使用build设计模式
            zhihuRetrofit = new Retrofit.Builder()
                    //设置服务器路径
                    .baseUrl("http://news-at.zhihu.com")
                    //添加转化库，默认是Gson
                    .addConverterFactory(GsonConverterFactory.create())
                    //添加回调库，采用RxJava
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    //设置使用okhttp网络请求
                    .client(zhihuHttpClient)
                    .build();
        }
        return zhihuRetrofit;
    }

    /*
    * 获取Juheretrofit对象
    * */
    public static Retrofit getJuheRetrofit() {

        if (juheRetrofit == null) {
            if (juheHttpClient == null) {
                juheHttpClient = OkHttp3Utils.getOkHttpClient();
            }
            juheRetrofit = new Retrofit.Builder()
                    .baseUrl("http://v.juhe.cn/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .client(juheHttpClient)
                    .build();
        }
        return juheRetrofit;
    }

}
