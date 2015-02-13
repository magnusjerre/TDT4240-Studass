package com.example.magnus.sheepanimationsprite;

import android.content.res.Resources;
import android.graphics.Point;

/**
 * Created by Magnus on 13/02/15.
 */
public class Singleton {

    private static Singleton instance = null;

    public Point screenSize;
    public Resources resources;

    private Singleton() {
        screenSize = new Point();
    }

    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
}
