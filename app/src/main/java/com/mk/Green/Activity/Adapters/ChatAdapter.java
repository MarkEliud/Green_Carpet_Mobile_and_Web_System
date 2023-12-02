package com.mk.Green.Activity.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


import com.mk.Green.R;

import java.util.List;
import java.util.Objects;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ViewHolder> {
    private List<ChatMessage> messages;
    private String userId;

    public ChatAdapter(List<ChatMessage> messages, String userId) {
        this.messages = messages;
        this.userId = userId;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(viewType, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ChatMessage message = messages.get(position);
        if (message.getSenderId().equals(userId) ) {
            // Message sent by current user

            if (holder.sentLayout != null) {
                holder.sentLayout.setVisibility(View.VISIBLE);
            }



            if (holder.receivedLayout != null) {
                holder.receivedLayout.setVisibility(View.GONE);
            }
            if (holder.sentMessage != null) {
                holder.sentMessage.setText(message.getMessage());
            }

        } else {
            // Message received by current user
            if (holder.sentLayout != null) {
                holder.sentLayout.setVisibility(View.GONE);
            }

         //   holder.sentLayout.setVisibility(View.GONE);
            holder.receivedLayout.setVisibility(View.VISIBLE);
            holder.receivedMessage.setText(message.getMessage());
        }
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    @Override
    public int getItemViewType(int position) {
        ChatMessage message = messages.get(position);
        if (Objects.equals(message.getSenderId(), userId)) {

            // Message sent by current user
            return R.layout.sent;
        } else {
            // Message received by current user
            return R.layout.received;
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout sentLayout;
        private TextView sentMessage;
        private LinearLayout receivedLayout;
        private TextView receivedMessage;

        public ViewHolder(View itemView) {
            super(itemView);

            sentLayout = itemView.findViewById(R.id.sent);
            sentMessage = itemView.findViewById(R.id.messageTextView);
            receivedLayout = itemView.findViewById(R.id.rece);
            receivedMessage = itemView.findViewById(R.id.messageTextView1);
        }
    }
}
