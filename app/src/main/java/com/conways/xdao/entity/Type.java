package com.conways.xdao.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by John on 2016/9/20.
 */
public class Type implements Parcelable {

    private int id;
    private String carType;
    private List<Color> colors;


    public Type() {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public List<Color> getColors() {
        return colors;
    }

    public void setColors(List<Color> colors) {
        this.colors = colors;
    }


    @Override
    public String toString() {
        return "Type{" +
                "id=" + id +
                ", carType='" + carType + '\'' +
                ", colors=" + colors +
                '}';
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.carType);
        dest.writeTypedList(this.colors);
    }

    protected Type(Parcel in) {
        this.id = in.readInt();
        this.carType = in.readString();
        this.colors = in.createTypedArrayList(Color.CREATOR);
    }

    public static final Parcelable.Creator<Type> CREATOR = new Parcelable.Creator<Type>() {
        @Override
        public Type createFromParcel(Parcel source) {
            return new Type(source);
        }

        @Override
        public Type[] newArray(int size) {
            return new Type[size];
        }
    };
}
