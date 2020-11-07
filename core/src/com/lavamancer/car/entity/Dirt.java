package com.lavamancer.car.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Pool;
import com.lavamancer.car.Main;
import com.lavamancer.car.util.Assets;
import com.lavamancer.car.util.Entity;

import java.util.ArrayList;

public class Dirt extends Entity {

    private static final float MAX_TIME_LIFE = 3;
    Sprite sprite;
    float x, y;
    float timeLife;
    float alpha;

    private static ArrayList<Dirt> pool = new ArrayList<>(); // TODO make generic
    public static Dirt newObject(float x, float y) {
        if (pool.isEmpty()) {
            return new Dirt(x, y);
        } else {
            Dirt dirt = pool.get(0);
            pool.remove(0);
            dirt.init(x, y);
            return dirt;
        }
    }

    public void destroy() {
        pool.add(this);
        Main.instance.entities.remove(this);
    }

    public Dirt(float x, float y) {
        sprite = new Sprite((Texture) Assets.getInstance().load("dirt.png", Texture.class));
        init(x, y);
    }

    private void init(float x, float y) {
        timeLife = MAX_TIME_LIFE;
        alpha = 0.6f;
        this.x = x;
        this.y = y;
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
        alpha = (timeLife / MAX_TIME_LIFE) - 0.5f;
        if (timeLife <= 0) {
            destroy();
        }
    }

}
