package com.mygdx.game.Main;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ShopClass implements Screen
{
    MyGdxGame game;
    SpriteBatch batch;
    Sprite merchantIcon;
    Sprite storeIcon;
    Sprite textTabIcon;
    Sprite fireWeapon;
    Sprite vaccuumWeapon;

    Texture imgBack;
    Texture shopKeeper;
    Texture textTab;
    Texture Back;
    Texture weaponA;
    Texture weaponB;

    public ShopClass(MyGdxGame myGame)
    {
        game = myGame;
        batch = new SpriteBatch();
        imgBack = new Texture("store.png");
        shopKeeper = new Texture("merchant.png");
        textTab = new Texture("Textbox.png");

         storeIcon = new Sprite(imgBack);
         merchantIcon = new Sprite(shopKeeper);
         storeIcon.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }
    @Override
    public void show()
    {

    }

    @Override
    public void render(float delta)
    {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        //batch.draw();
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


