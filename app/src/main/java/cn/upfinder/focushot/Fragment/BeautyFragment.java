package cn.upfinder.focushot.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.upfinder.focushot.Activity.ImageDescribeActivity;
import cn.upfinder.focushot.Adapter.BeautyAdapter;
import cn.upfinder.focushot.Bean.gank.GankData;
import cn.upfinder.focushot.Contract.BeautyContract;
import cn.upfinder.focushot.Presenter.BeautyPresenter;
import cn.upfinder.focushot.R;
import cn.upfinder.uilibrary.Adapter.BaseQuickAdapter;

public class BeautyFragment extends BaseFragment implements BeautyContract.View, SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {
    private static final String TAG = BeautyFragment.class.getSimpleName();

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.rvBeauty)
    RecyclerView rvBeauty;
    @BindView(R.id.srlContent)
    SwipeRefreshLayout srlContent;


    private String mParam1;
    private String mParam2;
    private BeautyPresenter mvpPresenter;
    private BeautyAdapter adapter;
    private int pageCurrent;


    public BeautyFragment() {
        // Required empty public constructor
    }


    public static BeautyFragment newInstance(String param1, String param2) {
        BeautyFragment fragment = new BeautyFragment();
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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_beauty, container, false);
        ButterKnife.bind(this, view);
        return view;

    }


    @Override
    protected void initView() {

    }

    @Override
    protected void initListener() {
        pageCurrent = 1;
        srlContent.setOnRefreshListener(this);
        srlContent.setRefreshing(true);
        adapter = new BeautyAdapter(R.layout.item_beauty_layout, new ArrayList<GankData.ResultsBean>());
        //开启上拉加载
        adapter.openLoadMore(10, true);
        adapter.openLoadAnimation();
        rvBeauty.setLayoutManager(new LinearLayoutManager(getContext()));
        rvBeauty.setHasFixedSize(true);
        rvBeauty.setAdapter(adapter);
        adapter.setOnLoadMoreListener(this);

        adapter.setOnRecyclerViewItemClickListener(new BaseQuickAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(getContext(), "单击Item", Toast.LENGTH_SHORT).show();
                GankData.ResultsBean beauty = adapter.getItem(position);
                Intent intent = new Intent(getContext(), ImageDescribeActivity.class);
                intent.putExtra(ImageDescribeActivity.INTENT_EXTRA_URL, beauty.getUrl());
                getContext().startActivity(intent);
            }
        });
    }

    @Override
    protected void initData() {
        mvpPresenter = (BeautyPresenter) getMvpPresenter();
        mvpPresenter.getBeautyData(1);
    }


    @Override
    public void showBeautyData(ArrayList<GankData.ResultsBean> beauties) {
        adapter.setNewData(beauties);
        srlContent.setRefreshing(false);
    }

    @Override
    public void showMoreBeautyData(ArrayList<GankData.ResultsBean> beauties) {
//        adapter.addData(beauties);
        adapter.notifyDataChangedAfterLoadMore(beauties, true);

    }

    @Override
    public BeautyContract.Presenter getMvpPresenter() {
        return new BeautyPresenter(getContext(), this);
    }

    @Override
    public void showError(String errorMsg) {
        Toast.makeText(getContext(), errorMsg, Toast.LENGTH_SHORT).show();
        srlContent.setRefreshing(false);
    }

    @Override
    public void onRefresh() {
        Log.d(TAG, "onRefresh: 下拉刷新页面");
        pageCurrent = 1;
        mvpPresenter.getBeautyData(pageCurrent);
    }

    //上拉加载事件监听
    @Override
    public void onLoadMoreRequested() {
        pageCurrent++;
        mvpPresenter.loadMoreBeautyData(pageCurrent);
    }


}
