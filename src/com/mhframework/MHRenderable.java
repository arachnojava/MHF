package com.mhframework;

import com.mhframework.platform.graphics.MHGraphicsCanvas;

/******************************************************************************
 * Interface to be implemented by all visual elements.
 * 
 * @author Michael Henson
 * @since 1.0
 */
public interface MHRenderable
{
    /**************************************************************************
     * Method to be called to perform data updates during the update phase
     * of the game loop.
     * 
     * @param elapsedTime Time elapsed in nanoseconds since the last iteration
     *                    of the game loop.
     */
    public void update(long elapsedTime);

    
    /**************************************************************************
     * Render onto the sent graphics canvas.
     * 
     * @param g The graphics canvas on which to render.
     */
    public void render(MHGraphicsCanvas g);

    
    /**************************************************************************
     * Render onto the sent graphics canvas at the specified screen 
     * coordinates.
     * 
     * @param g The graphics canvas on which to render.
     * @param x The x coordinate in view space.
     * @param y The y coordinate in view space.
     */
    public void render(MHGraphicsCanvas g, int x, int y);

}
