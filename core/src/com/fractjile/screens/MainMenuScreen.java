package com.fractjile.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.fractjile.Main;
import com.fractjile.Tools;

import java.awt.*;
import java.awt.event.InputEvent;

public class MainMenuScreen extends ScreenAdapter {

    private Main game;



    // For easy RGB conversion
    private Tools tools = new Tools();

    // To draw shapes
    private ShapeRenderer shapeRenderer = new ShapeRenderer();

    // Background Image and logo
    private static Texture background;
    private static Texture logo;

    // Window height and width
    private float window_width = tools.get_window_width();
    private float window_height = tools.get_window_height();

    private Stage stage;

    // Option menu
    private static Texture single_player;
    private static Texture single_player_clicked;

    private Image btnSP;
    private Image btnSP_clicked;

    private float single_player_x = window_width/2-100;
    private float single_player_y = window_height/2-50;

    // Constructor
    public MainMenuScreen(Main game) {
        this.game = game;
        loadTextures();
    }

    //game.setScreen(new GameScreen(game));  -- transition to next gamescreen

    @Override
    public void show(){

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        btnSP = new Image(single_player);
        btnSP_clicked = new Image(single_player_clicked);

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

    private void loadTextures() {

        background = new Texture(Gdx.files.internal("bg.jpg"));
        logo = new Texture(Gdx.files.internal("mm.png"));
        single_player = new Texture(Gdx.files.internal("mm_sp.png"));
        single_player_clicked = new Texture(Gdx.files.internal("mm_sp_selected.png"));

    }



}
