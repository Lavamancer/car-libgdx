package com.lavamancer.car;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.lavamancer.car.entity.Car;
import com.lavamancer.car.entity.Drawable;
import com.lavamancer.car.entity.Updatable;

public class Camera implements Updatable, Drawable {

    private final static int OFFSET_Y = 150;
    OrthographicCamera camera;
    Car car;
    float x;
    float carX;


    public Camera(Car car) {
        this.car = car;
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();
        camera = new OrthographicCamera(w, h);
        camera.zoom = 0.6f;
        x = car.x + car.sprite.getWidth() / 2f;
    }

    @Override
    public void update(float delta) {
        carX = car.x + car.sprite.getWidth() / 2f;

        if (carX > x) x += (carX - x) * 2 * delta;
        if (carX < x) x -= (x - carX) * 2 * delta;

        camera.position.set(x, car.y + OFFSET_Y + car.sprite.getHeight() / 2f, 0);

        float zoom = (car.acceleration * 0.02f);

        camera.zoom = 0.4f + zoom;

        camera.update();
    }

    @Override
    public void draw(SpriteBatch spriteBatch) {
        spriteBatch.setProjectionMatrix(camera.combined);
    }

}
