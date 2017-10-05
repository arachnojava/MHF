package com.mhframework.platform.graphics;

import com.mhframework.math.MHVector;

public abstract class MHBitmapImage
{
    protected MHBitmapImage(){}
    private MHVector size = new MHVector();
    
    public int getWidth()
    {
        return (int)size.getX();
    }
    
    
    public int getHeight()
    {
        return (int)size.getY();
    }

    
    protected void setSize(int width, int height)
    {
        size.setVector(width, height);
    }
    
    
    public abstract MHGraphicsCanvas getGraphicsCanvas();
}
