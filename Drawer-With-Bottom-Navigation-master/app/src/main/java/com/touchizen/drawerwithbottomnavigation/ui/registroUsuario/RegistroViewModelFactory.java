package com.touchizen.drawerwithbottomnavigation.ui.registroUsuario;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.touchizen.drawerwithbottomnavigation.data.repository.UserRepository;

public class RegistroViewModelFactory implements ViewModelProvider.Factory {

    private final UserRepository userRepository;

    public RegistroViewModelFactory(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(RegistroViewModel.class)) {
            return (T) new RegistroViewModel(userRepository);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
