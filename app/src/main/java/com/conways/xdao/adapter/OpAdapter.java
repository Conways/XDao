package com.conways.xdao.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.conways.xdao.R;
import com.conways.xdao.db.DbConstant;
import com.conways.xdao.db.Operation;
import com.conways.xdao.holder.OpHolder;
import com.conways.xdao.utils.TimeUtil;

import java.util.List;

/**
 * Created by John on 2016/9/19.
 */
public class OpAdapter extends RecyclerView.Adapter<OpHolder> {

    private Context context;
    private List<Operation> list;

    public OpAdapter(Context context, List<Operation> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public OpHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_operations, parent, false);
        OpHolder opHolder = new OpHolder(view);
        return opHolder;
    }

    @Override
    public void onBindViewHolder(OpHolder holder, int position) {
        Operation operation = list.get(position);
        holder.tvCount.setText(operation.getCount() + "");
        holder.tvOperator.setText(operation.getOperator());
        holder.tvTime.setText(TimeUtil.getDataOfTypeOne(operation.getTime()));
        holder.ivType.setImageResource(operation.getOperationType() == DbConstant.TYPE_IN ? R
                .drawable.in : R.drawable.out);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
