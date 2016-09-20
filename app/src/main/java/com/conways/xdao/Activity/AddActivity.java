package com.conways.xdao.Activity;

import android.os.Bundle;
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
import com.conways.xdao.db.DbConstant;
import com.conways.xdao.db.XdaoDbManager;
import com.conways.xdao.entity.Color;
import com.conways.xdao.entity.Operation;
import com.conways.xdao.entity.Type;
import com.conways.xdao.utils.DensityUtil;
import com.conways.xdao.utils.StringUtil;

import java.util.ArrayList;
import java.util.List;


public class AddActivity extends BaseActivity implements View.OnClickListener {

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
    private Button btSave;


    private List<Type> types;
    private List<Operation> operations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        initTitle();
        init();
        initData();
    }

    private void initTitle() {
        tvTitle = (TextView) this.findViewById(R.id.title_title);
        tvTitle.setText("添加库存");
        tvBack = (TextView) this.findViewById(R.id.title_back);
        tvBack.setText("取消");
        tvBack.setOnClickListener(this);

    }

    private void init() {
        etType = (EditText) this.findViewById(R.id.activity_add_car_type);
        ivTypeMore = (ImageView) this.findViewById(R.id.activity_add_car_type_more);
        ivTypeMore.setOnClickListener(this);
        etColor = (EditText) this.findViewById(R.id.activity_add_color);
        ivColorMore = (ImageView) this.findViewById(R.id.activity_add_color_more);
        ivColorMore.setOnClickListener(this);
        etCount = (EditText) this.findViewById(R.id.activity_add_count);
        ivCountMore = (ImageView) this.findViewById(R.id.activity_add_count_more);
        ivCountMore.setOnClickListener(this);
        etOperator = (EditText) this.findViewById(R.id.activity_add_operator);
        ivOpratorMore = (ImageView) this.findViewById(R.id.activity_add_operator_more);
        ivOpratorMore.setOnClickListener(this);
        btSave = (Button) this.findViewById(R.id.activity_add_save);
        btSave.setOnClickListener(this);
    }


    private void initData() {
        types = XdaoDbManager.getInstance().getTypes();
        operations = XdaoDbManager.getInstance().getOperations();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.title_back:
                this.finish();
                break;
            case R.id.activity_add_car_type_more:
                showPop(Types.type);
                break;
            case R.id.activity_add_color_more:
                showPop(Types.colors);
                break;
            case R.id.activity_add_count_more:
                showPop(Types.count);
                break;
            case R.id.activity_add_operator_more:
                showPop(Types.coperator);
                break;
            case R.id.activity_add_save:
                save();
                break;
            default:
                break;


        }
    }

    private void showPop(Types type) {

        View contentView = LayoutInflater.from(this).inflate(
                R.layout.pop_list, null);

        PopupWindow popupWindow = new PopupWindow(contentView,
                DensityUtil.getScreenWith(this) - DensityUtil.dip2px(this, 64), DensityUtil.dip2px
                (this, 108), true);
        popupWindow.setTouchable(true);
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(getResources().getDrawable(R.drawable.rect_solid_main));
        ViewGroup viewGroup = (ViewGroup) getWindow().getDecorView();
        int height = 0;
        switch (type) {
            case type:
                height = DensityUtil.dip2px(this, 104) + DensityUtil.getStatusBarHeight(this);
                break;
            case colors:
                height = DensityUtil.dip2px(this, 160) + DensityUtil.getStatusBarHeight(this);
                break;
            case count:
                height = DensityUtil.dip2px(this, 216) + DensityUtil.getStatusBarHeight(this);
                break;
            case coperator:
                height = DensityUtil.dip2px(this, 272) + DensityUtil.getStatusBarHeight(this);
                break;

            default:
                break;


        }
        popupWindow.showAtLocation(viewGroup.getChildAt(0), Gravity.NO_GRAVITY, DensityUtil.dip2px
                (this, 32), height);


    }


    private void save() {

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


        Operation operation = new Operation(DbConstant.CORPERATION_TYPE_IN, System.currentTimeMillis(), DbConstant.STATE_CREATE);
        operation.setCarType(etType.getText().toString());
        operation.setCarColor(etColor.getText().toString());
        operation.setCount(Integer.valueOf(etCount.getText().toString()));
        operation.setOperator(etOperator.getText().toString());

        if (XdaoDbManager.getInstance().addOperation(operation)) {
            showMsg("添加成功");
            updateData();
            this.finish();
        } else {
            showMsg("添加失败");
        }


    }

    private void updateData() {
        Type type = null;
        boolean hasColor = false;
        for (int i = 0; i < types.size(); i++) {
            if (types.get(i).getCarType().equals(etType.getText().toString().trim())) {
                type = types.get(i);
                List<Color> list = type.getColors();
                for (int j = 0; j < list.size(); j++) {
                    if (list.get(j).getColor().equals(etColor.getText().toString().trim())) {
                        hasColor = true;
                        break;
                    }
                }
                break;
            }

        }
        
        if (null==type){
            type=new Type();
            type.setCarType(etType.getText().toString().trim());
            Color color=new Color(etColor.getText().toString().trim());
            List<Color> list=new ArrayList<Color>();
            list.add(color);
            type.setColors(list);
            XdaoDbManager.getInstance().addType(type);

        }else{
            if(!hasColor){
                List<Color> list=type.getColors();
                list.add(new Color(etColor.getText().toString().trim()));
                type.setColors(list);
                XdaoDbManager.getInstance().modifyType(type);
            }
        }
        
    }


    enum Types {
        type,
        colors,
        count,
        coperator
    }
}
