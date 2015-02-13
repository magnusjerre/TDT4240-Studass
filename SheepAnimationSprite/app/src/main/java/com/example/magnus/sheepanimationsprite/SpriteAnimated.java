package com.example.magnus.sheepanimationsprite;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;

import sheep.game.Sprite;
import sheep.graphics.Image;
import sheep.math.Vector2;

/**
 * Created by Magnus on 13/02/15.
 */
public class SpriteAnimated extends Sprite {

    private Sprite[] subSprites;
    private float elapsedTimeForFrame = 0f;
    private float animationTimePerFrame = 0.1f;
    private int currentFrame = 0;

    private SpriteAnimated(Image[] images) {
        super(images[0]);
        subSprites = new Sprite[images.length];
        for (int i = 0; i < subSprites.length; i++) {
            subSprites[i] = new Sprite(images[i]);
        }
    }

    public static SpriteAnimated createFromSheet(Bitmap imageSheet, int numberOfFrames) {
        int frameWidth = imageSheet.getWidth() / numberOfFrames;
        int frameHeight = imageSheet.getHeight();

        Image[] frames = new Image[numberOfFrames];
        for (int i = 0; i < numberOfFrames; i++) {
            Bitmap frame = Bitmap.createBitmap(imageSheet, i * frameWidth, 0, frameWidth, frameHeight);
            BitmapDrawable drawable = new BitmapDrawable(Singleton.getInstance().resources, frame);
            frames[i] = new Image(drawable);
        }

        return new SpriteAnimated(frames);

    }

    public static SpriteAnimated CreateFromArrayOfImages(Image[] images) {
        return new SpriteAnimated(images);
    }

    @Override
    public void draw(Canvas canvas) {
        subSprites[currentFrame].draw(canvas);
    }

    @Override
    public void update(float dt) {
        super.update(dt);

        elapsedTimeForFrame += dt;
        if (elapsedTimeForFrame > animationTimePerFrame) {
            elapsedTimeForFrame = 0f;
            currentFrame = (currentFrame + 1) % subSprites.length;
        }

        updateCurrentSprite(dt);
    }

    private void updateCurrentSprite(float dt) {
        Sprite currentSprite = subSprites[currentFrame];
        currentSprite.setPosition(getPosition());
        currentSprite.setScale(getScale());
        currentSprite.setOrientation(getOrientation());
        currentSprite.setOffset(getOffset());
        currentSprite.update(dt);
    }

    public void setAnimationTimePerFrame(float animationTime) {
        animationTimePerFrame = animationTime;
    }

}
