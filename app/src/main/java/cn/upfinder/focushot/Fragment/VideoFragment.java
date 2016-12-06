package cn.upfinder.focushot.Fragment;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.upfinder.focushot.Activity.BrowserActivity;
import cn.upfinder.focushot.Adapter.VideoAdapter;
import cn.upfinder.focushot.Bean.gank.GankData;
import cn.upfinder.focushot.Contract.VideoContract;
import cn.upfinder.focushot.Presenter.VideoPresenter;
import cn.upfinder.focushot.R;
import cn.upfinder.uilibrary.Adapter.BaseQuickAdapter;

public class VideoFragment extends BaseFragment implements VideoContract.View, BaseQuickAdapter.RequestLoadMoreListener, SwipeRefreshLayout.OnRefreshListener {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    @BindView(R.id.rvVideo)
    RecyclerView rvVideo;
    @BindView(R.id.srlContent)
    SwipeRefreshLayout srlContent;

    private String mParam1;
    private String mParam2;

    private VideoPresenter mvpPresenter;
    private VideoAdapter adapter;
    private int pageCurrent;

    public VideoFragment() {
    }

    public static VideoFragment newInstance(String param1, String param2) {
        VideoFragment fragment = new VideoFragment();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_video, container, false);
        ButterKnife.bind(this, view);
        return view;
    }


    @Override
    protected void initView() {

    }

    @Override
    protected void initListener() {

        pageCurrent = 1;
        srlContent.setRefreshing(true);
        srlContent.setOnRefreshListener(this);

        rvVideo.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new VideoAdapter(R.layout.item_android_layout, null);
        adapter.openLoadAnimation();
        rvVideo.setHasFixedSize(true);
        rvVideo.setAdapter(adapter);
        adapter.openLoadMore(10, true);
        adapter.setOnLoadMoreListener(this);
        adapter.setOnRecyclerViewItemClickListener(new BaseQuickAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                GankData.ResultsBean video = adapter.getItem(position);
                Intent intent = new Intent(getContext(), BrowserActivity.class);
                intent.setData(Uri.parse(video.getUrl()));
                startActivity(intent);
            }
        });
    }

    @Override
    protected void initData() {
        mvpPresenter = (VideoPresenter) getMvpPresenter();
        mvpPresenter.getVideoData(pageCurrent);

    }

    @Override
    public VideoContract.Presenter getMvpPresenter() {
        if (mvpPresenter == null) {
            mvpPresenter = new VideoPresenter(getContext(), this);
        }
        return mvpPresenter;
    }

    @Override
    public void showError(String errorMsg) {
        Toast.makeText(getContext(), errorMsg, Toast.LENGTH_SHORT).show();
        srlContent.setRefreshing(false);

    }

    @Override
    public void showVideoData(ArrayList<GankData.ResultsBean> videos) {
        adapter.setNewData(videos);
        srlContent.setRefreshing(false);

    }

    @Override
    public void showMoreVideoData(ArrayList<GankData.ResultsBean> videos) {

        adapter.notifyDataChangedAfterLoadMore(videos, true);
    }

    @Override
    public void onLoadMoreRequested() {
        pageCurrent++;
        mvpPresenter.getMoreVideoData(pageCurrent);

    }

    @Override
    public void onRefresh() {

        pageCurrent = 1;
        mvpPresenter.getVideoData(pageCurrent);
    }
}
