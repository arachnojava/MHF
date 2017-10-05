package com.mhframework.platform.event;

public class MHKeyEvent
{
    private int keyCode;
    private boolean ctrl = false;
    private boolean alt = false;
    private boolean shift = false;
    
    
    public MHKeyEvent(int keyCode)
    {
        this.keyCode = keyCode;
    }
    
    
    public MHKeyEvent(int keyCode, boolean shift, boolean ctrl, boolean alt)
    {
        this.keyCode = keyCode;
        this.shift = shift;
        this.ctrl = ctrl;
        this.alt = alt;
    }
    
    
    public int getKeyCode()
    {
        return keyCode;
    }
    
    
    public boolean isShiftPressed()
    {
        return shift;
    }
    
    
    public boolean isControlPressed()
    {
        return ctrl;
    }
    
    
    public boolean isAltPressed()
    {
        return alt;
    }
}
