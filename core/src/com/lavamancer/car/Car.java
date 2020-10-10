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
    private static final float MAX_ACCELERATION = 11.5f;
    private static final float MIN_ACCELERATION = 0;

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
        sound.setVolume(soundId, 0.15f);
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
            x -= delta * (acceleration + 1) * 20;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            x += delta * (acceleration + 1) * 20;
        }

        y += (200 * delta) + (acceleration - 2.5f);

        sound.setPitch(soundId, 1 + ((acceleration - 2.5f) * 0.1f));
    }

    @Override
    public void draw(SpriteBatch spriteBatch) {
        spriteBatch.draw(sprite, x, y);
    }
}
