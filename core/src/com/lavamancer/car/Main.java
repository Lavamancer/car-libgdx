package com.lavamancer.car;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.HashMap;

public class Main extends ApplicationAdapter {

//	public static final int SCREEN_WIDTH = 128 * 6;
//	public static final int SCREEN_HEIGHT = 128 * 4;
	public static final int SCREEN_WIDTH = 128 * 4;
	public static final int SCREEN_HEIGHT = 128 * 7;

	SpriteBatch spriteBatch;
	Camera camera;
	Road road;
	Car car;


	@Override
	public void create () {
		spriteBatch = new SpriteBatch();
		car = new Car();
		camera = new Camera(car);
		road = new Road(car);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) Gdx.app.exit();

		float delta = Gdx.graphics.getDeltaTime();

		car.update(delta);
		camera.update(delta);
		road.update(delta);

		spriteBatch.begin();
		camera.draw(spriteBatch);
		road.draw(spriteBatch);
		car.draw(spriteBatch);
		spriteBatch.end();
	}

}
