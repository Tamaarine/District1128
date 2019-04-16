package com.mygdx.game.Actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.Controls.BButton;
import com.mygdx.game.Main.FightScreen;
import com.mygdx.game.Main.MyGdxGame;

public class Portal
{
    //The portal's animation
    private TextureAtlas portalAtlas;
    private Animation<TextureRegion> portalAnimation;
    private Rectangle position;
    private MyGdxGame game;
    private boolean isActivated;

    public Portal(MyGdxGame givenGame, float givenX, float givenY)
    {
        game=givenGame;

        portalAtlas=new TextureAtlas(Gdx.files.internal("Portal/Portal.atlas"));
        portalAnimation=new Animation<TextureRegion>(1/5f,portalAtlas.findRegions("P"), Animation.PlayMode.LOOP);

        position=new Rectangle(givenX,givenY,44,111);

        isActivated=false;
    }

    public void showPortal(SpriteBatch batch, float stateTime,Meko meko, BButton button2)
    {
        batch.draw(portalAnimation.getKeyFrame(stateTime),getX(),getY());

        entry(meko,button2);
    }

    public boolean detectTouching(Rectangle givenBody)
    {
        if(position.overlaps(givenBody))
        {
            return true;
        }

        return false;
    }

    public void entry(Meko meko,BButton button2)
    {
        float touchX=Gdx.input.getX();
        float touchY=Gdx.graphics.getHeight()-Gdx.input.getY();

        float mappedX=map(touchX,0,1766,meko.getX()-222,meko.getX()+222);
        float mappedY=map(touchY,0,1080,0,270);

        if((button2.getHitBox().contains(mappedX,mappedY)&&detectTouching(meko.getUseMeForCollision())))
        {
           isActivated=true;
        }
    }

    public float getX()
    {
        return position.x;
    }

    public float getY()
    {
        return position.y;
    }

    public boolean isActivated()
    {
        return isActivated;
    }

    public float map(float value,float low,float high,float toLow,float toHigh)
    {
        return toLow+(value-low)*(toHigh-toLow)/(high-low);
    }

}
