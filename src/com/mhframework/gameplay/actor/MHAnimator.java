package com.mhframework.gameplay.actor;


public class MHAnimator
{
    private int currentFrame = 0;
    private long timeSinceLastUpdate = 0;
    private MHAnimationSequence frames;
    
    public MHAnimator(MHAnimationSequence animation)
    {
        setAnimationSequence(animation);
    }
    
    
    public void setAnimationSequence(MHAnimationSequence animation)
    {
        frames = animation;
        currentFrame = 0;
    }
    
    
    public void update(long elapsedTime, MHAnimationListener listener)
    {
        timeSinceLastUpdate += elapsedTime;
        if (timeSinceLastUpdate > frames.getDurationMillis(currentFrame))
        {
            timeSinceLastUpdate = 0;
            currentFrame++;
            
            if (currentFrame >= frames.getNumFrames())
            {
                currentFrame = 0;

                if (listener != null)
                    listener.animationSequenceEnded(frames);
            }

            if (listener != null)
                listener.animationFrameChanged(getImageID());
        }
    }
    
    
    public String getImageID()
    {
        return frames.getImageID(currentFrame);
    }

    
    public static interface MHAnimationListener
    {
        public void animationFrameChanged(String imageID);
        public void animationSequenceEnded(MHAnimationSequence animation);
    }

}
