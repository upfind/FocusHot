package cn.upfinder.focushot.Activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.upfinder.focushot.R;
import rx.Subscription;
import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;

public class ImageDescribeActivity extends AppCompatActivity {

    public static final String INTENT_EXTRA_URL = "imageUrl";

    @BindView(R.id.ivBigImage)
    PhotoView ivBigImage;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.activity_image_describe)
    RelativeLayout activityImageDescribe;

    private PhotoViewAttacher photoViewAttacher;

    private String imgUrl;
    private boolean isHidden = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_describe);
        ButterKnife.bind(this);
        parseIntent();
        showImg();
        toolbar.setAlpha(0.7f);
    }

    private void showImg() {
        Glide.with(this)
                .load(imgUrl)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(ivBigImage);

        photoViewAttacher = new PhotoViewAttacher(ivBigImage);
        photoViewAttacher.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                new AlertDialog.Builder(ImageDescribeActivity.this)
                        .setMessage("设置成屏保")
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }

                        })
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                dialogInterface.dismiss();
                            }
                        })
                        .show();
                return true;
            }
        });


        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_36dp);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }


    private void parseIntent() {
        imgUrl = getIntent().getStringExtra(INTENT_EXTRA_URL);
    }
}
