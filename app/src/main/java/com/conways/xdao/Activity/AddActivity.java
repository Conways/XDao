package com.conways.xdao.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.conways.xdao.R;
import com.conways.xdao.db.Operation;
import com.conways.xdao.db.XdaoDbManager;

public class AddActivity extends BaseActivity {
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        button=(Button)this.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Operation operation=new Operation();
                operation.setOperator("user1");
                operation.setTime(System.currentTimeMillis());
                operation.setCarType("a");
                operation.setCount(20);
                if (XdaoDbManager.getInstance().add(operation)){
                    showMsg("suc");
                }else{
                    showMsg("fail");
                }

            }
        });
    }
}
