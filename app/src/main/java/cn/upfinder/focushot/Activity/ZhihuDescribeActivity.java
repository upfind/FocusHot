package cn.upfinder.focushot.Activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.upfinder.focushot.Bean.zhihu.Story;
import cn.upfinder.focushot.Contract.ZhihuDescribeContract;
import cn.upfinder.focushot.Presenter.ZhihuDescribePresenter;
import cn.upfinder.focushot.R;
import cn.upfinder.focushot.Utils.WebUtil;
import cn.upfinder.focushot.config.Config;
import cn.upfinder.uilibrary.View.ProgressTypeLayout;
import cn.upfinder.uilibrary.View.SwipeView;
import cn.upfinder.uilibrary.View.container.DefaultFooter;
import cn.upfinder.uilibrary.View.container.DefaultHeader;
import cn.upfinder.uilibrary.View.container.RotationHeader;

public class ZhihuDescribeActivity extends AppCompatActivity implements ZhihuDescribeContract.View, SwipeView.OnFreshListener {
    private static final String TAG = ZhihuDescribeActivity.class.getSimpleName();

    public static final String INTENT_STORYID_KEY = "story_id";
    @BindView(R.id.svRefreshLoad)
    SwipeView svRefreshLoad;
    @BindView(R.id.ptlProgress)
    ProgressTypeLayout ptlProgress;
    @BindView(R.id.activity_zhihu_discribe)
    LinearLayout activityZhihuDiscribe;
    @BindView(R.id.tbTop)
    Toolbar tbTop;
    @BindView(R.id.wvDescribe)
    WebView wvDescribe;
    private Context context;
    private ZhihuDescribePresenter mvpPresenter;

    private String title;
    private String url;
    private String[] scc;
    private boolean isEmpty;
    private String storyBody;

    private String storyId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhihu_discribe);
        ButterKnife.bind(this);
        this.context = this;
        mvpPresenter = (ZhihuDescribePresenter) getMvpPresenter();
        Intent intent = getIntent();
        storyId = intent.getStringExtra(INTENT_STORYID_KEY);
        mvpPresenter.getZhihuStory(storyId);
        initView();
    }

    private void initView() {
        tbTop.setTitle("知乎日报");
        tbTop.setBackgroundColor(getColor(R.color.colorPrimary));
        setSupportActionBar(tbTop);
        tbTop.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        svRefreshLoad.setListener(this);
        svRefreshLoad.setHeader(new DefaultHeader(context));

        WebSettings settings = wvDescribe.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        settings.setLoadWithOverviewMode(true);
        settings.setBuiltInZoomControls(false);
        settings.setDomStorageEnabled(true);
        settings.setDatabaseEnabled(true);
        settings.setAppCachePath(getCacheDir().getAbsolutePath() + "/webViewCache");
        settings.setAppCacheEnabled(true);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        wvDescribe.setWebChromeClient(new WebChromeClient());
    }

    @Override
    public ZhihuDescribeContract.Presenter getMvpPresenter() {
        if (this.mvpPresenter == null) {
            return new ZhihuDescribePresenter(context, this);
        } else {
            return this.mvpPresenter;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();

    }


    @Override
    protected void onDestroy() {

        //webView内存泄漏
        if (wvDescribe != null) {
            ((ViewGroup) wvDescribe.getParent()).removeView(wvDescribe);
            wvDescribe.destroy();
            wvDescribe = null;
        }
        super.onDestroy();
    }

    @Override
    public void showError(String errorMsg) {
        ptlProgress.showEmpty(getDrawable(R.mipmap.ic_launcher), "请求失败", "点击重新获取");
        Toast.makeText(context, errorMsg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showZhihuStory(Story story) {
        ptlProgress.showContent();
//        Toast.makeText(context, story.getTitle(), Toast.LENGTH_SHORT).show();
        svRefreshLoad.onFinishFreshAndLoad();
        url = story.getShare_url();
        isEmpty = TextUtils.isEmpty(story.getBody());
        storyBody = story.getBody();
        scc = story.getCss();
        if (isEmpty) {
            wvDescribe.loadUrl(url);
        } else {
            String data = WebUtil.buildHtmlWithCss(storyBody, scc, Config.isNight);
            wvDescribe.loadDataWithBaseURL(WebUtil.BASE_URL, data, WebUtil.MIME_TYPE, WebUtil.ENCODING, WebUtil.FAIL_URL);
        }

    }

    @Override
    public void showProgress() {
        ptlProgress.showLoading();
    }


    /*
    * 刷新控件下拉监听
    * */
    @Override
    public void onRefresh() {

        Toast.makeText(context, "下拉刷新", Toast.LENGTH_SHORT).show();
        mvpPresenter.getZhihuStory(storyId);

    }

    /*
    * 刷新控件上拉加载监听
    * */
    @Override
    public void onLoadMore() {
        Toast.makeText(context, "上拉加载", Toast.LENGTH_SHORT).show();
    }
}
