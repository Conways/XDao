package com.conways.xdao.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Parcel;

import com.conways.xdao.entity.Color;
import com.conways.xdao.entity.Operation;
import com.conways.xdao.entity.Type;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by John on 2016/9/19.
 */
public class XdaoDbManager {
    private static XdaoDbManager ourInstance = new XdaoDbManager();
    private XdaoDbHelper xdaoDbManager;
    private int inOfyesToday;
    private int inAll;
    private int outALL;
    private int left;

    public static XdaoDbManager getInstance() {
        return ourInstance;
    }

    private XdaoDbManager() {

    }

    public void init(Context context) {
        xdaoDbManager = new XdaoDbHelper(context, DbConstant.DB_NAME, null, DbConstant.DB_VERSION);
    }

    public boolean addOperation(Operation operation) {
        if (xdaoDbManager == null)
            return false;
        SQLiteDatabase sqLiteDatabase = xdaoDbManager.getWritableDatabase();
        if (sqLiteDatabase == null) {
            return false;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put(DbConstant.ACTION_TYPE, operation.getOperationType());
        contentValues.put(DbConstant.ACTION_COUNT, operation.getCount());
        contentValues.put(DbConstant.ACTION_TIME, operation.getTime()+"");
        contentValues.put(DbConstant.ACTION_CAR_TYPE, operation.getCarType());
        contentValues.put(DbConstant.ACTION_CAR_COLOR, operation.getCarColor());
        contentValues.put(DbConstant.ACTION_OPERATION, operation.getOperator());
        contentValues.put(DbConstant.ACTION_NOTE, operation.getNote());
        contentValues.put(DbConstant.ACTION_STATE, operation.getState());
        return sqLiteDatabase.insert(DbConstant.TABLE_ACTION, DbConstant.ACTION_ID, contentValues) != -1;
    }

    public List<Operation> getIn() {
        return null;
    }

    public List<Operation> getOperations() {
        SQLiteDatabase sqLiteDatabase = xdaoDbManager.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.query(DbConstant.TABLE_ACTION, null, null, null, null, null, null);
        return getOperation(cursor);
    }


    public List<Operation> getOut() {
        return null;
    }

    public int getLeft() {
        return 0;
    }

    private List<Operation> getOperation(Cursor cursor) {
        List<Operation> list = new ArrayList<Operation>();
        if (cursor != null) {
            try {
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    int id = cursor.getInt(cursor.getColumnIndex(DbConstant.ACTION_ID));
                    int opType = cursor.getInt(cursor.getColumnIndex(DbConstant.ACTION_TYPE));
                    int count = cursor.getInt(cursor.getColumnIndex(DbConstant.ACTION_COUNT));
                    long time = cursor.getLong(cursor.getColumnIndex(DbConstant.ACTION_TIME));
                    String carType = cursor.getString(cursor.getColumnIndex(DbConstant.ACTION_CAR_TYPE));
                    String color = cursor.getString(cursor.getColumnIndex(DbConstant.ACTION_CAR_COLOR));
                    String operator = cursor.getString(cursor.getColumnIndex(DbConstant.ACTION_OPERATION));
                    String note = cursor.getString(cursor.getColumnIndex(DbConstant.ACTION_NOTE));
                    int state = cursor.getInt(cursor.getColumnIndex(DbConstant.ACTION_STATE));
                    Operation operation = new Operation(id, opType, count, time, carType, color, operator, note, state);
                    list.add(operation);
                    cursor.moveToNext();
                }
            } finally {
                cursor.close();
            }
        }
        return list;
    }


    public boolean addType(Type type) {
        SQLiteDatabase sqLiteDatabase = xdaoDbManager.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DbConstant.CAR_TYPE_TYPE, type.getCarType());
        contentValues.put(DbConstant.CAR_TYPE_COLORS, new Gson().toJson(type.getColors()));
        return sqLiteDatabase.insert(DbConstant.TAB_CAR_TYPE, DbConstant.CAR_TYPE_ID, contentValues) != -1;
    }

    public List<Type> getTypes() {
        SQLiteDatabase sqLiteDatabase = xdaoDbManager.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query(DbConstant.TAB_CAR_TYPE, null, null, null, null, null, null);
        return getTypes(cursor);
    }


    public boolean modifyType(Type type) {
        SQLiteDatabase sqLiteDatabase = xdaoDbManager.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DbConstant.CAR_TYPE_COLORS, new Gson().toJson(type.getColors()));
        return sqLiteDatabase.update(DbConstant.TAB_CAR_TYPE, contentValues, DbConstant
                        .CAR_TYPE_ID + "=?",
                new String[]{type.getId() + ""}) != -1;
    }

    private List<Type> getTypes(Cursor cursor) {
        List<Type> types = new ArrayList<Type>();

        if (cursor != null) {
            try {
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    int id = cursor.getInt(cursor.getColumnIndex(DbConstant.CAR_TYPE_ID));
                    String type = cursor.getString(cursor.getColumnIndex(DbConstant.CAR_TYPE_TYPE));
                    String color = cursor.getString(cursor.getColumnIndex(DbConstant
                            .CAR_TYPE_COLORS));
                    Gson gson = new Gson();
                    List<Color> colors = gson.fromJson(color, new TypeToken<List<Color>>() {
                    }.getType());
                    Type tp = new Type();
                    tp.setId(id);
                    tp.setCarType(type);
                    tp.setColors(colors);
                    types.add(tp);
                    cursor.moveToNext();
                }
            } finally {
                cursor.close();
            }
        }

        return types;
    }


}
