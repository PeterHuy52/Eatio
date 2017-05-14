package com.doanchuyennganh.eatio.views.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.doanchuyennganh.eatio.R;
import com.doanchuyennganh.eatio.entity.Utility;
import com.doanchuyennganh.eatio.presensters.fonda.UtilitiesPresenter;
import com.doanchuyennganh.eatio.presensters.fonda.UtilitiesPresenterImpl;
import com.doanchuyennganh.eatio.views.fonda.FondaUtilitiesView;
import com.doanchuyennganh.eatio.views.fonda.adapter.UtilitiesAdapter;

import org.androidannotations.annotations.AfterTextChange;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.api.BackgroundExecutor;

import java.util.ArrayList;
import java.util.List;

import static com.doanchuyennganh.eatio.utils.AppConstants.UNICODE_PLUS_SYMBOL;
import static com.doanchuyennganh.eatio.utils.AppConstants.UNICODE_CANCEL_SYMBOL;

/**
 * Created by TungHo on 05/13/2017.
 */
@EViewGroup(R.layout.viewgroup_utilities_fonda)
public class FondaUtilitiesHolder extends RelativeLayout implements FondaUtilitiesView {

    public static final int MAX_UTIL_PER_FONDA = 8;

    /**
     * Token string received from parent activity
     */
    private String mToken;

    /**
     * Danh sách các tiện nghi của quán
     */
    @ViewById(R.id.utilities_rv_container)
    RecyclerView utilitiesRv;

    /**
     * Add more utilities button
     */
    @ViewById(R.id.add_more_btn)
    TextView addMoreUtilsBtn;

    /**
     * Edittext create utilities
     */
    @ViewById(R.id.add_more_utils_edt)
    EditText addMoreUtilsEdt;

    /**
     * Utilities accept btn
     */
    @ViewById(R.id.accept_btn)
    TextView utilsAcceptBtn;

    /**
     * Fonda id.
     * Đối tượng FondaUtilitiesHolder là danh sách utils của 1 fonda. nên phải có một fonda id parent.
     */
    private int fondaId;

    /**
     * Có phải của một fonda đang là sở hửu của người đăng nhập
     */
    private boolean isOwner;

    /**
     * list content
     */
    private List<Utility> utilities;

    UtilitiesPresenter presenter;

    /**
     * Được gọi khi người dùng chọn một item từ suggest list pop up
     */
    OnListSelectedItem listSelectedItemListener;

    /**
     * Item được chọn từ list pop up suggest, khi nhập từ editext
     */
    private Utility suggestListSelectedItem;

    /**
     * Dialog suggest những utilities khớp với input.
     * Get utilities by name call API :
     * HOST/utility?name={xyz}
     */
    private ListDialog<Utility> suggestListDialog;


    private boolean isMunualSetTxt;

    public FondaUtilitiesHolder(Context context) {
        super(context);
    }

