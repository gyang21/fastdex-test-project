package com.example.tong.myapplication;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by tong on 17/3/12.
 */
public class CustomView extends LinearLayout {
    @BindView(R.id.tv)
    TextView tv;

    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        inflate(context,R.layout.view_custom,this);
        ButterKnife.bind(this);

        tv.setText("mmmm");
    }
}
