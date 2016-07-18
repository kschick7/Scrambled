package com.studio47.scrambled;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.studio47.managers.GameStateManager;

public class Scrambled extends ApplicationAdapter {
	public static int WIDTH;
	public static int HEIGHT;

	public static OrthographicCamera camera;
	private SpriteBatch batch;
	private GameStateManager gameStateManager;
	
	@Override
	public void create () {
		WIDTH = Gdx.graphics.getWidth();
		HEIGHT = Gdx.graphics.getHeight();
		camera = new OrthographicCamera(WIDTH, HEIGHT);
		camera.translate(WIDTH / 2, HEIGHT / 2);
		camera.update();

		batch = new SpriteBatch();
		gameStateManager = new GameStateManager();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		gameStateManager.draw(batch);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		gameStateManager.dispose();
	}
}
