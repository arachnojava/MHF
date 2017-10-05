package com.mhframework.platform.android.graphics;


import java.io.IOException;
import java.io.InputStream;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import com.mhframework.platform.android.MHAndroidApplication;
import com.mhframework.platform.graphics.MHBitmapImage;
import com.mhframework.platform.graphics.MHGraphicsCanvas;


public class MHAndroidBitmap extends MHBitmapImage
{
    private Bitmap bitmap;
    private MHAndroidCanvas canvas;
    
    private MHAndroidBitmap(String filename) 
    {
    	try
    	{
    		AssetManager assetManager = MHAndroidApplication.getActivity().getAssets();
    		InputStream inputStream = assetManager.open(filename);
    		bitmap = BitmapFactory.decodeStream(inputStream);
            canvas = new MHAndroidCanvas(bitmap);
    		inputStream.close();
    	}
    	catch (IOException ioe)
    	{

    	}
	}
    
    
    public MHAndroidBitmap(int width, int height) 
    {
		bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        canvas = new MHAndroidCanvas(bitmap);
	}


	public static MHAndroidBitmap create(String filename)
    {
    	return new MHAndroidBitmap(filename);
    }


	public static MHBitmapImage create(int width, int height) 
	{
		return new MHAndroidBitmap(width, height);
	}
	
	
	public Bitmap getBitmap()
    {
        return bitmap;
    }
	
	
    @Override
    public MHGraphicsCanvas getGraphicsCanvas()
    {
        return canvas;
    }
}
