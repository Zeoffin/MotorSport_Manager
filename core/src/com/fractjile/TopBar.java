package com.fractjile;

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
import com.fractjile.screens.MenuScreen;


// TODO: Finalise and test in OptionScreen class
public class TopBar {

    // This class implements visual top bar for OptionScreen, PlayScreen and NewGameScreen

    private static Texture back;
    private static Texture back_clicked;
    private static Image btnBack;
    private Stage stage;

    public void listenerTopBar(final Main game) {

        btnBack = new Image(back);

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
        table.add(btnBack).left().top();

        stage = new Stage();
        stage.addActor(table);

    }

    public void drawTopBar() {

        // Draw stage
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();

    }

    public void initializeTopBar() {

        back = new Texture(Gdx.files.internal("optionsScreen/back.png"));
        back_clicked = new Texture(Gdx.files.internal("optionsScreen/back_clicked.png"));

    }


}
