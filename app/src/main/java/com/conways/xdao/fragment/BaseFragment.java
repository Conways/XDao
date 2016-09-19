package com.conways.xdao.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.conways.xdao.Activity.BaseActivity;

/**
 * Created by John on 2016/9/19.
 */
public class BaseFragment extends Fragment {

    protected String TAG=getClass().getSimpleName();
    private Toast toast;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    protected void toTargetActivity(Class<? extends BaseActivity> c) {
        Intent intent = new Intent();
        intent.setClass(getActivity(), c);
        startActivity(intent);
    }

    protected void showMsg(CharSequence msg){
        if (toast==null){
            toast=Toast.makeText(getActivity(),"",Toast.LENGTH_SHORT);
        }
        toast.setText(msg);
        toast.show();

    }
}
