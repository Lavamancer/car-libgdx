package com.lavamancer.car;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Road implements Updatable, Drawable {

    Sprite sprite;
    Car car;

    float firstY;
    float secondY;
    float thirdY;


    public Road(Car car) {
        this.car = car;
        sprite = new Sprite(new Texture("background.jpg"));

        firstY = sprite.getHeight();
        secondY = 0;
        thirdY = -sprite.getHeight();
    }

    @Override
    public void update(float delta) {
        if (car.y - thirdY > 180) {
            firstY += sprite.getHeight();
            secondY += sprite.getHeight();
            thirdY += sprite.getHeight();
        }
    }

    @Override
    public void draw(SpriteBatch spriteBatch) {
        sprite.setPosition(0, firstY);
        sprite.draw(spriteBatch);

        sprite.setPosition(0, secondY);
        sprite.draw(spriteBatch);

        sprite.setPosition(0, thirdY);
        sprite.draw(spriteBatch);
    }

}
