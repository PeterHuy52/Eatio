package com.doanchuyennganh.eatio.views.fonda.fondasearch;

import android.content.Context;

import com.doanchuyennganh.eatio.R;
import com.doanchuyennganh.eatio.utils.AppConstants;
import com.doanchuyennganh.eatio.views.BaseActivity;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.ViewById;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Nguyen Tan Luan on 5/21/2017.
 */
@EActivity(R.layout.activity_search)
public class FondaSearchActivity extends BaseActivity {
    @ViewById(R.id.img_quick_search)
    CircleImageView imgQuickSearch;

    @ViewById(R.id.img_search_by_city)
    CircleImageView imgSearchByCity;

    @ViewById(R.id.img_search_by_group)
    CircleImageView imgSearchByGroup;

    @ViewById(R.id.img_search_by_scale)
    CircleImageView imgSearchByScale;

    @ViewById(R.id.img_search_by_sale_off)
    CircleImageView imgSearchBySaleOff;

    @ViewById(R.id.img_search_by_culinary)
    CircleImageView imgSearchByCulinary;

    public static void run(Context context) {
        FondaSearchActivity_.intent(context).start();
    }

    @AfterViews
    void afterViews() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @OptionsItem(android.R.id.home)
    void homeButtonClick() {
        this.finish();
    }

    @Click(R.id.img_quick_search)
    void quickSearchClick() {
        FondaQuickSearchActivity.run(this);
    }

    @Click(R.id.img_search_by_city)
    void searchByCityClick() {
        FondaSearchDetailActivity.run(this, AppConstants.TypeToSearch.SEARCH_BY_CITY);
    }

    @Click(R.id.img_search_by_group)
    void searchByGroupClick() {
        FondaSearchDetailActivity.run(this, AppConstants.TypeToSearch.SEARCH_BY_CATEGORY);
    }

    @Click(R.id.img_search_by_scale)
    void setImgSearchByScaleClick() {
        FondaSearchDetailActivity.run(this, AppConstants.TypeToSearch.SEARCH_BY_SCALE);
    }

    @Click(R.id.img_search_by_sale_off)
    void searchBySaleOffClick() {
        FondaSearchDetailActivity.run(this, AppConstants.TypeToSearch.SEARCH_BY_SALE_OFF);
    }

    @Click(R.id.img_search_by_culinary)
    void searchByCulinaryClick() {
        FondaSearchDetailActivity.run(this, AppConstants.TypeToSearch.SEARCH_BY_CULINARY);
    }


}
