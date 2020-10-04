package com.lavamancer.car;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Car implements Updatable, Drawable {

    private static final float SPEED = 60;
    private static final float ACCELERATION = 5;
    private static final float MAX_ACCELERATION = 9;
    private static final float MIN_ACCELERATION = -2.5f;

    Sprite sprite;
    float x = 245;
    float y = 20;
    float acceleration = 0;
    Sound sound;
    long soundId = -1;


    public Car() {
        sprite = new Sprite(new Texture("car.png"));
        sound = Gdx.audio.newSound(Gdx.files.internal("engine.wav"));
        soundId = sound.loop();
    }

    @Override
    public void update(float delta) {

        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            acceleration += ACCELERATION * delta;
            if (acceleration > MAX_ACCELERATION) acceleration = MAX_ACCELERATION;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            acceleration -= ACCELERATION * delta;
            if (acceleration < MIN_ACCELERATION) acceleration = MIN_ACCELERATION;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            x -= SPEED * delta;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            x += SPEED * delta;
        }

        y += (200 * delta) + acceleration;

        sound.setPitch(soundId, 1 + (acceleration * 0.1f));
    }

    @Override
    public void draw(SpriteBatch spriteBatch) {
        spriteBatch.draw(sprite, x, y);
    }
}
