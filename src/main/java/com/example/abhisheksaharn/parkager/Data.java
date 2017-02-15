package com.example.abhisheksaharn.parkager;

import android.net.Uri;

/**
 * Created by Abhishek Saharn on 4/5/2015.
 */
public class Data {
    Uri iconId;
    String title;
    String id;

    public Data(Uri icon, String ptitle, String id) {
        iconId = icon;
        title = ptitle;
        this.id = id;

    }

    public void setIconId(Uri icon) {
        this.iconId = icon;
    }

    public void setTitle(String ptitle) {
        this.title = ptitle;

    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public Uri getIconId() {
        return iconId;
    }

    public String getTitle() {
        return title;
    }
}
