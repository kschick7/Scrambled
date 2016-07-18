package com.studio47.scrambled.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.studio47.scrambled.Scrambled;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Scrambled";
		config.width = 360;
		config.height = 600;
		config.useGL30 = false;
		config.resizable = false;
		new LwjglApplication(new Scrambled(), config);
	}
}
