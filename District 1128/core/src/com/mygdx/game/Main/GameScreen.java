package com.mygdx.game.Main;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.Actor.*;

import com.mygdx.game.Actor.Meko;
import com.mygdx.game.Animation.EntryScene;

import java.util.ArrayList;

public class GameScreen implements Screen {

    //Setting up all the variables
    MyGdxGame game;
    SpriteBatch gameBatch;
    ShapeRenderer shaper;

    //Blocks and players
    Meko meko;
    ArrayList<Block> blocks;

    OrthographicCamera camera;

    private float ms;

    //All the textures
    Texture roblox;
    Texture bg;
    Texture doorOpen;
    Texture doorClosed;

    //Necesssary varaible
    float delta;
    float stateTime;

    //Dialogue
    Conversation converse;
    Conversation converse2;
    Conversation converse3;
    Line testing;

    //Buttons
    LeftArrow leftArrow;
    RightArrow rightArrow;

    //Animation CutScene
    EntryScene scene1;

    public GameScreen(MyGdxGame pGame)
    {
        ms=0;
        pGame=game;
        gameBatch=new SpriteBatch();
        shaper=new ShapeRenderer();
        camera=new OrthographicCamera();
        camera.setToOrtho(false,Gdx.graphics.getWidth()/4,Gdx.graphics.getHeight()/4);

        meko=new Meko(0,64);
        blocks=new ArrayList<Block>();

        blocks.add(new Block(0,0));
        blocks.add(new Block(64,0));
        blocks.add(new Block(128,0));
        blocks.add(new Block(192,0));
        blocks.add(new Block(256,0));

        converse=new Conversation(meko);
        converse.addLine("Hurry up!","Vicky");
        converse.addLine("IHey I'm coming!","Meko");
        converse.addLine("I need to find a book in the library,","Meko");
        converse.addLine("Would you mind waiting me for a second?","Meko");
        converse.addLine("Of course not!","Vicky");
        converse.addLine("You've got a mission,"," ");
        converse.addLine("Find \"Silent Spring \" from the library."," ");


        converse2=new Conversation(meko);
        converse2.addLine("Hi! Could you please find me \"Silent Spring \" please?","Meko");
        converse2.addLine("Sure!","Librarian");
        converse2.addLine("......"," ");
        converse2.addLine("Here you go!","Librarian");
        converse2.addLine("Thank you! Have a good day!","Meko");
        converse2.addLine("You too!","Librarian");

        converse3=new Conversation(meko);
        converse3.addLine("[SCEAMING]"," ");


        doorOpen=new Texture(Gdx.files.internal("DoorOpen.png"));
        doorClosed=new Texture(Gdx.files.internal("DoorClosed.png"));

        leftArrow=new LeftArrow(meko);
        rightArrow=new RightArrow(meko);

        scene1=new EntryScene();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 1, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stateTime += Gdx.graphics.getDeltaTime();

        camera.position.x=meko.getHitBox().x+40;

        camera.update();

        gameBatch.setProjectionMatrix(camera.combined);
        shaper.setProjectionMatrix(camera.combined);


        if(!scene1.isComplete())
        {
            scene1.act();
        }
        else
        {

            //This means that only if the conversation is done then allow the character to move
            if(converse.isComplete())
            {
                //Replacement for the touchscreen
                if(Gdx.input.isTouched())
                {
                    float touchX=Gdx.input.getX();
                    float touchY=Gdx.graphics.getHeight()-Gdx.input.getY();

                    System.out.println(touchX+" "+touchY);
                    System.out.println(leftArrow.getHitBox().x+" "+leftArrow.getHitBox().y);

                    //This means that the player pressed the leftArrow therefore we move Meko to the left
                    if(leftArrow.getHitBox().contains(touchX,touchY))
                    {
                        meko.moveLeft(delta);
                        System.out.println("This is true");
                    }
                    else if(rightArrow.getHitBox().contains(touchX,touchY))
                    {
                        meko.moveRight(delta);
                    }

                    if((touchX>leftArrow.getX()&&touchX<leftArrow.getX()+leftArrow.getWidth())&&(touchY>leftArrow.getY()&&touchY<leftArrow.getY()+leftArrow.getHeight()))
                    {
                        meko.moveLeft(delta);
                    }

                }
                else
                {
                    meko.setActionCode(0);
                }
            }





            gameBatch.begin();
            shaper.begin(ShapeRenderer.ShapeType.Line);

            drawBackground();

            leftArrow.draw(gameBatch);
            rightArrow.draw(gameBatch);
            leftArrow.drawBox(shaper);
            rightArrow.drawBox(shaper);
            shaper.rect(meko.getHitBox().x,meko.getHitBox().y,meko.getHitBox().width,meko.getHitBox().height);

            //This means that Meko is walking to the left
            if (meko.getActionCode() == -1)
            {
                meko.drawLeftWalkAnimation(stateTime, gameBatch);
            }
            //This means that Meko is walking to the right
            else if (meko.getActionCode() == 1)
            {
                meko.drawRightWalkAnimation(stateTime, gameBatch);
            }
            //This means that Meko is not moving
            else
            {
                meko.draw(gameBatch);
            }


            converse.startConversation(gameBatch);

            gameBatch.end();
            shaper.end();


        }


    }

    public float getStateTime()
    {
        return stateTime;
    }

    public void drawBackground()
    {
        for(int i=0;i>-5;i--)
        {
            gameBatch.draw(doorClosed,0+(i*Gdx.graphics.getWidth()/4),0,Gdx.graphics.getWidth()/4,Gdx.graphics.getHeight()/4);
            gameBatch.draw(doorClosed,0+(-i*Gdx.graphics.getWidth()/4),0,Gdx.graphics.getWidth()/4,Gdx.graphics.getHeight()/4);
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

    }
}
