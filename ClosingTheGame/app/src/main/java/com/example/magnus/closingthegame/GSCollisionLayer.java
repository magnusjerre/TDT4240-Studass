package com.example.magnus.closingthegame;

import sheep.collision.CollisionLayer;
import sheep.collision.CollisionListener;
import sheep.game.Sprite;
import sheep.graphics.Image;

/**
 * Created by Magnus on 13/02/15.
 */
public class GSCollisionLayer extends CollisionLayer implements CollisionListener {

    Sprite heli1, heli2, leftArrow;

    public GSCollisionLayer() {
        Image backImage = new Image(R.drawable.back);
        Image heliImage = new Image(R.drawable.helicopter);

        heli1 = new Sprite(heliImage);
        heli2 = new Sprite(heliImage);
        leftArrow = new Sprite(backImage);

        heli1.setPosition(300, 100);
        heli2.setPosition(300,600);
        leftArrow.setPosition(100, 100);
        leftArrow.setScale(0.2f, 0.2f);

        heli1.setSpeed(0, 200);
        heli2.setSpeed(0, -140);

        heli1.addCollisionListener(this);
        heli2.addCollisionListener(this);

        addSprite(heli1);
        addSprite(heli2);
        addSprite(leftArrow);
    }

    @Override
    public void collided(Sprite sprite, Sprite sprite2) {
        //Not interested in collision with the leftArrow, I was just lazy and didn't care to
        //override update and draw..
        if (sprite != leftArrow && sprite2 != leftArrow) {
            heli1.setSpeed(-heli1.getSpeed().getX(), -heli1.getSpeed().getY());
            heli2.setSpeed(-heli2.getSpeed().getX(), -heli2.getSpeed().getY());
        }
    }
}
