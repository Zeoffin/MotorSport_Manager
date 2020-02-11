package com.fractjile.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.fractjile.Main;
import com.fractjile.Tools;

import java.awt.event.InputEvent;

public class MainMenuScreen extends ScreenAdapter {

    Main game;

    // For easy RGB conversion
    Tools tools = new Tools();

    // To draw shapes
    ShapeRenderer shapeRenderer = new ShapeRenderer();

    // Background Image and logo
    private static Texture background;
    private static Texture logo;

    // Window height and width
    private float window_width = tools.get_window_width();
    private float window_height = tools.get_window_height();

    // Option menu
    private static Texture single_player;
    float single_player_x = window_width/2-100;
    float single_player_y = window_height/2-50;

    // Constructor
    public MainMenuScreen(Main game) {
        this.game = game;
        loadTextures();
    }

    //game.setScreen(new GameScreen(game));  -- transition to next gamescreen

    @Override
    public void show(){

        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean touchDown(int x, int y, int pointer, int button) {
                if (x >= single_player_x+single_player.getWidth() && y>= single_player_y+single_player.getHeight()) {
                    game.setScreen(new GameScreen(game));
                }
                return true;
            }
        });

    }

    @Override
    public void render(float delta) {

//        Gdx.gl.glClearColor(tools.rgb_to_float(153), tools.rgb_to_float(142), tools.rgb_to_float(112), 1);
//        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(tools.rgb_to_float(163), tools.rgb_to_float(60), tools.rgb_to_float(44),1);
        shapeRenderer.rect(window_width/2 - 100, window_height/2 - 50,200,50);
        shapeRenderer.end();

        game.batch.begin();
        game.batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        game.batch.draw(logo,window_width/2-50, window_height/2+80);
        game.batch.draw(single_player, single_player_x, single_player_y);
        game.batch.end();

        System.out.println(Gdx.graphics.getFramesPerSecond());

    }

    @Override
    public void hide(){
        Gdx.input.setInputProcessor(null);
    }

    public void loadTextures() {

        background = new Texture(Gdx.files.internal("bg.jpg"));
        logo = new Texture(Gdx.files.internal("mm.png"));
        single_player = new Texture(Gdx.files.internal("mm_sp.png"));

    }

}
