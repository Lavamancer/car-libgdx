package com.lavamancer.car;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Camera implements Updatable, Drawable {

    OrthographicCamera camera;
    Car car;


    public Camera(Car car) {
        this.car = car;
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();
        camera = new OrthographicCamera(w, h);
        camera.zoom = 0.35f;
    }

    @Override
    public void update(float delta) {
        camera.position.set(car.x + car.sprite.getWidth() / 2, car.y + 60 + car.sprite.getHeight() / 2, 0);
        camera.update();
    }

    @Override
    public void draw(SpriteBatch spriteBatch) {
        spriteBatch.setProjectionMatrix(camera.combined);
    }

}
