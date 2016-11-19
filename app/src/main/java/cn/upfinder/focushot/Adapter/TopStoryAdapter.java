package cn.upfinder.focushot.Adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by upfinder on 2016/10/29 0029.
 */

public class TopStoryAdapter extends PagerAdapter {
    private static final String TAG = TopStoryAdapter.class.getSimpleName();


    private Context context;
    private ArrayList<View> viewList;


    public TopStoryAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return viewList.size();
    }

    public ArrayList<View> getViewList() {
        return viewList;
    }

    public void setViewList(ArrayList<View> viewList) {
        this.viewList = viewList;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(viewList.get(position));
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(viewList.get(position));
        return viewList.get(position);
    }
}
