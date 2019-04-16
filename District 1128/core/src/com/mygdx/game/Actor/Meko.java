package com.mygdx.game.Actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.Controls.AButton;
import com.mygdx.game.Controls.BButton;
import com.mygdx.game.Controls.LeftArrow;
import com.mygdx.game.Controls.RightArrow;

public class Meko
{
    public Rectangle full;
    public Rectangle position;
    private Rectangle useMeForCollision;
    public Texture texture;
    private Texture faceLeft;
    private Texture faceRight;
    public final float VELOCITY = 100f;
    private final float JUMPING_VELOCITY = 200f;
    private final float RUNNING_VELOCITY=120f;
    public Animation<TextureRegion> leftWalk;
    public Animation<TextureRegion> rightWalk;
    public Animation<TextureRegion> attackLeft;
    public Animation<TextureRegion> attackRight;
    public Animation<TextureRegion> leftRun;
    public Animation<TextureRegion> rightRun;
    public int actionCode;
    private boolean isAttacking;
    private float attackingTime;
    private boolean isMoving;
    private boolean isRunning;
    private boolean enableControl;
    private float deltaTime;

    //Controls
    LeftArrow leftArrow;
    RightArrow rightArrow;
    AButton button1;
    BButton button2;


    //Reference camera
    OrthographicCamera referenceCamera;

    public Meko(float x, float y)
    {
        full = new Rectangle(x - 41 / 2, y, 41, 112);
        position = new Rectangle(x, y, 0, 0);
        useMeForCollision=new Rectangle(x+20,y,41,112);
        texture = new Texture(Gdx.files.internal("Meko/Meko.png"));
        faceLeft=new Texture(Gdx.files.internal("Meko/MekoFaceLeft.png"));
        faceRight=new Texture(Gdx.files.internal("Meko/MekoFaceRight.png"));
        isAttacking = false;
        attackingTime=0;
        isMoving=false;
        isRunning=false;
        enableControl=true;
        actionCode = 1;
        deltaTime=Gdx.graphics.getDeltaTime();

        //Making animations here
        TextureAtlas leftWalkAtlas = new TextureAtlas(Gdx.files.internal("Meko/MekoLeft.atlas"));
        leftWalk = new Animation<TextureRegion>(1 / 7f, leftWalkAtlas.findRegions("M"), Animation.PlayMode.LOOP);

        TextureAtlas rightWalkAtlas = new TextureAtlas(Gdx.files.internal("Meko/MekoRight.atlas"));
        rightWalk = new Animation<TextureRegion>(1 / 7f, rightWalkAtlas.findRegions("M"), Animation.PlayMode.LOOP);

        TextureAtlas attackLeftAtlas = new TextureAtlas(Gdx.files.internal("Meko/MekoAttackLeft.atlas"));
        attackLeft = new Animation<TextureRegion>(1 / 3f, attackLeftAtlas.findRegions("M"), Animation.PlayMode.NORMAL);

        TextureAtlas attackRightAtlas = new TextureAtlas(Gdx.files.internal("Meko/MekoAttackRight.atlas"));
        attackRight = new Animation<TextureRegion>(1 / 3f, attackRightAtlas.findRegions("M"), Animation.PlayMode.NORMAL);

        TextureAtlas leftRunAtlas=new TextureAtlas(Gdx.files.internal("Meko/MekoRunLeft.atlas"));
        leftRun=new Animation<TextureRegion>(1/6f,leftRunAtlas.findRegions("M"), Animation.PlayMode.LOOP);

        TextureAtlas rightRunAtlas=new TextureAtlas(Gdx.files.internal("Meko/MekoRunRight.atlas"));
        rightRun=new Animation<TextureRegion>(1/6f,rightRunAtlas.findRegions("M"), Animation.PlayMode.LOOP);

        updateFull();
        updateUseMe();

        //Control variables
        leftArrow = new LeftArrow(this);
        rightArrow = new RightArrow(this);
        button1 = new AButton(this);
        button2 = new BButton(this);


        referenceCamera = new OrthographicCamera();
    }

