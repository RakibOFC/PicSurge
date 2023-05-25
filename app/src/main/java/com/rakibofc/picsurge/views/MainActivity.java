package com.rakibofc.picsurge.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.rakibofc.picsurge.databinding.ActivityMainBinding;
import com.rakibofc.picsurge.receivers.ConnectionReceiver;
import com.rakibofc.picsurge.viewmodels.MainViewModel;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

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

        // Toast connection status message
        // Toast.makeText(this, status, Toast.LENGTH_SHORT).show();

        binding.imageBtn.setOnClickListener(v -> {

            String imageUrl = "https://loremflickr.com/320/240";

            Picasso.get().load(imageUrl).into(new Target() {
                @Override
                public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                    binding.imageBtn.setImageBitmap(bitmap);
                }

                @Override
                public void onBitmapFailed(Exception e, Drawable errorDrawable) {

                }

                @Override
                public void onPrepareLoad(Drawable placeHolderDrawable) {

                }
            });
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