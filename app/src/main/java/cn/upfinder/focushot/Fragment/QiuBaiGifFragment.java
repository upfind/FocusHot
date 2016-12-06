package cn.upfinder.focushot.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.upfinder.focushot.Activity.ImageDescribeActivity;
import cn.upfinder.focushot.Adapter.QiuBaiAdapter;
import cn.upfinder.focushot.Adapter.QiuBaiGifAdapter;
import cn.upfinder.focushot.Bean.Jock.QiuBaiGif;
import cn.upfinder.focushot.Contract.QiuBaiGifContract;
import cn.upfinder.focushot.Presenter.QiuBaiGifPresenter;
import cn.upfinder.focushot.Presenter.QiuBaiPresenter;
import cn.upfinder.focushot.R;
import cn.upfinder.uilibrary.Adapter.BaseQuickAdapter;

public class QiuBaiGifFragment extends BaseFragment implements QiuBaiGifContract.View, BaseQuickAdapter.RequestLoadMoreListener, SwipeRefreshLayout.OnRefreshListener {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.rvContent)
    RecyclerView rvContent;
    @BindView(R.id.srlContent)
    SwipeRefreshLayout srlContent;

    private String mParam1;
    private String mParam2;

    private QiuBaiGifPresenter mvpPresenter;
    private QiuBaiGifAdapter adapter;

    private int currentPage;

    public QiuBaiGifFragment() {
        // Required empty public constructor
    }

    public static QiuBaiGifFragment newInstance(String param1, String param2) {
        QiuBaiGifFragment fragment = new QiuBaiGifFragment();
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
        View view = inflater.inflate(R.layout.fragment_recylerview, container, false);
        ButterKnife.bind(this, view);
        return view;
    }


    @Override
    protected void initView() {
        rvContent.setLayoutManager(new GridLayoutManager(getContext(), 2));
        rvContent.setHasFixedSize(true);
        adapter = new QiuBaiGifAdapter(R.layout.item_qiubaigif_layout, null);
        rvContent.setAdapter(adapter);
    }

    @Override
    protected void initListener() {

        srlContent.setRefreshing(true);
        srlContent.setOnRefreshListener(this);
        adapter.openLoadMore(12, true);
        adapter.setOnLoadMoreListener(this);
        adapter.setOnRecyclerViewItemClickListener(new BaseQuickAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                QiuBaiGif qiuBaiGif = adapter.getItem(position);
                Intent intent = new Intent(getContext(), ImageDescribeActivity.class);
                intent.putExtra(ImageDescribeActivity.INTENT_EXTRA_URL, qiuBaiGif.getGifUrl());
                startActivity(intent);
            }
        });
    }

    @Override
    protected void initData() {
        currentPage = 1;
        mvpPresenter = (QiuBaiGifPresenter) getMvpPresenter();
        mvpPresenter.loadGifList(currentPage);
    }

    @Override
    public void showGifList(ArrayList<QiuBaiGif> qiuBaiGifArrayList) {

        adapter.setNewData(qiuBaiGifArrayList);
        srlContent.setRefreshing(false);
    }

    @Override
    public void showMoreGifList(ArrayList<QiuBaiGif> qiuBaiGifArrayList) {

        adapter.notifyDataChangedAfterLoadMore(qiuBaiGifArrayList, true);
        srlContent.setRefreshing(false);
    }

    @Override
    public QiuBaiGifContract.Presenter getMvpPresenter() {
        if (mvpPresenter == null) {
            mvpPresenter = new QiuBaiGifPresenter(getContext(), this);
        }
        return mvpPresenter;
    }

    @Override
    public void showError(String errorMsg) {

        srlContent.setRefreshing(false);
        Toast.makeText(getContext(), errorMsg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLoadMoreRequested() {

        currentPage++;
        mvpPresenter.loadMoreGifList(currentPage);
    }


    @Override
    public void onRefresh() {
        currentPage = 1;
        mvpPresenter.loadGifList(currentPage);
    }
}
