package com.mhframework;

import com.mhframework.math.physics.MHPhysicsCore;
import com.mhframework.platform.event.MHInputEventHandler;
import com.mhframework.platform.graphics.MHGraphicsCanvas;
import com.mhframework.ui.MHGuiComponent;
import com.mhframework.ui.MHGuiComponentList;
import com.mhframework.ui.MHMouseCursor;

/********************************************************************
 * Abstract class for deriving game screens.  
 * 
 * <p>Unlike previous versions of MHFramework (before 3.0), this 
 * class does not have support built in for event handling since the 
 * <code>MHInputEventHandler</code> singleton has consolidated and
 * generalized that functionality.  If a developer wishes to have a
 * screen handle its own events, then they can declare their screen 
 * class to implement the appropriate listener interface from the 
 * {@link com.mhframework.platform.event} package.
 */
public abstract class MHScreen implements MHRenderable
{
    private String description;
    private MHGuiComponentList components;
    private MHMouseCursor mouseCursor = new MHMouseCursor(MHMouseCursor.Type.CROSSHAIR);
    
    /****************************************************************
     * Default constructor.
     */
    public MHScreen()
    {
        this("");
    }
    
    
    /****************************************************************
     * Overloaded constructor taking an optional descriptive string
     * that may be used for a variety of purposes, such as a screen
     * title or a mapping in a key/value pair.
     */
    public MHScreen(String description)
    {
        setDescription(description);
        components = new MHGuiComponentList();
    }
    
 
    /****************************************************************
     * Return the description string of this screen.
     * 
     * @return The description string of this screen
     */
    public String getDescription()
    {
        return description;
    }

    
    /****************************************************************
     * Set the description string of this screen.
     * 
     * @param description A descriptive string for this screen.
     */
    public void setDescription(String description)
    {
        this.description = description;
    }

    
    /**************************************************************************
     * Adds a GUI component to this screen.
     * 
     * @param c The component to add.
     */
    public void add(MHGuiComponent c)
    {
        components.add(c);
    }
    
    
    /****************************************************************
     * Called automatically by <code>MHScreenManager</code>, this
     * method is intended to be overridden to perform any 
     * initialization that needs to happen after the screen's 
     * instantiation but prior to the screen being displayed.  The 
     * base implementation in <code>MHScreen</code> is just an empty 
     * hook.
     */
    public void load() {}
    
    
    /****************************************************************
     * Called automatically by <code>MHScreenManager</code>, this
     * method is intended to be overridden to perform any cleanup or
     * finalization required for a screen.  The base implementation 
     * in <code>MHScreen</code> is just an empty hook.
     */ 
    public void unload() {}

    
    /****************************************************************
     * Method for the update phase of the game loop.  It is 
     * called automatically by <code>MHScreenManager</code>.
     */
    public void update(long elapsedTime)
    {
        MHPhysicsCore.getInstance().update(elapsedTime);
        components.update(elapsedTime);
    }

    
    /****************************************************************
     * Method for rendering the screen to the sent graphics
     * canvas.  It is called automatically by 
     * <code>MHScreenManager</code> as part of the game loop.
     * Since the implementation in this class renders the GUI, any
     * screen containing a GUI should call this method in their
     * override.
     */
    public void render(MHGraphicsCanvas g)
    {
        components.render(g);
        mouseCursor.render(g);
    }
}
