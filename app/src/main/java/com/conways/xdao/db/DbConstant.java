package com.conways.xdao.db;

/**
 * Created by John on 2016/9/19.
 */
public class DbConstant {
    //数据库版本和名字
    public static final int DB_VERSION = 1;
    public static final String DB_NAME = "xdaodb";
    //操作表TABLE_ACTION和字段名字
    public static final String TABLE_ACTION = "table_action";
    public static final String ACTION_ID = "action_id";
    public static final String ACTION_TYPE = "action_type";
    public static final String ACTION_COUNT = "action_count";
    public static final String ACTION_CAR_TYPE = "action_car_type";
    public static final String ACTION_CAR_COLOR = "action_car_color";
    public static final String ACTION_OPERATION = "action_operation";
    public static final String ACTION_TIME = "action_time";
    public static final String ACTION_NOTE = "action_note";
    public static final String ACTION_STATE = "action_state";
    //条目字段状态类型
    public static final int STATE_SYC = 0x00;
    public static final int STATE_CREATE = 0x01;
    //操作类型
    public static final int CORPERATION_TYPE_IN = 0x00;
    public static final int CORPERATION_TYPE_OUT = 0x01;


    public static final String CREAT_TABLE_ACTION = "CREATE TABLE IF NOT EXISTS " +
            TABLE_ACTION + "(" +
            ACTION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            ACTION_TYPE + " INTEGER," +
            ACTION_COUNT + " INTEGER," +
            ACTION_CAR_TYPE + " TEXT," +
            ACTION_CAR_COLOR + " TEXT," +
            ACTION_OPERATION + " TEXT," +
            ACTION_TIME + " TEXT," +
            ACTION_NOTE + " TEXT," +
            ACTION_STATE + " INTEGER)";

    //操作表TAB_TYPE和字段名字
    public static final String TAB_CAR_TYPE = "tab_car_type";

    public static final String CAR_TYPE_ID = "car_type_id";
    public static final String CAR_TYPE_TYPE = "car_type_type";
    public static final String CAR_TYPE_COLORS = "car_type_colors";

    public static final String CREAT_TAB_CAR_TYPE = "CREATE TABLE IF NOT EXISTS " +
            TAB_CAR_TYPE + "(" +
            CAR_TYPE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            CAR_TYPE_TYPE + " TEXT," +
            CAR_TYPE_COLORS + " TEXT)";


    //操作表TAB_TYPE和字段名字

    public static final String TAB_CORPERATOR = "tab_corperator";

    public static final String CORPERATOR_ID = "corperator_id";
    public static final String CORPERATOR_NAME = "corperator_name";


    public static final String CREAT_TAB_CORPERATOR = "CREATE TABLE IF NOT EXISTS " +
            TAB_CORPERATOR + "(" +
            CORPERATOR_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            CORPERATOR_NAME + " TEXT)";

}
