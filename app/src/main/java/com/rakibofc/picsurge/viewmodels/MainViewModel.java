package com.rakibofc.picsurge.viewmodels;

import android.app.Application;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.io.IOException;
import java.net.URL;

public class MainViewModel extends AndroidViewModel {

    MutableLiveData<Bitmap> liveImage;

    public MainViewModel(@NonNull Application application) {
        super(application);

        liveImage = new MutableLiveData<>();
        loadData();
    }

    public LiveData<Bitmap> getImage() {
        return liveImage;
    }

    public void loadData() {

        String imageUrl = "https://loremflickr.com/320/240";

        new Thread(() -> {

            try {
                URL url = new URL(imageUrl);
                liveImage.postValue(BitmapFactory.decodeStream(url.openConnection().getInputStream()));

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }
}
