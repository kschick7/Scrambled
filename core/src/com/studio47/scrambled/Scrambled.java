package com.studio47.scrambled;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.studio47.context.DisplayContext;
import com.studio47.managers.GameStateManager;

public class Scrambled extends ApplicationAdapter {
	public static int WIDTH;
	public static int HEIGHT;

	private GameStateManager gameStateManager;
	
	@Override
	public void create () {
		WIDTH = Gdx.graphics.getWidth();
		HEIGHT = Gdx.graphics.getHeight();
		OrthographicCamera camera = new OrthographicCamera(WIDTH, HEIGHT);
		camera.translate(WIDTH / 2, HEIGHT / 2);
		camera.update();

		DisplayContext.init(new SpriteBatch(), new ShapeRenderer(), camera);
		gameStateManager = new GameStateManager();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gameStateManager.update(Gdx.graphics.getDeltaTime());
		DisplayContext.getSpriteBatch().begin();
		gameStateManager.draw();
		DisplayContext.getSpriteBatch().end();
	}
	
	@Override
	public void dispose () {
		gameStateManager.dispose();
	}
}
