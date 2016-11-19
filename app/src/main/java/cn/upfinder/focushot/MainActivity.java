package cn.upfinder.focushot;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;

import com.tencent.smtt.sdk.WebView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.wvTencent)
    WebView wvTencent;
    @BindView(R.id.activity_main)
    RelativeLayout activityMain;

    private com.tencent.smtt.sdk.WebViewClient client = new com.tencent.smtt.sdk.WebViewClient() {
        @Override
        public boolean shouldOverrideUrlLoading(WebView webView, String s) {
            webView.loadUrl(s);
            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        wvTencent.setWebViewClient(client);
        wvTencent.loadUrl("http://www.youku.com/");
    }
}
