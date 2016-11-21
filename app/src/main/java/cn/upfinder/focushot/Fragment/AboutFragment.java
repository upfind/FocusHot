package cn.upfinder.focushot.Fragment;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.upfinder.focushot.Activity.BrowserActivity;
import cn.upfinder.focushot.R;


public class AboutFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.btnGoView)
    Button btnGoView;
    @BindView(R.id.etVIPUrl)
    EditText etVIPUrl;

    private String mParam1;
    private String mParam2;


    public AboutFragment() {
        // Required empty public constructor
    }


    public static AboutFragment newInstance(String param1, String param2) {
        AboutFragment fragment = new AboutFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_about, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(R.id.btnGoView)
    public void onClick() {
        String url = String.valueOf(etVIPUrl.getText());
        if (!url.isEmpty()) {
            Intent intent = new Intent(getContext(), BrowserActivity.class);
            intent.setData(Uri.parse("http://jx.71ki.com/index.php?url=" + url));
            startActivity(intent);
        } else {
            Toast.makeText(getContext(), "请输入要看的视频地址", Toast.LENGTH_SHORT).show();
        }


    }
}
