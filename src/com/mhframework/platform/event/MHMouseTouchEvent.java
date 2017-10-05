package com.mhframework.platform.event;

import com.mhframework.math.MHVector;

public class MHMouseTouchEvent
{
    private int x;
    private int y;
    
    public MHMouseTouchEvent(int x, int y)
    {
        this.x = x;
        this.y = y;
    }
    
    
    public int getX()
    {
        return x;
    }
    
    
    public int getY()
    {
        return y;
    }


    public MHVector getPoint()
    {
        return new MHVector(x, y);
    }
}
