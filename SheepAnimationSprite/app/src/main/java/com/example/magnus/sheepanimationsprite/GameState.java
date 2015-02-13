package com.example.magnus.sheepanimationsprite;

import android.graphics.Canvas;
import android.graphics.Color;

import sheep.game.State;
import sheep.game.World;

/**
 * Created by Magnus on 13/02/15.
 */
public class GameState extends State {

    World gameWorld;
    GameCollisionLayer layer;

    public GameState() {
        gameWorld = new World();
        layer = new GameCollisionLayer();
        gameWorld.addLayer(layer);
    }

    public void update(float dt) {
        gameWorld.update(dt);
    }

    public void draw(Canvas canvas) {
        canvas.drawColor(Color.GRAY);
        gameWorld.draw(canvas);
    }
}
