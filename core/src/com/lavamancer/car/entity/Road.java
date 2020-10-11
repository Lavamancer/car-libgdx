package com.lavamancer.car.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Road extends Entity {

    Sprite sprite;
    Car car;

    float[] roads = new float[7];
    int multiplier;

    public Road(Car car) {
        this.car = car;
        sprite = new Sprite(new Texture("background.jpg"));

        multiplier = (int) (roads.length / 2f);

        for (int i = 0; i < roads.length; i++) {
            roads[i] = sprite.getHeight() * (-multiplier + i);
        }

    }

    @Override
    public void update(float delta) {
        if (car.y - roads[multiplier] > 0) { // 0 1 2 3 4
            for (int i = 0; i < roads.length; i++) {
                roads[i] += sprite.getHeight();
            }
        }
    }

    @Override
    public void draw(SpriteBatch spriteBatch) {
        for (int i = 0; i < roads.length; i++) {
            sprite.setPosition(0, roads[i]);
            sprite.draw(spriteBatch);
        }
    }

}
