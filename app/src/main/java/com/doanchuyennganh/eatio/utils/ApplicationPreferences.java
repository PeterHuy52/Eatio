package com.doanchuyennganh.eatio.utils;

import org.androidannotations.annotations.sharedpreferences.DefaultInt;
import org.androidannotations.annotations.sharedpreferences.DefaultString;
import org.androidannotations.annotations.sharedpreferences.SharedPref;

/**
 * Created by TungHo on 05/06/2017.
 */
@SharedPref(SharedPref.Scope.APPLICATION_DEFAULT)
public interface ApplicationPreferences {

    @DefaultString("")
    String userToken();

    @DefaultInt(-1)
    int userId();


}
