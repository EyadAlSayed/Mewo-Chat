package com.example.pushersocketexample.ui.activities;


import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pushersocketexample.R;
import com.pusher.client.Pusher;
import com.pusher.client.PusherOptions;
import com.pusher.client.channel.Channel;
import com.pusher.client.connection.ConnectionEventListener;
import com.pusher.client.connection.ConnectionState;
import com.pusher.client.connection.ConnectionStateChange;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PusherOptions options = new PusherOptions();
        options.setCluster("ap2");

        Pusher pusher = new Pusher("4dc61ab100c156df5150", options);
        pusher.connect();

        pusher.connect(new ConnectionEventListener() {
            @Override
            public void onConnectionStateChange(ConnectionStateChange change) {
                Log.d("EYAD", "onConnectionStateChange: " + change.getCurrentState() + "  FROM " + change.getPreviousState());
            }

            @Override
            public void onError(String message, String code, Exception e) {
                Log.e("EYAD", "onError: There was a problem connecting!");
            }
        }, ConnectionState.ALL);

//        pusher.disconnect();

        Channel channel = pusher.subscribe("chat-message");

         // Bind to listen for events called "my-event" sent to "my-channel"
        channel.bind("message", event -> Log.d("EYAD", "Received event with data: " + event.toString()));



    }
}