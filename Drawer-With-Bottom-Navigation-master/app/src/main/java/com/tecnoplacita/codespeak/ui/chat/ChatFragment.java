package com.tecnoplacita.codespeak.ui.chat;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tecnoplacita.codespeak.R;

import java.util.List;
import java.util.Locale;

public class ChatFragment extends Fragment implements TextToSpeech.OnInitListener {
    private ChatViewModel chatViewModel;
    private ChatAdapter chatAdapter;
    private EditText inputMessage;
    private TextToSpeech textToSpeech;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chat, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        inputMessage = view.findViewById(R.id.edit_text_message);
        Button sendButton = view.findViewById(R.id.button_send);

        chatAdapter = new ChatAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(chatAdapter);

        chatViewModel = new ViewModelProvider(this, new ChatViewModelFactory(getContext())).get(ChatViewModel.class);

        chatViewModel.getMessages().observe(getViewLifecycleOwner(), new Observer<List<ChatMessage>>() {
            @Override
            public void onChanged(List<ChatMessage> chatMessages) {
                chatAdapter.setMessages(chatMessages);
                recyclerView.scrollToPosition(chatMessages.size() - 1); // Desplazarse al último mensaje
            }
        });

        sendButton.setOnClickListener(v -> {
            String message = inputMessage.getText().toString();
            if (!message.isEmpty()) {
                chatViewModel.sendMessage(message);
                inputMessage.setText(""); // Limpiar el EditText
            }
        });

        // Inicializar TextToSpeech
        textToSpeech = new TextToSpeech(getContext(), this);

        return view;
    }

    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {
            int result = textToSpeech.setLanguage(Locale.US);
            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                // Manejar el error si el idioma no es compatible
            }
        }
    }

    private void speak(String text) {
        if (textToSpeech != null) {
            textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null, null);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (textToSpeech != null) {
            textToSpeech.stop();
            textToSpeech.shutdown(); // Liberar recursos
        }
    }
}
