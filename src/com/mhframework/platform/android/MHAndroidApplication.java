package com.mhframework.platform.android;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.Window;
import android.view.WindowManager;

import com.mhframework.MHGame;
import com.mhframework.MHVideoSettings;
import com.mhframework.math.MHVector;
import com.mhframework.platform.MHGameApplication;
import com.mhframework.platform.android.graphics.MHAndroidCanvas;
import com.mhframework.platform.graphics.MHGraphicsCanvas;

public class MHAndroidApplication implements MHGameApplication
{
    private static final MHVector DISPLAY_ORIGIN = new MHVector(0, 0);
    private MHVector displaySize;
	@SuppressWarnings("unused")
	private MHAndroidInputEventHandler eventHandler;
	private static Activity activity;
    private static MHView view;
    
    private  MHAndroidApplication(Activity activity,
            MHVideoSettings displaySettings)
    {
    	MHAndroidApplication.activity = activity;
        activity.requestWindowFeature(Window.FEATURE_NO_TITLE);
        activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
         
        displaySize = new MHVector(displaySettings.displayWidth, displaySettings.displayHeight);
        
        view = new MHView(activity);
        activity.setContentView(view);
        // Register event handlers.
        eventHandler = new MHAndroidInputEventHandler(view);
    }

    
    public static Activity getActivity()
    {
    	return activity;
    }
    
    
    public static MHView getView()
    {
        return view;
    }
    

    public MHVector getDisplayOrigin()
    {
        return DISPLAY_ORIGIN;
    }


    public MHVector getDisplaySize()
    {
        return displaySize;
    }


    public void shutdown()
    {
        // TODO Auto-generated method stub

    }


    public void present(MHGraphicsCanvas backBuffer)
    {
        view.present(backBuffer);
    }


    public static MHGameApplication create(Activity activity, MHVideoSettings displaySettings)
    {
        return new MHAndroidApplication(activity, displaySettings);
    }

    
    public class MHView extends SurfaceView
    {
        SurfaceHolder surface;

        public MHView(Context context)
        {
            super(context);
            surface = getHolder();
        }
        

        public void present(MHGraphicsCanvas backBuffer)
        {
            if (surface.getSurface().isValid())
            {
                Bitmap bmp = ((MHAndroidCanvas)MHGame.getBackBuffer()).getBitmap();
                Canvas canvas = surface.lockCanvas();
                canvas.drawBitmap(bmp, 0, 0, null);
                surface.unlockCanvasAndPost(canvas);
            }
        }
        
        
        @Override 
        public void onDraw(Canvas canvas) 
        {
            super.onDraw(canvas);
        }
    }
}
