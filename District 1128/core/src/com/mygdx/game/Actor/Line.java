package com.mygdx.game.Actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;

public class Line implements Disposable {

    //Creating the instance variables
    private BitmapFont font;
    private String line;
    private String name;
    private Texture background;
    private Texture mekoBox;
    private Texture vickyBox;

    //Final variables
    private final int MAX_LENGTH=44;

    public Line(String givenString,String givenName)
    {
        font=new BitmapFont();
        background=new Texture(Gdx.files.internal("Textbox.png"));
        mekoBox=new Texture(Gdx.files.internal("MekoBox.png"));
        vickyBox=new Texture(Gdx.files.internal("VickyBox.png"));
        line=givenString;
        name=givenName;
    }

    public void drawLine(SpriteBatch batch,float x)
    {
        if(name.equals("Vicky"))
        {
            batch.draw(vickyBox,x,0,Gdx.graphics.getWidth()/4,Gdx.graphics.getHeight()/4);
        }
        else if(name.equals("Meko"))
        {
            batch.draw(mekoBox,x,0,Gdx.graphics.getWidth()/4,Gdx.graphics.getHeight()/4);
        }
        else if(name.equals("None"))
        {
            batch.draw(background,x,0,Gdx.graphics.getWidth()/4,Gdx.graphics.getHeight()/4);
        }
        font.setColor(Color.WHITE);
        //font.draw(batch,name,20,115);
        //font.draw(batch,line,20,95);
        if(!name.equals("None"))
        {
            font.draw(batch,name,x+15,115);
        }
        font.draw(batch,line,x+15,95);

    }

    public void addCharacter()
    {
        line=line+"A";
    }

    public int length()
    {
        return line.length();
    }

    public void dispose()
    {
        font.dispose();
    }

}
