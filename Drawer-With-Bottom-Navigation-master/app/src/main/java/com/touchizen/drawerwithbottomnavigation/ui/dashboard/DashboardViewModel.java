package com.touchizen.drawerwithbottomnavigation.ui.dashboard;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DashboardViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public DashboardViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Aun no se tienen Estados Financieros Disponibles");
    }

    public LiveData<String> getText() {
        return mText;
    }
}