package cn.upfinder.focushot.Adapter;

import android.widget.TextView;

import java.util.ArrayList;

import cn.upfinder.focushot.Bean.gank.GankData;
import cn.upfinder.focushot.R;
import cn.upfinder.uilibrary.Adapter.BaseQuickAdapter;
import cn.upfinder.uilibrary.Adapter.BaseViewHolder;

/**
 * Created by upfinder on 2016/11/21 0021.
 */

public class VideoAdapter extends BaseQuickAdapter<GankData.ResultsBean> {

    public VideoAdapter(int layoutResId, ArrayList<GankData.ResultsBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, GankData.ResultsBean item) {

        ((TextView) helper.getView(R.id.tvTitle)).setText(item.getDesc());
        ((TextView) helper.getView(R.id.tvInfo)).setText(item.toString());
    }
}
