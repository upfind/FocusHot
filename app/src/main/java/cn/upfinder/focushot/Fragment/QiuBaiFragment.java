package cn.upfinder.focushot.Fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
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
import cn.upfinder.focushot.Adapter.QiuBaiAdapter;
import cn.upfinder.focushot.Bean.Jock.QiuBaiBean;
import cn.upfinder.focushot.Contract.QiuBaiContract;
import cn.upfinder.focushot.Presenter.QiuBaiPresenter;
import cn.upfinder.focushot.R;
import cn.upfinder.uilibrary.View.ProgressTypeLayout;
import cn.upfinder.uilibrary.View.SwipeView;
import cn.upfinder.uilibrary.View.container.DefaultFooter;
import cn.upfinder.uilibrary.View.container.DefaultHeader;

public class QiuBaiFragment extends BaseFragment implements SwipeView.OnFreshListener, QiuBaiContract.View {
    private static final String TAG = QiuBaiFragment.class.getSimpleName();

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.rvContent)
    RecyclerView rvContent;
    @BindView(R.id.svRefreshLoad)
    SwipeView svRefreshLoad;
    @BindView(R.id.ptlProgress)
    ProgressTypeLayout ptlProgress;

    private QiuBaiPresenter mvpPresenter;
    private QiuBaiAdapter adapter;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private int currentPage;

    public QiuBaiFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static QiuBaiFragment newInstance(String param1, String param2) {
        QiuBaiFragment fragment = new QiuBaiFragment();
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
        View view = inflater.inflate(R.layout.fragment_swipe, container, false);
        ButterKnife.bind(this, view);
        return view;
    }


    @Override
    protected void initListener() {
        mvpPresenter = (QiuBaiPresenter) getMvpPresenter();
        //设置滑动监听
        svRefreshLoad.setListener(this);

        //设置下拉头部样式
        svRefreshLoad.setHeader(new DefaultHeader(getContext()));
        svRefreshLoad.setFooter(new DefaultFooter(getContext()));

        adapter = new QiuBaiAdapter(R.layout.item_qiubai_layout, null);
        rvContent.setLayoutManager(new LinearLayoutManager(getContext()));
        rvContent.setHasFixedSize(true);
        rvContent.setAdapter(adapter);
    }

    @Override
    protected void initData() {

        currentPage = 1;
        mvpPresenter.getQiuBaiData(currentPage);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    protected void initView() {

    }


    @Override
    public void onRefresh() {
        currentPage = 1;
        mvpPresenter.getQiuBaiData(currentPage);
        Toast.makeText(getContext(), "刷新中。。。", Toast.LENGTH_SHORT).show();
    }

    //上啦加载  mRecyclerView内部集成的自动加载  上啦加载用不上   在其他View使用
    @Override
    public void onLoadMore() {
        currentPage++;
        mvpPresenter.getMoreQiuBaiData(currentPage);
        Toast.makeText(getContext(), "加载中。。。", Toast.LENGTH_SHORT).show();
    }


    @Override
    public QiuBaiContract.Presenter getMvpPresenter() {
        if (mvpPresenter == null) {
            mvpPresenter = new QiuBaiPresenter(getContext(), this);
        }
        return mvpPresenter;
    }

    @Override
    public void showError(String errorMsg) {

        Log.e(TAG, "onError:" + errorMsg);
        Toast.makeText(getContext(), errorMsg, Toast.LENGTH_SHORT).show();
        svRefreshLoad.onFinishFreshAndLoad();
    }

    @Override
    public void showQiuBaiData(ArrayList<QiuBaiBean> qiuBaiList) {

        adapter.setNewData(qiuBaiList);
        svRefreshLoad.onFinishFreshAndLoad();
    }

    @Override
    public void showMoreQiuBaiData(ArrayList<QiuBaiBean> qiuBaiList) {

        adapter.notifyDataChangedAfterLoadMore(qiuBaiList, true);
        svRefreshLoad.onFinishFreshAndLoad();
    }
}
