package com.fractjile;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class Tools {

    public TextureAtlas textureAtlas;
    public Skin skin;

    // Converts 0-1 rgb value to allow input of raw rgb value ranging 0 - 255
    public float rgb_to_float(int color) {

        float lib_color = (float)((100*color)/255)/100;

        return lib_color;
    }

    // Public class for loading skin globally
    public void loadSkin() {

        textureAtlas = new TextureAtlas(Gdx.files.internal("ui/uiskin.atlas"));
        skin = new Skin(Gdx.files.internal("ui/uiskin.json"), textureAtlas);

    }

}
