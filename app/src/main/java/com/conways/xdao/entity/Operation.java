package com.conways.xdao.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Comparator;

/**
 * Created by John on 2016/9/19.
 */
public class Operation implements Parcelable {

    public Operation() {
        super();
    }

    public Operation(int operationType, long time, int state) {
        this.operationType = operationType;
        this.time = time;
        this.state = state;
    }

    public Operation(int id, int operationType, int count, long time, String carType, String carColor, String operator, String note, int state) {
        this.id = id;
        this.operationType = operationType;
        this.count = count;
        this.time = time;
        this.carType = carType;
        this.carColor = carColor;
        this.operator = operator;
        this.note = note;
        this.state = state;
    }

    private int id;
    private int operationType;
    private int count;
    private long time;
    private String carType;
    private String carColor;
    private String operator;
    private String note;
    private int state;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOperationType() {
        return operationType;
    }

    public void setOperationType(int operationType) {
        this.operationType = operationType;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public String getCarColor() {
        return carColor;
    }

    public void setCarColor(String carColor) {
        this.carColor = carColor;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }


    @Override
    public String toString() {
        return "Operation{" +
                "id=" + id +
                ", operationType=" + operationType +
                ", count=" + count +
                ", time=" + time +
                ", carType='" + carType + '\'' +
                ", carColor='" + carColor + '\'' +
                ", operator='" + operator + '\'' +
                ", note='" + note + '\'' +
                ", state=" + state +
                '}';
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeInt(this.operationType);
        dest.writeInt(this.count);
        dest.writeLong(this.time);
        dest.writeString(this.carType);
        dest.writeString(this.carColor);
        dest.writeString(this.operator);
        dest.writeString(this.note);
        dest.writeInt(this.state);
    }

    protected Operation(Parcel in) {
        this.id = in.readInt();
        this.operationType = in.readInt();
        this.count = in.readInt();
        this.time = in.readLong();
        this.carType = in.readString();
        this.carColor = in.readString();
        this.operator = in.readString();
        this.note = in.readString();
        this.state = in.readInt();
    }

    public static final Parcelable.Creator<Operation> CREATOR = new Parcelable.Creator<Operation>() {
        @Override
        public Operation createFromParcel(Parcel source) {
            return new Operation(source);
        }

        @Override
        public Operation[] newArray(int size) {
            return new Operation[size];
        }
    };


    public static class TimeCompare implements Comparator<Operation> {
        @Override
        public int compare(Operation operation, Operation t1) {
            if (t1.getTime() - operation.getTime() > 0) {
                return 1;
            } else if (t1.getTime() - operation.getTime() < 0) {
                return -1;
            } else {
                return 0;
            }
        }
    }
}
