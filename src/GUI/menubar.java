package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ResourceBundle;

import Bean.DBBean;
import language.language_convert;
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
    private menubar show=this;

    private JMenu menuFile;
    private JMenu menuSetting;
    private JMenu menuHelp;
    private JMenuItem menuItem_Quit;
    private JMenu menu_Language;
    private JMenuItem menuItem_Font;
    private JMenuItem menuItem_help;
    private JMenuItem menuItem_about;
    private JMenuItem menuItem_flash;
    private JRadioButtonMenuItem Chinese;
    private JRadioButtonMenuItem English;
    private JRadioButtonMenuItem German;

    private DBBean db;
    private Object[] para;

    public menubar(ResourceBundle resourceBundle, JFrame jFrame, DBBean db, Object[] para) {
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

        this.db = db;
        this.para = para;

        menuFile = new JMenu(MenuFileStirng);
        menuSetting = new JMenu(menuSettingStirng);
        menuHelp = new JMenu(menuHelpStirng);
        add(menuFile);
        add(menuSetting);
        add(menuHelp);

        menuItem_Quit = new JMenuItem(menuItem_QuitString);
        menuFile.add(menuItem_Quit);

        Chinese = new JRadioButtonMenuItem(ChineseSring);
        Chinese.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.dispose();
                JFrame win = null;
                if(jFrame.getClass().equals(new Employee(resourceBundle, db, "repository1").getClass())){
                    win=new Employee(language_convert.language_convertChinese(), db, ((Employee)jFrame).getBelongto());
                    win.setBounds(450, 150, 1500, 1000);
                }else if(jFrame.getClass().equals(new Manager(resourceBundle, db).getClass())) {
                    win = new Manager(language_convert.language_convertChinese(), db);
                    win.setBounds(450, 150, 1500, 1000);
                }else if(jFrame.getClass().equals(new Shopkeeper(resourceBundle, db, "repository1").getClass())){
                    win=new Shopkeeper(language_convert.language_convertChinese(), db, ((Shopkeeper)jFrame).getBelongto());
                    win.setBounds(450, 150, 1500, 1000);
                }else if(jFrame.getClass().equals(new windowsToLogin(resourceBundle, db).getClass())){
                    win=new windowsToLogin(language_convert.language_convertChinese(), db);
                    win.setBounds(400, 250, 400, 300);
                }
                win.setVisible(true);
                win.setResizable(false);
                win.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            }
        });
        Chinese.setSelected(true);
        English = new JRadioButtonMenuItem(EnglishSring);
        English.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.dispose();
                JFrame win = null;
                if(jFrame.getClass().equals(new Employee(resourceBundle, db, "repository1").getClass())){
                    win=new Employee(language_convert.language_convertEnglish(), db, ((Employee)jFrame).getBelongto());
                    win.setBounds(450, 150, 1500, 1000);
                }else if(jFrame.getClass().equals(new Manager(resourceBundle, db).getClass())){
                    win=new Manager(language_convert.language_convertEnglish(), db);
                    win.setBounds(450, 150, 1500, 1000);
                }else if(jFrame.getClass().equals(new Shopkeeper(resourceBundle, db, "repository1").getClass())){
                    win=new Shopkeeper(language_convert.language_convertEnglish(), db, ((Shopkeeper)jFrame).getBelongto());
                    win.setBounds(450, 150, 1500, 1000);
                }else if(jFrame.getClass().equals(new windowsToLogin(resourceBundle, db).getClass())){
                    win=new windowsToLogin(language_convert.language_convertEnglish(), db);
                    win.setBounds(400, 250, 400, 300);
                }
                win.setVisible(true);
                win.setResizable(false);
                win.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            }
        });
        German = new JRadioButtonMenuItem(DeutschSring);
        German.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.dispose();
                JFrame win = null;
                if(jFrame.getClass().equals(new Employee(resourceBundle, db, "repository1").getClass())){
                    win=new Employee(language_convert.language_convertDeutsch(), db, ((Employee)jFrame).getBelongto());
                    win.setBounds(450, 150, 1500, 1000);
                }else if(jFrame.getClass().equals(new Manager(resourceBundle, db).getClass())){
                    win=new Manager(language_convert.language_convertDeutsch(), db);
                    win.setBounds(450, 150, 1500, 1000);
                }else if(jFrame.getClass().equals(new Shopkeeper(resourceBundle, db, "repository1"))){
                    win=new Shopkeeper(language_convert.language_convertDeutsch(), db, ((Shopkeeper)jFrame).getBelongto());
                    win.setBounds(450, 150, 1500, 1000);
                }else if(jFrame.getClass().equals(new windowsToLogin(resourceBundle, db).getClass())){
                    win=new windowsToLogin(language_convert.language_convertDeutsch(), db);
                    win.setBounds(400, 250, 400, 300);
                }
                win.setVisible(true);
                win.setResizable(false);
                win.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            }
        });

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

        // 新建刷新加
        // todo @sxz
        menuItem_flash = new JMenuItem("刷新");
        menuHelp.add(menuItem_flash);
        menuItem_flash.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (para != null){
                    init_box temp = new init_box(resourceBundle, db);
                    temp.refresh(String.valueOf(para[0]), (MyJPanel[])para[1], db);
                    if (para[2] != null) temp.refresh((List[])para[2], db);
                }
            }
        });
    }
}

