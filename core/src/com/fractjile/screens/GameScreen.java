package com.fractjile.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.fractjile.Main;
import com.fractjile.Tools;
import com.fractjile.values.StringValues;

public class GameScreen implements Screen {

    private Main game;
    private Tools tools = new Tools();

    // Textures
    private Texture background;

    public GameScreen(Main game) {

        this.game = game;
        Main.state = "GameScreen";
        loadTextures();

    }

    @Override
    public void show() {

        Stage stage = new Stage();
        Gdx.input.setInputProcessor(stage);

    }

    @Override
    public void render(float delta) {

        game.batch.begin();
        game.batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        game.font.draw(game.batch, StringValues.game_version, Gdx.graphics.getWidth() *(float)0.9, Gdx.graphics.getHeight()*(float)0.05);
        game.batch.end();

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

    private void loadTextures() {

        background = new Texture(Gdx.files.internal("background_game.jpg"));

    }

}
