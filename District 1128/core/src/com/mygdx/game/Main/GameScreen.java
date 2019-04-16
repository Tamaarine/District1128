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

import com.mygdx.game.Actor.Meko;
import com.mygdx.game.Actor.Portal;
import com.mygdx.game.Animation.EntryScene;
import com.mygdx.game.Controls.LeftArrow;
import com.mygdx.game.Controls.RightArrow;

public class GameScreen implements Screen {

    //Setting up all the variables
    MyGdxGame game;
    SpriteBatch gameBatch;
    ShapeRenderer shaper;

    //Blocks and players
    Meko meko;

    OrthographicCamera camera;

    private float ms;

    //All the textures
    Texture roblox;
    Texture bg;
    Texture doorOpen;
    Texture doorClosed;

    //Necesssary varaible
    float delta;
    float stateTime;
    float dimOut;

    //Buttons
    LeftArrow leftArrow;
    RightArrow rightArrow;

    //Animation CutScene
    EntryScene scene1;

    //Portals
    Portal gateToHell;

    //Music
    private Music backgroundMusic;

    public GameScreen(MyGdxGame pGame)
    {
        ms=0;
        game=pGame;
        gameBatch=new SpriteBatch();
        shaper=new ShapeRenderer();
        camera=new OrthographicCamera();
        camera.setToOrtho(false,Gdx.graphics.getWidth()/4,Gdx.graphics.getHeight()/4);
        dimOut=0f;

        meko=new Meko(0,64);
        meko.setReferenceCamera(camera);

        doorOpen=new Texture(Gdx.files.internal("DoorOpen.png"));
        doorClosed=new Texture(Gdx.files.internal("DoorClosed.png"));

        backgroundMusic=Gdx.audio.newMusic(Gdx.files.internal("Song/Second.mp3"));
        backgroundMusic.setLooping(true);
        backgroundMusic.setVolume(1);
        startMusic();

        leftArrow=new LeftArrow(meko);
        rightArrow=new RightArrow(meko);

        scene1=new EntryScene(meko);

        gateToHell=new Portal(game,140,70);

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        stateTime += Gdx.graphics.getDeltaTime();

        camera.position.x=meko.getX();
        //camera.position.y=meko.getY()+75;
        //camera.position.y=meko.getY()+135;
        camera.update();

        gameBatch.setProjectionMatrix(camera.combined);
        shaper.setProjectionMatrix(camera.combined);

        if(!gateToHell.isActivated())
        {
            Gdx.gl.glClearColor(0, 1, 0, 0);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

            if(!scene1.isComplete())
            {
                scene1.act();
            }
            else
            {
                gameBatch.begin();

                shaper.begin(ShapeRenderer.ShapeType.Line);

                drawBackground();

                gateToHell.showPortal(gameBatch,stateTime,meko,meko.getButton2());

                meko.draw(gameBatch,stateTime);

                gameBatch.end();
                shaper.end();


            }
        }
        //Dim this out
        else
        {
            dimOut+=0.01f;

            Gdx.gl.glEnable(GL20.GL_BLEND);
            Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
            shaper.begin(ShapeRenderer.ShapeType.Filled);
            shaper.setColor(new Color(0, 0, 0, dimOut));
            shaper.rect(meko.getX()-222,meko.getY()-64 , Gdx.graphics.getWidth()/4, Gdx.graphics.getHeight()/4);
            shaper.end();
            Gdx.gl.glDisable(GL20.GL_BLEND);

            if(dimOut>1)
            {
                game.setScreen(new FightScreen());

                stopMusic();
            }
        }


    }

    public float getStateTime()
    {
        return stateTime;
    }

    public void drawBackground()
    {
        for(int i=0;i>-5;i--)
        {
            gameBatch.draw(doorClosed,0+(i*Gdx.graphics.getWidth()/4),0,Gdx.graphics.getWidth()/4,Gdx.graphics.getHeight()/4);
            gameBatch.draw(doorClosed,0+(-i*Gdx.graphics.getWidth()/4),0,Gdx.graphics.getWidth()/4,Gdx.graphics.getHeight()/4);
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

    public float map(float value,float low,float high,float toLow,float toHigh)
    {
        return toLow+(value-low)*(toHigh-toLow)/(high-low);
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
