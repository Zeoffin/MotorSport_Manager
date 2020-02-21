package com.fractjile.values;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.fractjile.Main;
import com.fractjile.Tools;

public class TextureValues {

    public Main game;
    private Tools tools = new Tools();

    private float window_width = tools.get_window_width();
    private float window_height = tools.get_window_height();

    // Background Image and logo
    public static Texture background;
    public static Texture logo;

    public TextureValues(Main game) {

        this.game = game;

    }

    public void loadGlobalTextures() {

        background = new Texture(Gdx.files.internal("bg.jpg"));
        logo = new Texture(Gdx.files.internal("mm.png"));

    }

    public void drawGlobalTextures() {

        game.batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        game.batch.draw(logo,window_width/2-50, window_height/2+80);

    }

    public void disposeGlobalTextures() {

        background.dispose();
        logo.dispose();

    }

}
