package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

public class menubar extends JMenuBar {
    private String MenuFileStirng;//文件
    private String menuSettingStirng;//设置
    private String menuHelpStirng;//帮助
    private String menuItem_QuitString;//退出
    private String ChineseSring;//简体中文
    private String EnglishSring;//英语
    private String DeutschSring;//德语
    private String menu_LanguageSring;//选择语言
    private String menuItem_FontSring;//字体
    private String menuItem_helpSring;//帮助
    private String menuItem_aboutSring;//关于

    private JMenu menuFile;
    private JMenu menuSetting;
    private JMenu menuHelp;
    private JMenuItem menuItem_Quit;
    private JMenu menu_Language;
    private JMenuItem menuItem_Font;
    private JMenuItem menuItem_help;
    private JMenuItem menuItem_about;
    private JRadioButtonMenuItem Chinese;
    private JRadioButtonMenuItem English;
    private JRadioButtonMenuItem German;


    public menubar(ResourceBundle resourceBundle) {
        MenuFileStirng = resourceBundle.getString("MenuFileStirng");//文件
        menuSettingStirng = resourceBundle.getString("menuSettingStirng");//设置
        menuHelpStirng = resourceBundle.getString("menuHelpStirng");//帮助
        menuItem_QuitString = resourceBundle.getString("menuItem_QuitString");//退出
        ChineseSring = resourceBundle.getString("ChineseSring");//简体中文
        EnglishSring = resourceBundle.getString("EnglishSring");//英语
        DeutschSring = resourceBundle.getString("DeutschSring");//德语
        menu_LanguageSring = resourceBundle.getString("menu_LanguageSring");//选择语言
        menuItem_FontSring = resourceBundle.getString("menuItem_FontSring");//字体
        menuItem_helpSring = resourceBundle.getString("menuItem_helpSring");//帮助
        menuItem_aboutSring = resourceBundle.getString("menuItem_aboutSring");//关于

        menuFile = new JMenu(MenuFileStirng);
        menuSetting = new JMenu(menuSettingStirng);
        menuHelp = new JMenu(menuHelpStirng);
        add(menuFile);
        add(menuSetting);
        add(menuHelp);

        menuItem_Quit = new JMenuItem(menuItem_QuitString);
        menuFile.add(menuItem_Quit);

        Chinese = new JRadioButtonMenuItem(ChineseSring);
        Chinese.setSelected(true);
        English = new JRadioButtonMenuItem(EnglishSring);
        German = new JRadioButtonMenuItem(DeutschSring);

        ButtonGroup group = new ButtonGroup();
        group.add(Chinese);
        group.add(English);
        group.add(German);

        menu_Language = new JMenu(menu_LanguageSring);
        menu_Language.add(Chinese);
        menu_Language.add(English);
        menu_Language.add(German);

        menuItem_Font = new JMenuItem(menuItem_FontSring);
        menuItem_Font.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ////////////////待添加监听
            }
        });
        menuSetting.add(menu_Language);
        menuSetting.addSeparator();
        menuSetting.add(menuItem_Font);

        menuItem_help = new JMenuItem(menuItem_helpSring);
        menuItem_about = new JMenuItem(menuItem_aboutSring);
        menuHelp.add(menuItem_help);
        menuHelp.addSeparator();
        menuHelp.add(menuItem_about);


    }
}

