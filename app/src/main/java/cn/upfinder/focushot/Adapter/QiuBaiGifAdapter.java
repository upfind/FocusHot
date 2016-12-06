package cn.upfinder.focushot.Adapter;

import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import cn.upfinder.focushot.Bean.Jock.QiuBaiGif;
import cn.upfinder.focushot.R;
import cn.upfinder.uilibrary.Adapter.BaseQuickAdapter;
import cn.upfinder.uilibrary.Adapter.BaseViewHolder;

/**
 * Created by upfinder on 2016/12/6 0006.
 * 糗事百科 动图
 */

public class QiuBaiGifAdapter extends BaseQuickAdapter<QiuBaiGif> {

    public QiuBaiGifAdapter(int layoutResId, List<QiuBaiGif> data) {
        super(layoutResId, data);
    }


    @Override
    protected void convert(BaseViewHolder helper, QiuBaiGif item) {

        Glide.with(mContext)
                .load(item.getImgUrl())
                .crossFade()
                .placeholder(R.mipmap.ic_launcher)
                .centerCrop()
                .into((ImageView) helper.getView(R.id.ivGif));

        ((TextView) helper.getView(R.id.tvInfo)).setText(item.getTextInfo());
    }
}
