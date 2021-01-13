package pos;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.EmptyBorder;

import Bean.DBBean;
import com.mysql.cj.protocol.Resultset;
import language.language_convert;
import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class windowstologin extends JFrame {
    /***/
    private ResourceBundle resourceBundle;
    private String user_login;//用户登录
    private String user_name;//用户名
    private String user_password;//密码
    private String cancel;//取消
    private String login;//登录


    private JTextField textField_login_username;
    private JTextField textField_login_password;

    public static void main(String[] argv){
        windowstologin a= new windowstologin(null, new DBBean());
        a.setVisible(true);
    }

    public windowstologin(ResourceBundle resourceBundle, DBBean db) {

        resourceBundle = language_convert.language_convertChinese();
        user_login = resourceBundle.getString("user_login");//用户登录
        user_name = resourceBundle.getString("user_name");//用户名
        user_password = resourceBundle.getString("user_password");//密码
        cancel = resourceBundle.getString("cancel");//取消
        login = resourceBundle.getString("login");//登录


        try {
            BeautyEyeLNFHelper.launchBeautyEyeLNF();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //title//用户登录
        setTitle(user_login);

        JPanel panel_total = new JPanel();
        panel_total.setLayout(new BorderLayout());
        this.add(panel_total);

        /*******登陆界面********/
        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(60, 60, 60, 60));
        panel_total.add(panel, BorderLayout.CENTER);
        GridBagLayout gbl_panel = new GridBagLayout();
        gbl_panel.columnWidths = new int[]{30, 100, 148, 0};
        gbl_panel.rowHeights = new int[]{20, 34, 34, 34, 0};
        gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
        gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        panel.setLayout(gbl_panel);

        JLabel Label_login_username = new JLabel(user_name);
        Label_login_username.setFont(new Font("微软雅黑", Font.BOLD, 16));
        Label_login_username.setHorizontalAlignment(SwingConstants.CENTER);
        GridBagConstraints gbc_Label_login_username = new GridBagConstraints();
        gbc_Label_login_username.fill = GridBagConstraints.BOTH;
        gbc_Label_login_username.insets = new Insets(0, 0, 5, 5);
        gbc_Label_login_username.gridx = 1;
        gbc_Label_login_username.gridy = 1;
        panel.add(Label_login_username, gbc_Label_login_username);

        textField_login_username = new JTextField("");
        GridBagConstraints gbc_textField_login_username = new GridBagConstraints();
        gbc_textField_login_username.fill = GridBagConstraints.BOTH;
        gbc_textField_login_username.insets = new Insets(0, 0, 5, 0);
        gbc_textField_login_username.gridx = 2;
        gbc_textField_login_username.gridy = 1;
        panel.add(textField_login_username, gbc_textField_login_username);
        textField_login_username.setColumns(10);

        JLabel Label_login_password = new JLabel(user_password);
        Label_login_password.setFont(new Font("微软雅黑", Font.BOLD, 16));
        Label_login_password.setHorizontalAlignment(SwingConstants.CENTER);
        GridBagConstraints gbc_Label_login_password = new GridBagConstraints();
        gbc_Label_login_password.fill = GridBagConstraints.BOTH;
        gbc_Label_login_password.insets = new Insets(0, 0, 5, 5);
        gbc_Label_login_password.gridx = 1;
        gbc_Label_login_password.gridy = 2;
        panel.add(Label_login_password, gbc_Label_login_password);

        textField_login_password = new JTextField();
        GridBagConstraints gbc_textField_login_password = new GridBagConstraints();
        gbc_textField_login_password.fill = GridBagConstraints.BOTH;
        gbc_textField_login_password.insets = new Insets(0, 0, 5, 0);
        gbc_textField_login_password.gridx = 2;
        gbc_textField_login_password.gridy = 2;
        panel.add(textField_login_password, gbc_textField_login_password);
        textField_login_password.setColumns(10);

        /***********按钮及事件监听***********/
        JButton Button_login_cancel = new JButton(cancel);
        Button_login_cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        JButton Button_login_login = new JButton(login);
        Button_login_login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /*****测试代码*****/
                try {
                    ResultSet res = db.executeFind(textField_login_username.getText(), "login", "user_name");
                    if (!res.next() || textField_login_password.getText().equals("")){
                        JTextArea aboutarea = new JTextArea();
                        aboutarea.setText("用户名或密码错误！\n");
                        JOptionPane.showConfirmDialog(null,aboutarea,"Error!",JOptionPane.PLAIN_MESSAGE);
                        textField_login_username.setText("");
                        textField_login_password.setText("");
                    }
                    else{
                        if (res.getString("user_password").equals(textField_login_password.getText())){
                            dispose();
                            main a = new main(null, new DBBean(), res.getString("belongto"));
                        }
                    }

                }catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }

        });
        GridBagConstraints gbc_Button_login_login = new GridBagConstraints();
        gbc_Button_login_login.fill = GridBagConstraints.BOTH;
        gbc_Button_login_login.insets = new Insets(0, 0, 0, 5);
        gbc_Button_login_login.gridx = 1;
        gbc_Button_login_login.gridy = 3;
        panel.add(Button_login_login, gbc_Button_login_login);

        GridBagConstraints gbc_Button_login_cancel = new GridBagConstraints();
        gbc_Button_login_cancel.fill = GridBagConstraints.BOTH;
        gbc_Button_login_cancel.gridx = 2;
        gbc_Button_login_cancel.gridy = 3;
        panel.add(Button_login_cancel, gbc_Button_login_cancel);

        Box horizontalBox = Box.createHorizontalBox();
        panel_total.add(horizontalBox, BorderLayout.SOUTH);

        Component horizontalGlue = Box.createHorizontalGlue();
        horizontalBox.add(horizontalGlue);


        this.setBounds(400, 250, 400, 300);
    }
}
