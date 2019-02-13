package com.anmoworkz.nytimes;

import android.app.Application;

import com.anmoworkz.nytimes.componenet.DaggerNYTimesComponents;
import com.anmoworkz.nytimes.componenet.NYTimesComponents;
import com.anmoworkz.nytimes.model.HardCover;

public class NYTimesApplication extends Application {
    private NYTimesComponents appComponents;
    @Override
    public void onCreate() {
        super.onCreate();
        appComponents=DaggerNYTimesComponents.builder().hardCover(new HardCover(this)).build();
    }
   public NYTimesComponents getNYTimesComponents(){
        return appComponents;

    }
}
