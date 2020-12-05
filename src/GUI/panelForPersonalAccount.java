package GUI;

import Bean.DBBean;
import language.language_convert;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Component;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ResourceBundle;


/**
 * 本页面为“个人账户”页面
 * 包含“修改密码”、“退出登录“两个子页面
 * ”修改密码“页面界面提供已登陆用户的密码修改
 * ”退出登录“页面界面提供退出和注销服务
 */
public class panelForPersonalAccount extends JPanel {

    private String changePasswordString;//修改密码
    private String oldPasswordString;//旧密码
    private String newPasswordString;//新密码
    private String confirmString;//确  认
    private String logOutString;//退出登录
    private String cancelAccountString;//注销账户
    private String quitSystemString;//退出系统

    private JTextField textField_changePassword_oldPassword;
    private JTextField textField_changePassword_newPassword;

    public panelForPersonalAccount(ResourceBundle resourceBundle) {
        changePasswordString = resourceBundle.getString("changePasswordString");//修改密码
        oldPasswordString = resourceBundle.getString("oldPasswordString");//旧密码
        newPasswordString = resourceBundle.getString("newPasswordString");//新密码
        confirmString = resourceBundle.getString("confirmString");//确  认
        logOutString = resourceBundle.getString("logOutString");//退出登录
        cancelAccountString = resourceBundle.getString("cancelAccountString");//注销账户
        quitSystemString = resourceBundle.getString("quitSystemString");//退出系统

        setLayout(new BorderLayout(0, 0));

        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        add(tabbedPane, BorderLayout.CENTER);

        //“修改密码”界面
        JPanel panel_changePassword = new JPanel();
        panel_changePassword.setToolTipText(changePasswordString);
        tabbedPane.addTab(changePasswordString, null, panel_changePassword, null);
        panel_changePassword.setLayout(new BorderLayout(0, 0));

        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(40, 40, 40, 40));
        panel_changePassword.add(panel, BorderLayout.CENTER);
        GridBagLayout gbl_panel = new GridBagLayout();
        gbl_panel.columnWidths = new int[]{40, 100, 180, 0};
        gbl_panel.rowHeights = new int[]{40, 40, 40, 0};
        gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
        gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
        panel.setLayout(gbl_panel);

        JLabel Label_changePassword_oldPasswordDisplay = new JLabel(oldPasswordString);
        Label_changePassword_oldPasswordDisplay.setHorizontalAlignment(SwingConstants.CENTER);
        GridBagConstraints gbc_Label_changePassword_oldPasswordDisplay = new GridBagConstraints();
        gbc_Label_changePassword_oldPasswordDisplay.fill = GridBagConstraints.BOTH;
        gbc_Label_changePassword_oldPasswordDisplay.insets = new Insets(0, 0, 5, 5);
        gbc_Label_changePassword_oldPasswordDisplay.gridx = 1;
        gbc_Label_changePassword_oldPasswordDisplay.gridy = 1;
        panel.add(Label_changePassword_oldPasswordDisplay, gbc_Label_changePassword_oldPasswordDisplay);

        textField_changePassword_oldPassword = new JTextField();
        textField_changePassword_oldPassword.setColumns(10);
        GridBagConstraints gbc_textField_changePassword_oldPassword = new GridBagConstraints();
        gbc_textField_changePassword_oldPassword.fill = GridBagConstraints.BOTH;
        gbc_textField_changePassword_oldPassword.insets = new Insets(0, 0, 5, 0);
        gbc_textField_changePassword_oldPassword.gridx = 2;
        gbc_textField_changePassword_oldPassword.gridy = 1;
        panel.add(textField_changePassword_oldPassword, gbc_textField_changePassword_oldPassword);

        JLabel Label_changePasswprd_newPasswordDisplay = new JLabel(newPasswordString);
        Label_changePasswprd_newPasswordDisplay.setHorizontalAlignment(SwingConstants.CENTER);
        GridBagConstraints gbc_Label_changePasswprd_newPasswordDisplay = new GridBagConstraints();
        gbc_Label_changePasswprd_newPasswordDisplay.fill = GridBagConstraints.BOTH;
        gbc_Label_changePasswprd_newPasswordDisplay.insets = new Insets(0, 0, 0, 5);
        gbc_Label_changePasswprd_newPasswordDisplay.gridx = 1;
        gbc_Label_changePasswprd_newPasswordDisplay.gridy = 2;
        panel.add(Label_changePasswprd_newPasswordDisplay, gbc_Label_changePasswprd_newPasswordDisplay);

        textField_changePassword_newPassword = new JTextField();
        textField_changePassword_newPassword.setColumns(10);
        GridBagConstraints gbc_textField_changePassword_newPassword = new GridBagConstraints();
        gbc_textField_changePassword_newPassword.fill = GridBagConstraints.BOTH;
        gbc_textField_changePassword_newPassword.gridx = 2;
        gbc_textField_changePassword_newPassword.gridy = 2;
        panel.add(textField_changePassword_newPassword, gbc_textField_changePassword_newPassword);

        Box horizontalBox = Box.createHorizontalBox();
        panel_changePassword.add(horizontalBox, BorderLayout.SOUTH);

        Component verticalStrut_1 = Box.createVerticalStrut(60);
        horizontalBox.add(verticalStrut_1);

        Component horizontalGlue = Box.createHorizontalGlue();
        horizontalBox.add(horizontalGlue);

        JButton Button_changePassword_save = new JButton(confirmString);
        horizontalBox.add(Button_changePassword_save);

        Component horizontalGlue_1 = Box.createHorizontalGlue();
        horizontalBox.add(horizontalGlue_1);

        Component verticalStrut = Box.createVerticalStrut(20);
        horizontalBox.add(verticalStrut);

        //”退出登录“界面
        JPanel panel_logout = new JPanel();
        tabbedPane.addTab(logOutString, null, panel_logout, null);
        panel_logout.setLayout(new BoxLayout(panel_logout, BoxLayout.X_AXIS));

        Component horizontalGlue_2 = Box.createHorizontalGlue();
        panel_logout.add(horizontalGlue_2);

        JButton Button_logout_logout = new JButton(cancelAccountString);
        Button_logout_logout.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Component cmp = e.getComponent();
                while (!(cmp instanceof JFrame) || cmp.getParent() != null) {
                    cmp = cmp.getParent();
                }
                ((JFrame) cmp).dispose();
                windowsToLogin win = new windowsToLogin(language_convert.language_convertAll(), new DBBean());
                win.setVisible(true);
                win.setBounds(400, 250, 400, 300);
                win.setResizable(false);
                win.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            }
        });


        panel_logout.add(Button_logout_logout);

        JButton Button_logout_quit = new JButton(quitSystemString);
        Button_logout_quit.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Component cmp = e.getComponent();
                while (!(cmp instanceof JFrame) || cmp.getParent() != null) {
                    cmp = cmp.getParent();
                }
                ((JFrame) cmp).dispose();
            }
        });
        panel_logout.add(Button_logout_quit);

        Component horizontalGlue_3 = Box.createHorizontalGlue();
        panel_logout.add(horizontalGlue_3);
    }

}
