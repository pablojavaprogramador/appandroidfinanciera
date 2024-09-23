package com.tecnoplacita.codespeak.ui.verbos;


import android.content.Context;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class VerbosViewModelFactory implements ViewModelProvider.Factory {
    private final Context context;

    public VerbosViewModelFactory(Context context) {
        this.context = context;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(VerbosViewModel.class)) {
            return (T) new VerbosViewModel(context);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
