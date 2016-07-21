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

    public static SpriteBatch getSpriteBatch() {
        return displayContext.spriteBatch;
    }

    public static ShapeRenderer getShapeRenderer() {
        return displayContext.shapeRenderer;
    }

    public static Camera getCamera() {
        return displayContext.camera;
    }

    public static int getScreenHeight() {
        return (int)displayContext.camera.viewportHeight;
    }

    public static int getScreenWidth() {
        return (int)displayContext.camera.viewportWidth;
    }

    public static void init(SpriteBatch batch, ShapeRenderer shapeRenderer, Camera camera) {
        displayContext = new DisplayContext(batch, shapeRenderer, camera);
    }
}
