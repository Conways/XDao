package com.conways.xdao.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.conways.xdao.R;

/**
 * Created by John on 2016/9/19.
 */
public class OpHolder extends RecyclerView.ViewHolder {
    public TextView tvCount;
    public TextView tvTime;
    public TextView tvOperator;
    public ImageView ivType;

    public OpHolder(View itemView) {
        super(itemView);
        tvCount = (TextView) itemView.findViewById(R.id.item_operations_count);
        tvTime = (TextView) itemView.findViewById(R.id.item_operations_time);
        tvOperator = (TextView) itemView.findViewById(R.id.item_operations_operator);
        ivType = (ImageView) itemView.findViewById(R.id.item_operations_type);
    }
}
