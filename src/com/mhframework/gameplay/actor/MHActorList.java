package com.mhframework.gameplay.actor;

import java.util.ArrayList;
import com.mhframework.platform.graphics.MHGraphicsCanvas;

/******************************************************************************
 * 
 * @author Michael Henson
 *
 */
public class MHActorList
{
    private ArrayList<MHActor> list;
    
    public MHActorList()
    {
        list = new ArrayList<MHActor>();
    }
    
    public void add(MHActor actor)
    {
        list.add(actor);
    }
    
    public void remove(MHActor actor)
    {
        list.remove(actor);
    }

    public void update(long elapsedTime)
    {
        for (MHActor a : list)
            a.update(elapsedTime);
    }
    
    public void render(MHGraphicsCanvas g)
    {
        for (MHActor a : list)
            a.render(g);
    }
}
