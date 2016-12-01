package cn.upfinder.focushot.API;

import java.util.ArrayList;

import cn.upfinder.focushot.Bean.Jock.QiuBaiBean;
import cn.upfinder.focushot.Bean.Juhe.JuheNews;
import cn.upfinder.focushot.Bean.gank.GankData;
import cn.upfinder.focushot.Bean.zhihu.Daily;
import cn.upfinder.focushot.Bean.zhihu.DailyItem;
import cn.upfinder.focushot.Bean.zhihu.Story;
import cn.upfinder.focushot.Utils.ParseHtmlUtil;
import cn.upfinder.focushot.Utils.RetrofitUtils;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by upfinder on 2016/10/28 0028.
 */
public class ApiManage {
    private static ApiManage apiManage;
    /**
     * The Zhihu api.
     */
    private ZhihuApi zhihuApi;

    /**
     * The gank api.
     */
    private GankApi gankApi;

    /*
    * the juhe Api
    * */
    private JuheApi juheApi;

    //QiuBaiApi
    private QiuBaiApi qiuBaiApi;

    private Object monitor = new Object();

    public ApiManage() {
    }

    /**
     * Gets instence.
     *
     * @return the instence
     */
    public static ApiManage getInstence() {
        if (apiManage == null) {
            synchronized (ApiManage.class) {
                if (apiManage == null) {
                    apiManage = new ApiManage();
                }
            }
        }
        return apiManage;
    }

    /**
     * Gets zhihu service.
     *
     * @return the zhihu service
     */
    private ZhihuApi getZhihuService() {
        if (zhihuApi == null) {
            synchronized (monitor) {
                if (zhihuApi == null) {
                    zhihuApi = RetrofitUtils.getZhihuRetrofit().create(ZhihuApi.class);
                }
            }
        }
        return zhihuApi;
    }

    private GankApi getGankService() {
        if (gankApi == null) {
            synchronized (monitor) {
                if (gankApi == null) {
                    gankApi = RetrofitUtils.getGankRetrofit().create(GankApi.class);
                }
            }
        }
        return gankApi;
    }


    private JuheApi getJuheService() {
        if (juheApi == null) {
            synchronized (monitor) {
                if (juheApi == null) {
                    juheApi = RetrofitUtils.getJuheRetrofit().create(JuheApi.class);
                }
            }
        }
        return juheApi;
    }

    private QiuBaiApi getQiuBaiService() {
        if (qiuBaiApi == null) {
            synchronized (monitor) {
                if (qiuBaiApi == null) {
                    qiuBaiApi = RetrofitUtils.getQiuBaiRetrofit().create(QiuBaiApi.class);
                }
            }
        }
        return qiuBaiApi;
    }

    public void getGankData(String type, int page, Observer<ArrayList<GankData.ResultsBean>> observer) {
        Observable<ArrayList<GankData.ResultsBean>> observable = getGankService().getGankData(type, page).map(new Func1<GankData, ArrayList<GankData.ResultsBean>>() {
            @Override
            public ArrayList<GankData.ResultsBean> call(GankData gankData) {
                if (gankData.isError()) {
                    throw new RuntimeException("获取数据失败");
                }
                ArrayList<GankData.ResultsBean> resultsBeans = gankData.getResults();
                return resultsBeans;
            }
        });
        setSubscribe(observable, observer);
    }

    public void getZhihuLastDailys(Observer<Daily> observer) {
        Observable<Daily> observable = getZhihuService().getLastDaily().map(new Func1<Daily, Daily>() {
            @Override
            public Daily call(Daily daily) {
                if (daily.getStories() == null || daily.getTop_stories() == null) {
                    throw new RuntimeException("获取知乎数据失败");
                } else {
                    for (DailyItem item : daily.getStories()) {
                        item.setDate(daily.getDate());
                    }
                    return daily;
                }
            }
        });
        setSubscribe(observable, observer);
    }

    public void getZhihuBeforDailys(String date, Observer<ArrayList<DailyItem>> observer) {

        Observable<ArrayList<DailyItem>> observable = getZhihuService().getBeforeDaily(date).map(new Func1<Daily, ArrayList<DailyItem>>() {
            @Override
            public ArrayList<DailyItem> call(Daily daily) {
                if (daily.getStories() == null) {
                    throw new RuntimeException("获取知乎数据失败");
                } else {
                    for (DailyItem item : daily.getStories()) {
                        item.setDate(daily.getDate());
                    }
                    return daily.getStories();
                }
            }
        });

        setSubscribe(observable, observer);

    }

    public void getZhihuStory(String storyId, Observer<Story> observer) {
        Observable<Story> observable = getZhihuService().getStory(storyId);
        setSubscribe(observable, observer);
    }

    public void getJuheNews(String newsType, Observer<ArrayList<JuheNews.ResultBean.DataBean>> observer) {
        Observable<ArrayList<JuheNews.ResultBean.DataBean>> observable = getJuheService().getJuheNews(newsType, "bf17f828f3cce5b67da7a631ad8bc991").map(new Func1<JuheNews, ArrayList<JuheNews.ResultBean.DataBean>>() {
            @Override
            public ArrayList<JuheNews.ResultBean.DataBean> call(JuheNews juheNews) {
                ArrayList<JuheNews.ResultBean.DataBean> dataBeanArrayList = (ArrayList<JuheNews.ResultBean.DataBean>) juheNews.getResult().getData();
                return dataBeanArrayList;
            }
        });
        setSubscribe(observable, observer);
    }


    /*获取糗事百科的数据
    * */
    public void getQiuBaiJock(int page, Observer<ArrayList<QiuBaiBean>> observer) {
        Observable<ArrayList<QiuBaiBean>> observable = getQiuBaiService().getQiuBaiList(page).map(ParseHtmlUtil.getStringFunc1()).map(ParseHtmlUtil.getQiuBaiListFunc());
        setSubscribe(observable, observer);
    }

    /*
    * 插入观察者，设置监听
    * */
    private <T> void setSubscribe(Observable<T> observable, Observer<T> observer) {
        observable.subscribeOn(Schedulers.io())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

}
