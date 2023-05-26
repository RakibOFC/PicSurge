package com.rakibofc.picsurge.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.IntentFilter;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.StrictMode;

import com.rakibofc.picsurge.databinding.ActivityMainBinding;
import com.rakibofc.picsurge.receivers.ConnectionReceiver;
import com.rakibofc.picsurge.viewmodels.MainViewModel;

import java.io.IOException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    final static String CONNECTIVITY_ACTION = "android.net.conn.CONNECTIVITY_CHANGE";
    IntentFilter intentFilter;
    ConnectionReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Inflates the layout XML file for the main activity and creates an instance of the binding class
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());

        // Initializes the ViewModel for the main activity using the ViewModelProvider
        MainViewModel viewModel = new ViewModelProvider(this).get(MainViewModel.class);

        // Set content view
        setContentView(binding.getRoot());

        // Initialize connection status receiver
        intentFilter = new IntentFilter();
        receiver = new ConnectionReceiver();

        // Add action in intent filter
        intentFilter.addAction(CONNECTIVITY_ACTION);

        // Get and set image from ViewModel
        viewModel.getImage().observe(this, binding.imageBtn::setImageBitmap);

        /*
        * viewModel.getImage().observe(this, new Observer<Drawable>() {
            @Override
            public void onChanged(Drawable drawable) {
                binding.imageBtn.setImageDrawable(drawable);
            }
        });
        * */

        binding.imageBtn.setOnClickListener(v -> {

            String imageUrl = "https://loremflickr.com/320/240";

            new Thread(() -> {
                try {
                    synchronized (this) {
                        wait(500);

                        runOnUiThread(() -> {

                            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                            StrictMode.setThreadPolicy(policy);

                            try {
                                URL url = new URL(imageUrl);

                                binding.imageBtn.setImageBitmap(
                                        BitmapFactory.decodeStream(url.openConnection().getInputStream()));

                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        });
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(receiver, intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(receiver);
    }
}