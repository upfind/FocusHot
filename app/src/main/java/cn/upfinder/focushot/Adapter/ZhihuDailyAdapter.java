package cn.upfinder.focushot.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.xmlpull.v1.XmlPullParser;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.upfinder.focushot.Activity.ZhihuDescribeActivity;
import cn.upfinder.focushot.R;
import cn.upfinder.focushot.Bean.zhihu.Daily;
import cn.upfinder.focushot.Bean.zhihu.DailyItem;

/**
 * Created by upfinder on 2016/10/28 0028.
 */

public class ZhihuDailyAdapter extends RecyclerView.Adapter {
    private static final String TAG = ZhihuDailyAdapter.class.getSimpleName();

    private static final int ITEM_TYPE_HEADER = 0;
    private static final int ITEM_TYPE_FOOTER = 1;
    private static final int ITEM_TYPE_CONTENT = 2;

    private int headerCount = 1;
    private static int footerCount = 0;

    private Context context;
    private ArrayList<DailyItem> topStoryList;
    private ArrayList<DailyItem> dailyStoryList;

    public ZhihuDailyAdapter(Context context, ArrayList<DailyItem> topStoryList, ArrayList<DailyItem> dailyStoryList) {

        this.context = context;
        this.topStoryList = topStoryList;
        this.dailyStoryList = dailyStoryList;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ITEM_TYPE_HEADER) {
            return new HeaderViewHolder();
        } else if (viewType == ITEM_TYPE_FOOTER) {
            return null;
        } else if (viewType == ITEM_TYPE_CONTENT) {
            return new ContactViewHolder(null);
        } else {
            return null;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (isHeaderView(position)) {
            ((HeaderViewHolder) holder).bindData(topStoryList);
        } else if (isFooterView(position)) {

        } else if (isContactView(position)) {
            ((ContactViewHolder) holder).bindData(dailyStoryList.get(position - getHeaderCount()));
        }
    }

    @Override
    public int getItemCount() {
        return getHeaderCount() + dailyStoryList.size(); //头部布局+今日数据
    }

    public void addItems(ArrayList<DailyItem> dailyItems) {
        this.dailyStoryList.addAll(dailyItems);
    }


    @Override
    public int getItemViewType(int position) {
        if (isHeaderView(position)) {
            return ITEM_TYPE_HEADER;
        } else if (isFooterView(position)) {
            return ITEM_TYPE_FOOTER;
        } else {
            return ITEM_TYPE_CONTENT;
        }
    }


    //判断当前item是否是HeaderView
    private boolean isHeaderView(int position) {
        return getHeaderCount() != 0 && position < getHeaderCount();
    }

    //判断当前item是否是FooterView
    private boolean isFooterView(int position) {
        return footerCount != 0 && position > getHeaderCount() + dailyStoryList.size();
    }

    //判断item是ContactView
    private boolean isContactView(int position) {
        int num = position - getHeaderCount();
        return num >= 0 && num < position - footerCount;
    }


    public void setTopDailyList(ArrayList<DailyItem> topDailyList) {
        this.topStoryList = topDailyList;
    }

    public void setDailyStoryList(ArrayList<DailyItem> dailyStoryList) {
        this.dailyStoryList = dailyStoryList;
    }


    public int getHeaderCount() {
        return headerCount;
    }

    /*Header布局的ViewHolder*/
    public class HeaderViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.vpTopStory)
        ViewPager vpTopStory;

        public HeaderViewHolder() {
            super(LayoutInflater.from(context).inflate(R.layout.top_story_layout, null, false));
            ButterKnife.bind(this, itemView);
        }

        public void bindData(ArrayList<DailyItem> itemList) {

            //存放View的list
            ArrayList<View> viewList = new ArrayList<>();
            //将topStory循环生成ViewList
            for (DailyItem item : itemList) {
                View itemView = LayoutInflater.from(context).inflate(R.layout.item_top_story, null);
                if (item != null && item.getImages() != null && item.getImages().size() > 0) {
                    ImageView imageView = (ImageView) itemView.findViewById(R.id.ivTopStoryImg);
                    Glide.with(context)
                            .load(item.getImages().get(0))
                            .error(R.mipmap.ic_launcher)
                            .placeholder(R.mipmap.ic_launcher)
                            .into(imageView);
                }
                TextView tvTitle = (TextView) itemView.findViewById(R.id.tvTopStoryTitle);
                tvTitle.setText(item.getTitle());
                viewList.add(itemView);

            }

            TopStoryAdapter adapter = new TopStoryAdapter(context);
            adapter.setViewList(viewList);
            vpTopStory.setAdapter(adapter);
        }

    }

    /*内容布局的ViewHolder*/
    public class ContactViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tvStoryTitle)
        TextView tvStoryTitle;
        @BindView(R.id.ivStoryImg)
        ImageView ivStoryImg;

        public ContactViewHolder(ViewGroup root) {
            super(LayoutInflater.from(context).inflate(R.layout.item_rlv_story, root, false));
            ButterKnife.bind(this, itemView);
        }

        public void bindData(final DailyItem item) {
            tvStoryTitle.setText(item.getTitle());
            Glide.with(context)
                    .load(item.getImages().get(0))
                    .error(R.mipmap.ic_launcher)
                    .placeholder(R.mipmap.ic_launcher)
                    .into(ivStoryImg);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, ZhihuDescribeActivity.class);
                    intent.putExtra(ZhihuDescribeActivity.INTENT_STORYID_KEY, item.getId() + "");
                    context.startActivity(intent);
                }
            });
        }
    }


}
