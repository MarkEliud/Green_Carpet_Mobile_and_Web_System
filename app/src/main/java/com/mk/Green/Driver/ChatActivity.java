package com.mk.Green.Driver;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.mk.Green.Activity.Adapters.ChatAdapter;
import com.mk.Green.Activity.Adapters.ChatMessage;
import com.mk.Green.Activity.LoginActivity;
import com.mk.Green.R;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChatActivity extends AppCompatActivity {
    private String userId = "2"; // Our user ID
    String choice,current;
    private String user ; // ID of the caregiver we're chatting with
    private EditText messageText;
    private Button sendButton;
    private RecyclerView recyclerView;
    private ChatAdapter chatAdapter;
    private List<ChatMessage> messages;
    String rece = LoginActivity.ip+"recyclerview/loadmessages.php";
    String send =LoginActivity.ip+ "recyclerview/sendmessage.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat);

        final Intent intent = getIntent();
        choice = intent.getStringExtra("choice");
        current = intent.getStringExtra("current");

        messageText = findViewById(R.id.message_text);
        sendButton = findViewById(R.id.send_button);
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        messages = new ArrayList<>();
        chatAdapter = new ChatAdapter(messages, current);
        recyclerView.setAdapter(chatAdapter);

        // Load initial chat messages
        loadChatMessages();

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Send message to server
                sendMessage(messageText.getText().toString());

                // Add message to local list and update RecyclerView
                messages.add(new ChatMessage(current, choice, messageText.getText().toString()));
                chatAdapter.notifyDataSetChanged();

                // Clear message text
                messageText.setText("");
                Toast.makeText(ChatActivity.this, choice, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadChatMessages() {
        StringRequest request = new StringRequest(Request.Method.GET, rece +"?sender_id="+ current + "&receiver_id=" + choice,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                String senderId = jsonObject.getString("sender");
                                String receiverId = jsonObject.getString("receiver");
                                String message = jsonObject.getString("message");
                                messages.add(new ChatMessage(senderId, receiverId, message));
                            }
                            chatAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });
        Volley.newRequestQueue(this).add(request);
    }

    private void sendMessage(String message) {
        // Send POST request to server to send message
      //  String url = "http://localhost/send_message.php";
        StringRequest request = new StringRequest(Request.Method.POST, send,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Message sent successfully
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                //Map<String, String> params = null;
                HashMap<String, String> params = new HashMap<>();
                params.put("sender_id", current);
                params.put("receiver_id", choice);
                params.put("message", message);
                params.put("user", current);
                return params;
            }
        };
        Volley.newRequestQueue(this).add(request);
    }
}