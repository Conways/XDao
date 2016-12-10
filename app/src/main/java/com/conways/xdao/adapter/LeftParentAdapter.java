package com.conways.xdao.adapter;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.TextView;

import com.conways.xdao.R;
import com.conways.xdao.db.DbConstant;
import com.conways.xdao.entity.Operation;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by John on 2016/9/22.
 */
public class LeftParentAdapter extends BaseExpandableListAdapter {

    private List<Operation> lists;
    private Context contex;
    private List<Left> lefts;


    public LeftParentAdapter(List<Operation> lists, Context contex) {
        this.lists = lists;
        this.contex = contex;
        init();
    }

    private void init() {
        lefts = new ArrayList<>();

        List<Operation> listIn = new ArrayList<>();
        List<Operation> listOut = new ArrayList<>();
        //分类入和出
        for (Operation operation : lists) {
            if (operation.getOperationType() == DbConstant.CORPERATION_TYPE_OUT) {
                listOut.add(operation);
                continue;
            }
            listIn.add(operation);
        }
        //归位
        Operation tempOP=null;
        for (Operation operation:listIn) {

        }




    }

    @Override
    public int getGroupCount() {
        return lefts.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return lefts.get(i).getList().size();
    }

    @Override
    public Left getGroup(int i) {
        return lefts.get(i);
    }

    @Override
    public Operation getChild(int i, int i1) {
        return lefts.get(i).getList().get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        ParentHold parentHold;
        if (null == view) {
            view = LayoutInflater.from(contex).inflate(R.layout.item_left_parent, viewGroup, false);
            parentHold = new ParentHold();
            parentHold.tvType = (TextView) view.findViewById(R.id.item_left_parent_type);
            view.setTag(parentHold);
        } else {
            parentHold = (ParentHold) view.getTag();
        }

        return null;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        return null;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }

    class ParentHold {

        TextView tvType;

    }

    class ChildHold {
        TextView tvColor;
        TextView tvCount;
    }


    class Left {
        String type;
        List<Operation> list;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public List<Operation> getList() {
            return list;
        }

        public void setList(List<Operation> list) {
            this.list = list;
        }
    }
}
