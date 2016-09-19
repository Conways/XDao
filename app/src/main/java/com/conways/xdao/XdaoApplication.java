package com.conways.xdao;

import android.app.Application;

import com.conways.xdao.db.XdaoDbManager;

/**
 * Created by John on 2016/9/19.
 */
public class XdaoApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        init();
    }


    private void init() {
        initDB();
    }

    private void initDB() {
        XdaoDbManager.getInstance().init(this);
    }
}
