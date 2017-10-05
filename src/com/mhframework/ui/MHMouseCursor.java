package com.mhframework.ui;

import com.mhframework.platform.MHPlatform;
import com.mhframework.platform.event.MHMouseTouchEvent;
import com.mhframework.platform.event.MHMouseTouchListener;
import com.mhframework.platform.graphics.MHColor;
import com.mhframework.platform.graphics.MHGraphicsCanvas;

public class MHMouseCursor implements MHMouseTouchListener
{
    public enum Type
    {
        CROSSHAIR, CIRCLE, IMAGE, HIDDEN;
    }
 
    private Type type = Type.CROSSHAIR;
    protected String imageID;
    protected MHColor color;
    protected int x, y;
    private MHMouseCursorRenderer renderer;
    //private MHVector hotspot = new MHVector(0,0);
    
    public MHMouseCursor(Type type)
    {
        MHPlatform.addMouseTouchListener(this);
        this.type = type;

        switch (type)
        {
            case CROSSHAIR:
                renderer = new Crosshair();
                break;
            case CIRCLE:
                break;
            case HIDDEN:
                break;
            case IMAGE:
                break;
            default:
                break;
        }
    }

    
    private MHColor getColor()
    {
        if (color == null)
            color = MHPlatform.createColor(255, 255, 255);

        return color;
    }
    
    public void render(MHGraphicsCanvas g)
    {
        g.setColor(getColor());
        renderer.render(g, x, y);
    }


    @Override
    public void onMouseDown(MHMouseTouchEvent e)
    {
        x = (int)(e.getX() + MHPlatform.getDisplayOrigin().getX());
        y = (int)(e.getY() + MHPlatform.getDisplayOrigin().getY());
    }


    @Override
    public void onMouseUp(MHMouseTouchEvent e)
    {
        x = (int)(e.getX() + MHPlatform.getDisplayOrigin().getX());
        y = (int)(e.getY() + MHPlatform.getDisplayOrigin().getY());
    }


    @Override
    public void onMouseMoved(MHMouseTouchEvent e)
    {
        x = (int)(e.getX() + MHPlatform.getDisplayOrigin().getX());
        y = (int)(e.getY() + MHPlatform.getDisplayOrigin().getY());
    }
}

class Crosshair implements MHMouseCursorRenderer
{
    private static final int SIZE = 5;
    @Override
    public void render(MHGraphicsCanvas g, int x, int y)
    {
        g.drawLine(x, y-SIZE, x, y+SIZE);
        g.drawLine(x-SIZE, y, x+SIZE, y);
    }


}