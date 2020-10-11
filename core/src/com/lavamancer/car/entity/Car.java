package com.lavamancer.car.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.lavamancer.car.Main;

public class Car extends Entity {

    private static final float SPEED = 60;
    private static final float ACCELERATION = 5;
    private static final float MAX_ACCELERATION = 11.5f;
    private static final float MIN_ACCELERATION = 0;

    public Sprite sprite;
    public float x = 245;
    public float y = 20;
    public float acceleration = 0;
    Sound sound;
    long soundId;

    private static final float MAX_DIRT_TIMER = 0.1f;
    float dirtTimer = MAX_DIRT_TIMER;


    public Car() {
        sprite = new Sprite(new Texture("car.png"));
        sound = Gdx.audio.newSound(Gdx.files.internal("engine.wav"));
        soundId = sound.loop();
        sound.setVolume(soundId, 0.15f);
    }

    private void checkDirt(float delta) {
        dirtTimer -= delta;
        if (dirtTimer <= 0) {
            dirtTimer = MAX_DIRT_TIMER;
            Main.instance.entities.add(new Dirt(x + 19, y));
            Main.instance.entities.add(new Dirt(x + 2, y));
        }
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

        checkDirt(delta);
    }

    @Override
    public void draw(SpriteBatch spriteBatch) {
        spriteBatch.draw(sprite, x, y);
    }
}
