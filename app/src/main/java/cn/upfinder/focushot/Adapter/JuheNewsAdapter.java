package cn.upfinder.focushot.Adapter;

import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import cn.upfinder.focushot.Bean.Juhe.JuheNews;
import cn.upfinder.focushot.R;
import cn.upfinder.uilibrary.Adapter.BaseQuickAdapter;
import cn.upfinder.uilibrary.Adapter.BaseViewHolder;

/**
 * Created by Administrator on 2016/11/18 0018.
 */

public class JuheNewsAdapter extends BaseQuickAdapter<JuheNews.ResultBean.DataBean> {

    public JuheNewsAdapter(int layoutResId, List<JuheNews.ResultBean.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, JuheNews.ResultBean.DataBean item) {

        if (item.getUrl() != null) {
            Glide.with(mContext)
                    .load(item.getThumbnail_pic_s())
                    .crossFade()
                    .placeholder(R.mipmap.ic_launcher)
                    .centerCrop()
                    .into((ImageView) helper.getView(R.id.ivLogo));
        }

        ((TextView) helper.getView(R.id.tvTitle)).setText(item.getTitle());
        ((TextView) helper.getView(R.id.tvInfo)).setText(item.toString());
    }
}
