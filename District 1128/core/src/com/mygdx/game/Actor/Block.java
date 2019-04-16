package com.mygdx.game.Actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class Block {

    public Rectangle full;
    public Sprite sprite;
    public Texture texture;
    public final float velocity=100f;


    public Block(float x,float y)
    {
        full=new Rectangle(x,y,64,64);
        texture=new Texture(Gdx.files.internal("Block.png"));
        sprite=new Sprite(texture,0,0,64,64);
        setPosition(x,y);
    }

    public void draw(SpriteBatch batch)
    {
        sprite.draw(batch);
    }

    public void setPosition(float x,float y)
    {
        full.x=x;
        full.y=y;
        sprite.setPosition(x,y);
    }

    public Rectangle getHitBox()
    {
        return full;
    }
}
