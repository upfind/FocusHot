package cn.upfinder.focushot.Fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.upfinder.focushot.Adapter.StoryAdapter;
import cn.upfinder.focushot.Bean.zhihu.Story;
import cn.upfinder.focushot.R;
import cn.upfinder.uilibrary.Adapter.BaseQuickAdapter;
import cn.upfinder.uilibrary.View.ProgressTypeLayout;
import cn.upfinder.uilibrary.View.SwipeView;
import cn.upfinder.uilibrary.View.container.DefaultFooter;
import cn.upfinder.uilibrary.View.container.DefaultHeader;

public class SwipeFragment extends BaseFragment implements SwipeView.OnFreshListener, BaseQuickAdapter.RequestLoadMoreListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.rvContent)
    RecyclerView rvContent;
    @BindView(R.id.svRefreshLoad)
    SwipeView svRefreshLoad;
    @BindView(R.id.ptlProgress)
    ProgressTypeLayout ptlProgress;


    private StoryAdapter adapter;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public SwipeFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static SwipeFragment newInstance(String param1, String param2) {
        SwipeFragment fragment = new SwipeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }


    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_swipe, container, false);
    }

    @Override
    protected void initListener() {
        //设置滑动监听
        svRefreshLoad.setListener(this);

        //设置下拉头部样式
        svRefreshLoad.setHeader(new DefaultHeader(getContext()));
        svRefreshLoad.setFooter(new DefaultFooter(getContext()));

        ArrayList<Story> stories = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            Story story = new Story();
            story.setBody("故事主题介绍部分内容");
            story.setTitle("故事标题");
            stories.add(story);
        }

        adapter = new StoryAdapter(R.layout.item_story_layout, stories);
        adapter.setOnLoadMoreListener(this);
        rvContent.setLayoutManager(new LinearLayoutManager(getContext()));
        rvContent.setHasFixedSize(true);
        rvContent.setAdapter(adapter);
    }

    @Override
    protected void initData() {

    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }


    @Override
    public void onRefresh() {
        Toast.makeText(getContext(), "刷新中。。。", Toast.LENGTH_SHORT).show();
    }

    //上啦加载  mRecyclerView内部集成的自动加载  上啦加载用不上   在其他View使用
    @Override
    public void onLoadMore() {
        Toast.makeText(getContext(), "加载中。。。", Toast.LENGTH_SHORT).show();
    }


    //加载更多监听
    @Override
    public void onLoadMoreRequested() {
        Toast.makeText(getContext(), "加载中。。。", Toast.LENGTH_SHORT).show();
    }
}
