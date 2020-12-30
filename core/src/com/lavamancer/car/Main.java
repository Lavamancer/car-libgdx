package com.lavamancer.car;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.lavamancer.car.entity.Car;
import com.lavamancer.car.entity.Tree;
import com.lavamancer.car.util.Entity;
import com.lavamancer.car.entity.Road;

import java.util.ArrayList;

public class Main extends ApplicationAdapter {

	public static final int SCREEN_WIDTH = 128 * 4;
	public static final int SCREEN_HEIGHT = 128 * 7;

	public static Main instance;

	SpriteBatch spriteBatch;
	Camera camera;
	Road road;
	public Car car;
	public ArrayList<Entity> entities = new ArrayList<>();
	ArrayList<Entity> entitiesAux = new ArrayList<>();


	@Override
	public void create () {
		instance = this;
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

		Tree.spawn(delta);

		car.update(delta);
		camera.update(delta);
		road.update(delta);
		entitiesAux.clear();
		entitiesAux.addAll(entities);
		for (Entity entity : entitiesAux) {
			entity.update(delta);
		}

		spriteBatch.begin();
		camera.draw(spriteBatch);
		road.draw(spriteBatch);
		car.draw(spriteBatch);
		for (int i = entities.size() - 1; i >= 0; i--) {
			Entity entity = entities.get(i);
			entity.draw(spriteBatch);
		}
		spriteBatch.end();
	}

}
