package com.tecnoplacita.codespeak.ui.chat;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class ChatViewModel extends ViewModel {
    private MutableLiveData<List<ChatMessage>> messages;

    public ChatViewModel() {
        messages = new MutableLiveData<>(new ArrayList<>());
    }

    public LiveData<List<ChatMessage>> getMessages() {
        return messages;
    }

    public void sendMessage(String input) {
        List<ChatMessage> currentMessages = messages.getValue();
        currentMessages.add(new ChatMessage(input, true)); // Mensaje del usuario

        String response = getResponse(input);
        currentMessages.add(new ChatMessage(response, false)); // Respuesta del bot

        messages.setValue(currentMessages);
        // Aquí se podría llamar a la función speak de ChatFragment si se tiene una referencia
    }

    private String getResponse(String input) {
        // Procesamiento simple
        if (input.toLowerCase().contains("hola")) {
            return "¡Hola! ¿Por fa  introduce el verbo?";
        } else if (input.toLowerCase().contains("what is your name")) {
            return "Soy un CodeSpeak. ¿Introduce el Verbo para su analisis?";
        }  else {
            return "No estoy seguro de cómo responder a eso. ¡Porfa intenta ingresar un verbo!";
        }
    }
}
