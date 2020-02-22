package com.fractjile.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.fractjile.Main;
import com.fractjile.values.NumericValues;
import com.fractjile.values.StringValues;
import com.fractjile.values.TextureValues;

import java.util.HashMap;


public class OptionsScreen implements Screen {

    private Main game;
    private Stage stage;
    private TextureValues textureValues;

    // List for choosing resolution
    private List<String> resolutionList;

    public HashMap<Integer, String> resolutionMap = new HashMap<>();

    // Textures
    private static Texture back;
    private static Texture back_clicked;
    private static Texture apply;
    private static Texture apply_clicked;

    // Buttons
    private Image btnBack;
    private Image btnApply;

    TextButton fullscreen;
    TextButton windowed;

    public OptionsScreen(Main game) {

        this.game = game;
        textureValues = new TextureValues(game);
        loadTextures();
        populateResolutions();

    }

    @Override
    public void show() {

        // Skin
        TextureAtlas textureAtlas = new TextureAtlas(Gdx.files.internal("ui/uiskin.atlas"));
        Skin skin = new Skin(Gdx.files.internal("ui/uiskin.json"), textureAtlas);

        // Text labels for options
        Label resolutionLabel = new Label(StringValues.resolutionText, skin);
        Label fullscreenLabel = new Label(StringValues.fullscreenText, skin);

        // For toggling between fullscreen and windowed
        fullscreen = new TextButton(StringValues.fullscreenToggle, skin);
        windowed = new TextButton(StringValues.windowedToggle, skin);

        final ButtonGroup buttonGroup = new ButtonGroup(windowed, fullscreen);
        buttonGroup.setMaxCheckCount(1);
        buttonGroup.setMinCheckCount(1);
        buttonGroup.setUncheckLast(true);
        buttonGroup.setChecked("Windowed");

        // Scrollpane for selecting resolution
        ScrollPane scrollPane = new ScrollPane(null, skin);

        // Set up resolution hashMap
        resolutionList = new List<>(skin);
        resolutionList.setFillParent(true);
        resolutionList.setItems(StringValues.resolutions);

        // Set up buttons
        btnBack = new Image(back);
        btnApply = new Image(apply);

        // Stage and set inputs
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

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

        // Apply button listener
        btnApply.addListener(new InputListener() {

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {

                if(button == Input.Buttons.LEFT) {
                    btnApply.setDrawable(new TextureRegionDrawable(new TextureRegion(apply_clicked)));
                }

                return true;

            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

                if(button == Input.Buttons.LEFT) {

                    btnApply.setDrawable(new TextureRegionDrawable(new TextureRegion(apply)));

                    // If windowed checked, apply resolution
                    if(buttonGroup.getCheckedIndex() == 0) {

                        // Split resolution values from StringValues into X and Y, then apply
                        String[] splitString = resolutionMap.get(resolutionList.getSelectedIndex()).split("x");
                        Gdx.graphics.setWindowedMode(Integer.parseInt(splitString[0]),Integer.parseInt(splitString[1]));

                    // Apply fullscreen
                    } else if (buttonGroup.getCheckedIndex() == 1) {

                        Gdx.graphics.setFullscreenMode(Gdx.graphics.getDisplayMode());

                    }

                }

            }

        });

        scrollPane.setActor(resolutionList);

        // Set up a table which will be shown
        Table table = new Table();
        table.setFillParent(true);
        table.add(resolutionLabel);
        table.row();
        table.add(scrollPane).size(300,180).colspan(2).padBottom(20);
        table.row();
        table.add(fullscreen).padBottom(20).right();
        table.add(windowed).padBottom(20).left().padLeft(10);
        table.row();
        table.add(btnBack).size(100,50).right();
        table.add(btnApply).size(100,50).left().padLeft(10);

        stage.addActor(table);

    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();

        // Draw background and logo
        textureValues.drawGlobalTextures();

        // Draw version to screen
        game.font.draw(game.batch, StringValues.game_version, NumericValues.version_x_position, NumericValues.version_y_position);

        game.batch.end();

        checkedButton(fullscreen);
        checkedButton(windowed);

        // Draw stage
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();

        goBack();

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
        back_clicked.dispose();
        apply_clicked.dispose();
        apply.dispose();
        stage.dispose();
        game.dispose();

    }


    // Method for loading all textures in this screen
    private void loadTextures() {

        back = new Texture(Gdx.files.internal("optionsScreen/back.png"));
        back_clicked = new Texture(Gdx.files.internal("optionsScreen/back_clicked.png"));
        apply = new Texture(Gdx.files.internal("optionsScreen/apply.png"));
        apply_clicked = new Texture(Gdx.files.internal("optionsScreen/apply_clicked.png"));
        textureValues.loadGlobalTextures();

    }


    // Go back to menu screen using ESC key
    private void goBack() {

        if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {

            game.setScreen(new MenuScreen(game));

        }

    }


    // Populate resolution hashmap with all available resolutions taken from StringValues class
    private void populateResolutions() {

        for(int i = 0; i<StringValues.resolutions.length; i++) {

            resolutionMap.put(i, StringValues.resolutions[i]);

        }

    }


    private void checkedButton(TextButton textButton) {

        if(!textButton.isChecked()) {

            textButton.setColor(1,1,1,(float)0.7);

        } else {

            textButton.setColor((float)0.2,(float)0.2,(float)0.2,(float)0.7);

        }

    }

}
