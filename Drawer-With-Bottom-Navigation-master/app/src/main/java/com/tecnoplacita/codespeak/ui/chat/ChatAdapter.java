package com.tecnoplacita.codespeak.ui.chat;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tecnoplacita.codespeak.R;

import java.util.ArrayList;
import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ChatViewHolder> {
    private List<ChatMessage> messages = new ArrayList<>();

    @NonNull
    @Override
    public ChatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_chat_message, parent, false);
        return new ChatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatViewHolder holder, int position) {
        ChatMessage chatMessage = messages.get(position);
        holder.bind(chatMessage);
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    public void setMessages(List<ChatMessage> messages) {
        this.messages = messages;
        notifyDataSetChanged();
    }

    static class ChatViewHolder extends RecyclerView.ViewHolder {
        private TextView userMessage;
        private TextView botMessage;

        public ChatViewHolder(@NonNull View itemView) {
            super(itemView);
            userMessage = itemView.findViewById(R.id.user_message);
            botMessage = itemView.findViewById(R.id.bot_message);
        }

        public void bind(ChatMessage chatMessage) {
            if (chatMessage.isUser()) {
                userMessage.setText(chatMessage.getMessage());
                userMessage.setVisibility(View.VISIBLE);
                botMessage.setVisibility(View.GONE);
            } else {
                botMessage.setText(chatMessage.getMessage());
                botMessage.setVisibility(View.VISIBLE);
                userMessage.setVisibility(View.GONE);
            }
        }
    }
}
