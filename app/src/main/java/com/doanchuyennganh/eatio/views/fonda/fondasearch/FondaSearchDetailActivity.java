package com.doanchuyennganh.eatio.views.fonda.fondasearch;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.doanchuyennganh.eatio.R;
import com.doanchuyennganh.eatio.application.appcomponent.AppComponent;
import com.doanchuyennganh.eatio.entity.Culinary;
import com.doanchuyennganh.eatio.entity.FondaGroup;
import com.doanchuyennganh.eatio.presensters.fonda.fondalist.FondaListPresenter;
import com.doanchuyennganh.eatio.utils.AppConstants;
import com.doanchuyennganh.eatio.views.base.BaseActivity;
import com.doanchuyennganh.eatio.views.fonda.fondalist.FondaListFragment;
import com.doanchuyennganh.eatio.views.ui.CustomViewInputSpinner;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nguyen Tan Luan on 5/21/2017.
 */
public class FondaSearchDetailActivity extends BaseActivity<FondaListPresenter> implements FondaSearchDetailView, AdapterView.OnItemSelectedListener {

    @ViewById(R.id.spinner_type_search)
    CustomViewInputSpinner spinner;

    public static void run(Context context, int searchkey) {
        /*FondaSearchDetailActivity_.intent(context).extra("type", searchkey).start();*/
    }

    @Extra("type")
    int type;

    /*@Bean(FondaListPresenterImpl.class)
    FondaListPresenter mPresenter;*/

    FondaListFragment mFondaListFragment;

    @AfterViews
    void afterView() {
        mPresenter.setView(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        spinner.setOnItemSelectedListener(this);
    }

    @AfterViews
    void initViews() {
        switch (type) {
            case AppConstants.TypeToSearch.SEARCH_BY_CITY:
                initViewsByCity();
                break;
            case AppConstants.TypeToSearch.SEARCH_BY_CATEGORY:
                initViewsByGroup();
                break;
            case AppConstants.TypeToSearch.SEARCH_BY_SCALE:
                initViewsByScale();
                break;
            case AppConstants.TypeToSearch.SEARCH_BY_SALE_OFF:
                initViewsBySaleOff();
                break;
            case AppConstants.TypeToSearch.SEARCH_BY_CULINARY:
                initViewsByCulinary();
                break;
            default:
                break;
        }

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_search_fonda_detail;
    }

    @Override
    protected void inject(AppComponent appComponent) {
        appComponent.inject(this);
    }

    @OptionsItem(android.R.id.home)
    void homeButtonClick() {
        this.finish();
    }

    private void initViewsBySaleOff() {
        spinner.setVisibility(View.GONE);
        getSupportActionBar().setTitle("Sale Off Fonda");
        getListFondaByType(String.valueOf(1));
    }

    private void initViewsByCulinary() {
        spinner.setAttrs(this.getResources().getDrawable(R.drawable.ic_category), this.getString(R.string.culinary_hint));
        mPresenter.getFondaCulinaryList();
    }

    private void initViewsByScale() {
        spinner.setAttrs(this.getResources().getDrawable(R.drawable.ic_scale), this.getString(R.string.scale_hint));
        String[] options = this.getResources().getStringArray(R.array.scale_option);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, options);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(adapter);
        //String scale = String.valueOf(spinner.getSelectedIndex() + 1);
        //getListFondaByType(scale);
    }

    private void initViewsByGroup() {
        spinner.setAttrs(this.getResources().getDrawable(R.drawable.ic_category), this.getString(R.string.fonda_group_hint));
        mPresenter.getFondaGroupList();
    }

    private void initViewsByCity() {
        spinner.setAttrs(this.getResources().getDrawable(R.drawable.ic_location), this.getString(R.string.city_hint));
        String[] options = this.getResources().getStringArray(R.array.city_option);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, options);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(adapter);
        //String city = spinner.getValue();
        //getListFondaByType(city);
    }

    @Override
    public void updateFondaGroupSpinner(List<FondaGroup> fondaGroups) {
        ArrayList<String> fondasName = new ArrayList<>();
        for (int i = 0; i < fondaGroups.size(); i++) {
            fondasName.add(fondaGroups.get(i).name);
        }
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, fondasName);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(adapter);
        /*String group = spinner.getValue();
        getListFondaByType(group);*/
    }

    @Override
    public void updateCulinarySpinner(List<Culinary> culinaries) {
        ArrayList<String> culinariesName = new ArrayList<>();
        for (int i = 0; i < culinaries.size(); i++) {
            culinariesName.add(culinaries.get(i).name);
        }
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, culinariesName);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(adapter);
        //String name = spinner.getValue();
    }

    private void getListFondaByType(String query) {
        //mFondaListFragment = FondaListFragment_.builder().arg("type", type).arg("value", query).build();
        FragmentManager framentManager = getSupportFragmentManager();
        framentManager.beginTransaction().replace(R.id.layout_search_list, mFondaListFragment).commit();
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        switch (type) {
            case AppConstants.TypeToSearch.SEARCH_BY_CITY:
                String city = spinner.getValue();
                getListFondaByType(city);
                break;
            case AppConstants.TypeToSearch.SEARCH_BY_CATEGORY:
                String group = spinner.getValue();
                getListFondaByType(group);
                break;
            case AppConstants.TypeToSearch.SEARCH_BY_SCALE:
                String scale = String.valueOf(spinner.getSelectedIndex() + 1);
                getListFondaByType(scale);
                break;
            case AppConstants.TypeToSearch.SEARCH_BY_SALE_OFF:
                break;
            case AppConstants.TypeToSearch.SEARCH_BY_CULINARY:
                String culinary = spinner.getValue();
                getListFondaByType(culinary);
                break;
            default:
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
