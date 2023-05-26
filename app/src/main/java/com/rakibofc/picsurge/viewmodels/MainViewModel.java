package com.rakibofc.picsurge.viewmodels;

import android.app.Application;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class MainViewModel extends AndroidViewModel {

    MutableLiveData<Bitmap> liveImage;

    public MainViewModel(@NonNull Application application) {
        super(application);

        liveImage = new MutableLiveData<>();
    }

    public LiveData<Bitmap> getImage() {
        return liveImage;
    }

    public void loadData() {

        /*String imageUrl = "https://loremflickr.com/320/240";

        new Thread(() -> {

            try {
                URL url = new URL(imageUrl);
                liveImage.setValue(BitmapFactory.decodeStream(url.openConnection().getInputStream()));

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Log.e("Info", "Bitmap bitmap");

        }).start();*/

        }

        /*Picasso.get().load(imageUrl).into(new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                Log.e("Info", "Bitmap bitmap");
                liveImage.setValue(bitmap);
            }

            @Override
            public void onBitmapFailed(Exception e, Drawable errorDrawable) {
                Log.e("Info", "Image Failed");
            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {
                Log.e("Info", "Set Image");
            }
        });*/

}
