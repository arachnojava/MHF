package com.mhframework.ui;

import java.util.ArrayList;

import com.mhframework.platform.MHPlatform;
import com.mhframework.platform.event.MHKeyEvent;
import com.mhframework.platform.event.MHKeyListener;
import com.mhframework.platform.event.MHMouseTouchEvent;
import com.mhframework.platform.event.MHMouseTouchListener;
import com.mhframework.platform.graphics.MHColor;
import com.mhframework.platform.graphics.MHGraphicsCanvas;
import com.mhframework.ui.event.MHButtonListener;

/******************************************************************************
 * 
 * @author Michael
 *
 */
public abstract class MHButton extends MHLabel implements MHKeyListener, MHMouseTouchListener
{
    
    public static enum Type
    {
        TEXT, IMAGE, ANIMATION;
    }
    
    protected MHCommand command;
    private ArrayList<MHButtonListener> listeners;
    
    private MHButton()
    {
        super("");
    }

    
    /**************************************************************************
     * 
     * @param buttonType
     * @param data
     * @return
     */
    public static MHButton create(Type buttonType, String data)
    {
        switch(buttonType)
        {
            case TEXT:
                return new MHTextButton(data);
            case IMAGE:
                break;
            case ANIMATION:
                break;
            default:
                break;
        }
        
        return new MHTextButton(data);
    }
    
    
    public void addButtonListener(MHButtonListener listener)
    {
        if (listeners == null)
            listeners = new ArrayList<MHButtonListener>();
        
        listeners.add(listener);
    }
    
    
    /**************************************************************************
     * 
     * @param command
     */
    public void setCommand(MHCommand command)
    {
        this.command = command;
    }
    
    
    /**************************************************************************
     * 
     */
    public void executeCommand()
    {
        if (command != null)
            command.execute();
    }
    
    


    private void dispatchEvent()
    {
        if (listeners == null)
            return;
        
        for (MHButtonListener l : listeners)
        {
            l.onButtonPressed(this);
        }

    }
    
    /**************************************************************************
     * 
     */
    private static class MHTextButton extends MHButton
    {
        private MHColor highlight, shadow;
        
        public MHTextButton(String caption)
        {
            this.setEnabled(true);
            this.setText(caption);
            this.setAlignment(MHLabel.ALIGN_CENTER);
            MHPlatform.addMouseTouchListener(this);
        }
        
        
        private MHColor getHighlight()
        {
            if (highlight == null)
            {
                highlight = MHPlatform.createColor(255, 255, 255, 140);
                normalColors.borderColor = normalColors.backgroundColor;
            }
            
            return highlight;
        }
        
        
        private MHColor getShadow()
        {
            if (shadow == null)
            {
                shadow = MHPlatform.createColor(0, 0, 0, 140);
                normalColors.borderColor = normalColors.backgroundColor;
            }
            
            return shadow;
        }
        
        
        public void render(MHGraphicsCanvas g)
        {
            super.render(g);
            
            // Draw 3D border.
            int left = (int)(getX()+MHPlatform.getDisplayOrigin().getX());
            int right = left + getWidth();
            int top = (int)(getY()+MHPlatform.getDisplayOrigin().getY());
            int bottom = top + getHeight();
            
            // Draw the shadowed edges.
            g.setColor(getShadow());
            g.drawLine(left, bottom, right, bottom);
            g.drawLine(right, top, right, bottom);
            
            // Draw the highlighted edges.
            g.setColor(getHighlight());
            g.drawLine(left, top, right, top);
            g.drawLine(left, top, left, bottom);
        }
        
        
        @Override
        public void onMouseDown(MHMouseTouchEvent e)
        {
            if (!isEnabled()) return;
            
            if (contains(e.getX(), e.getY()))
            {
                this.setFocused(true);
            }
            else
            {
                this.setFocused(false);
            }
        }


        @Override
        public void onMouseUp(MHMouseTouchEvent e)
        {
            if (!isEnabled()) return;
            
            if (contains(e.getX(), e.getY()))
            {
                this.setFocused(true);
            
                if (command != null)
                    command.execute();
                
                super.dispatchEvent();
            }
            else
            {
                this.setFocused(false);
            }

        }


        @Override
        public void onMouseMoved(MHMouseTouchEvent e)
        {
            if (!isEnabled()) return;
            
            if (contains(e.getX(), e.getY()))
            {
                this.setFocused(true);
            }
            else
            {
                this.setFocused(false);
            }
        }

        @Override
        public void onKeyDown(MHKeyEvent e)
        {
            // TODO Auto-generated method stub
            
        }

        @Override
        public void onKeyUp(MHKeyEvent e)
        {
            // TODO Auto-generated method stub
            
        }
    }
}
