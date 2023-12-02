package com.mk.Green.Activity.Adapters;
public class ChatMessage {
    private String sender;
    private String receiver;
    private String message;

    public ChatMessage(String senderId, String receiverId, String message) {
        this.sender = senderId;
        this.receiver = receiverId;
        this.message = message;
    }

    public String getSenderId() {
        return sender;
    }

    public String getReceiverId() {
        return receiver;
    }

    public String getMessage() {
        return message;
    }
}