    public void draw(SpriteBatch batch, float time)
    {
        //gravityFall();

        if (Gdx.input.isTouched())
        {
            float touchX = Gdx.input.getX();
            float touchY = Gdx.graphics.getHeight() - Gdx.input.getY();

            //System.out.println(touchX+" "+touchY);
            //System.out.println(leftArrow.getHitBox().x+" "+leftArrow.getHitBox().y);

            float mappedX = map(touchX, 0, 1766, getX() - 222, getX() + 222);
            float mappedY = map(touchY, 0, 1080, 0, 270);


            //System.out.println(mappedX+" "+touchY);
            System.out.println(referenceCamera.position.y);

            if(enableControl)
            {

                if (leftArrow.getHitBox().contains(mappedX, mappedY))
                {
                    //This means that the left arrow and the B button is pressed therefore make Meko run
                    //button2.getHitBox().contains(mappedX,mappedY)||
                    if(Gdx.input.isKeyPressed(Input.Keys.B))
                    {
                        runLeft(deltaTime);
                        isRunning=true;
                    }
                    //This means that the B button is not pressed therefore walk normaly
                    else
                    {
                        moveLeft(deltaTime);
                        isMoving=true;
                    }

                }
                else if (rightArrow.getHitBox().contains(mappedX, mappedY))
                {
                    //This menas that the right arrow is pressed with the B button therefore make Meko run to the right
                    if(Gdx.input.isKeyPressed(Input.Keys.B))
                    {
                        runRight(deltaTime);
                        isRunning=true;
                    }
                    else
                    {
                        moveRight(deltaTime);
                        isMoving=true;
                    }
                }
                //This means that no movement button is made
                else
                {
                    isMoving=false;
                    isRunning=false;
                }

                if (button1.getHitBox().contains(mappedX, mappedY))
                {
                    isAttacking = true;
                }
                if (button2.getHitBox().contains(mappedX, mappedY))
                {
                    //jump(Gdx.graphics.getDeltaTime());
                }
            }
        }
        else
        {
            isMoving=false;
            isRunning=false;
        }

        leftArrow.draw(batch);
        rightArrow.draw(batch);
        button1.draw(batch);
        button2.draw(batch);
        //leftArrow.drawBox(shaper);
        //rightArrow.drawBox(shaper);
        //shaper.rect(meko.getHitBox().x,meko.getHitBox().y,meko.getHitBox().width,meko.getHitBox().height);

        //shaper.rect(meko.getX(),meko.getY(),meko.getHitBox().width,meko.getHitBox().getHeight());
        //This means that Meko is walking to the left

        if (isAttacking)
        {
            if (getActionCode() == -1)
            {
                showAttackLeftAnimation(batch);
            }
            else
            {
                showAttackRightAnimation(batch);
            }
        }
        else
        {
            if(isRunning)
            {
                if(getActionCode()==-1)
                {
                    drawLeftRunAnimation(time,batch);
                }
                else if(getActionCode()==1)
                {
                    drawRightRunAnimation(time,batch);
                }
            }
            else if(isMoving)
            {
                if (getActionCode() == -1)
                {
                    drawLeftWalkAnimation(time, batch);
                }
                //This means that Meko is walking to the right
                else if (getActionCode() == 1)
                {
                    drawRightWalkAnimation(time, batch);
                }
            }
            else
            {
                if (getActionCode() == -1)
                {
                    batch.draw(faceLeft,full.x,full.y);
                }
                //This means that Meko is walking to the right
                else if (getActionCode() == 1)
                {
                    batch.draw(faceRight,full.x,full.y);
                }
            }
        }


    }

    public int hit(Rectangle anotherR)
    {
        if (full.overlaps(anotherR))
        {
            return 1;
        }

        return -1;
    }

    /*
    public void drawStill(SpriteBatch batch)
    {
        batch.draw(texture,full.x,full.y);

        setActionCode(0);
    }
*/
    public void setPosition(float x, float y)
    {
        position.x = x;
        position.y = y;
    }

