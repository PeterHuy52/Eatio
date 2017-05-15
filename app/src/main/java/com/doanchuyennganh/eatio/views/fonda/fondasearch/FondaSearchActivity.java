package com.doanchuyennganh.eatio.views.fonda.fondasearch;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.doanchuyennganh.eatio.R;
import com.doanchuyennganh.eatio.api.responses.Paging;
import com.doanchuyennganh.eatio.entity.Fonda;
import com.doanchuyennganh.eatio.presensters.fonda.fondalist.FondaListPresenter;
import com.doanchuyennganh.eatio.presensters.fonda.fondalist.FondaListPresenterImpl;
import com.doanchuyennganh.eatio.views.BaseActivity;
import com.doanchuyennganh.eatio.views.fonda.FondaDetailActivity;
import com.doanchuyennganh.eatio.views.fonda.adapter.SearchFondaAdapter;
import com.doanchuyennganh.eatio.views.fonda.fondalist.EndlessRecyclerOnScrollListener;
import com.doanchuyennganh.eatio.views.fonda.fondalist.FondaListView;
import com.doanchuyennganh.eatio.views.fonda.fondalist.OnClickListener;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;

/**
 * Created by Nguyen Tan Luan on 5/15/2017.
 */
@EActivity(R.layout.activity_search)
@OptionsMenu(R.menu.menu_main)
public class FondaSearchActivity extends BaseActivity implements FondaListView, SwipeRefreshLayout.OnRefreshListener, OnClickListener {
    @ViewById
    RecyclerView recyclerView;

    @ViewById
    ProgressBar progressBar;

    @ViewById
    SwipeRefreshLayout swipeRefreshLayout;

    @Bean
    SearchFondaAdapter mFondaAdapter;

    @Bean(FondaListPresenterImpl.class)
    FondaListPresenter mFondaListPresenter;

    SearchView mSearchView;

    private final int FIRST_PAGE = 1;

    ArrayList<Fonda> mFondaList;

    private EndlessRecyclerOnScrollListener mLoadMoreRecylerView;

    private int mLastPage;

    private String mQuery = "";

    public static void run(Context context){
        FondaSearchActivity_.intent(context).start();
    }

    @AfterViews
    void afterViews() {
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mFondaList = new ArrayList<>();
        mFondaListPresenter.setView(this);
        mFondaAdapter.setOnClickListener(this);
        mFondaListPresenter.getFondas(FIRST_PAGE);
        swipeRefreshLayout.setOnRefreshListener(this);
        setupRecylerView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuItem item_search = menu.findItem(R.id.menu_search);
        MenuItem item_edit = menu.findItem(R.id.menu_edit);
        item_edit.setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER);
        mSearchView = new SearchView(this);
        mSearchView = (SearchView) item_search.getActionView();
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                mQuery = query;
                mFondaList.clear();
                if (TextUtils.isEmpty(mQuery)) {
                    mFondaListPresenter.getFondas(FIRST_PAGE);
                } else {
                    mFondaListPresenter.getFondasByName(mQuery, FIRST_PAGE);
                    //mFondaListPresenter.getFondasByAddress(mQuery, FIRST_PAGE);
                }
                mLoadMoreRecylerView.onReset();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                mQuery = newText;
                mFondaList.clear();
                if (TextUtils.isEmpty(mQuery)) {
                    mFondaListPresenter.getFondas(FIRST_PAGE);
                    mLoadMoreRecylerView.onReset();
                }/*else {
                    mFondaListPresenter.getFondasByName(mQuery, FIRST_PAGE);
                    //mFondaListPresenter.getFondasByAddress(mQuery, FIRST_PAGE);
                }*/

                return true;
            }
        });
        return true;
    }

    @OptionsItem(android.R.id.home)
    void buttonHomeClick() {
        this.finish();
    }

    private void setupRecylerView() {
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mFondaAdapter);
        setOnLoadMore(mLayoutManager);
        recyclerView.setOnScrollListener(mLoadMoreRecylerView);
    }

    @Override
    public void updateFondaListView(Paging<Fonda> paging) {
        mLastPage = paging.getLastPage();
        ArrayList<Fonda> fondas = paging.getData();
        if (swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(false);
        }
        if (progressBar.isShown()) {
            progressBar.setVisibility(View.GONE);
        }

        mFondaList.addAll(fondas);
        mFondaAdapter.setItems(mFondaList);
        mFondaAdapter.notifyDataSetChanged();
    }

    @Override
    public void onRefresh() {
        mFondaList.clear();
        mLoadMoreRecylerView.onReset();
        swipeRefreshLayout.setRefreshing(true);
        mFondaListPresenter.getFondasByName(mQuery, FIRST_PAGE);
        //mFondaListPresenter.getFondasByAddress(mQuery, FIRST_PAGE);
    }

    private void setOnLoadMore(LinearLayoutManager linearLayoutManager) {
        mLoadMoreRecylerView = new EndlessRecyclerOnScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int current_page) {
                if (current_page <= mLastPage) {
                    progressBar.setVisibility(View.VISIBLE);
                    if (TextUtils.isEmpty(mQuery)) {
                        mFondaListPresenter.getFondas(current_page);
                    } else {
                        mFondaListPresenter.getFondasByName(mQuery, current_page);
                        //mFondaListPresenter.getFondasByAddress(mQuery, current_page);
                    }
                }
            }
        };
    }

    @Override
    public void onItemClick(View view, int id) {
        FondaDetailActivity.run(this, id);
    }

    @Override
    public void onItemClick(View view, String url) {

    }
}
