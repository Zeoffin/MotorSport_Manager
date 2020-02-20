package com.fractjile.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.fractjile.Main;
import com.fractjile.Tools;
import com.fractjile.values.StringValues;

public class MenuScreen implements Screen {

    private Main game;

    // For easy RGB conversion
    private Tools tools = new Tools();

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
    private static Texture options;
    private static Texture options_selected;
    private static Texture quit;
    private static Texture quit_selected;

    private Image btnSP, btnOption, btnQuit;

    // Constructor
    public MenuScreen(Main game) {
        this.game = game;
        Main.state = "MenuScreen";
        loadTextures();
    }

    @Override
    public void show() {

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        btnSP = new Image(single_player);
        btnOption = new Image(options);
        btnQuit = new Image(quit);

        // Single player button listeners
        btnSP.addListener(new InputListener() {

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {

                if(button == Input.Buttons.LEFT) {
                    btnSP.setDrawable(new TextureRegionDrawable(new TextureRegion(single_player_clicked)));
                }

                return true;

            }


            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

                if(button == Input.Buttons.LEFT) {

                    btnSP.setDrawable(new TextureRegionDrawable(new TextureRegion(single_player)));
                    btnSP.addAction(Actions.removeActor());
                    game.setScreen(new GameScreen(game));
                }
            }

        });

        // Options listener
        btnOption.addListener(new InputListener() {

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {

                if(button == Input.Buttons.LEFT) {
                    btnOption.setDrawable(new TextureRegionDrawable(new TextureRegion(options_selected)));
                }

                return true;

            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

                if(button == Input.Buttons.LEFT) {

                    btnOption.setDrawable(new TextureRegionDrawable(new TextureRegion(options)));
                    //btnSP.addAction(Actions.removeActor());

                }
            }

        });

        // Quit listener
        btnQuit.addListener(new InputListener() {

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {

                if(button == Input.Buttons.LEFT) {

                    btnQuit.setDrawable(new TextureRegionDrawable(new TextureRegion(quit_selected)));
                    return true;

                }

                return true;

            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

                if(button == Input.Buttons.LEFT) {

                    btnQuit.setDrawable(new TextureRegionDrawable(new TextureRegion(quit)));
                    //btnSP.addAction(Actions.removeActor());

                }

            }

        });

        Table table = new Table();
        table.setFillParent(true);
        table.row();
        table.add(btnSP).size(200,100).padTop(150);
        table.row();
        table.add(btnOption).size(200,100).padTop(5);
        table.row();
        table.add(btnQuit).size(200,100).padTop(5);

        stage.addActor(table);

    }

    @Override
    public void render(float delta) {

        game.batch.begin();
        game.batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        game.batch.draw(logo,window_width/2-50, window_height/2+80);
        game.font.draw(game.batch, StringValues.game_version, Gdx.graphics.getWidth() *(float)0.9, Gdx.graphics.getHeight()*(float)0.05);
        game.batch.end();

        stage.act();
        stage.draw();

        System.out.println(Gdx.graphics.getFramesPerSecond());

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

        single_player.dispose();
        single_player_clicked.dispose();
        logo.dispose();
        background.dispose();
        stage.dispose();

    }

    private void loadTextures() {

        background = new Texture(Gdx.files.internal("bg.jpg"));
        logo = new Texture(Gdx.files.internal("mm.png"));
        single_player = new Texture(Gdx.files.internal("mm_sp.png"));
        options = new Texture(Gdx.files.internal("mm_options.png"));
        options_selected = new Texture(Gdx.files.internal("mm_options_selected.png"));
        quit = new Texture(Gdx.files.internal("mm_quit.png"));
        quit_selected = new Texture(Gdx.files.internal("mm_quit_selected.png"));
        single_player_clicked = new Texture(Gdx.files.internal("mm_sp_selected.png"));

    }

}