package com.mygdx.game.Main;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class EntryPoint implements Screen
{
    SpriteBatch batch;
    Texture img;
    Texture bg;
    Texture titlePng;

    //Final variables
    float sysWidth;
    float sysHeight;

    MyGdxGame game;

    public EntryPoint(MyGdxGame pGame)
    {
        game=pGame;

        //Initalizating the variables
        batch = new SpriteBatch();
        img = new Texture("badlogic.jpg");
        bg=new Texture("StartBG.png");
        titlePng=new Texture("Title.png");

    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        float test=(sysWidth-762)/2;

        batch.begin();

        batch.draw(bg,0,0,1776,1080);

        batch.draw(titlePng,443.5f,800,889,217,0,0,127,31,false,false);
        batch.draw(titlePng,(Gdx.graphics.getWidth()-762)/2,642,762,138,0,42,127,23,false,false);

        if(Gdx.input.isTouched())
        {
            float touchX=Gdx.input.getX();
            float touchY=Gdx.input.getY();

            System.out.print(touchX+" ");
            System.out.println(touchY);

            if(touchX>=507&&touchX<=1269)
            {
                if(touchY>=300&&touchY<=438)
                {
                    game.setScreen(new GameScreen(game));
                }
            }

        }



        batch.end();

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
    public void dispose () {
        batch.dispose();
        img.dispose();
    }
}
