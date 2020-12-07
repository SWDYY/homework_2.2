package GUI;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import Bean.DBBean;

/**
 * 按钮“忘记密码？”触发的弹窗
 */
public class windowsToChangePassword extends JFrame {

    private String FindPasswordString; // 找回密码
    private String user_name;//用户名
    private String phoneString;// 联系方式
    private String newPasswordString;// 新密码
    private String confirmString;// 确认
    private String cancel;// 取   消

    private JTextField textField_forgetPassword_IDDisplay;
    private JTextField textField_forgetPassword_phoneNumber;
    private JTextField textField_forgetPassword_newPasswordDisplay;

    public windowsToChangePassword(ResourceBundle resourceBundle,DBBean db) {
        FindPasswordString = resourceBundle.getString("FindPasswordString"); // 找回密码
        user_name = resourceBundle.getString("user_name");//用户名
        phoneString = resourceBundle.getString("phonenum");// 联系方式
        newPasswordString = resourceBundle.getString("newPasswordString");// 新密码
        confirmString = resourceBundle.getString("confirmString");// 确认
        cancel = resourceBundle.getString("cancel");// 取   消

        setTitle(FindPasswordString);
        JPanel panel_total = new JPanel();
        getContentPane().add(panel_total);
        panel_total.setLayout(new BorderLayout());
        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(30, 30, 30, 30));
        panel_total.add(panel, BorderLayout.CENTER);
        GridBagLayout gbl_panel = new GridBagLayout();
        gbl_panel.columnWidths = new int[]{45, 100, 178, 0};
        gbl_panel.rowHeights = new int[]{30, 41, 41, 41, 0};
        gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
        gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        panel.setLayout(gbl_panel);

        JLabel Label_forgetPassword_ID = new JLabel(user_name);
        GridBagConstraints gbc_Label_forgetPassword_ID = new GridBagConstraints();
        gbc_Label_forgetPassword_ID.fill = GridBagConstraints.BOTH;
        gbc_Label_forgetPassword_ID.insets = new Insets(0, 0, 5, 5);
        gbc_Label_forgetPassword_ID.gridx = 1;
        gbc_Label_forgetPassword_ID.gridy = 1;
        panel.add(Label_forgetPassword_ID, gbc_Label_forgetPassword_ID);

        textField_forgetPassword_IDDisplay = new JTextField();
        GridBagConstraints gbc_textField_forgetPassword_IDDisplay = new GridBagConstraints();
        gbc_textField_forgetPassword_IDDisplay.fill = GridBagConstraints.BOTH;
        gbc_textField_forgetPassword_IDDisplay.insets = new Insets(0, 0, 5, 0);
        gbc_textField_forgetPassword_IDDisplay.gridx = 2;
        gbc_textField_forgetPassword_IDDisplay.gridy = 1;
        panel.add(textField_forgetPassword_IDDisplay, gbc_textField_forgetPassword_IDDisplay);
        textField_forgetPassword_IDDisplay.setColumns(10);

        JLabel Label_forgetPassword_phoneNumber = new JLabel(phoneString);
        GridBagConstraints gbc_Label_forgetPassword_phoneNumber = new GridBagConstraints();
        gbc_Label_forgetPassword_phoneNumber.fill = GridBagConstraints.BOTH;
        gbc_Label_forgetPassword_phoneNumber.insets = new Insets(0, 0, 5, 5);
        gbc_Label_forgetPassword_phoneNumber.gridx = 1;
        gbc_Label_forgetPassword_phoneNumber.gridy = 2;
        panel.add(Label_forgetPassword_phoneNumber, gbc_Label_forgetPassword_phoneNumber);

        textField_forgetPassword_phoneNumber = new JTextField();
        GridBagConstraints gbc_textField_forgetPassword_phoneNumber = new GridBagConstraints();
        gbc_textField_forgetPassword_phoneNumber.fill = GridBagConstraints.BOTH;
        gbc_textField_forgetPassword_phoneNumber.insets = new Insets(0, 0, 5, 0);
        gbc_textField_forgetPassword_phoneNumber.gridx = 2;
        gbc_textField_forgetPassword_phoneNumber.gridy = 2;
        panel.add(textField_forgetPassword_phoneNumber, gbc_textField_forgetPassword_phoneNumber);
        textField_forgetPassword_phoneNumber.setColumns(10);

        JLabel Label_forgetPassword_newPassword = new JLabel(newPasswordString);
        GridBagConstraints gbc_Label_forgetPassword_newPassword = new GridBagConstraints();
        gbc_Label_forgetPassword_newPassword.fill = GridBagConstraints.BOTH;
        gbc_Label_forgetPassword_newPassword.insets = new Insets(0, 0, 0, 5);
        gbc_Label_forgetPassword_newPassword.gridx = 1;
        gbc_Label_forgetPassword_newPassword.gridy = 3;
        panel.add(Label_forgetPassword_newPassword, gbc_Label_forgetPassword_newPassword);

        textField_forgetPassword_newPasswordDisplay = new JTextField();
        GridBagConstraints gbc_textField_forgetPassword_newPasswordDisplay = new GridBagConstraints();
        gbc_textField_forgetPassword_newPasswordDisplay.fill = GridBagConstraints.BOTH;
        gbc_textField_forgetPassword_newPasswordDisplay.gridx = 2;
        gbc_textField_forgetPassword_newPasswordDisplay.gridy = 3;
        panel.add(textField_forgetPassword_newPasswordDisplay, gbc_textField_forgetPassword_newPasswordDisplay);
        textField_forgetPassword_newPasswordDisplay.setColumns(10);


        Box horizontalBox = Box.createHorizontalBox();
        panel_total.add(horizontalBox, BorderLayout.SOUTH);

        Component horizontalGlue_left = Box.createHorizontalGlue();
        horizontalBox.add(horizontalGlue_left);

        JButton button_addItem_confirm = new JButton(confirmString);
        button_addItem_confirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //todo
                ResultSet find=db.executeFind(textField_forgetPassword_IDDisplay.getText(),"login", "user_name");
                try {
                    find.next();
                    if(find.getString("phonenum").equals(textField_forgetPassword_phoneNumber.getText()))
                        db.executeUpdate("'"+textField_forgetPassword_IDDisplay.getText()+"'","login", "user_name"
                                ,textField_forgetPassword_newPasswordDisplay.getText(),"user_password");
                    else{
                        JTextArea aboutarea = new JTextArea();
                        aboutarea.setText("电话号码错误\n");
                        JOptionPane.showConfirmDialog(null,aboutarea,"Error",JOptionPane.PLAIN_MESSAGE);

                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                dispose();
            }
        });
        horizontalBox.add(button_addItem_confirm);

        Component rigidArea = Box.createRigidArea(new Dimension(70, 70));
        horizontalBox.add(rigidArea);

        JButton button_addItem_cancel = new JButton(cancel);
        button_addItem_cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        horizontalBox.add(button_addItem_cancel);

        Component horizontalGlue_right = Box.createHorizontalGlue();
        horizontalBox.add(horizontalGlue_right);

    }

}
