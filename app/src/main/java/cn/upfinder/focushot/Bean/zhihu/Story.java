package cn.upfinder.focushot.Bean.zhihu;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by upfinder on 2016/10/28 0028.
 */

public class Story {


    /**
     * body : <div class="main-wrap content-wrap">
     * <div class="headline">href="http://www.zhihu.com/question/20874566">查看知乎讨论<span class="js-question-holder"></span></a></div>
     * <p>
     * </div>
     * <p>
     * <p>
     * </div>
     * </div>
     * image_source : Angel Abril Ruiz / CC BY
     * title : 卖衣服的新手段：把耐用品变成「不停买新的」
     * image : http://p4.zhimg.com/30/59/30594279d368534c6c2f91b2c00c7806.jpg
     * share_url : http://daily.zhihu.com/story/3892357
     * js : []
     * ga_prefix : 050615
     * images : ["http://p3.zhimg.com/69/d0/69d0ab1bde1988bd475bc7e0a25b713e.jpg"]
     * type : 0
     * id : 3892357
     * css : ["http://news-at.zhihu.com/css/news_qa.auto.css?v=4b3e3"]
     */

    @SerializedName("body")
    private String body;
    @SerializedName("image_source")
    private String image_source;
    @SerializedName("title")
    private String title;
    @SerializedName("image")
    private String image;
    @SerializedName("share_url")
    private String share_url;
    @SerializedName("ga_prefix")
    private String ga_prefix;
    @SerializedName("images")
    private List<String> images;
    @SerializedName("css")
    private String[] css;

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getImage_source() {
        return image_source;
    }

    public void setImage_source(String image_source) {
        this.image_source = image_source;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getShare_url() {
        return share_url;
    }

    public void setShare_url(String share_url) {
        this.share_url = share_url;
    }

    public String getGa_prefix() {
        return ga_prefix;
    }

    public void setGa_prefix(String ga_prefix) {
        this.ga_prefix = ga_prefix;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public String[] getCss() {
        return css;
    }

    public void setCss(String[] css) {
        this.css = css;
    }
}
