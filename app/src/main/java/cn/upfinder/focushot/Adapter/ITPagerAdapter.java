package cn.upfinder.focushot.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by upfinder on 2016/11/16 0016.
 * IT干货界面，ViewPager的Adapter
 */

public class ITPagerAdapter extends FragmentPagerAdapter {

    private ArrayList<String> tabTitle;
    private ArrayList<Fragment> fragments;


    public ITPagerAdapter(FragmentManager fm, ArrayList<String> tabTitle, ArrayList<Fragment> fragments) {
        super(fm);
        this.tabTitle = tabTitle;
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitle.get(position);
    }
}