    public Rectangle getHitBox()
    {
        return full;
    }

    public void moveLeft(float delta)
    {
        position.x -= VELOCITY * delta;

        updateFull();
        updateUseMe();
        setActionCode(-1);
    }

    public void runLeft(float delta)
    {
        position.x-=RUNNING_VELOCITY*delta;

        updateFull();
        updateUseMe();
        setActionCode(-1);
    }

    public void moveRight(float delta)
    {
        position.x += VELOCITY * delta;

        updateFull();
        updateUseMe();

        setActionCode(1);
    }

    public void runRight(float delta)
    {
        position.x += RUNNING_VELOCITY * delta;

        updateFull();
        updateUseMe();

        setActionCode(1);
    }

    public void jump(float delta)
    {
        position.y += JUMPING_VELOCITY * delta;

        updateFull();

        setActionCode(0);
    }

    public void gravityFall()
    {
        if (getY() > 60)
        {
            position.y--;
        }
    }

    public void showAttackLeftAnimation(SpriteBatch batch)
    {
        if(isAttacking)
        {
            attackingTime+=0.03f;

            drawAttackLeftAnimation(attackingTime, batch);

            if(getAttackLeft().isAnimationFinished(attackingTime))
            {
                isAttacking=false;
                attackingTime=0;
            }
        }
    }
    public void showAttackRightAnimation(SpriteBatch batch)
    {
        if(isAttacking)
        {
            attackingTime+=0.03f;

            drawAttackRightAnimation(attackingTime, batch);

            if(getAttackRight().isAnimationFinished(attackingTime))
            {
                isAttacking=false;
                attackingTime=0;
            }
        }
    }



    public void drawLeftWalkAnimation(float stateTime, SpriteBatch batch)
    {
        batch.draw(leftWalk.getKeyFrame(stateTime), full.x, full.y);
    }

    public void drawRightWalkAnimation(float stateTime, SpriteBatch batch)
    {
        batch.draw(rightWalk.getKeyFrame(stateTime), full.x, full.y);
    }

    public void drawLeftRunAnimation(float stateTime,SpriteBatch batch)
    {
        batch.draw(leftRun.getKeyFrame(stateTime),full.x,full.y);
    }

    public void drawRightRunAnimation(float stateTime,SpriteBatch batch)
    {
        batch.draw(rightRun.getKeyFrame(stateTime),full.x,full.y);
    }

    public void drawAttackLeftAnimation(float stateTime, SpriteBatch batch)
    {
        batch.draw(attackLeft.getKeyFrame(stateTime), full.x, full.y);
    }

    public void drawAttackRightAnimation(float stateTime, SpriteBatch batch)
    {
        batch.draw(attackRight.getKeyFrame(stateTime), full.x, full.y);
    }

    public int getActionCode()
    {
        return actionCode;
    }

    public Animation<TextureRegion> getAttackLeft()
    {
        return attackLeft;
    }

    public Animation<TextureRegion> getAttackRight()
    {
        return attackRight;
    }

    public void setActionCode(int code)
    {
        actionCode = code;
    }

    public float getX()
    {
        return position.x;
    }

    public float getY()
    {
        return position.y;
    }

    public Rectangle getUseMeForCollision()
    {
        return useMeForCollision;
    }

    public Rectangle getPosition()
    {
        return position;
    }

    public void updateFull()
    {
        full.x = position.x -41;
        full.y = position.y - 8;
    }

    public void updateUseMe()
    {
        useMeForCollision.x=full.x+41;
        useMeForCollision.y=full.y+8;
    }

    public float map(float value, float low, float high, float toLow, float toHigh)
    {
        return toLow + (value - low) * (toHigh - toLow) / (high - low);
    }

    public void setReferenceCamera(OrthographicCamera givenCamera)
    {
        referenceCamera = givenCamera;
    }

    public void setEnableControl(boolean b)
    {
        enableControl=b;
    }

    public BButton getButton2()
    {
        return button2;
    }
}
