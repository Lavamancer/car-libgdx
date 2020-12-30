package com.lavamancer.car.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.lavamancer.car.Main;
import com.lavamancer.car.util.Entity;

public class Car extends Entity {

    private static final float SPEED = 60;
    private static final float ACCELERATION = 5;
    private static final float MAX_ACCELERATION = 11.5f;
    private static final float MIN_ACCELERATION = 0;

    public Sprite sprite;
    public float x = 245;
    public float y = 20;
    public float acceleration;
    Sound sound;
    long soundId;
    private float angleSpeed;
    private float rotation;

    private static final float MAX_DIRT_TIMER = 0.1f;
    float dirtTimer = MAX_DIRT_TIMER;


    public Car() {
        sprite = new Sprite(new Texture("car.png"));
        sound = Gdx.audio.newSound(Gdx.files.internal("engine.wav"));
        soundId = sound.loop();
        sound.setVolume(soundId, 0.15f);
    }

    private void checkDirt(float delta) {
        float dirtTime = delta * acceleration * 2; // TODO fix time generator
        dirtTimer -= dirtTime <= 0 ? delta : dirtTime;
        if (dirtTimer <= 0) {
            dirtTimer = MAX_DIRT_TIMER;
            Main.instance.entities.add(Dirt.create(x + 19, y));
            Main.instance.entities.add(Dirt.create(x + 2, y));
        }
    }

    private void checkAngle(float delta) {
        rotation = -angleSpeed * 4f * delta;
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
        angleSpeed = 0;
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            angleSpeed = (acceleration + 1) * -20;
            x += delta * angleSpeed;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            angleSpeed = (acceleration + 1) * 20;
            x += delta * angleSpeed;
        }

        y += (200 * delta) + (acceleration - 2.5f);

        sound.setPitch(soundId, 1 + ((acceleration - 2.5f) * 0.1f));

        checkDirt(delta);
        checkAngle(delta);
    }

    @Override
    public void draw(SpriteBatch spriteBatch) {
        sprite.setPosition(x, y);
        sprite.setRotation(rotation);
        sprite.draw(spriteBatch);
    }

}
