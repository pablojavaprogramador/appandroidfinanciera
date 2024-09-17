package com.touchizen.drawerwithbottomnavigation.ui.login;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.touchizen.drawerwithbottomnavigation.data.LoginDataSource;

public class LoginViewModelFactory implements ViewModelProvider.Factory {

    private final LoginDataSource loginDataSource;

    public LoginViewModelFactory(LoginDataSource loginDataSource) {
        this.loginDataSource = loginDataSource;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(LoginViewModel.class)) {
            // Crear una instancia de LoginViewModel
            return (T) new LoginViewModel(loginDataSource);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
