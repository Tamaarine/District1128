package com.mygdx.game.Outdated;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;

import java.util.ArrayList;

public class Dialogue implements Disposable
{
    ArrayList<Line> lines;
    Texture dialogueBackground;
    private float futureTime;
    private int currentLine;
    private float stateTime;
    private boolean complete;
    private final float DURATION=2f;
    private final int GAP=25;
    private String characterName;
    private Texture characterPicture;

    //This class will contain 3 Line in total
    public Dialogue(String a,String b,String c,String name)
    {
        futureTime=0;
        stateTime=Gdx.graphics.getDeltaTime();
        dialogueBackground=new Texture(Gdx.files.internal("Textbox.png"));
        complete=false;

        lines=new ArrayList<Line>();

        lines.add(new Line(a,1));
        lines.add(new Line(b,2));
        lines.add(new Line(c,3));

        characterName=name;

        currentLine=0;



    }

    public void drawDialogue(SpriteBatch batch)
    {
        stateTime+=Gdx.graphics.getDeltaTime();

        batch.draw(dialogueBackground,0,0,Gdx.graphics.getWidth()/4,Gdx.graphics.getHeight()/4);

        if(stateTime>futureTime&&currentLine==0)
        {
            futureTime=stateTime+DURATION;

            currentLine=1;
        }
        else if(stateTime>futureTime&&currentLine==1)
        {
            futureTime=stateTime+DURATION;

            currentLine=2;
        }
        else if(stateTime>futureTime&&currentLine==2)
        {
            futureTime=stateTime+DURATION;

            currentLine=3;
        }

        lines.get(0).drawName(batch,GAP,115,characterName);

        if(currentLine==1)
        {
            lines.get(0).drawLine(batch,GAP,90);
        }
        else if(currentLine==2)
        {
            lines.get(0).drawLine(batch,GAP,90);
            lines.get(1).drawLine(batch,GAP,65);
        }
        else if(currentLine==3)
        {
            lines.get(0).drawLine(batch,GAP,90);
            lines.get(1).drawLine(batch,GAP,65);
            lines.get(2).drawLine(batch,GAP,40);

            if(Gdx.input.isTouched())
            {
                complete=true;
            }
        }

    }

    public boolean isComplete()
    {
        return complete;
    }

    public void dispose()
    {

    }
}
