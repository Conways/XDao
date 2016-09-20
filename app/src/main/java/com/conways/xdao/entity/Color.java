package com.conways.xdao.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by John on 2016/9/20.
 */
public class Color implements Parcelable {


    private String color;

    public Color(String color) {
        this.color = color;
    }

    public Color() {
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.color);
    }

    protected Color(Parcel in) {
        this.color = in.readString();
    }

    public static final Parcelable.Creator<Color> CREATOR = new Parcelable.Creator<Color>() {
        @Override
        public Color createFromParcel(Parcel source) {
            return new Color(source);
        }

        @Override
        public Color[] newArray(int size) {
            return new Color[size];
        }
    };
}
