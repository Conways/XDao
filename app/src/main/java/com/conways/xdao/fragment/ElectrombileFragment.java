package com.conways.xdao.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.conways.xdao.Activity.AddActivity;
import com.conways.xdao.common.DividerItemDecoration;
import com.conways.xdao.R;
import com.conways.xdao.adapter.OpAdapter;
import com.conways.xdao.entity.Operation;
import com.conways.xdao.db.XdaoDbManager;

import java.util.ArrayList;
import java.util.List;

public class ElectrombileFragment extends BaseFragment implements View.OnClickListener {


    private OnFragmentInteractionListener mListener;


    public static ElectrombileFragment newInstance() {
        ElectrombileFragment fragment = new ElectrombileFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    private TextView tvYesterday;
    private TextView tvleft;
    private TextView tvAdd;
    private TextView tvOut;

    private RecyclerView recyclerView;
    private List<Operation> list;
    private OpAdapter opAdapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_electrombile, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
    }

    private void init() {
        tvYesterday = (TextView) getActivity().findViewById(R.id.fragment_electrombile_yesterday_out);
        tvleft = (TextView) getActivity().findViewById(R.id.fragment_electrombile_left);
        tvAdd = (TextView) getActivity().findViewById(R.id.fragment_electrombile_add);
        tvOut = (TextView) getActivity().findViewById(R.id.fragment_electrombile_out);
        tvYesterday.setOnClickListener(this);
        tvleft.setOnClickListener(this);
        tvAdd.setOnClickListener(this);
        tvOut.setOnClickListener(this);
        recyclerView = (RecyclerView) getActivity().findViewById(R.id.fragment_electrombile_action_list);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),
                DividerItemDecoration.VERTICAL_LIST,R.drawable.item_line));
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    public void onResume() {
        super.onResume();
        if (!isHidden()){
            update();
        }
    }

    private void update(){
        if (list==null){
            list=new ArrayList<Operation>();
        }
        list.clear();
        list.addAll(XdaoDbManager.getInstance().getOperations());
        if (opAdapter==null){
            opAdapter=new OpAdapter(getActivity(),list);
            recyclerView.setAdapter(opAdapter);
        }else{
            opAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fragment_electrombile_add:
                toTargetActivity(AddActivity.class);
                break;

            default:
                break;
        }
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
