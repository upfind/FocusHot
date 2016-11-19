package cn.upfinder.focushot.Adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import cn.upfinder.focushot.Bean.gank.GankData;
import cn.upfinder.focushot.R;
import cn.upfinder.uilibrary.Adapter.BaseQuickAdapter;
import cn.upfinder.uilibrary.Adapter.BaseViewHolder;

/**
 * Created by upfinder on 2016/11/15 0015.
 */

public class BeautyAdapter extends BaseQuickAdapter<GankData.ResultsBean> {

    public BeautyAdapter(int layoutResId, ArrayList<GankData.ResultsBean> beautyList) {
        super(layoutResId, beautyList);
    }

    @Override
    protected void convert(BaseViewHolder helper, GankData.ResultsBean item) {
        Glide.with(mContext)
                .load(item.getUrl())
                .crossFade()
                .placeholder(R.mipmap.ic_launcher)
                .centerCrop()
                .into((ImageView) helper.getView(R.id.ivBeauty));

    }
}
