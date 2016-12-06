package cn.upfinder.focushot.Adapter;

import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import cn.upfinder.focushot.Bean.Jock.QiuBaiBean;
import cn.upfinder.focushot.R;
import cn.upfinder.uilibrary.Adapter.BaseQuickAdapter;
import cn.upfinder.uilibrary.Adapter.BaseViewHolder;
import cn.upfinder.uilibrary.View.ImageView.GlideCircleTransform;

/**
 * Created by upfinder on 2016/11/30 0030.
 */

public class QiuBaiAdapter extends BaseQuickAdapter<QiuBaiBean> {

    public QiuBaiAdapter(int layoutResId, ArrayList<QiuBaiBean> data) {
        super(layoutResId, data);
    }


    @Override
    protected void convert(BaseViewHolder helper, QiuBaiBean item) {

        Glide.with(mContext)
                .load(item.getAuthorImgUrl())
                .error(R.mipmap.ic_launcher)
                .placeholder(R.mipmap.ic_launcher)
                .centerCrop()
                .transform(new GlideCircleTransform(mContext))
                .into((ImageView) helper.getView(R.id.ivAuthorImg));

        ((TextView) helper.getView(R.id.tvAuthorName)).setText(item.getAuthorName());
        ((TextView) helper.getView(R.id.tvQiuBaiContent)).setText(item.getContentStr());
        ((TextView) helper.getView(R.id.tvLiked)).setText(item.getLikedNum());

    }
}
