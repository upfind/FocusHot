package cn.upfinder.focushot.Adapter;

import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import cn.upfinder.focushot.Bean.gank.GankData;
import cn.upfinder.focushot.R;
import cn.upfinder.uilibrary.Adapter.BaseQuickAdapter;
import cn.upfinder.uilibrary.Adapter.BaseViewHolder;

/**
 * Created by Administrator on 2016/11/16 0016.
 */

public class AndroidAdapter extends BaseQuickAdapter<GankData.ResultsBean> {

    public AndroidAdapter(int layoutResId, List<GankData.ResultsBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, GankData.ResultsBean item) {

        String[] imgs = item.getImages();
        if (imgs != null) {
            String imgUrl = imgs[0];
            if (imgUrl != null) {
                Glide.with(mContext)
                        .load(item.getImages()[0])
                        .crossFade()
                        .placeholder(R.mipmap.ic_launcher)
                        .centerCrop()
                        .into((ImageView) helper.getView(R.id.ivLogo));
            }
        }

        ((TextView) helper.getView(R.id.tvTitle)).setText(item.getDesc());
        ((TextView) helper.getView(R.id.tvInfo)).setText(item.getWho() + "-" + item.getCreatedAt());
    }
}
