package com.fractjile;

import com.badlogic.gdx.Gdx;

public class Tools {

    public float rgb_to_float(int color) {

        float lib_color = (float)((100*color)/255)/100;

        return lib_color;
    }

    public float get_window_width() {
        return Gdx.graphics.getWidth();
    }

    public float get_window_height() {
        return Gdx.graphics.getHeight();
    }

}
