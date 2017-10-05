package com.test;
//package com.botz;

import com.mhframework.MHGame;
import com.mhframework.MHScreen;
import com.mhframework.MHScreenManager;
import com.mhframework.math.MHRandom;
import com.mhframework.math.MHVector;
import com.mhframework.math.geom.MHRectangle;
import com.mhframework.math.physics.MHPhysicsCore;
import com.mhframework.platform.MHPlatform;
import com.mhframework.platform.event.MHInputEventHandler;
import com.mhframework.platform.event.MHMouseTouchEvent;
import com.mhframework.platform.event.MHMouseTouchListener;
import com.mhframework.platform.graphics.MHColor;
import com.mhframework.platform.graphics.MHFont;
import com.mhframework.platform.graphics.MHGraphicsCanvas;
import com.mhframework.ui.MHButton;
import com.mhframework.ui.MHLabel;
import com.mhframework.ui.event.MHButtonListener;




public class TestScreen extends MHScreen implements MHMouseTouchListener, MHButtonListener
{
    static final String IMAGES_DIR = "assets/images/";
    MHLabel lblTest,lblMultiLineTest;
    MHButton btnExit;
    MHColor bgColor;
    MHColor groundColor;
    MHColor textColor;
    MHVector position, velocity;
    double speed = 0.1;
    int screenWidth, screenHeight;
    String message = "Test Screen";
    TestSprite sprite;
    
    public TestScreen()
    {
        super("Test Screen");
        MHInputEventHandler.getInstance().addMouseTouchListener(this);
    }

    
    @Override
    public void load()
    {
        sprite = new TestSprite();
        sprite.setGravity(true);
        MHPhysicsCore.getInstance().addBody(sprite.getPhysics());
        MHPhysicsCore.getInstance().setMaxFallingSpeed(0.5);
        
        bgColor = MHPlatform.createColor(0, 0, 100, 255);
        groundColor = MHPlatform.createColor(0, 180, 0, 255);
        textColor = MHPlatform.createColor(0, 255, 180, 255);
                
        screenWidth = (int) MHScreenManager.getApplicationObject().getDisplaySize().getX();
        screenHeight = (int) MHScreenManager.getApplicationObject().getDisplaySize().getY();

        
        int x = MHRandom.random(10, screenWidth-20);
        int y = MHRandom.random(10, screenHeight-20);
        position = new MHVector(x, y);
        velocity = new MHVector(MHRandom.random(0.1, 2.0), MHRandom.random(0.1, 2.0));
        velocity = velocity.normalize().multiply(speed);
        lblTest = MHLabel.create("This is a label!");
        lblTest.setBounds(10, 20, 200, 20);
        lblMultiLineTest = MHLabel.create("Here is another label with a lot more text to waste space and test the line wrap.");
        lblMultiLineTest.setBounds(10, lblTest.getY()+lblTest.getHeight() + 5, 200, 20);

        btnExit = MHButton.create(MHButton.Type.TEXT, "Exit");
        btnExit.setBounds(new MHRectangle(screenWidth-100, screenHeight - 60, 80, 40));
        btnExit.addButtonListener(this);
        
        add(lblTest);
        add(lblMultiLineTest);
        add(btnExit);
    }

    
    @Override
    public void update(long elapsedTime)
    {
        super.update(elapsedTime);

        sprite.update(elapsedTime);
        
        position = position.add(velocity.multiply(elapsedTime));
        
        int maxX = screenWidth - MHFont.getDefaultFont().stringWidth(message);
        
        if (position.getX() <= 0)
        {
            position.setX(0);
            velocity.setX(-velocity.getX());
        }
        if (position.getX() > maxX)
        {
            position.setX(maxX);
            velocity.setX(-velocity.getX());
        }
        if (position.getY() <= 0)
        {
            position.setY(0);
            velocity.setY(-velocity.getY());
        }
        if (position.getY() > screenHeight)
        {
            position.setY(screenHeight);
            velocity.setY(-velocity.getY());
        }
    }

    
    @Override
    public void render(MHGraphicsCanvas g)
    {
        g.fill(bgColor);
        
        sprite.render(g);
        
        g.setColor(groundColor);
        g.fillRect(0, 300+sprite.getHeight(), MHScreenManager.getDisplayWidth(), 300+sprite.getHeight());

        g.setColor(textColor);
        g.drawString("FPS: " + MHGame.getFramesPerSecond(), 10, 20);
        g.drawString(message, (int)position.getX(), (int)position.getY());
        super.render(g);
    }


	public void onMouseDown(MHMouseTouchEvent e) 
	{
		message = "Mouse down at (" + e.getX() + ", " + e.getY() + ")";
	}


	public void onMouseUp(MHMouseTouchEvent e) 
	{
		message = "Mouse up at (" + e.getX() + ", " + e.getY() + ")";
	}


	public void onMouseMoved(MHMouseTouchEvent e) 
	{
		message = "Mouse moved at (" + e.getX() + ", " + e.getY() + ")";
	}


    @Override
    public void render(MHGraphicsCanvas g, int x, int y)
    {
        // TODO Auto-generated method stub
        
    }


    @Override
    public void onButtonPressed(MHButton button)
    {
        if (button == btnExit)
            MHGame.setProgramOver(true);
    }

}