package cn.upfinder.focushot.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.upfinder.focushot.R;

public class QiuBaiGifFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.rvContent)
    RecyclerView rvContent;
    @BindView(R.id.srlContent)
    SwipeRefreshLayout srlContent;

    private String mParam1;
    private String mParam2;


    public QiuBaiGifFragment() {
        // Required empty public constructor
    }

    public static QiuBaiGifFragment newInstance(String param1, String param2) {
        QiuBaiGifFragment fragment = new QiuBaiGifFragment();
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
        View view = inflater.inflate(R.layout.fragment_recylerview, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

}
