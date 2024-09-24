package com.tecnoplacita.codespeak.ui.chat;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class ChatViewModelFactory implements ViewModelProvider.Factory {
    private final Context context;

    public ChatViewModelFactory(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(ChatViewModel.class)) {
            return (T) new ChatViewModel(context); // Pasar el contexto al ViewModel
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
