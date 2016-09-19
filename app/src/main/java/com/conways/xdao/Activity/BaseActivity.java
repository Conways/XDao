package com.conways.xdao.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

/**
 * Created by John on 2016/9/18.
 */
public class BaseActivity extends Activity {

    protected String TAG = getClass().getSimpleName();
    private Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected void showMsg(CharSequence msg) {
        if (toast == null) {
            toast = Toast.makeText(this, "", Toast.LENGTH_SHORT);
        }
        toast.setText(msg);
        toast.show();

    }
}
