package com.studio47.context;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 * Created by Kyle on 7/19/2016.
 */
public class DisplayContext {
    private SpriteBatch spriteBatch;
    private ShapeRenderer shapeRenderer;
    private Camera camera;
    private static DisplayContext displayContext;

    private DisplayContext(SpriteBatch spriteBatch, ShapeRenderer shapeRenderer, Camera camera) {
        this.spriteBatch = spriteBatch;
        this.shapeRenderer = shapeRenderer;
        this.shapeRenderer.setProjectionMatrix(camera.combined);
        this.camera = camera;
    }

    public SpriteBatch getSpriteBatch() {
        return spriteBatch;
    }

    public ShapeRenderer getShapeRenderer() {
        return shapeRenderer;
    }

    public Camera getCamera() {
        return camera;
    }

    public int getHeight() {
        return (int)camera.viewportHeight;
    }

    public int getWidth() {
        return (int)camera.viewportWidth;
    }

    public static void set(SpriteBatch batch, ShapeRenderer shapeRenderer, Camera camera) {
        displayContext = new DisplayContext(batch, shapeRenderer, camera);
    }

    public static DisplayContext get() {
        return displayContext;
    }
}
