package com.example.magnus.closingthegame;

import android.graphics.Canvas;
import android.graphics.Color;
import android.view.MotionEvent;

import sheep.game.State;
import sheep.game.World;
import sheep.input.TouchListener;

/**
 * Created by Magnus on 13/02/15.
 */
public class TitleScreen extends State implements TouchListener {

    World gameworld;
    TSTopLayer layer;

    public TitleScreen() {
        gameworld = new World();
        layer = new TSTopLayer();
        gameworld.addLayer(layer);
    }

    public void update(float dt) {
        gameworld.update(dt);
    }

    public void draw(Canvas canvas) {
        canvas.drawColor(Color.GRAY);
        gameworld.draw(canvas);
    }

    @Override
    public boolean onTouchUp(MotionEvent event) {
        //When pressing the screen, the game should start. To start it, the next state (GameState)
        //must be push onto the game stack.
        getGame().pushState(new GameState());
        return true;
    }
}
