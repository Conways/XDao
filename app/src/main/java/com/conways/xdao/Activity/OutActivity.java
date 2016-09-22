package com.conways.xdao.Activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.conways.xdao.R;
import com.conways.xdao.adapter.PopAdapter;
import com.conways.xdao.common.DividerItemDecoration;
import com.conways.xdao.db.DbConstant;
import com.conways.xdao.db.XdaoDbManager;
import com.conways.xdao.entity.Color;
import com.conways.xdao.entity.Operation;
import com.conways.xdao.entity.Operator;
import com.conways.xdao.entity.Type;
import com.conways.xdao.utils.DensityUtil;
import com.conways.xdao.utils.StringUtil;

import java.util.ArrayList;
import java.util.List;

public class OutActivity extends BaseActivity implements View.OnClickListener {

    private TextView tvTitle;
    private TextView tvBack;
    private EditText etType;
    private ImageView ivTypeMore;
    private EditText etColor;
    private ImageView ivColorMore;
    private EditText etCount;
    private ImageView ivCountMore;
    private EditText etOperator;
    private ImageView ivOpratorMore;

    private EditText etNote;
    private Button btOut;


    private List<Type> types;
    private List<Operator> operators;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_out);
        initTitle();
        init();
        initData();
    }

    private void initTitle() {
        tvTitle = (TextView) this.findViewById(R.id.title_title);
        tvTitle.setText("出库");
        tvBack = (TextView) this.findViewById(R.id.title_back);
        tvBack.setText("取消");
        tvBack.setOnClickListener(this);

    }

    private void init() {
        etType = (EditText) this.findViewById(R.id.activity_out_car_type);
        etType.setEnabled(false);
        ivTypeMore = (ImageView) this.findViewById(R.id.activity_out_car_type_more);
        ivTypeMore.setOnClickListener(this);
        etColor = (EditText) this.findViewById(R.id.activity_out_color);
        etColor.setEnabled(false);
        ivColorMore = (ImageView) this.findViewById(R.id.activity_out_color_more);
        ivColorMore.setOnClickListener(this);
        etCount = (EditText) this.findViewById(R.id.activity_out_count);
        ivCountMore = (ImageView) this.findViewById(R.id.activity_out_count_more);
        ivCountMore.setOnClickListener(this);
        etOperator = (EditText) this.findViewById(R.id.activity_out_operator);
        ivOpratorMore = (ImageView) this.findViewById(R.id.activity_out_operator_more);
        ivOpratorMore.setOnClickListener(this);
        btOut = (Button) this.findViewById(R.id.activity_out_save);
        btOut.setOnClickListener(this);
        etNote = (EditText) this.findViewById(R.id.activity_out_note);
    }


    private void initData() {
        types = XdaoDbManager.getInstance().getTypes();
        operators = XdaoDbManager.getInstance().getOperators();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.title_back:
                this.finish();
                break;
            case R.id.activity_out_car_type_more:
                showPop(Types.type);
                break;
            case R.id.activity_out_color_more:
                showPop(Types.colors);
                break;
            case R.id.activity_out_count_more:
                showPop(Types.count);
                break;
            case R.id.activity_out_operator_more:
                showPop(Types.coperator);
                break;
            case R.id.activity_out_save:
                out();
                break;
            default:
                break;


        }
    }

    private void showPop(final Types type) {
        final List<String> list = new ArrayList<>();
        int height = 0;
        switch (type) {
            case type:
                height = DensityUtil.dip2px(this, 104) + DensityUtil.getStatusBarHeight(this);
                for (int i = 0; i < types.size(); i++) {
                    list.add(types.get(i).getCarType());
                }
                break;
            case colors:
                height = DensityUtil.dip2px(this, 160) + DensityUtil.getStatusBarHeight(this);
                if (StringUtil.isEmpty(etType.getText().toString())) {
                    break;
                }
                for (int i = 0; i < types.size(); i++) {
                    if (types.get(i).getCarType().equals(etType.getText().toString())) {
                        List<Color> colors = types.get(i).getColors();
                        for (int j = 0; j < colors.size(); j++) {
                            list.add(colors.get(j).getColor());
                        }
                        break;
                    }
                }

                break;
            case count:
                height = DensityUtil.dip2px(this, 216) + DensityUtil.getStatusBarHeight(this);
                for (int i = 0; i < 100; i++) {
                    list.add(i + "");
                }
                break;
            case coperator:
                height = DensityUtil.dip2px(this, 272) + DensityUtil.getStatusBarHeight(this);
                for (int i = 0; i < operators.size(); i++) {
                    list.add(operators.get(i).getName());
                }
                break;

            default:
                break;

        }

        View contentView = LayoutInflater.from(this).inflate(R.layout.pop_list, null);
        RecyclerView recyclerView = (RecyclerView) contentView.findViewById(R.id.pop_list_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL_LIST, R.drawable.item_line_white));
        recyclerView.setHasFixedSize(true);
        final PopAdapter popAdapter = new PopAdapter(list, this);

        recyclerView.setAdapter(popAdapter);

        final PopupWindow popupWindow = new PopupWindow(contentView,
                DensityUtil.getScreenWith(this) - DensityUtil.dip2px(this, 64), DensityUtil.dip2px
                (this, 108), true);
        popupWindow.setTouchable(true);
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(getResources().getDrawable(R.drawable.rect_solid_main));
        ViewGroup viewGroup = (ViewGroup) getWindow().getDecorView();
        popupWindow.showAtLocation(viewGroup.getChildAt(0), Gravity.NO_GRAVITY, DensityUtil.dip2px(this, 32), height);
        popAdapter.setListenter(new PopAdapter.ClickListenter() {
            @Override
            public void click(int position) {
                switch (type) {
                    case type:
                        etType.setText(list.get(position));
                        break;
                    case colors:
                        etColor.setText(list.get(position));
                        break;
                    case count:
                        etCount.setText(list.get(position));
                        break;
                    case coperator:
                        etOperator.setText(list.get(position));
                        break;

                    default:
                        break;


                }
                if (popupWindow.isShowing()) {
                    popupWindow.dismiss();
                }
            }
        });
    }


    private void out() {

        if (StringUtil.isEmpty(etType.getText().toString())) {
            showMsg("车型不能为空");
            return;
        }

        if (StringUtil.isEmpty(etColor.getText().toString())) {
            showMsg("颜色不能为空");
            return;
        }

        if (StringUtil.isEmpty(etCount.getText().toString())) {
            showMsg("数量不能为空");
            return;
        }

        if (!StringUtil.isNumber(etCount.getText().toString())) {
            showMsg("数量格式不对");
            return;
        }

        int count = Integer.valueOf(etCount.getText().toString());
        if (count > Integer.MAX_VALUE / 2) {
            showMsg("数量太大");
            return;
        }

        if (StringUtil.isEmpty(etOperator.getText().toString())) {
            showMsg("操作人不能为空");
            return;
        }

        Log.i(TAG, "save: " + System.currentTimeMillis());
        Operation operation = new Operation(DbConstant.CORPERATION_TYPE_OUT, System
                .currentTimeMillis(), DbConstant.STATE_CREATE);
        operation.setCarType(etType.getText().toString());
        operation.setCarColor(etColor.getText().toString());
        operation.setCount(Integer.valueOf(etCount.getText().toString()));
        operation.setOperator(etOperator.getText().toString());
        operation.setNote(etNote.getText().toString());

        if (XdaoDbManager.getInstance().addOperation(operation)) {
            showMsg("出库成功");
            updateOperator();
            this.finish();
        } else {
            showMsg("出库失败");
        }
    }

    private void updateOperator() {
        boolean isIn = false;
        for (Operator operator : operators) {
            if (etOperator.getText().toString().equals(operator.getName())) {
                isIn = true;
                break;
            }
        }
        if (!isIn) {
            Operator operator = new Operator(etOperator.getText().toString());
            XdaoDbManager.getInstance().addOperator(operator);
        }
    }


    public enum Types {
        type,
        colors,
        count,
        coperator
    }
}
