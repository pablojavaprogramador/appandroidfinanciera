package com.tecnoplacita.codespeak.ui.login;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.tecnoplacita.codespeak.data.LoginDataSource;

import android.content.Context;

public class LoginViewModelFactory implements ViewModelProvider.Factory {

    private final LoginDataSource loginDataSource;
    private final Context context;

    public LoginViewModelFactory(LoginDataSource loginDataSource, Context context) {
        this.loginDataSource = loginDataSource;
        this.context = context;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(LoginViewModel.class)) {
            // Crear una instancia de LoginViewModel con LoginDataSource y Context
            return (T) new LoginViewModel(loginDataSource, context);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
