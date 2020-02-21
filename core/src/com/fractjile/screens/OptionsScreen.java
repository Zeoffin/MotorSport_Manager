package com.fractjile.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.fractjile.Main;
import com.fractjile.values.StringValues;
import com.fractjile.values.TextureValues;

public class OptionsScreen implements Screen {

    private Main game;
    private TextureValues textureValues;

    public OptionsScreen(Main game) {

        this.game = game;
        textureValues = new TextureValues(game);
        loadTextures();
        Main.state = "OptionsScreen";

    }

    @Override
    public void show() {

        Stage stage = new Stage();
        Gdx.input.setInputProcessor(stage);

    }

    @Override
    public void render(float delta) {

        game.batch.begin();
        textureValues.drawGlobalTextures();
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

        textureValues.disposeGlobalTextures();

    }

    private void loadTextures() {

        textureValues.loadGlobalTextures();

    }

}
