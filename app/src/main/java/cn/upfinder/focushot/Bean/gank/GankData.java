package cn.upfinder.focushot.Bean.gank;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by upfinder on 2016/11/15 0015.
 */

public class GankData {

    /**
     * error : false
     * results : [{"_id":"582962f9421aa911d3bb7edb","createdAt":"2016-11-14T15:08:41.874Z","desc":"史上最强的 Android 日志库 XLog","images":["http://img.gank.io/bbd6baf4-01ba-4040-956c-32b5f7d9c2e8"],"publishedAt":"2016-11-15T11:26:11.821Z","source":"web","type":"Android","url":"https://github.com/elvishew/xLog","used":true,"who":"Elvis Hew"}]
     */

    private boolean error;
    private ArrayList<ResultsBean> results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public ArrayList<ResultsBean> getResults() {
        return results;
    }

    public void setResults(ArrayList<ResultsBean> results) {
        this.results = results;
    }

    public static class ResultsBean {
        /**
         * _id : 582962f9421aa911d3bb7edb
         * createdAt : 2016-11-14T15:08:41.874Z
         * desc : 史上最强的 Android 日志库 XLog
         * images : ["http://img.gank.io/bbd6baf4-01ba-4040-956c-32b5f7d9c2e8"]
         * publishedAt : 2016-11-15T11:26:11.821Z
         * source : web
         * type : Android
         * url : https://github.com/elvishew/xLog
         * used : true
         * who : Elvis Hew
         */

        private String _id;
        private String createdAt;
        private String desc;
        private String publishedAt;
        private String source;
        private String type;
        private String url;
        private boolean used;
        private String who;
        private String[] images;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getPublishedAt() {
            return publishedAt;
        }

        public void setPublishedAt(String publishedAt) {
            this.publishedAt = publishedAt;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public boolean isUsed() {
            return used;
        }

        public void setUsed(boolean used) {
            this.used = used;
        }

        public String getWho() {
            return who;
        }

        public void setWho(String who) {
            this.who = who;
        }

        public String[] getImages() {
            return images;
        }

        public void setImages(String[] images) {
            this.images = images;

        }

        public String toString() {
            return createdAt + "-作者：" + who + "-来自：" + source;
        }
    }
}
