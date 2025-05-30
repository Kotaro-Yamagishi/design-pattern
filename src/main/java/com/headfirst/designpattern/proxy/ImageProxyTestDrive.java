package com.headfirst.designpattern.proxy;

import java.net.URL;

public class ImageProxyTestDrive {
    

    public void test(){
        URL imageURL = null;
        // テスト用画像用意できなかった為、nullでをセット
        Icon icon = new ImageProxy(imageURL);
        System.out.println("Icon width: " + icon.getIconWidth());
        System.out.println("Icon height: " + icon.getIconHeight());
        icon.paintIcon();
        
        // 画像の読み込みはここで行われる
        System.out.println("Loading image...");
        icon.paintIcon();
    }
}
