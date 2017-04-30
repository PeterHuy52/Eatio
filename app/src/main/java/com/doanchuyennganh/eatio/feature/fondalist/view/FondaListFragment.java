package com.doanchuyennganh.eatio.feature.fondalist.view;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.doanchuyennganh.eatio.R;
import com.doanchuyennganh.eatio.data.model.FondaModel;
import com.doanchuyennganh.eatio.feature.base.impl.MainFragment;
import com.doanchuyennganh.eatio.feature.fondalist.presenter.FondaListPresenter;
import com.doanchuyennganh.eatio.feature.fondalist.presenter.FondaListPresenterImpl;
import com.doanchuyennganh.eatio.ui.listener.RecycleViewItemClickListener;
import com.doanchuyennganh.eatio.ui.adapter.FondaAdapter;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;

/**
 * Created by Nguyen Tan Luan on 4/22/2017.
 */
@EFragment(R.layout.fragment_fonda_list)
public class FondaListFragment extends MainFragment<FondaListPresenter> implements FondaListView, RecycleViewItemClickListener{
    @ViewById
    RecyclerView recyclerView;

    @Bean
    FondaAdapter mAdapter;
    
    @Bean
    void initBean(FondaListPresenterImpl presenter){
        this.mPresenter=presenter;
    }        
    ArrayList<FondaModel> list=new ArrayList<>();
    
    @AfterViews
    void initView(){
        mPresenter.setView(this);
        mPresenter.setNavigator(this);
        setupData();
        //mAdapter.setItems(list);
        mAdapter.setListener(this);
        RecyclerView.LayoutManager mLayoutManager=new LinearLayoutManager(this.getContext());
        mLayoutManager.setAutoMeasureEnabled(false);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
    }

    private void setupData() {
        mPresenter.getListFonda("");
        /*for(int i=0;i<6;i++){
            FondaModel fonda=new FondaModel();
            fonda.setName("Hot And Cold "+i);
            LocationModel location=new LocationModel();
            location.city="3 "+i+"Han Thuyen, Quan 1, HCM";
            CommentModel comment=new CommentModel();
            comment.content="Cung dc do";
            fonda.setLocation(location);
            //fonda.setCommentModels(comment);
            list.add(fonda);
        }*/
    }

    @Override
    public void onItemClick(View view, int position) {
        //FondaDetailActivity_.intent(getContext()).start();
    }

    @Override
    public void onItemLongClick(View view, int position) {

    }

    @Override
    public void showListFonda(ArrayList<FondaModel> data) {
        mAdapter.setItems(data);
        mAdapter.notifyDataSetChanged();
    }
}
