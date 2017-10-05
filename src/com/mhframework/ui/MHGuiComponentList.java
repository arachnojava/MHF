package com.mhframework.ui;

import java.util.ArrayList;
import com.mhframework.MHRenderable;
import com.mhframework.MHScreenManager;
import com.mhframework.platform.MHPlatform;
import com.mhframework.platform.event.MHKeyEvent;
import com.mhframework.platform.event.MHKeyListener;
import com.mhframework.platform.event.MHMouseTouchEvent;
import com.mhframework.platform.event.MHMouseTouchListener;
import com.mhframework.platform.graphics.MHGraphicsCanvas;

public class MHGuiComponentList implements MHRenderable, MHKeyListener, MHMouseTouchListener
{
    private ArrayList<MHGuiComponent> components;
    private int selectedIndex = 0;
    
    public MHGuiComponentList()
    {
        components = new ArrayList<MHGuiComponent>();
    }


    public void update(long elapsedTime)
    {
        for (MHGuiComponent c : components)
        {
            c.update(elapsedTime);
        }
    }

    

    public void render(MHGraphicsCanvas g)
    {
        for (MHGuiComponent c : components)
        {
            if (c.isVisible())
                c.render(g);
        }
    }

    
    public void add(MHGuiComponent c)
    {
        components.add(c);
    }
    
    
    public MHGuiComponent nextFocusableComponent()
    {
        do
        {
            selectedIndex = (selectedIndex + 1) % components.size();
        } while (!components.get(selectedIndex).isFocusable());
        
        return components.get(selectedIndex);
    }
    
    
    public MHGuiComponent previousFocusableComponent()
    {
        do
        {
            selectedIndex--;
            if (selectedIndex < 0)
                selectedIndex = components.size()-1;
        } while (!components.get(selectedIndex).isFocusable());
        
        return components.get(selectedIndex);
    }

    
    public MHGuiComponent getSelectedComponent()
    {
        return components.get(selectedIndex);
    }

    
    public void onKeyDown(MHKeyEvent e)
    {
        if (e.getKeyCode() == MHPlatform.getKeyCodes().keyDownArrow())
            nextFocusableComponent();
        else if (e.getKeyCode() == MHPlatform.getKeyCodes().keyUpArrow())
            previousFocusableComponent();
    }


    public void onKeyUp(MHKeyEvent e)
    {
        if (e.getKeyCode() == MHPlatform.getKeyCodes().keyEnter())
            getSelectedComponent().executeCommand();
    }


    public void onMouseDown(MHMouseTouchEvent e)
    {
        MHGuiComponent c = getComponentAt(e.getX(), e.getY());
        if (c != null)
            selectedIndex = getComponentIndex(c);
    }


    public void onMouseUp(MHMouseTouchEvent e)
    {
        MHGuiComponent c = getComponentAt(e.getX(), e.getY());
        if (c != null)
            c.executeCommand();
    }


    public void onMouseMoved(MHMouseTouchEvent e)
    {
        MHGuiComponent c = getComponentAt(e.getX(), e.getY());
        if (c != null)
            selectedIndex = getComponentIndex(c);
    }


    public void render(MHGraphicsCanvas g, int x, int y)
    {
        for (MHGuiComponent c : components)
            c.render(g, x, y);
    }
    
    
    private int getComponentIndex(MHGuiComponent c)
    {
        for (int i = 0; i < components.size(); i++)
        {
            if (components.get(i) == c)
                return i;
        }
        
        return -1;
    }
    
    
    private MHGuiComponent getComponentAt(int x, int y)
    {
        for (MHGuiComponent c : components)
            if (c.contains(x, y))
                return c;
        
        return null;
    }
}
