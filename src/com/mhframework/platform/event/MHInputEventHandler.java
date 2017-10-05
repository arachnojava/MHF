package com.mhframework.platform.event;

import java.util.ArrayList;

/******************************************************************************
 * Observable singleton that consolidates low-level user input events.
 * 
 * <p>In MHFramework 3 and up, any object can be an event listener.  It only
 * needs to implement the appropriate listener interface (<code>MHKeyListener</code>,
 * <code>MHMouseTouchListener</code>, etc.) and then register
 * itself with this class by calling <code>MHInputEventHandler.addKeyListener()</code>,
 * <code>MHInputEventHandler.addMouseTouchListener()</code>, etc.
 * 
 * @since 3.0
 * @author Michael Henson
 *
 */
public class MHInputEventHandler implements MHMouseTouchListener, MHKeyListener
{
    private static MHInputEventHandler instance;
    private ArrayList<MHMouseTouchListener> mouseListeners;
    private ArrayList<MHKeyListener> keyListeners;
    
    private MHInputEventHandler()
    {
        mouseListeners = new ArrayList<MHMouseTouchListener>();
        keyListeners = new ArrayList<MHKeyListener>();
    }
    
    
    public static MHInputEventHandler getInstance()
    {
        if (instance == null)
            instance = new MHInputEventHandler();
        
        return instance;
    }
    
    
    public void addKeyListener(MHKeyListener observer)
    {
        keyListeners.add(observer);
    }
    

    public void removeKeyListener(MHKeyListener observer)
    {
        keyListeners.remove(observer);
    }
    

    public void addMouseTouchListener(MHMouseTouchListener observer)
    {
        mouseListeners.add(observer);
    }
    

    public void removeMouseTouchListener(MHMouseTouchListener observer)
    {
        mouseListeners.remove(observer);
    }
    

    public void onKeyDown(MHKeyEvent e)
    {
        for (MHKeyListener observer : keyListeners)
            observer.onKeyDown(e);
    }


    public void onKeyUp(MHKeyEvent e)
    {
        for (MHKeyListener observer : keyListeners)
            observer.onKeyUp(e);
    }


    public void onMouseDown(MHMouseTouchEvent e)
    {
        
        for (MHMouseTouchListener observer : mouseListeners)
            observer.onMouseDown(e);
    }


    public void onMouseUp(MHMouseTouchEvent e)
    {
        for (MHMouseTouchListener observer : mouseListeners)
            observer.onMouseUp(e);
    }


    public void onMouseMoved(MHMouseTouchEvent e)
    {
        for (MHMouseTouchListener observer : mouseListeners)
            observer.onMouseMoved(e);
    }
}
