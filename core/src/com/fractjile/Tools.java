package com.fractjile;

public class Tools {

    public float rgb_to_float(int color) {

        float lib_color = (float)((100*color)/255)/100;

        return lib_color;
    }

}
