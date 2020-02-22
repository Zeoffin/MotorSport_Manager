package com.fractjile.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.fractjile.Main;
import com.fractjile.values.NumericValues;
import com.fractjile.values.StringValues;

public class DesktopLauncher {

	// Default windows size on launch
	public static final int windows_height = 720;
	public static final int windows_width = 1280;

	public static void main (String[] arg) {

		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		config.title = StringValues.game_title;
		config.height = windows_height;
		config.width = windows_width;
		config.resizable = false;

		new LwjglApplication(new Main(), config);

	}

}
