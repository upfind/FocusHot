package cn.upfinder.focushot.Bean.zhihu;

import java.util.List;

/**
 * Created by upfinder on 2016/10/28 0028.
 */

public class DailyItem {


    /**
     * images : ["http://pic2.zhimg.com/b2b495101c09156ecff37e3bf74059b1.jpg"]
     * type : 0
     * id : 8921915
     * ga_prefix : 102806
     * title : 瞎扯 · 如何正确地吐槽
     */

    private int type;
    private int id;
    private String ga_prefix;
    private String title;
    private List<String> images;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    private String date;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGa_prefix() {
        return ga_prefix;
    }

    public void setGa_prefix(String ga_prefix) {
        this.ga_prefix = ga_prefix;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }
}
