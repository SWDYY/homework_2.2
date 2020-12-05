package language;

import GUI.windowsToLogin;

import javax.swing.*;
import java.util.Locale;
import java.util.ResourceBundle;

public class language_convert {
    private static String basename_English =  "language_resource.my_English" ;
    private static String basename_Deutsch =  "language_resource.my_Deutsch" ;
    private static String basename_Chinese =  "language_resource.my_Chinese" ;
    private static Locale cn = Locale.CHINA; //中文
    private static Locale us = Locale.US; //英文
    private static Locale Deu=Locale.GERMAN;//德语
    private static ResourceBundle myResourcesCN = ResourceBundle.getBundle(basename_Chinese,cn);//中文
    private static ResourceBundle myResourcesUS = ResourceBundle.getBundle(basename_English,us);//英文
    private static ResourceBundle myResourcesDeu = ResourceBundle.getBundle(basename_Deutsch,Deu);//德语

    /**
     * 设定不同语言    更改返回值返回不同语言
     * @return
     */
    public static ResourceBundle language_convertAll(){
        return myResourcesCN;
    }
}
