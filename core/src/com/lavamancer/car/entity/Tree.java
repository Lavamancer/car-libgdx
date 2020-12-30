package com.lavamancer.car.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.lavamancer.car.Main;
import com.lavamancer.car.util.Assets;
import com.lavamancer.car.util.Drawable;
import com.lavamancer.car.util.Entity;
import com.lavamancer.car.util.Updatable;

public class Tree extends Entity {

    private static final int SPAWN_X = 320;
    private static final int SPAWN_X_MARGIN = 120;
    private static final int SPAWN_X_OFFSET = 200;

    Sprite sprite;
    float x, y;
    static int count;

    // TODO pool
    // TODO destroy
    
    public static void spawn(float delta) {
        if (Math.random() > 0.595f) {
            float offsetX = (float) (Math.signum(Math.random() - 0.5f) * (SPAWN_X_MARGIN + Math.random() * SPAWN_X_OFFSET));
            Main.instance.entities.add(new Tree(SPAWN_X + offsetX, Main.instance.car.y + 500));
        }
    }

    private Tree(float x, float y) {
        System.out.println("Tree " + ++count);
        sprite = new Sprite(Assets.getInstance().load("tree.png", Texture.class));
        this.x = x;
        this.y = y;
        sprite.setPosition(x, y);
    }

    @Override
    public void draw(SpriteBatch spriteBatch) {
        sprite.draw(spriteBatch);
    }

    @Override
    public void update(float delta) {

    }

}
