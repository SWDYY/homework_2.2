package GUI;

import Bean.DBBean;
import language.language_convert;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.util.Enumeration;

public class main {
    //设置全局字体
    public static void initGlobalFontSetting(Font fnt){
        FontUIResource fontRes = new FontUIResource(fnt);
        for(Enumeration keys = UIManager.getDefaults().keys(); keys.hasMoreElements();){
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            if(value instanceof FontUIResource)
                UIManager.put(key, fontRes);
        }
    }
    public static void main(String[] args) throws Exception {
        DBBean db=new DBBean();
        initGlobalFontSetting(new Font("仿宋", Font.PLAIN, 15));  //统一设置字体
        org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
        windowsToLogin winToLogin = new windowsToLogin(language_convert.language_convertAll(),db);
        winToLogin.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        winToLogin.setVisible(true);
        winToLogin.setBounds(400,150,400,300);
        //winToLogin.setResizable(false);
        winToLogin.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


    }
}
