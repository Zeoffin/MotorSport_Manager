package com.fractjile.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.fractjile.Main;
import com.fractjile.values.NumericValues;
import com.fractjile.values.StringValues;

public class GameScreen implements Screen {

    private Main game;
    private Stage stage;

    // Textures
    private Texture background;

    public GameScreen(Main game) {

        this.game = game;
        loadTextures();

    }

    @Override
    public void show() {

        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();
        game.batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        game.font.draw(game.batch, StringValues.game_version, NumericValues.version_x_position, NumericValues.version_y_position);
        game.batch.end();

    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
        game.font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        game.font.getData().setScale(1, 1);
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

        stage.dispose();
        game.dispose();

    }

    private void loadTextures() {

        background = new Texture(Gdx.files.internal("background_game.jpg"));

    }

}
