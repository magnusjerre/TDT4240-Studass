package com.example.magnus.sheepanimationsprite;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.Log;

import sheep.collision.CollisionLayer;
import sheep.collision.CollisionListener;
import sheep.game.Layer;
import sheep.game.Sprite;
import sheep.graphics.Image;
import sheep.math.BoundingBox;

/**
 * Created by Magnus on 13/02/15.
 */
public class GameLayer extends CollisionLayer implements CollisionListener {

    SpriteAnimated sprite1, sprite2;

    public GameLayer() {
        Bitmap helicopterSheet = BitmapFactory.decodeResource(
                Singleton.getInstance().resources, R.drawable.helicopter_sheet);
        sprite1 = SpriteAnimated.createFromSheet(helicopterSheet, 4);
        sprite1.setPosition(Singleton.getInstance().screenSize.x / 4,
                Singleton.getInstance().screenSize.y / 5);
        sprite1.setAcceleration(0,10);
        sprite1.setScale(0.6f,0.6f);

        sprite1.addCollisionListener(this);
        addSprite(sprite1);

        Image[] heliImages = new Image[4];
        heliImages[0] = new Image(R.drawable.heliframe0);
        heliImages[1] = new Image(R.drawable.heliframe1);
        heliImages[2] = new Image(R.drawable.heliframe2);
        heliImages[3] = new Image(R.drawable.heliframe3);

        sprite2 = SpriteAnimated.CreateFromArrayOfImages(heliImages);
        sprite2.setPosition(Singleton.getInstance().screenSize.x / 4,
                Singleton.getInstance().screenSize.y / 2);
        sprite2.setSpeed(0, -100);
        sprite2.addCollisionListener(this);
        addSprite(sprite2);
    }

    @Override
    public void update(float v) {
        super.update(v);
        sprite1.setOrientation(sprite1.getOrientation() + 0.2f);
    }

    @Override
    public void collided(Sprite sprite, Sprite sprite2) {
        sprite1.setSpeed(-sprite1.getSpeed().getX(), -sprite1.getSpeed().getY());
        sprite2.setSpeed(-sprite2.getSpeed().getX(), -sprite2.getSpeed().getY());
    }
}
