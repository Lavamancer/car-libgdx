package com.lavamancer.car;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Car implements Updatable, Drawable {

    private static final float SPEED = 60;
    Sprite sprite;
    float x = 245;
    float y = 20;
    float acceleration = 0;


    public Car() {
        sprite = new Sprite(new Texture("car.png"));
    }

    @Override
    public void update(float delta) {

        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            acceleration += 5 * delta;
            if (acceleration > 5) acceleration = 5;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            acceleration -= 5 * delta;
            if (acceleration < -2) acceleration = -2;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            x -= SPEED * delta;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            x += SPEED * delta;
        }

        y += (200 * delta) + acceleration;
    }

    @Override
    public void draw(SpriteBatch spriteBatch) {
        spriteBatch.draw(sprite, x, y);
    }
}
