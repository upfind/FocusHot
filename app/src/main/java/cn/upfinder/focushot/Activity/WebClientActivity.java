package cn.upfinder.focushot.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.upfinder.focushot.R;

public class WebClientActivity extends AppCompatActivity {
    private static final String TAG = WebClientActivity.class.getSimpleName();
    public static final String INTENT_EXTRA_URL = "webUrl";
    @BindView(R.id.webClient)
    WebView webClient;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.pbWebClient)
    ProgressBar pbWebClient;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_client);
        ButterKnife.bind(this);

        initView();

        url = getIntent().getStringExtra(INTENT_EXTRA_URL);
        webClient.loadUrl(url);
    }

    private void initView() {
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_36dp);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        webClient.getSettings().setJavaScriptEnabled(true); //支持javascript
        webClient.requestFocus();
        webClient.getSettings().setJavaScriptCanOpenWindowsAutomatically(true); //设置允许

        webClient.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress == 100) {
                    pbWebClient.setVisibility(View.INVISIBLE);
                } else {
                    if (pbWebClient.getVisibility() == View.INVISIBLE) {
                        pbWebClient.setVisibility(View.VISIBLE);
                    }
                    pbWebClient.setProgress(newProgress);
                }
                super.onProgressChanged(view, newProgress);
            }
        });

        //webview自己处理链接点击
        webClient.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                view.loadUrl(url);
                return true;
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && webClient.canGoBack()) {

            webClient.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        webClient.destroy();
    }
}
