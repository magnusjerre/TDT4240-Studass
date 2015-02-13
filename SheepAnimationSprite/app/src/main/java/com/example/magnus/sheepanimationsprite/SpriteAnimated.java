package com.example.magnus.sheepanimationsprite;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;

import sheep.game.Sprite;
import sheep.graphics.Image;
import sheep.math.Vector2;

/**
 * Created by Magnus on 13/02/15.
 *
 * This sprite contains several sprites that are used for drawing only. It is the main sprite
 * (aka. this) that is used for setting speed, acceleration and such, and also for handling
 * collisions.
 *
 * There are other ways to do sprite animation, but this way is easy to understand and has the
 * benefit of keeping the advantages of the built-in sprite functionality.
 */
public class SpriteAnimated extends Sprite {

    private Sprite[] subSprites;    //The sprites used for animation
    private float elapsedTimeForFrame = 0f; //Elapsed time for current sprite
    private float animationTimePerFrame = 0.1f; //Time per frame
    private int currentFrame = 0;   //The sprite (frame) which should be drawn now

    private SpriteAnimated(Image[] images) {
        super(images[0]);   //Normal sprite initialisation with the first sprite in the array
        subSprites = new Sprite[images.length];
        for (int i = 0; i < subSprites.length; i++) {
            subSprites[i] = new Sprite(images[i]);  //Create and add each sprite/frame
        }
    }

    /**
     * Creates an AnimationSprite based on a bitmap that contains some number of images used in
     * the animation
     * @param imageSheet
     * @param numberOfFrames
     * @return
     */
    public static SpriteAnimated createFromSheet(Bitmap imageSheet, int numberOfFrames) {
        int frameWidth = imageSheet.getWidth() / numberOfFrames;    //Width per frame/sprite
        int frameHeight = imageSheet.getHeight();   //Height per frame/sprite

        Image[] frames = new Image[numberOfFrames];
        for (int i = 0; i < numberOfFrames; i++) {
            Bitmap frame = Bitmap.createBitmap(imageSheet, i * frameWidth, 0, frameWidth, frameHeight);
            BitmapDrawable drawable = new BitmapDrawable(Singleton.getInstance().resources, frame);
            frames[i] = new Image(drawable);
        }

        return new SpriteAnimated(frames);

    }

    /**
     * Creates an AnimationSprite from an array of images
     * @param images
     * @return
     */
    public static SpriteAnimated CreateFromArrayOfImages(Image[] images) {
        return new SpriteAnimated(images);
    }

    @Override
    public void draw(Canvas canvas) {
        //Only draw the current sprite/frame. Don't do: this.draw(canvas)
        subSprites[currentFrame].draw(canvas);
    }

    @Override
    public void update(float dt) {
        super.update(dt);   //Update this as normal

        elapsedTimeForFrame += dt;  //Update elapsed time with dt
        if (elapsedTimeForFrame > animationTimePerFrame) {
            elapsedTimeForFrame = 0f;
            currentFrame = (currentFrame + 1) % subSprites.length;
        }

        updateCurrentSprite(dt);
    }

    /**
     * Only some properties of the current sub-sprite should be updated; position, scale,
     * orientation and offset. Only these need to be updated for the sub-sprites to be drawn
     * correctly with respect to each of these properties.
     * @param dt
     */
    private void updateCurrentSprite(float dt) {
        Sprite currentSprite = subSprites[currentFrame];
        currentSprite.setPosition(getPosition());
        currentSprite.setScale(getScale());
        currentSprite.setOrientation(getOrientation());
        currentSprite.setOffset(getOffset());
        currentSprite.update(dt);
    }

    /**
     * The default is 0.1 seconds per frame.
     * @param animationTime
     */
    public void setAnimationTimePerFrame(float animationTime) {
        animationTimePerFrame = animationTime;
    }

}
