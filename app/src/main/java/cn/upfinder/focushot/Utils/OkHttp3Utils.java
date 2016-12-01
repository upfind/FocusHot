package cn.upfinder.focushot.Utils;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import cn.upfinder.focushot.MyApplication;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;

/*
 *okHttp的配置
 * 缓存已经添加
 * 也可根据自己的需要进行相关的修改
 */
public class OkHttp3Utils {

    private static OkHttpClient mOkHttpClient;

    //通过设置网络拦截器进行缓存
    private static final Interceptor REWRITE_CACHE_CONTROL_INTERCEPTOR = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Response originalResponse = chain.proceed(chain.request());
            if (NetWorkUtil.isNetWorkAvailable(MyApplication.getContext())) { //如果网络可用
                int maxAge = 60; //在线缓存在一分钟内可读取
                return originalResponse.newBuilder()
                        .removeHeader("Pragma")
                        .removeHeader("Cache-Control")
                        .header("Cache-Control", "public, max-age=" + maxAge)
                        .build();

            } else { //网络连接不可用
                int maxStale = 60 * 60 * 24 * 28; //离线缓存保存四周
                return originalResponse.newBuilder()
                        .removeHeader("pragma")
                        .removeHeader("Cache-Control")
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                        .build();

            }
        }
    };

    //设置Cache
    //缓存地址
    private static File httpCacheDirectory = new File(MyApplication.getContext().getCacheDir(), "FocusHotCache");
    //缓存大小
    private static int cacheSize = 50 * 1024 * 1024; //10M
    private static Cache cache = new Cache(httpCacheDirectory, cacheSize);

    /**
     * 获取OkHttpClient对象
     */
    public static OkHttpClient getOkHttpClient() {

        if (null == mOkHttpClient) {

            //同样okhttp3后也使用build设计模式
            mOkHttpClient = new OkHttpClient.Builder()
                    .addNetworkInterceptor(REWRITE_CACHE_CONTROL_INTERCEPTOR)
                    .addInterceptor(REWRITE_CACHE_CONTROL_INTERCEPTOR)
                    .cache(cache)
                    .connectTimeout(30, TimeUnit.SECONDS)
                    .writeTimeout(30, TimeUnit.SECONDS)
                    .readTimeout(30, TimeUnit.SECONDS)
                    .build();
        }

        return mOkHttpClient;
    }


}
