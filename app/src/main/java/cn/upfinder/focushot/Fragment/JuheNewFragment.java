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
import cn.upfinder.focushot.Adapter.JuheNewsAdapter;
import cn.upfinder.focushot.Bean.Juhe.JuheNews;
import cn.upfinder.focushot.Contract.JuheNewContract;
import cn.upfinder.focushot.Presenter.JuheNewsPresenter;
import cn.upfinder.focushot.R;
import cn.upfinder.uilibrary.Adapter.BaseQuickAdapter;


public class JuheNewFragment extends BaseFragment implements JuheNewContract.View, SwipeRefreshLayout.OnRefreshListener {
    private static final String ARG_PARAM = "paramType";

    public static final String ARG_TOP = "top";
    public static final String ARG_SHEHUI = "shehui";
    public static final String ARG_GUONEI = "guonei";
    public static final String ARG_GUOJI = "guoji";
    public static final String ARG_YULE = "yule";
    public static final String ARG_TIYU = "tiyu";
    public static final String ARG_JUNSHI = "junshi";
    public static final String ARG_KEJI = "keji";
    public static final String ARG_CAIJING = "caijing";
    public static final String ARG_SHISHANG = "shishang";
    @BindView(R.id.rvJuheNew)
    RecyclerView rvJuheNew;
    @BindView(R.id.srfContent)
    SwipeRefreshLayout srfContent;


    private JuheNewsPresenter mvpPresenter;
    private JuheNewsAdapter adapter;

    private String paramType;

    public JuheNewFragment() {
    }


    public static JuheNewFragment newInstance(String paramNewsType) {
        JuheNewFragment fragment = new JuheNewFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM, paramNewsType);
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
    protected void initView() {

    }

    @Override
    protected void initListener() {
        srfContent.setRefreshing(true);
        srfContent.setOnRefreshListener(this);

        adapter = new JuheNewsAdapter(R.layout.item_android_layout, null);
        rvJuheNew.setLayoutManager(new LinearLayoutManager(getContext()));
        rvJuheNew.setHasFixedSize(true);
        rvJuheNew.setAdapter(adapter);
        adapter.setOnRecyclerViewItemClickListener(new BaseQuickAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(getContext(), BrowserActivity.class);
                intent.setData(Uri.parse(adapter.getItem(position).getUrl()));
//                intent.setData(Uri.parse("http://jx.71ki.com/index.php?url=http://v.youku.com/v_show/id_XMTgyNzY0MDMwNA==.html?spm=a2htv.20009910.m_86821.5~5~5!2~5!2~A&from=y1.3-tv-grid-1007-9910.86827-86824-86821.2-1"));
                startActivity(intent);
            }
        });
    }

    @Override
    protected void initData() {

        mvpPresenter = (JuheNewsPresenter) getMvpPresenter();
        mvpPresenter.getJuheNewsData(paramType);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_juhe_new, container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void showNewsData(ArrayList<JuheNews.ResultBean.DataBean> juheNewsList) {
        adapter.setNewData(juheNewsList);
        srfContent.setRefreshing(false);
    }


    @Override
    public JuheNewContract.Presenter getMvpPresenter() {
        if (mvpPresenter == null) {
            mvpPresenter = new JuheNewsPresenter(getContext(), this);
        }
        return mvpPresenter;
    }

    @Override
    public void showError(String errorMsg) {

        Toast.makeText(getContext(), errorMsg, Toast.LENGTH_SHORT).show();
        srfContent.setRefreshing(false);
    }

    @Override
    public void onRefresh() {

        mvpPresenter.getJuheNewsData(paramType);
    }
}
