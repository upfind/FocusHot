package cn.upfinder.focushot.Utils;

import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import cn.upfinder.focushot.Bean.Jock.QiuBaiBean;
import cn.upfinder.focushot.Bean.Jock.QiuBaiGif;
import okhttp3.ResponseBody;
import rx.functions.Func1;

/**
 * Created by upfinder on 2016/11/27.
 */

public class ParseHtmlUtil {
    private static final String TAG = ParseHtmlUtil.class.getSimpleName();

    private static final String TO_CHARSET_NAME = "utf-8";

    private static Func1<ResponseBody, String> stringFunc1;

    private static Func1<String, ArrayList<QiuBaiBean>> qiuBaiListFunc;

    private static Func1<String, ArrayList<QiuBaiGif>> qiuBaiGifListFunc;


    public static Func1<ResponseBody, String> getStringFunc1() {
        if (stringFunc1 == null) {
            stringFunc1 = new Func1<ResponseBody, String>() {
                @Override
                public String call(ResponseBody responseBody) {
                    try {
                        String str = new String(responseBody.bytes(), TO_CHARSET_NAME);
                        return str;
                    } catch (IOException e) {
                        e.printStackTrace();
                        return null;
                    }
                }
            };
        }
        return stringFunc1;
    }

    public static Func1<String, ArrayList<QiuBaiBean>> getQiuBaiListFunc() {
        if (qiuBaiListFunc == null) {
            qiuBaiListFunc = new Func1<String, ArrayList<QiuBaiBean>>() {
                @Override
                public ArrayList<QiuBaiBean> call(String s) {
                    ArrayList<QiuBaiBean> qiuBaiJockEntityArrayList = new ArrayList<QiuBaiBean>();

                    try {
                        Document document = Jsoup.parse(s);
                        Elements elements = document.select("div.article.block.untagged.mb15");
                        for (Element item : elements) {
                            QiuBaiBean qiuBaiJock = new QiuBaiBean();
                            Element author = item.select("div.author.clearfix").first();
                            String authorImgUrl = author.select("a").first().select("img").attr("src");
                            String authorName = author.select("a").first().select("img").attr("alt");
                            String authorAge = author.select("div.articleGender.womenIcon").text();
                            Element jock = item.select("div.content").first();
                            String jockContent = jock.select("span").text();
                            Element stats = item.select("div.stats").first();
                            String likedNum = stats.select("i.number").first().text();
                            qiuBaiJock.setAuthorImgUrl(authorImgUrl);
                            qiuBaiJock.setAuthorName(authorName);
                            qiuBaiJock.setContentStr(jockContent);
                            qiuBaiJock.setAuthorAge(authorAge);
                            qiuBaiJock.setLikedNum(likedNum);
                            qiuBaiJockEntityArrayList.add(qiuBaiJock);
                        }
                    } catch (Exception e) {
                        Log.e(TAG, "call: ", e);
                    }

                    return qiuBaiJockEntityArrayList;
                }
            };
        }
        return qiuBaiListFunc;
    }


    public static Func1<String, ArrayList<QiuBaiGif>> getQiuBaiGifListFunc() {

        if (qiuBaiGifListFunc == null) {

            qiuBaiGifListFunc = new Func1<String, ArrayList<QiuBaiGif>>() {
                @Override
                public ArrayList<QiuBaiGif> call(String s) {
                    ArrayList<QiuBaiGif> qiuBaiGifArrayList = new ArrayList<>();

                    Document document = Jsoup.parse(s);
                    Element element = document.select("ul.list").first();
                    Elements gifItemElements = element.select("a.images");

                    try {
                        for (Element item : gifItemElements) {
                            QiuBaiGif qiuBaiGif = new QiuBaiGif();
                            //获取跳转到详情页的<a> 标签
                        /*1：获取详情页地址后，解析详情页获取gif真实地址*/
                            String detailsUrl = item.attr("href");
                            Document detailsDocument = Jsoup.connect("http://www.qiushibaike.com" + detailsUrl).get();
                            String gifUrl = detailsDocument.select("a.imageHref").select("img").attr("src");
                            qiuBaiGif.setGifUrl(gifUrl);
                        /*2:获取封面图片地址，文字描述*/
                            String imgUrl = item.select("div.imagesDiv").attr("data-src");
                            qiuBaiGif.setImgUrl(imgUrl);
                            String textInfo = item.select("div.imagesText").text();
                            qiuBaiGif.setTextInfo(textInfo);
                            qiuBaiGifArrayList.add(qiuBaiGif);
                        }
                    } catch (Exception e) {
                        Log.e(TAG, "call: ", e);

                    }
                    return qiuBaiGifArrayList;
                }
            };
        }

        return qiuBaiGifListFunc;
    }


}
