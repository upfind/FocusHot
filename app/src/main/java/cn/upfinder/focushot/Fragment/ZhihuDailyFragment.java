package cn.upfinder.focushot.Fragment;


import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.upfinder.focushot.Adapter.ZhihuDailyAdapter;
import cn.upfinder.focushot.Bean.zhihu.Daily;
import cn.upfinder.focushot.Bean.zhihu.DailyItem;
import cn.upfinder.focushot.Contract.ZhihuDailyContract;
import cn.upfinder.focushot.Presenter.ZhihuDailyPresenter;
import cn.upfinder.focushot.R;
import cn.upfinder.uilibrary.View.SwipeView;


public class ZhihuDailyFragment extends BaseFragment implements ZhihuDailyContract.View, SwipeRefreshLayout.OnRefreshListener {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.rvDaily)
    RecyclerView rvDaily;
    @BindView(R.id.srlContent)
    SwipeRefreshLayout srlContent;

    private String currentLoadMoreDate;

    private String mParam1;
    private String mParam2;

    private ZhihuDailyContract.Presenter mvpPresenter;
    private ZhihuDailyAdapter adapter;

    private RecyclerView.OnScrollListener loadingMoreListener;

    public ZhihuDailyFragment() {
    }

    public static ZhihuDailyFragment newInstance(String param1, String param2) {
        ZhihuDailyFragment fragment = new ZhihuDailyFragment();
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
        return inflater.inflate(R.layout.fragment_zhihu_daily, container, false);
    }

    @Override
    protected void initListener() {


        loadingMoreListener = new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if (dy >0){ //向下滚动
//                    int visibleItemCount =
                }
            }
        };


        srlContent.setRefreshing(true);
        srlContent.setOnRefreshListener(this);
        adapter = new ZhihuDailyAdapter(getContext(), new ArrayList<DailyItem>(), new ArrayList<DailyItem>());
        rvDaily.setLayoutManager(new LinearLayoutManager(getContext()));
        rvDaily.setAdapter(adapter);
    }

    @Override
    protected void initData() {
        mvpPresenter = getMvpPresenter();
        mvpPresenter.getLastZhihuDaily();
    }


    public ZhihuDailyContract.Presenter getMvpPresenter() {
        return new ZhihuDailyPresenter(getActivity(), this);
    }

    @Override
    public void showError(String errorMsg) {
        if (rvDaily != null) {
            Snackbar.make(rvDaily, errorMsg, Snackbar.LENGTH_SHORT).setAction("重试", new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mvpPresenter.getLastZhihuDaily();
                }
            }).show();
        }
        srlContent.setRefreshing(false);
    }


    @Override
    public void showDaily(Daily daily) {
        currentLoadMoreDate = daily.getDate();
        adapter.setDailyStoryList(daily.getStories());
        adapter.setTopDailyList(daily.getTop_stories());
        adapter.notifyDataSetChanged();
        srlContent.setRefreshing(false);

    }


    @Override
    public void showMoreDaily(ArrayList<DailyItem> dailys) {
        currentLoadMoreDate = dailys.get(0).getDate();
        adapter.addItems(dailys);
        adapter.notifyDataSetChanged();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onRefresh() {
        mvpPresenter.getLastZhihuDaily();
    }
}
