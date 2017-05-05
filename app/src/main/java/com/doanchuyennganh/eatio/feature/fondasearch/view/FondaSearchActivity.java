package com.doanchuyennganh.eatio.feature.fondasearch.view;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.doanchuyennganh.eatio.R;
import com.doanchuyennganh.eatio.data.model.FondaGroupModel;
import com.doanchuyennganh.eatio.data.model.FondaModel;
import com.doanchuyennganh.eatio.data.model.LocationModel;
import com.doanchuyennganh.eatio.feature.base.Navigator;
import com.doanchuyennganh.eatio.feature.base.impl.MainActivity;
import com.doanchuyennganh.eatio.feature.fondadetail.view.FondaDetailActivity_;
import com.doanchuyennganh.eatio.feature.fondasearch.presenter.FondaSearchPresenter;
import com.doanchuyennganh.eatio.feature.fondasearch.presenter.FondaSearchPresenterImpl;
import com.doanchuyennganh.eatio.ui.listener.RecycleViewItemClickListener;
import com.doanchuyennganh.eatio.ui.adapter.FondaSearchAdapter;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.OptionsMenuItem;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;

/**
 * Created by Nguyen Tan Luan on 4/24/2017.
 */
@EActivity(R.layout.activity_fonda_search)
@OptionsMenu(R.menu.menu_main)
public class FondaSearchActivity extends MainActivity<FondaSearchPresenter> implements FondaSearchView,Navigator, RecycleViewItemClickListener {

    @ViewById(R.id.recyclerView)
    RecyclerView mRcvFondaSearch;

    @OptionsMenuItem
    MenuItem menuSearch;

    SearchView mSearchView;

    @Bean
    FondaSearchAdapter adapter;

    ArrayList<FondaModel> fakeDataList;

    @Bean
    void initBean(FondaSearchPresenterImpl presenter){
        this.mPresenter=presenter;
    }

    @AfterViews
    void initView(){
        mPresenter.setView(this);
        setupToolbar();
        //setFakeData();
        //adapter.setItems(fakeDataList);
        adapter.setOnClickListener(this);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(this);
        mRcvFondaSearch.setHasFixedSize(true);
        mRcvFondaSearch.setLayoutManager(layoutManager);
        mRcvFondaSearch.setItemAnimator(new DefaultItemAnimator());
        mRcvFondaSearch.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuItem item=menu.findItem(R.id.menuSearch);
        mSearchView=new SearchView(this);
        mSearchView=(SearchView)item.getActionView();
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                mPresenter.getListFondaByName(query);
                //adapter.getFilter().filter(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                mPresenter.getListFondaByName(newText);
                //adapter.getFilter().filter(newText);
                return true;
            }
        });
        return true;

    }

    public void setFakeData(){
        fakeDataList=new ArrayList<>();
        for(int i=0;i<10;i++){
            FondaModel fondaModel=new FondaModel();
            fondaModel.setName("E"+i+"atio");
            LocationModel locationModel=new LocationModel();
            locationModel.setCity("KTX khu B" +i);
            fondaModel.setLocation(locationModel);
            FondaGroupModel fondaGroupModel=new FondaGroupModel();
            fondaGroupModel.setName("Buffe/Dessert" +i);
            fondaModel.setFondaGroup(fondaGroupModel);
            fakeDataList.add(fondaModel);
        }
    }

    public void setupToolbar(){
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //searchFonda();

    }


    @Override
    public void onItemClick(View view, int position) {
        FondaDetailActivity_.intent(this).start();
        Toast.makeText(this, "position= "+position, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemLongClick(View view, int position) {

    }

    @Override
    public void showListFonda(ArrayList<FondaModel> fondaModels) {
        adapter.setItems(fondaModels);
        adapter.notifyDataSetChanged();
    }
}
