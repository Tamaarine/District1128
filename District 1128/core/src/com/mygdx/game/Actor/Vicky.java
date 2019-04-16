package com.mygdx.game.Actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

public class Vicky {
    public Rectangle full;
    public Rectangle position;
    public Texture texture;
    public final float velocity=100f;
    public Animation<TextureRegion> leftWalk;
    public Animation<TextureRegion> rightWalk;
    public Animation<TextureRegion> leftRun;
    public Animation<TextureRegion> rightRun;
    public int actionCode;

    private Texture vickyLeft;
    private Texture vickyRight;

    public Vicky(float x,float y)
    {
        full=new Rectangle(x-41/2,y,41,112);
        position=new Rectangle(x,y,0,0);
        texture=new Texture(Gdx.files.internal("Vicky/Vicky.png"));

        actionCode=5;

        //Making animations here
        TextureAtlas leftWalkAtlas=new TextureAtlas(Gdx.files.internal("Vicky/VickyLeft.atlas"));
        leftWalk=new Animation<TextureRegion>(1/7f,leftWalkAtlas.findRegions("V"), Animation.PlayMode.LOOP);

        TextureAtlas rightWalkAtlas=new TextureAtlas(Gdx.files.internal("Vicky/VickyRight.atlas"));
        rightWalk=new Animation<TextureRegion>(1/7f,rightWalkAtlas.findRegions("V"), Animation.PlayMode.LOOP);

        vickyLeft=new Texture(Gdx.files.internal("Vicky/VickyFacingLeft.png"));
        vickyRight=new Texture(Gdx.files.internal("Vicky/VickyFacingRight.png"));

        updateFull();

    }

    public int hit(Rectangle anotherR)
    {
        if(full.overlaps(anotherR))
        {
            return 1;
        }

        return -1;
    }

    public void drawStill(SpriteBatch batch)
    {
        batch.draw(texture,full.x,full.y);

        setActionCode(0);
    }

    public void setPosition(float x,float y)
    {
        position.x=x;
        position.y=y;
    }

    public Rectangle getHitBox()
    {
        return full;
    }

    public void moveLeft(float delta)
    {
        position.x-=velocity*delta;

        updateFull();
        setActionCode(-1);
    }

    public void moveRight(float delta)
    {
        position.x+=velocity*delta;

        updateFull();

        setActionCode(1);
    }

    public void drawLeftWalkAnimation(float stateTime,SpriteBatch batch)
    {
        batch.draw(leftWalk.getKeyFrame(stateTime),full.x,full.y);
    }

    public void drawRightWalkAnimation(float stateTime,SpriteBatch batch)
    {
        batch.draw(rightWalk.getKeyFrame(stateTime),full.x,full.y);
    }

    public int getActionCode()
    {
        return actionCode;
    }

    public void setActionCode(int code)
    {
        actionCode=code;
    }

    public float getX()
    {
        return position.x;
    }

    public float getY()
    {
        return position.y;
    }

    public Rectangle getPosition()
    {
        return position;
    }

    public void updateFull()
    {
        full.x=position.x-41;
        full.y=position.y-8;
    }

    public float map(float value,float low,float high,float toLow,float toHigh)
    {
        return toLow+(value-low)*(toHigh-toLow)/(high-low);
    }

    public void drawLeft(SpriteBatch batch)
    {
        batch.draw(vickyLeft,full.x,full.y);
    }
}
