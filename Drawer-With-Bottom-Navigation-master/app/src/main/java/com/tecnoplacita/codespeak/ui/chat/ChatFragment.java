package com.tecnoplacita.codespeak.ui.chat;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tecnoplacita.codespeak.R;

import java.util.ArrayList;
import java.util.List;

public class ChatFragment extends Fragment {
    private ChatViewModel viewModel;
    private ChatAdapter chatAdapter;
    private List<ChatMessage> chatMessages;
    private EditText inputMessage;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chat, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        inputMessage = view.findViewById(R.id.edit_text_message);
        Button sendButton = view.findViewById(R.id.button_send);

        chatMessages = new ArrayList<>();
        chatAdapter = new ChatAdapter(chatMessages);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(chatAdapter);

        viewModel = new ViewModelProvider(this, new ChatViewModelFactory()).get(ChatViewModel.class);
        setupObservers();

        sendButton.setOnClickListener(v -> {
            String messageText = inputMessage.getText().toString();
            if (!TextUtils.isEmpty(messageText)) {
                sendMessage(messageText);
            }
        });

        return view;
    }

    private void sendMessage(String messageText) {
        chatMessages.add(new ChatMessage(messageText, true)); // true for user message
        chatAdapter.notifyItemInserted(chatMessages.size() - 1);
        inputMessage.setText("");

        viewModel.sendMessageToChatbot(messageText);
    }

    private void setupObservers() {
        viewModel.getResponseLiveData().observe(getViewLifecycleOwner(), response -> {
            chatMessages.add(new ChatMessage(response, false)); // false for bot message
            chatAdapter.notifyItemInserted(chatMessages.size() - 1);
        });
    }
}
