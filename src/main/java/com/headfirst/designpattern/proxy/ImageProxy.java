package com.headfirst.designpattern.proxy;
import java.net.URL;

import javax.swing.ImageIcon;

import com.headfirst.designpattern.proxy.Icon;

public class ImageProxy implements Icon {

    volatile ImageIcon imageIcon;
    final URL imageURL;
    Thread retrievalThread;
    boolean retrieving = false;

    public ImageProxy(URL imageURL) {
        this.imageURL = imageURL;
    }

    synchronized void setImageIcon(ImageIcon imageIcon) {
        this.imageIcon = imageIcon;
    }

    @Override
    public int getIconWidth() {
        if (imageIcon != null) {
            return imageIcon.getIconWidth();
        }
        return 0; // or some default width  
    }

    @Override
    public int getIconHeight() {
        if (imageIcon != null) {
            return imageIcon.getIconHeight();
        }
        return 0; // or some default height
    }

    @Override
    public void paintIcon() {
        if (imageIcon == null) {
            if (!retrieving) {
                retrieving = true;
                retrievalThread = new Thread(() -> {
                    imageIcon = new ImageIcon(imageURL);
                    retrieving = false;
                });
                retrievalThread.start();
            }
        } else {
            imageIcon.paintIcon(null, null, 0, 0); // Assuming null for component and graphics context
        }
    }
    
}
