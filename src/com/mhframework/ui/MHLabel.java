package com.mhframework.ui;

import com.mhframework.MHScreenManager;
import com.mhframework.platform.graphics.MHFont;
import com.mhframework.platform.graphics.MHGraphicsCanvas;

public class MHLabel extends MHGuiComponent
{
    public static final int ALIGN_LEFT   = -1;
    public static final int ALIGN_CENTER =  0;    
    public static final int ALIGN_RIGHT  =  1;
    
    private int textMargin = 5;
    private String text;
    private String[] lines;
    private MHFont font;
    private int alignment = ALIGN_LEFT;
    
    protected MHLabel(String caption)
    {
        text = caption;
        this.setVisible(true);
        this.setEnabled(true);
        this.setFocusable(false);
        this.setFont(MHFont.getDefaultFont());
    }
    
    
    public static MHLabel create(String caption)
    {
        return new MHLabel(caption);
    }

    
    public void render(MHGraphicsCanvas g)
    {
        this.render(g, getX(), getY());
    }

    
    public void resize(MHGraphicsCanvas g)
    {
        int width = this.getWidth() - textMargin*2;

        lines = font.splitLines(text, width);
        
        for (int i = 0; i < lines.length; i++)
            width = Math.max(width, font.stringWidth(lines[i]));

        width += textMargin*2;
        int height = font.getHeight() * lines.length + textMargin*2;
        
        super.setBounds(getX(), getY(), width, height);
    }
    
    
    public void setFont(MHFont font)
    {
        this.font = font;
    }
    
    
    public void setText(String caption)
    {
        text = caption;
    }
    

    @Override
    public void render(MHGraphicsCanvas g, int x, int y)
    {
        g.setFont(font);
        resize(g);
        super.render(g);
        
        int x0 = (int) MHScreenManager.getDisplayOrigin().getX();
        int y0 = (int) MHScreenManager.getDisplayOrigin().getY();
        int ty = y+textMargin+font.getHeight();
        for (String s : lines)
        {
            // if left aligned...
            int tx = x+textMargin;
            
            switch (alignment)
            {
                case ALIGN_RIGHT:
                    tx = x + getWidth() - textMargin - font.stringWidth(s);
                    break;
                case ALIGN_CENTER:
                    tx = x + (getWidth()/2) - (font.stringWidth(s)/2);
                    break;
            }
            
            g.drawString(s, x0+tx, y0+ty);
            ty += font.getHeight();
        }
    }


    /**
     * @return the alignment
     */
    public int getAlignment()
    {
        return alignment;
    }


    /**
     * @param alignment the alignment to set
     */
    public void setAlignment(int alignment)
    {
        this.alignment = alignment;
    }
}
