package com.mygdx.game.Main;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.Actor.Conversation;
import com.mygdx.game.Actor.Meko;
import com.mygdx.game.Actor.TinMonster;

import java.util.ArrayList;

public class FightScreen implements Screen {

    //Meko
    Meko meko;

    //Necessary variables
    OrthographicCamera camera;
    SpriteBatch gameBatch;
    ShapeRenderer shaper;

    float deltaTime;
    float stateTime;
    float dimIn;

    //Enemies
    ArrayList<TinMonster> tins=new ArrayList<TinMonster>();

    ArrayList<Conversation> converseQuer;
    Conversation tutorialConverse;

    //Song
    private Music backgroundMusic;

    //Background
    Texture forest;

    public FightScreen()
    {
        meko=new Meko(400,50);
        dimIn=1f;

        stateTime=0;
        deltaTime=Gdx.graphics.getDeltaTime();
        forest=new Texture(Gdx.files.internal("Forest2.png"));
        backgroundMusic=Gdx.audio.newMusic(Gdx.files.internal("Song/Teacher.mp3"));

        backgroundMusic.setLooping(true);
        backgroundMusic.play();

        camera=new OrthographicCamera();
        gameBatch=new SpriteBatch();
        shaper=new ShapeRenderer();

        camera.setToOrtho(false,Gdx.graphics.getWidth()/4,Gdx.graphics.getHeight()/4);

        //Making the enemies
        tins.add(new TinMonster(1000,50));
        tins.add(new TinMonster(1200,50));
        tins.add(new TinMonster(1400,50));

        //This will be responisble for handling the conversations
        converseQuer=new ArrayList<Conversation>();

        tutorialConverse=new Conversation(meko);
        tutorialConverse.addLine("Hmmmm it seems like your have fallen into a weird dimension","None");
        tutorialConverse.addLine("Watch out for those tin can monsters!","None");
        tutorialConverse.addLine("You can defeat them by punching them","None");

        converseQuer.add(tutorialConverse);



    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta)
    {
        Gdx.gl.glClearColor(0, 1, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.position.x=meko.getX();
        //camera.position.y=meko.getY()+75;
        camera.update();
        gameBatch.setProjectionMatrix(camera.combined);
        shaper.setProjectionMatrix(camera.combined);

        stateTime+=Gdx.graphics.getDeltaTime();

        gameBatch.begin();

        if(dimIn>0)
        {
            dimIn-=0.05f;

            Gdx.gl.glEnable(GL20.GL_BLEND);
            Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
            shaper.begin(ShapeRenderer.ShapeType.Filled);
            shaper.setColor(new Color(0, 0, 0, dimIn));
            shaper.rect(meko.getX()-222,meko.getY()-50 , Gdx.graphics.getWidth()/4, Gdx.graphics.getHeight()/4);
            shaper.end();
            Gdx.gl.glDisable(GL20.GL_BLEND);
        }
        else
        {
            if(converseQuer.size()>0)
            {
                converseQuer.get(0).startConversation(gameBatch);

                if(converseQuer.get(0).isComplete())
                {
                    converseQuer.remove(0);
                }
            }
            else
            {
                meko.setEnableControl(true);
            }

        }

        drawBackground();
        drawEnemies();

        //Handling Meko's moving mechanism
        meko.draw(gameBatch,stateTime);

        gameBatch.end();

    }

    public void drawBackground()
    {
        gameBatch.draw(forest,0,0);
    }

    public void drawEnemies()
    {
        for(TinMonster t:tins)
        {
            t.draw(gameBatch,stateTime);
        }
    }

    public void startMusic()
    {
        backgroundMusic.play();
    }

    public void stopMusic()
    {
        backgroundMusic.stop();
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
