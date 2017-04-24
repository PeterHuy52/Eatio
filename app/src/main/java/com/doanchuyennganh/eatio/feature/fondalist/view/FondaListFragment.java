package com.doanchuyennganh.eatio.feature.fondalist.view;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.doanchuyennganh.eatio.R;
import com.doanchuyennganh.eatio.data.model.CommentModel;
import com.doanchuyennganh.eatio.data.model.FondaModel;
import com.doanchuyennganh.eatio.data.model.LocationModel;
import com.doanchuyennganh.eatio.feature.base.impl.MainFragment;
import com.doanchuyennganh.eatio.feature.createfonda.presenter.FondaPresenter;
import com.doanchuyennganh.eatio.feature.fondadetail.view.FondaDetailActivity_;
import com.doanchuyennganh.eatio.feature.leftmenu.view.RecycleViewItemClickListener;
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
public class FondaListFragment extends MainFragment<FondaPresenter> implements FondaListView, RecycleViewItemClickListener{
    @ViewById
    RecyclerView recyclerView;

    @Bean
    FondaAdapter adapter;

    ArrayList<FondaModel> list=new ArrayList<>();

    @AfterViews
    void initView(){
        setupData();
        adapter.setItems(list);
        adapter.setListener(this);
        RecyclerView.LayoutManager mLayoutManager=new LinearLayoutManager(this.getContext());
        mLayoutManager.setAutoMeasureEnabled(false);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    private void setupData() {

        for(int i=0;i<6;i++){
            FondaModel fonda=new FondaModel();
            fonda.setName("Hot And Cold "+i);
            LocationModel location=new LocationModel();
            location.city="3 "+i+"Han Thuyen, Quan 1, HCM";
            CommentModel comment=new CommentModel();
            comment.content="Cung dc do";
            fonda.setLocationModel(location);
            fonda.setCommentModel(comment);
            list.add(fonda);
        }
    }

    @Override
    public void onItemClick(View view, int position) {
        FondaDetailActivity_.intent(getContext()).start();
    }

    @Override
    public void onItemLongClick(View view, int position) {

    }
}
