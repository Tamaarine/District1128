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

    //Final variables
    private final int MAX_LENGTH=44;

    public Line(String givenString,String givenName)
    {
        font=new BitmapFont();
        background=new Texture(Gdx.files.internal("Textbox.png"));
        line=givenString;
        name=givenName;
    }

    public void drawLine(SpriteBatch batch,float x)
    {
        batch.draw(background,x+40,0,Gdx.graphics.getWidth()/4,Gdx.graphics.getHeight()/4);
        font.setColor(new Color(255f/255f, 255f/255f, 255f/255f, 255f/255f));
        //font.draw(batch,name,20,115);
        //font.draw(batch,line,20,95);
        font.draw(batch,name,x+55,115);
        font.draw(batch,line,x+55,95);

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
