package com.conways.xdao.Activity;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.conways.xdao.R;
import com.conways.xdao.fragment.ElectrombileFragment;
import com.conways.xdao.fragment.PartsFragment;

public class MainActivity extends FragmentActivity implements ElectrombileFragment.OnFragmentInteractionListener, View.OnClickListener,PartsFragment.OnFragmentInteractionListener {
    private static final String CURRENT_TAB = "current_tab";
    private static final String[] TAGS = {"electric", "parts"};
    private static final int ID_MENU_ELECTRIC = 0x001;
    private static final int ID_MENU_PARTS = 0x002;

    private LinearLayout llElectric;
    private LinearLayout llParts;
    private ImageView ivElectric;
    private ImageView ivparts;
    private TextView tvElectric;
    private TextView tvParts;
    private ElectrombileFragment electrombileFragment;
    private PartsFragment partsFragment;


    private FragmentManager fragmentManager;
    private int currentTab = ID_MENU_ELECTRIC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        restoreFragment(savedInstanceState);
        setSlectedTab(ID_MENU_ELECTRIC);
    }

    private void init() {
        fragmentManager = getSupportFragmentManager();
        llElectric=(LinearLayout)this.findViewById(R.id.main_activity_electrombile);
        llParts=(LinearLayout)this.findViewById(R.id.main_activity_parts);
        llElectric.setOnClickListener(this);
        llParts.setOnClickListener(this);
        tvElectric = (TextView) this.findViewById(R.id.main_activity_electrombile_text);
        tvParts = (TextView) this.findViewById(R.id.main_activity_parts_text);
        ivElectric=(ImageView)this.findViewById(R.id.main_activity_electrombile_icon);
        ivparts=(ImageView)this.findViewById(R.id.main_activity_parts_icon);
    }


    private void restoreFragment(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            electrombileFragment = (ElectrombileFragment) fragmentManager.findFragmentByTag(TAGS[0]);
            partsFragment = (PartsFragment) fragmentManager.findFragmentByTag(TAGS[1]);
            int current = savedInstanceState.getInt(CURRENT_TAB);
            setSlectedTab(current);
        } else {
            setSlectedTab(ID_MENU_ELECTRIC);
        }
    }

    private void setSlectedTab(int id) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        resetAll(fragmentTransaction);
        currentTab = id;

        switch (id) {
            case ID_MENU_ELECTRIC:
                tvElectric.setTextColor(0xff67C19B);
                ivElectric.setImageResource(R.drawable.electrombile_checked);
                if (electrombileFragment == null) {
                    electrombileFragment = ElectrombileFragment.newInstance();
                    fragmentTransaction.add(R.id.main_fragment_hold, electrombileFragment, TAGS[0]);
                } else {
                    fragmentTransaction.show(electrombileFragment);
                }
                break;
            case ID_MENU_PARTS:
                tvParts.setTextColor(0xff67C19B);
                ivparts.setImageResource(R.drawable.parts_checked);
                if (partsFragment == null) {
                    partsFragment = PartsFragment.newInstance();
                    fragmentTransaction.add(R.id.main_fragment_hold, partsFragment, TAGS[1]);
                } else {
                    fragmentTransaction.show(partsFragment);
                }
                break;
            default:
                break;
        }

        fragmentTransaction.commit();

    }

    private void resetAll(FragmentTransaction fragmentTransaction) {
        tvElectric.setTextColor(0xff999999);
        tvParts.setTextColor(0xff999999);
        ivparts.setImageResource(R.drawable.parts_normal);
        ivElectric.setImageResource(R.drawable.electrombile_normal);


        if (electrombileFragment != null) {
            fragmentTransaction.hide(electrombileFragment);
        }

        if (partsFragment != null) {
            fragmentTransaction.hide(partsFragment);
        }

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.main_activity_electrombile:
                setSlectedTab(ID_MENU_ELECTRIC);
                break;
            case R.id.main_activity_parts:
                setSlectedTab(ID_MENU_PARTS);
                break;
            default:
                break;
        }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(CURRENT_TAB, currentTab);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
