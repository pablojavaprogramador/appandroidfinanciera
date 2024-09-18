package com.touchizen.drawerwithbottomnavigation.ui.registroUsuario;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.touchizen.drawerwithbottomnavigation.data.repository.UserRepository;

public class TokenValidationViewModelFactory implements ViewModelProvider.Factory {

    private final UserRepository userRepository;

    public TokenValidationViewModelFactory(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(TokenValidationViewModel.class)) {
            return (T) new TokenValidationViewModel(userRepository);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
