package com.mygdx.game.Actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

public abstract class GameObject {

    //Variables that all the other classes tending from GameObject will have
    public Rectangle full;
    public Sprite sprite;
    public Texture texture;

    public GameObject(int x,int y,int width,int height)
    {
        full=new Rectangle(0,0,width,height);
        texture=new Texture(Gdx.files.internal("default.jpg"));
        sprite=new Sprite(texture,x,y,width,height);

        setPosition(x,y);
    }

    public Rectangle getHitBox()
    {
        return full;
    }

    public float getX()
    {
        return full.x;
    }

    public float getY()
    {
        return full.y;
    }

    public float getWidth()
    {
        return full.width;
    }

    public float getHeight()
    {
        return full.height;
    }

    public void setWidth(float x)
    {
        full.width=x;
    }

    public void setHeight(float x)
    {
        full.height=x;
    }

    public void setTexture(Texture givenTexture)
    {
        texture=givenTexture;
    }

    public void setPosition(float givenX,float givenY)
    {
        full.x=givenX;
        full.y=givenY;
    }

    public Sprite getSprite()
    {
        return sprite;
    }

    public void setSpriteTexture(Texture givenTexture)
    {
        sprite.setTexture(givenTexture);
    }




}
