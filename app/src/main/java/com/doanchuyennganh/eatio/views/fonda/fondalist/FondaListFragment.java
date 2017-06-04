package com.doanchuyennganh.eatio.views.fonda.fondalist;

import android.location.Location;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.doanchuyennganh.eatio.R;
import com.doanchuyennganh.eatio.api.responses.Paging;
import com.doanchuyennganh.eatio.entity.Fonda;
import com.doanchuyennganh.eatio.presensters.fonda.fondalist.FondaListPresenter;
import com.doanchuyennganh.eatio.presensters.fonda.fondalist.FondaListPresenterImpl;
import com.doanchuyennganh.eatio.presensters.map.LocationPresenter;
import com.doanchuyennganh.eatio.presensters.map.LocationPresenterImpl;
import com.doanchuyennganh.eatio.utils.AppConstants;
import com.doanchuyennganh.eatio.views.fonda.FondaDetailActivity;
import com.doanchuyennganh.eatio.views.fonda.adapter.FondaAdapter;
import com.doanchuyennganh.eatio.views.mapactivity.LocationView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;

/**
 * Created by Nguyen Tan Luan on 5/13/2017.
 */
@EFragment(R.layout.fragment_fonda_list)
public class FondaListFragment extends Fragment implements FondaListView, SwipeRefreshLayout.OnRefreshListener,
        OnClickListener, LocationView {

    @ViewById
    RecyclerView recyclerView;

    @ViewById
    ProgressBar progressBar;

    @ViewById
    SwipeRefreshLayout swipeRefreshLayout;

    @ViewById(R.id.ly_error)
    LinearLayout lyEmptyList;

    @Bean
    FondaAdapter mFondaAdapter;

    @Bean(FondaListPresenterImpl.class)
    FondaListPresenter mFondaListPresenter;

    @FragmentArg("type")
    int type;

    @FragmentArg("value")
    String query;

    private final int FIRST_PAGE = 1;

    ArrayList<Fonda> mFondaList = new ArrayList<>();
    ArrayList<String> mDistanceList = new ArrayList<>();

    LocationPresenter mLocationPresenter;

    private EndlessRecyclerOnScrollListener mLoadMoreRecylerView;

    private Location mCurrentLocation;
    private int mLastPage;


    @AfterViews
    void afterViews() {

        mLocationPresenter = new LocationPresenterImpl(this);
        mLocationPresenter.getLocation();

        mFondaListPresenter.setView(this);
        //mFondaListPresenter.getFondas(FIRST_PAGE);
        getFondaListByType(FIRST_PAGE);

        mFondaAdapter.setOnClickListener(this);

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this.getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mFondaAdapter);
        setOnLoadMore(mLayoutManager);
        recyclerView.setOnScrollListener(mLoadMoreRecylerView);
        swipeRefreshLayout.setOnRefreshListener(this);
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
        if (lyEmptyList.getVisibility() == View.VISIBLE) {
            hideEmptyList();
        }
        mFondaList.addAll(fondas);
        caculateDistance(fondas);   // sai chính tả

    }

    @Override
    public void showEmptyList() {
        if (mFondaList.isEmpty()) {
            lyEmptyList.setVisibility(View.VISIBLE);
        } else {
            Toast.makeText(getActivity(), "Error, please try again...", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void hideEmptyList() {
        lyEmptyList.setVisibility(View.GONE);
    }


    @Override
    public void onRefresh() {
        mFondaList.clear();
        mDistanceList.clear();
        mLoadMoreRecylerView.onReset();
        swipeRefreshLayout.setRefreshing(true);
        //mFondaListPresenter.getFondas(FIRST_PAGE);
        getFondaListByType(FIRST_PAGE);
    }

    private void setOnLoadMore(LinearLayoutManager linearLayoutManager) {
        mLoadMoreRecylerView = new EndlessRecyclerOnScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int current_page) {
                if (current_page <= mLastPage) {
                    progressBar.setVisibility(View.VISIBLE);
                    //mFondaListPresenter.getFondas(current_page);
                    getFondaListByType(current_page);
                }
            }
        };
    }

    @Override
    public void onItemClick(View view, int id) {
        FondaDetailActivity.run(getContext(), id);
    }

    @Override
    public void onItemClick(String url, int position) {
    }

    @Override
    public void onItemLongClick(int position) {
    }

    @Override
    public void failLocation() {

    }

    @Override
    public void currentLocation(Location mLastLocation) {
        mCurrentLocation = mLastLocation;

    }

    private void caculateDistance(ArrayList<Fonda> fondas) {
        ArrayList<String> distanceList = new ArrayList<>();
        float[] results = new float[3];
        for (Fonda fonda : fondas) {
            if (fonda.location != null && mCurrentLocation != null) {
                Location.distanceBetween(mCurrentLocation.getLatitude(), mCurrentLocation.getLongitude(),
                        fonda.location.latitude, fonda.location.longitude, results);
                String distance;
                if (results[0] < 1000)
                    distance = ((int) results[0]) / 10 * 10 + " meters";
                else
                    distance = String.format("%.1f", results[0] / 1000) + " km";

                distanceList.add(distance);
            } else {
                distanceList.add("0 km");
            }
        }
        mDistanceList.addAll(distanceList);
        mFondaAdapter.setItems(mFondaList);
        mFondaAdapter.setDistanceList(mDistanceList);
        mFondaAdapter.notifyDataSetChanged();
    }

    private void getFondaListByType(int currentPage) {
        switch (type) {
            case AppConstants.TypeToSearch.SEARCH_BY_CITY:
                mFondaListPresenter.getFondasByCity(query, currentPage);
                break;
            case AppConstants.TypeToSearch.SEARCH_BY_CATEGORY:
                mFondaListPresenter.getFondasByGroup(query, currentPage);
                break;
            case AppConstants.TypeToSearch.SEARCH_BY_SCALE:
                mFondaListPresenter.getFondasByScale(query, currentPage);
                break;
            case AppConstants.TypeToSearch.SEARCH_BY_SALE_OFF:
                mFondaListPresenter.getFondasBySaleOff(query, currentPage);
                break;
            case AppConstants.TypeToSearch.SEARCH_BY_CULINARY:
                mFondaListPresenter.getFondasBySaleOff(query, currentPage);
                break;
            default:
                mFondaListPresenter.getFondas(currentPage);
                break;
        }
    }
}
