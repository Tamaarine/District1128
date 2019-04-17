package com.mygdx.game.Main;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class StatScreen implements Screen
{
    //Necessary variables
    private BitmapFont font;
    private SpriteBatch batch;
    private OrthographicCamera camera;

    //Textures
    private Texture bottle;
    private Texture user;
    private Texture scan;

    //Numbers for the pictures
    private int bottleNum=25665;
    private int userNum=5789;
    private int scanNum=12832;



    public StatScreen()
    {
        font=new BitmapFont();
        batch=new SpriteBatch();
        camera=new OrthographicCamera();

        camera.setToOrtho(false, Gdx.graphics.getWidth()/4,Gdx.graphics.getHeight()/4);


    }

    @Override
    public void show()
    {

    }

    @Override
    public void render(float delta)
    {
        //Clearing the screen
        Gdx.gl.glClearColor(0, 1, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //Setting the camera
        batch.setProjectionMatrix(camera.combined);

        batch.begin();

        font.draw(batch,"DashBoard",Gdx.graphics.getWidth()/8,200);

        








        batch.end();



    }

    @Override
    public void resize(int width, int height)
    {

    }

    @Override
    public void pause()
    {

    }

    @Override
    public void resume()
    {

    }

    @Override
    public void hide()
    {

    }

    @Override
    public void dispose()
    {

    }
}