    public FondaUtilitiesHolder(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FondaUtilitiesHolder(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @SuppressLint("NewApi")
    public FondaUtilitiesHolder(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @AfterViews
    void afterViews(){
        utilities = new ArrayList<>();
        presenter = new UtilitiesPresenterImpl(this);

    }

    @Click(R.id.add_more_btn)
    public void addUtilsClick() {
        if (addMoreUtilsBtn.getText().equals(UNICODE_PLUS_SYMBOL)){
            // begin edit
            addMoreUtilsBtn.setText(UNICODE_CANCEL_SYMBOL);
            addMoreUtilsEdt.setVisibility(View.VISIBLE);
            utilsAcceptBtn.setVisibility(View.VISIBLE);
            utilitiesRv.setVisibility(View.GONE);
            addMoreUtilsEdt.setText("");
        }
        else if (addMoreUtilsBtn.getText().equals(UNICODE_CANCEL_SYMBOL)){
            // cancel
            addMoreUtilsBtn.setText(UNICODE_PLUS_SYMBOL);
            addMoreUtilsEdt.setVisibility(View.GONE);
            utilsAcceptBtn.setVisibility(View.GONE);
            utilitiesRv.setVisibility(View.VISIBLE);
            this.suggestListSelectedItem = null;
            // cancel thì không load suggest list nữa.
            BackgroundExecutor.cancelAll("startGettingInfo", true);
        }
    }

    @Click(R.id.accept_btn)
    public void utilsAcceptBtnClick(){

        if (this.suggestListSelectedItem == null) {
            // nếu chọn từ list thì push bằng utility id
            presenter.addFondaUtilities(mToken, this.fondaId, addMoreUtilsEdt.getText().toString());
        } else {
            // Nếu user tự nhập tên thì push bằng utility name
            presenter.addFondaUtilities(mToken, this.fondaId, suggestListSelectedItem.id);
        }
    }

    @AfterTextChange(R.id.add_more_utils_edt)
    public void utilsTextChanged(){
        // Mỗi lần input thì chờ delay 1.7 giây rồi gửi request get dữ liệu.
        // Nếu task backrground đang chạy thì cancel it.
        if (isMunualSetTxt)
            return;
        String input = addMoreUtilsEdt.getText().toString();
        if (input.equals("")) {
            utilsAcceptBtn.setEnabled(false);
        } else {
            BackgroundExecutor.cancelAll("startGettingInfo", true);
            startGettingInfo(input);
            utilsAcceptBtn.setEnabled(true);
        }

    }

    @Background(delay = 1700, id = "startGettingInfo")
    public void startGettingInfo(String input){
        presenter.getUtilities(input);
    }

    /**
     * Được gọi mỗi khi utilities changed
     */
    public void updateView(){
        if (utilities != null && !utilities.isEmpty()){
            final UtilitiesAdapter adapter = new UtilitiesAdapter(utilities);

            adapter.setOnItemSelectedListener(new UtilitiesAdapter.OnItemSelected() {
                @Override
                public void onSelectItem(final Utility u) {
                    if (FondaUtilitiesHolder.this.isOwner){
                        EdtDialog dialog = EdtDialog.EdtDialogHelper.build(FondaUtilitiesHolder.this.getContext(),
                                "Update description", u.description,
                                new EdtDialog.onAcceptBtnClickCallback() {
                                    @Override
                                    public void acceptBtnClick(String content) {
                                        presenter.updateFondaUtility(mToken, fondaId, u.id, content);
                                    }
                                },
                                new EdtDialog.onCancelBtnClickCallback() {
                                    @Override
                                    public void cancelBtnClick() {
                                        presenter.removeFondaUtility(mToken, fondaId, u.id);
                                    }
                                });
                        dialog.setNegativeBtnTxt("Remove");
                        dialog.show();
                    }
                    else {

                    }
                }
            });
            utilitiesRv.setAdapter(adapter);

            // Nếu quá số utils của một fonda thì disable nút add
            addMoreUtilsBtn.setEnabled(utilities.size() < MAX_UTIL_PER_FONDA);
        }


        if (isOwner){
            addMoreUtilsBtn.setText(UNICODE_PLUS_SYMBOL);
            addMoreUtilsBtn.setVisibility(View.VISIBLE);
        }
        else {
            addMoreUtilsBtn.setVisibility(View.GONE);
        }
        addMoreUtilsEdt.setVisibility(View.GONE);
        utilsAcceptBtn.setVisibility(View.GONE);
        utilitiesRv.setVisibility(View.VISIBLE);
    }


    @Override
    public void updateSuggestListUtilities(List<Utility> collection) {
        if (this.addMoreUtilsEdt.getVisibility() == GONE)
            return;
        if (suggestListDialog != null){
            if (suggestListDialog.isShowing())
                suggestListDialog.dismiss();
            suggestListDialog = null;
        }
        this.suggestListDialog = new ListDialog<>(this.getContext(), collection, new ListDialog.Callback() {
            @Override
            public void onSetText(TextView view, Object content) {
                Utility u = (Utility) content;
                view.setText(u.name);
            }
        });
        suggestListDialog.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // user select an item
                Utility u = (Utility) parent.getAdapter().getItem(position);
                if (listSelectedItemListener != null)
                    listSelectedItemListener.onSelectedItem(u);
                addMoreUtilsEdt.setText(u.name);
                isMunualSetTxt = true;
                FondaUtilitiesHolder.this.suggestListSelectedItem = u;
                suggestListDialog.dismiss();
            }
        });
        suggestListDialog.show();
    }

    @Override
    public void updateFondaListUtilities(List<Utility> collection) {
        utilities.clear();
        utilities.addAll(collection);
        updateView();
    }

    public void setOnListSelectedItemListener(@NonNull OnListSelectedItem listener){
        this.listSelectedItemListener = listener;
    }

    public void setOwner(boolean isOwner){
        this.isOwner = isOwner;
    }

    public void setFondaId(int fondaId){
        this.fondaId = fondaId;
        presenter.getFondaUtilities(fondaId);
    }

    public void setUtilities(List<Utility> utilities){
        this.utilities.addAll(utilities);
        updateView();
    }

    public void setToken(String token){
        this.mToken = token;
    }

    public interface OnListSelectedItem {
        void onSelectedItem(Utility u);
    }
}
