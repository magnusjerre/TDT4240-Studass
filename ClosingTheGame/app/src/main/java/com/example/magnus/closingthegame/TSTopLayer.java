package com.example.magnus.closingthegame;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import sheep.game.Layer;
import sheep.math.BoundingBox;

/**
 * Created by Magnus on 13/02/15.
 */
public class TSTopLayer extends Layer {

    public String titleScreen;
    public Paint paint;

    public TSTopLayer() {
        titleScreen = "The title screen";
        paint = new Paint();
        paint.setColor(Color.GREEN);
        paint.setTextSize(100);
    }

    @Override
    public void update(float v) {

    }

    @Override
    public void draw(Canvas canvas, BoundingBox boundingBox) {
        canvas.drawText(titleScreen, 20, canvas.getHeight() / 2, paint);
    }
}
