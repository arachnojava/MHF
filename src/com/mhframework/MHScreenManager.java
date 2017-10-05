package com.mhframework;

import com.mhframework.math.MHVector;
import com.mhframework.platform.MHGameApplication;
import com.mhframework.platform.MHPlatform;
import com.mhframework.platform.graphics.MHColor;
import com.mhframework.platform.graphics.MHGraphicsCanvas;


/******************************************************************************
 * An object of this class holds a reference to the screen that is currently
 * visible.
 * 
 * @author Michael Henson
 */
public class MHScreenManager
{
    private final MHScreen NULL_SCREEN = new NullScreen();
    private static MHScreenManager instance;
    private static MHGameApplication app;
    private MHScreen currentScreen = null;
    //private Map<String, MHScreen> screens = Collections.synchronizedMap(new HashMap<String, MHScreen>());

    /**************************************************************************
     * Constructor.
     */
    private MHScreenManager()
    {
        // Init currentScreen to null screen.
        currentScreen = NULL_SCREEN;
    }


    /**************************************************************************
     * Sets an initial screen to be displayed when the application begins.
     * 
     * @param screen The first screen to be displayed when the app begins.
     */
    public void setStartScreen(MHScreen screen)
    {
        if (screen == null)
            screen = NULL_SCREEN;

        currentScreen = screen;
    }


    /**************************************************************************
     * Returns a reference to the current screen.
     * 
     * @return A reference to the screen currently being displayed
     */
    public MHScreen getCurrentScreen()
    {
        return currentScreen;
    }


    /**************************************************************************
     * Assigns a reference to the enclosing application that is currently
     * using this screen manager object.
     * 
     * @param app The MHGameApplication object that is using this screen
     *            manager.
     */
    public static void setContext(MHGameApplication app)
    {
        MHScreenManager.app = app;
    }


    /**************************************************************************
     * Returns a reference to the enclosing application that is currently
     * using this screen manager object.
     * 
     * @return The MHGameApplication object that is using this screen
     *          manager.
     */
    public static MHGameApplication getApplicationObject()
    {
        return app;
    }


    /**************************************************************************
     * Returns the singleton instance of the screen manager.
     * 
     * @return A reference to this screen manager object.
     */
    public static MHScreenManager getInstance()
    {
        if (instance == null)
            instance = new MHScreenManager();

        return instance;
    }


    /**************************************************************************
     * Updates the current screen by delegation.
     * 
     * @param elapsedTime The time elapsed in nanoseconds since the last call
     *                    to this method.
     */
    public void update(long elapsedTime)
    {
        currentScreen.update(elapsedTime);
    }


    /**************************************************************************
     * Renders the current screen by delegation.
     * 
     * @param backBuffer The buffer in memory on which to render the screen.
     */
    public void render(MHGraphicsCanvas backBuffer)
    {
        currentScreen.render(backBuffer);
    }


    /**************************************************************************
     * Unloads the current screen, replaces it with a new screen, and loads the
     * new screen.
     * 
     * @param newScreen The next screen to display.
     */
    public void changeScreen(MHScreen newScreen)
    {
        if (newScreen == null)
            newScreen = NULL_SCREEN;

        currentScreen.unload();
        currentScreen = newScreen;
        currentScreen.load();
    }


    public static int getDisplayWidth()
    {
        return (int) app.getDisplaySize().getX();
    }


    public static int getDisplayHeight()
    {
        return (int) app.getDisplaySize().getY();
    }


    /**************************************************************************
     * Dummy screen inspired by the Null Object pattern to prevent exceptions
     * in the event that a developer fails to provide a valid screen
     * implementation upon instantiating the screen manager.
     * 
     * @author Michael Henson
     */
    private class NullScreen extends MHScreen
    {
        private MHColor textColor = MHPlatform.createColor(0, 255, 0, 255);
        private MHColor bgColor = MHPlatform.createColor(0, 0, 0, 255);

        public NullScreen()
        {
            super("No screens loaded.");
        }

        @Override
        public void update(long elapsedTime)
        {
        }

        @Override
        public void render(MHGraphicsCanvas g)
        {
            render(g, 10, 25);
        }


        public void render(MHGraphicsCanvas g, int x, int y)
        {
            g.fill(bgColor);
            g.setColor(textColor);
            g.drawString("No screen loaded.", x, y);
        }
    }


    public static MHVector getDisplayOrigin()
    {
        return app.getDisplayOrigin();
    }
}
