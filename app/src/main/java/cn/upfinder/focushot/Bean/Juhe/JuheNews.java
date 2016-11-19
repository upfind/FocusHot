package cn.upfinder.focushot.Bean.Juhe;

import java.util.List;

/**
 * Created by Administrator on 2016/11/18 0018.
 */

public class JuheNews {


    /**
     * reason : 成功的返回
     * result : {"stat":"1","data":[{"title":"家长太粗心错写10倍剂量 老师帮喂药致孩子中毒","date":"2016-11-18 06:52","author_name":"成都商报","thumbnail_pic_s":"http://04.imgmini.eastday.com/mobile/20161118/20161118065246_2a1d3c702325462bb0c7e10a52ea0b06_1_mwpm_03200403.jpg","thumbnail_pic_s02":"http://04.imgmini.eastday.com/mobile/20161118/20161118065246_2a1d3c702325462bb0c7e10a52ea0b06_1_mwpl_05500201.jpg","thumbnail_pic_s03":"http://04.imgmini.eastday.com/mobile/20161118/20161118065246_2a1d3c702325462bb0c7e10a52ea0b06_1_mwpl_05500201.jpg","url":"http://mini.eastday.com/mobile/161118065246322.html?qid=juheshuju","uniquekey":"161118065246322","type":"头条","realtype":"社会"}]}
     */

    private String reason;
    private ResultBean result;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * stat : 1
         * data : [{"title":"家长太粗心错写10倍剂量 老师帮喂药致孩子中毒","date":"2016-11-18 06:52","author_name":"成都商报","thumbnail_pic_s":"http://04.imgmini.eastday.com/mobile/20161118/20161118065246_2a1d3c702325462bb0c7e10a52ea0b06_1_mwpm_03200403.jpg","thumbnail_pic_s02":"http://04.imgmini.eastday.com/mobile/20161118/20161118065246_2a1d3c702325462bb0c7e10a52ea0b06_1_mwpl_05500201.jpg","thumbnail_pic_s03":"http://04.imgmini.eastday.com/mobile/20161118/20161118065246_2a1d3c702325462bb0c7e10a52ea0b06_1_mwpl_05500201.jpg","url":"http://mini.eastday.com/mobile/161118065246322.html?qid=juheshuju","uniquekey":"161118065246322","type":"头条","realtype":"社会"}]
         */

        private String stat;
        private List<DataBean> data;

        public String getStat() {
            return stat;
        }

        public void setStat(String stat) {
            this.stat = stat;
        }

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean {
            /**
             * title : 家长太粗心错写10倍剂量 老师帮喂药致孩子中毒
             * date : 2016-11-18 06:52
             * author_name : 成都商报
             * thumbnail_pic_s : http://04.imgmini.eastday.com/mobile/20161118/20161118065246_2a1d3c702325462bb0c7e10a52ea0b06_1_mwpm_03200403.jpg
             * thumbnail_pic_s02 : http://04.imgmini.eastday.com/mobile/20161118/20161118065246_2a1d3c702325462bb0c7e10a52ea0b06_1_mwpl_05500201.jpg
             * thumbnail_pic_s03 : http://04.imgmini.eastday.com/mobile/20161118/20161118065246_2a1d3c702325462bb0c7e10a52ea0b06_1_mwpl_05500201.jpg
             * url : http://mini.eastday.com/mobile/161118065246322.html?qid=juheshuju
             * uniquekey : 161118065246322
             * type : 头条
             * realtype : 社会
             */

            private String title;
            private String date;
            private String author_name;
            private String thumbnail_pic_s;
            private String thumbnail_pic_s02;
            private String thumbnail_pic_s03;
            private String url;
            private String uniquekey;
            private String type;
            private String realtype;

            public String toString() {
                return getAuthor_name() + "-" + getDate();
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getAuthor_name() {
                return author_name;
            }

            public void setAuthor_name(String author_name) {
                this.author_name = author_name;
            }

            public String getThumbnail_pic_s() {
                return thumbnail_pic_s;
            }

            public void setThumbnail_pic_s(String thumbnail_pic_s) {
                this.thumbnail_pic_s = thumbnail_pic_s;
            }

            public String getThumbnail_pic_s02() {
                return thumbnail_pic_s02;
            }

            public void setThumbnail_pic_s02(String thumbnail_pic_s02) {
                this.thumbnail_pic_s02 = thumbnail_pic_s02;
            }

            public String getThumbnail_pic_s03() {
                return thumbnail_pic_s03;
            }

            public void setThumbnail_pic_s03(String thumbnail_pic_s03) {
                this.thumbnail_pic_s03 = thumbnail_pic_s03;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getUniquekey() {
                return uniquekey;
            }

            public void setUniquekey(String uniquekey) {
                this.uniquekey = uniquekey;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getRealtype() {
                return realtype;
            }

            public void setRealtype(String realtype) {
                this.realtype = realtype;
            }
        }
    }
}
