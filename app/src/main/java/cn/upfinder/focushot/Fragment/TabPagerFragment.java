package cn.upfinder.focushot.Fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.upfinder.focushot.Adapter.ITPagerAdapter;
import cn.upfinder.focushot.R;

public class TabPagerFragment extends Fragment {
    private static final String TAG = TabPagerFragment.class.getSimpleName();

    private static final String ARG_PARAM = "param";
    public static final int ARG_NEWS = 21;
    public static final int ARG_IT = 22;
    @BindView(R.id.tblIT)
    TabLayout tblIT;
    @BindView(R.id.vpIT)
    ViewPager vpIT;


    private ArrayList<String> tabTitle; //顶部Tab名称
    private ArrayList<Fragment> fragments; //viewPager中填充Fragment

    private ITPagerAdapter adapter;

    private int paramShowType;


    public TabPagerFragment() {
        // Required empty public constructor
    }

    public static TabPagerFragment newInstance(int paramShowType) {
        TabPagerFragment fragment = new TabPagerFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM, paramShowType);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            paramShowType = getArguments().getInt(ARG_PARAM);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tabpager, container, false);
        ButterKnife.bind(this, view);
        Log.e(TAG, "onCreateView: ");
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.e(TAG, "onViewCreated: ");
        switch (paramShowType) {
            case ARG_IT:  //显示It相关
                initITFragments();
                break;
            case ARG_NEWS: //显示新闻相关
                initNewsFragments();
                break;
        }
    }

    private void initITFragments() {
        tblIT.setTabMode(TabLayout.MODE_FIXED);
        Log.e(TAG, "initView: ");
        tabTitle = new ArrayList<>();
        tabTitle.add(MobileFragment.ARG_ANDROID);
        tabTitle.add(MobileFragment.ARG_IOS);
        tabTitle.add(MobileFragment.ARG_HTML);
        tabTitle.add(MobileFragment.ARG_MORE);

        fragments = new ArrayList<>();
        fragments.add(MobileFragment.newInstance(MobileFragment.ARG_ANDROID));
        fragments.add(MobileFragment.newInstance(MobileFragment.ARG_IOS));
        fragments.add(MobileFragment.newInstance(MobileFragment.ARG_HTML));
        fragments.add(MobileFragment.newInstance(MobileFragment.ARG_MORE));

        vpIT.setOffscreenPageLimit(4);
        adapter = new ITPagerAdapter(getChildFragmentManager(), tabTitle, fragments);
        vpIT.setAdapter(adapter);
        //将TabLayout和ViewPager关联起来
        tblIT.setupWithViewPager(vpIT);
        //给TabLayout设置设配器
        tblIT.setTabsFromPagerAdapter(adapter);
    }


    private void initNewsFragments() {

        tblIT.setTabMode(TabLayout.MODE_SCROLLABLE);
        Log.e(TAG, "initView: 初始化新闻相关的 ");
        tabTitle = new ArrayList<>();
        tabTitle.add("头条");
        tabTitle.add("知乎");
        tabTitle.add("国内");
        tabTitle.add("娱乐");
        tabTitle.add("国际");
        tabTitle.add("科技");
        tabTitle.add("社会");
        tabTitle.add("体育");
        tabTitle.add("财经");
        tabTitle.add("军事");
        tabTitle.add("时尚");


        fragments = new ArrayList<>();
        fragments.add(JuheNewFragment.newInstance(JuheNewFragment.ARG_TOP));
        fragments.add(ZhihuDailyFragment.newInstance("", ""));
        fragments.add(JuheNewFragment.newInstance(JuheNewFragment.ARG_GUONEI));
        fragments.add(JuheNewFragment.newInstance(JuheNewFragment.ARG_YULE));
        fragments.add(JuheNewFragment.newInstance(JuheNewFragment.ARG_GUOJI));
        fragments.add(JuheNewFragment.newInstance(JuheNewFragment.ARG_KEJI));
        fragments.add(JuheNewFragment.newInstance(JuheNewFragment.ARG_SHEHUI));
        fragments.add(JuheNewFragment.newInstance(JuheNewFragment.ARG_TIYU));
        fragments.add(JuheNewFragment.newInstance(JuheNewFragment.ARG_CAIJING));
        fragments.add(JuheNewFragment.newInstance(JuheNewFragment.ARG_JUNSHI));
        fragments.add(JuheNewFragment.newInstance(JuheNewFragment.ARG_SHISHANG));

        vpIT.setOffscreenPageLimit(10);
        adapter = new ITPagerAdapter(getChildFragmentManager(), tabTitle, fragments);
        vpIT.setAdapter(adapter);
        //将TabLayout和ViewPager关联起来
        tblIT.setupWithViewPager(vpIT);
        //给TabLayout设置设配器
        tblIT.setTabsFromPagerAdapter(adapter);
    }

}
