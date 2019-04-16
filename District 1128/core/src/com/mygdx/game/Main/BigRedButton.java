package com.mygdx.game.Main;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class BigRedButton implements Screen
{
    MyGdxGame game;
    SpriteBatch batch;
    Sprite button;
    Texture buttonIcon;
    Texture backGround;
    Texture textTab;
    public int score = 0;
    public int iconSizeW = 100;
    public int iconSizeH = 100;

    public int iconX = Gdx.graphics.getWidth()/2 - iconSizeW/2;
    public int iconY = Gdx.graphics.getHeight()/2 - iconSizeH/2;

    public int ScreenWidth = Gdx.graphics.getWidth();
    public int ScreenHeight = Gdx.graphics.getHeight();

    public BigRedButton(MyGdxGame myGame)
    {
        game = myGame;
        batch = new SpriteBatch();
        buttonIcon = new Texture("earth-icon.png");
        backGround = new Texture("background-the forest.png");
        Sprite button = new Sprite(buttonIcon);
        button.setSize(iconSizeW,iconSizeH);
        button.setPosition(iconX,iconY);

    }
    @Override
    public void show()
    {

    }

    @Override
    public void render(float delta)
    {
        if(Gdx.input.isTouched())
        {
            if(isHovering(button))
            {
                score++;
            }
        }
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(backGround,0,0,ScreenWidth, ScreenHeight);
        batch.draw(button, iconX,iconY, iconSizeW, iconSizeH);
        if(score > 500)
        {
            game.setScreen(new BigRedButton(game));
        }
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
        batch.dispose();

    }
    public boolean isHovering(Sprite sprite)
    {
        if (Gdx.input.isTouched()) {

            if (Gdx.input.getX() > sprite.getX() && Gdx.input.getX() < sprite.getX() + sprite.getWidth())
            {
                if (Gdx.input.getY() > sprite.getY() && Gdx.input.getY() < sprite.getY() + sprite.getHeight())
                {

                    return true;
                }
            }
        }
        return false;
    }
    public void ScrollRight(Sprite sprite)
    {

    }
    public double compenSize(int Pos, int min, int max)
    {
        //THIS HAS BEEN TESTED BY WALID KHAN
        //*max-min)/2 should be the maximum size
        int center = (int)((max-min)/2) + min;
        //distance away from center
        int dist = Math.abs(Pos-center);
        double ratio = dist/center;
        return 1- Math.abs(ratio) + .5;
    }

}


