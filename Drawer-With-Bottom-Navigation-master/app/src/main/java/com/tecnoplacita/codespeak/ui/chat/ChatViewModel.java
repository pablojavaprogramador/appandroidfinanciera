package com.tecnoplacita.codespeak.ui.chat;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ChatViewModel extends ViewModel {
    private final MutableLiveData<String> responseLiveData = new MutableLiveData<>();
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();

    public LiveData<String> getResponseLiveData() {
        return responseLiveData;
    }

    public void sendMessageToChatbot(String message) {
        executorService.execute(() -> {
            // Aquí iría la lógica para enviar el mensaje al servicio de chatbot y recibir la respuesta
            String response = ""; // Lógica para obtener la respuesta del servicio
            responseLiveData.postValue(response);
        });
    }
}
