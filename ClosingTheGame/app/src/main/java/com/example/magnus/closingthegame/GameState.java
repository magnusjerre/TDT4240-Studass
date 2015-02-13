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
public class GameState extends State implements TouchListener {

    World gameWorld;
    GSCollisionLayer layer;

    public GameState() {
        gameWorld = new World();
        layer = new GSCollisionLayer();
        gameWorld.addLayer(layer);
    }

    public void update(float dt) {
        gameWorld.update(dt);
    }

    public void draw(Canvas canvas) {
        canvas.drawColor(Color.GRAY);
        gameWorld.draw(canvas);
    }

    @Override
    public boolean onTouchUp(MotionEvent event) {
        //If the leftarrow is pressed, then this scene should no longer be displayed, and the
        //previous (TitleScreen) should be displayed instead. Must therefore pop this state from
        //the game.
        if (layer.leftArrow.getBoundingBox().contains(event.getX(), event.getY())) {
            getGame().popState();
            return true;
        }
        return super.onTouchUp(event);
    }
}
