package cn.upfinder.focushot.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
import cn.upfinder.focushot.Activity.WebClientActivity;
import cn.upfinder.focushot.Adapter.AndroidAdapter;
import cn.upfinder.focushot.Bean.gank.GankData;
import cn.upfinder.focushot.Contract.MobileContract;
import cn.upfinder.focushot.Presenter.MobilePresenter;
import cn.upfinder.focushot.R;
import cn.upfinder.uilibrary.Adapter.BaseQuickAdapter;

public class MobileFragment extends BaseFragment implements MobileContract.View, BaseQuickAdapter.RequestLoadMoreListener, SwipeRefreshLayout.OnRefreshListener {
    private static final String TAG = MobileFragment.class.getSimpleName();

    private static final String ARG_PARAM = "paramType";
    public static final String ARG_ANDROID = "Android";
    public static final String ARG_HTML = "前端";
    public static final String ARG_IOS = "iOS";
    public static final String ARG_MORE = "拓展资源";

    @BindView(R.id.rvAndroid)
    RecyclerView rvAndroid;
    @BindView(R.id.srfContent)
    SwipeRefreshLayout srfContent;

    private String paramType;

    private AndroidAdapter adapter;
    private MobilePresenter mvpPresenter;
    private int pageCurrent;


    public MobileFragment() {
    }

    public static MobileFragment newInstance(String param1) {
        MobileFragment fragment = new MobileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            paramType = getArguments().getString(ARG_PARAM);
        }

    }


    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_mobile, container, false);
    }

    @Override
    protected void initListener() {
        pageCurrent = 1;
        srfContent.setRefreshing(true);
        srfContent.setOnRefreshListener(this);

        adapter = new AndroidAdapter(R.layout.item_android_layout, null);
        rvAndroid.setLayoutManager(new LinearLayoutManager(getContext()));
        rvAndroid.setHasFixedSize(true);
        rvAndroid.setAdapter(adapter);
        adapter.openLoadMore(10, true);
        adapter.setOnLoadMoreListener(this);

        adapter.setOnRecyclerViewItemClickListener(new BaseQuickAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                Intent intent = new Intent(getContext(), WebClientActivity.class);
                intent.putExtra(WebClientActivity.INTENT_EXTRA_URL, adapter.getItem(position).getUrl());
                startActivity(intent);
            }
        });
    }

    @Override
    protected void initData() {

        mvpPresenter = (MobilePresenter) getMvpPresenter();
        mvpPresenter.getAndroidData(paramType, 1);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }


    @Override
    public void showAndroidData(ArrayList<GankData.ResultsBean> androidList) {
        adapter.setNewData(androidList);
        srfContent.setRefreshing(false);
    }

    @Override
    public void showMoreAndroidData(ArrayList<GankData.ResultsBean> androidList) {
        adapter.notifyDataChangedAfterLoadMore(androidList, true);
    }

    @Override
    public MobileContract.Presenter getMvpPresenter() {
        if (mvpPresenter == null) {
            mvpPresenter = new MobilePresenter(getContext(), this);
        }
        return mvpPresenter;
    }

    @Override
    public void showError(String errorMsg) {

        Toast.makeText(getContext(), errorMsg, Toast.LENGTH_SHORT).show();
        srfContent.setRefreshing(false);
    }

    @Override
    public void onLoadMoreRequested() {
        pageCurrent++;
        mvpPresenter.loadMoreAndroidData(paramType, pageCurrent);
    }

    @Override
    public void onRefresh() {
        mvpPresenter.getAndroidData(paramType, 1);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
