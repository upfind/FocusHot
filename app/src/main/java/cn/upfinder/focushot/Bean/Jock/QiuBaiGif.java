package cn.upfinder.focushot.Bean.Jock;

import java.io.Serializable;

/**
 * Created by upfinder on 2016/12/6 0006.
 * 糗百动图
 */

public class QiuBaiGif implements Serializable {

    private String type;
    private String imgUrl;
    private String textInfo;
    private String gifUrl;


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getTextInfo() {
        return textInfo;
    }

    public void setTextInfo(String textInfo) {
        this.textInfo = textInfo;
    }

    public String getGifUrl() {
        return gifUrl;
    }

    public void setGifUrl(String gifUrl) {
        this.gifUrl = gifUrl;
    }
}
