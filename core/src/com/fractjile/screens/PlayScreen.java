package com.fractjile.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.fractjile.Main;
import com.fractjile.Tools;
import com.fractjile.values.NumericValues;
import com.fractjile.values.StringValues;
import com.fractjile.values.TextureValues;

public class PlayScreen implements Screen {

    private Main game;
    private Stage stage;

    // Tools class
    Tools tools = new Tools();

    private TextButton start_new_game;
    private TextButton continue_game;

    private Texture back;
    private Texture back_clicked;
    private Image btnBack;

    private TextureValues textureValues;

    public PlayScreen(Main game) {

        this.game = game;
        textureValues = new TextureValues(game);
        textureValues.loadGlobalTextures();
        loadTextures();

    }

    @Override
    public void show() {

        tools.loadSkin();

        btnBack = new Image(back);

        start_new_game = new TextButton("Start new game\n\n\nCreate your character\nand take control\nof a team",tools.skin);
        continue_game = new TextButton("Continue\n\n\nContinue your last\nsaved game",tools.skin);

        start_new_game.getLabel();

        start_new_game.setTransform(true);
        continue_game.setTransform(true);

        // Back button listener
        btnBack.addListener(new InputListener() {

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {

                if(button == Input.Buttons.LEFT) {
                    btnBack.setDrawable(new TextureRegionDrawable(new TextureRegion(back_clicked)));
                }

                return true;

            }


            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

                if(button == Input.Buttons.LEFT) {

                    btnBack.setDrawable(new TextureRegionDrawable(new TextureRegion(back)));
                    btnBack.addAction(Actions.removeActor());
                    game.setScreen(new MenuScreen(game));
                }
            }

        });

        Table table = new Table();
        table.setFillParent(true);
        table.add(start_new_game).minSize(200,300);
        table.add(continue_game).minSize(200,300).padLeft(20);
        table.row();
        table.add(btnBack).size(100,50).colspan(2).padTop(20);

        stage = new Stage();
        stage.addActor(table);
        Gdx.input.setInputProcessor(stage);

    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();
        textureValues.drawGlobalTextures();
        game.font.draw(game.batch, StringValues.game_version, NumericValues.version_x_position, NumericValues.version_y_position);
        game.batch.end();

        // Draw stage
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();

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

        textureValues.disposeGlobalTextures();
        back.dispose();
        stage.dispose();
        game.dispose();

    }

    private void loadTextures() {

        back = new Texture(Gdx.files.internal("optionsScreen/back.png"));
        back_clicked = new Texture(Gdx.files.internal("optionsScreen/back_clicked.png"));

    }

}
