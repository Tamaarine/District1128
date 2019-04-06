package com.mygdx.game.Main;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.Actor.Block;
import com.mygdx.game.Actor.Conversation;
import com.mygdx.game.Actor.Dialogue;
import com.mygdx.game.Actor.Meko;

import java.util.ArrayList;

public class GameScreen implements Screen {

    //Setting up all the variables
    MyGdxGame game;
    SpriteBatch gameBatch;

    //Blocks and players
    Meko meko;
    ArrayList<Block> blocks;

    OrthographicCamera camera;

    private float ms;

    //All the textures
    Texture roblox;
    Texture bg;

    //Necesssary varaible
    float delta;
    float stateTime;

    //Dialogue
    Conversation converse;

    public GameScreen(MyGdxGame pGame)
    {
        ms=0;
        pGame=game;
        gameBatch=new SpriteBatch();
        camera=new OrthographicCamera();
        camera.setToOrtho(false,Gdx.graphics.getWidth()/4,Gdx.graphics.getHeight()/4);

        meko=new Meko(0,64);
        blocks=new ArrayList<Block>();

        blocks.add(new Block(0,0));
        blocks.add(new Block(64,0));
        blocks.add(new Block(128,0));
        blocks.add(new Block(192,0));
        blocks.add(new Block(256,0));

        converse=new Conversation();
        converse.addDialogue("Hello there","Hi I did not see you","Hello","Meko");
        converse.addDialogue("Welcome to our game","It is really not that much for now","I will try to work on it as soon as i can","Meko");

        bg=new Texture(Gdx.files.internal("Hallway1.png"));


    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 1, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stateTime += Gdx.graphics.getDeltaTime();

        gameBatch.setProjectionMatrix(camera.combined);

        //This means that only if the conversation is done then allow the character to move
        if(converse.isComplete())
        {
            if (Gdx.input.isTouched()) {
                float touchX = Gdx.input.getX();

                //This means that we are moving the character to the right
                if (touchX >= Gdx.graphics.getWidth() / 2) {
                    meko.moveRight(delta);
                } else if (touchX <= Gdx.graphics.getWidth() / 2) {
                    meko.moveLeft(delta);
                }
            }
            //This line of code is for reinforcement
            else {
                meko.setActionCode(0);
            }
        }

        gameBatch.begin();

        gameBatch.draw(bg,0,0);


        //This means that Meko is walking to the left
        if (meko.getActionCode() == -1) {
            meko.drawLeftWalkAnimation(stateTime, gameBatch);
        }
        //This means that Meko is walking to the right
        else if (meko.getActionCode() == 1) {
            meko.drawRightWalkAnimation(stateTime, gameBatch);
        }
        //This means that Meko is not moving
        else {
            meko.draw(gameBatch);
        }



        converse.nextLine(gameBatch);


        gameBatch.end();



    }

    public float getStateTime()
    {
        return stateTime;
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
}
