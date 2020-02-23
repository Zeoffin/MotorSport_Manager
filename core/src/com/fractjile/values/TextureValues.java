package com.fractjile.values;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.fractjile.Main;

public class TextureValues {

    public Main game;

    private float window_width = Gdx.graphics.getWidth();
    private float window_height = Gdx.graphics.getHeight();

    // Background Image and logo
    public static Texture background;
    //public static Texture logo;

    public TextureValues(Main game) {

        this.game = game;

    }

    public void loadGlobalTextures() {

        background = new Texture(Gdx.files.internal("background_game.jpg"));
        //logo = new Texture(Gdx.files.internal("mm.png"));

    }

    public void drawGlobalTextures() {

        game.batch.draw(background, 0, 0, window_width, window_height);
        //game.batch.draw(logo,window_width/2-50, window_height/2+80);

    }

    public void disposeGlobalTextures() {

        background.dispose();
        //logo.dispose();

    }

}
