package com.conways.xdao.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.conways.xdao.R;

/**
 * Created by John on 2016/9/21.
 */
public class PopHolder extends RecyclerView.ViewHolder{

    public TextView tvValue;

    public PopHolder(View itemView) {
        super(itemView);
        tvValue=(TextView)itemView.findViewById(R.id.item_pop_list_value);

    }


}
