package com.lavamancer.car.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Dirt extends Entity {

    private static final float MAX_TIME_LIFE = 3;
    Sprite sprite;
    float x, y;
    float timeLife = MAX_TIME_LIFE;
    float alpha = 0.6f;

    public Dirt(float x, float y) {
        this.x = x;
        this.y = y;
        sprite = new Sprite(new Texture("dirt.png"));
        sprite.setPosition(x, y);
    }

    @Override
    public void draw(SpriteBatch spriteBatch) {
        sprite.setAlpha(alpha);
        sprite.draw(spriteBatch);
    }

    @Override
    public void update(float delta) {
        timeLife -= delta;
        alpha = timeLife / MAX_TIME_LIFE;
        if (timeLife <= 0) {

        }
    }
}
