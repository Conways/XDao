package com.conways.xdao.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.conways.xdao.R;

import org.w3c.dom.Text;

public class LeftActivity extends BaseActivity implements View.OnClickListener {

    private TextView tvBack;
    private TextView tvTitle;

    private ExpandableListView elvLeft;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_left);
        initTitle();
        init();
    }

    private void initTitle() {
        tvTitle = (TextView) this.findViewById(R.id.title_title);
        tvTitle.setText("库存剩余");
        tvBack = (TextView) this.findViewById(R.id.title_back);
        tvBack.setText("返回");
        tvBack.setOnClickListener(this);
    }


    private void init(){
        elvLeft=(ExpandableListView)this.findViewById(R.id.activity_left_list);

    }

    @Override
    public void onClick(View view) {

    }
}
