package cn.upfinder.focushot.Bean.zhihu;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Upfinder on 2016/10/28 0028.
 */

public class Daily {

    @SerializedName("date")
    private String date;
    @SerializedName("stories")
    private ArrayList<DailyItem> stories;
    @SerializedName("top_stories")
    private ArrayList<DailyItem> top_stories;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public ArrayList<DailyItem> getStories() {
        return stories;
    }

    public void setStories(ArrayList<DailyItem> stories) {
        this.stories = stories;
    }

    public ArrayList<DailyItem> getTop_stories() {
        return top_stories;
    }

    public void setTop_stories(ArrayList<DailyItem> top_stories) {
        this.top_stories = top_stories;
    }
}
