package com.dvdfu.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.dvdfu.game.MainGame;

public class DesktopLauncher {
	public static void main(String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 1366/2;
		config.height = 768/2;
		config.resizable = false;
		new LwjglApplication(new MainGame(), config);
	}
}
