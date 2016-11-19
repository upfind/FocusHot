package cn.upfinder.focushot.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Created by upfinder on 2016/11/17 0017.
 * 对Fragment进行重构
 */

public abstract class BaseFragment extends Fragment {

    private View rootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = initView(inflater, container);
        ButterKnife.bind(this, rootView);
        return rootView;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initListener();
        initData();
    }


    protected abstract View initView(LayoutInflater inflater, ViewGroup container);

    protected abstract void initListener();

    protected abstract void initData();
}
