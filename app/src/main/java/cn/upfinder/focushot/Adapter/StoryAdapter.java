package cn.upfinder.focushot.Adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.List;

import cn.upfinder.focushot.Bean.zhihu.Story;
import cn.upfinder.focushot.R;
import cn.upfinder.uilibrary.Adapter.BaseQuickAdapter;
import cn.upfinder.uilibrary.Adapter.BaseViewHolder;

/**
 * Created by Administrator on 2016/11/3 0003.
 */

public class StoryAdapter extends BaseQuickAdapter<Story> {


    public StoryAdapter(int layoutResId, List<Story> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Story item) {
        Glide.with(mContext)
                .load(item.getImage())
                .crossFade()
                .placeholder(R.mipmap.ic_launcher)
                .into((ImageView) helper.getView(R.id.book_info_image_url));
        helper.setText(R.id.book_info_textview_name, item.getTitle());
        helper.setText(R.id.book_info_textview_author, item.getTitle());
        helper.setText(R.id.book_info_textview_introduction, "简介:" + item.getTitle());
    }
}
