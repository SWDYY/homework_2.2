package GUI;

import javax.swing.*;
import java.awt.BorderLayout;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import Bean.DBBean;

/**
 * 权限：经理
 * 按钮：新增员工账户
 * 功能：弹出面板以添加员工账户信息
 */
public class windowsForAddEmployeeAccount extends JFrame {

    private String phoneString;// 联系方式
    private String positionString;// 职位
    private String shopkeeperString;// 店长
    private String panel_sellStirng;// 销售
    private String palceString;// 工作地点
    private String saveString;// 保   存
    private String cancel;// 取   消
    private String user_name;//用户名
    private String new_repository_name=null;

    private JTextField textField_addAccount_usernameDisplay;
    private JTextField textField_addAccount_phoneNumberDisplay;

    public windowsForAddEmployeeAccount(ResourceBundle resourceBundle,DBBean db) {
        phoneString = resourceBundle.getString("phoneString");// 联系方式
        positionString = resourceBundle.getString("positionString");// 职位
        shopkeeperString = resourceBundle.getString("shopkeeperString");// 店长
        panel_sellStirng = resourceBundle.getString("panel_sellStirng");// 销售
        palceString = resourceBundle.getString("palceString");// 工作地点
        saveString = resourceBundle.getString("saveString");// 保   存
        cancel = resourceBundle.getString("cancel");// 取   消
        user_name = resourceBundle.getString("user_name");//用户名

        JPanel panel_total = new JPanel();
        panel_total.setLayout(new BorderLayout());
        this.add(panel_total);

        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(0, 10, 0, 30));
        panel_total.add(panel, BorderLayout.CENTER);
        GridBagLayout gbl_panel = new GridBagLayout();
        gbl_panel.columnWidths = new int[]{30, 100, 200, 0};
        gbl_panel.rowHeights = new int[]{30, 45, 45, 35, 35, 0};
        gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
        gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        panel.setLayout(gbl_panel);

        /***用户名***/
        JLabel label_addAccount_username = new JLabel(user_name);
        label_addAccount_username.setHorizontalAlignment(SwingConstants.CENTER);
        GridBagConstraints gbc_label_addAccount_username = new GridBagConstraints();
        gbc_label_addAccount_username.fill = GridBagConstraints.BOTH;
        gbc_label_addAccount_username.insets = new Insets(0, 0, 5, 5);
        gbc_label_addAccount_username.gridx = 1;
        gbc_label_addAccount_username.gridy = 1;
        panel.add(label_addAccount_username, gbc_label_addAccount_username);

        textField_addAccount_usernameDisplay = new JTextField();
        GridBagConstraints gbc_textField_addAccount_usernameDisplay = new GridBagConstraints();
        gbc_textField_addAccount_usernameDisplay.fill = GridBagConstraints.BOTH;
        gbc_textField_addAccount_usernameDisplay.insets = new Insets(0, 0, 5, 0);
        gbc_textField_addAccount_usernameDisplay.gridx = 2;
        gbc_textField_addAccount_usernameDisplay.gridy = 1;
        panel.add(textField_addAccount_usernameDisplay, gbc_textField_addAccount_usernameDisplay);
        textField_addAccount_usernameDisplay.setColumns(10);

        /***联系方式***/
        JLabel label_addAccount_phoneNumber = new JLabel(phoneString);
        label_addAccount_phoneNumber.setHorizontalAlignment(SwingConstants.CENTER);
        GridBagConstraints gbc_label_addAccount_phoneNumber = new GridBagConstraints();
        gbc_label_addAccount_phoneNumber.fill = GridBagConstraints.BOTH;
        gbc_label_addAccount_phoneNumber.insets = new Insets(0, 0, 5, 5);
        gbc_label_addAccount_phoneNumber.gridx = 1;
        gbc_label_addAccount_phoneNumber.gridy = 2;
        panel.add(label_addAccount_phoneNumber, gbc_label_addAccount_phoneNumber);

        textField_addAccount_phoneNumberDisplay = new JTextField();
        GridBagConstraints gbc_textField_addAccount_phoneNumberDisplay = new GridBagConstraints();
        gbc_textField_addAccount_phoneNumberDisplay.fill = GridBagConstraints.BOTH;
        gbc_textField_addAccount_phoneNumberDisplay.insets = new Insets(0, 0, 5, 0);
        gbc_textField_addAccount_phoneNumberDisplay.gridx = 2;
        gbc_textField_addAccount_phoneNumberDisplay.gridy = 2;
        panel.add(textField_addAccount_phoneNumberDisplay, gbc_textField_addAccount_phoneNumberDisplay);
        textField_addAccount_phoneNumberDisplay.setColumns(10);

        /***职位***/
        JLabel label_addAccount_position = new JLabel(positionString);
        label_addAccount_position.setHorizontalAlignment(SwingConstants.CENTER);
        GridBagConstraints gbc_label_addAccount_position = new GridBagConstraints();
        gbc_label_addAccount_position.fill = GridBagConstraints.BOTH;
        gbc_label_addAccount_position.insets = new Insets(0, 0, 5, 5);
        gbc_label_addAccount_position.gridx = 1;
        gbc_label_addAccount_position.gridy = 3;
        panel.add(label_addAccount_position, gbc_label_addAccount_position);

        Box horizontalBox_addAccount_choosePosition = Box.createHorizontalBox();
        GridBagConstraints gbc_horizontalBox_addAccount_choosePosition = new GridBagConstraints();
        gbc_horizontalBox_addAccount_choosePosition.fill = GridBagConstraints.BOTH;

        gbc_horizontalBox_addAccount_choosePosition.insets = new Insets(0, 0, 5, 0);
        gbc_horizontalBox_addAccount_choosePosition.gridx = 2;
        gbc_horizontalBox_addAccount_choosePosition.gridy = 3;
        panel.add(horizontalBox_addAccount_choosePosition, gbc_horizontalBox_addAccount_choosePosition);

        JRadioButton radioButton_addAccount_shopkeeper = new JRadioButton(shopkeeperString);
        horizontalBox_addAccount_choosePosition.add(radioButton_addAccount_shopkeeper);

        ArrayList<String> repository_name_list=new ArrayList<String>();
        ResultSet repository_name_all=db.executeFindAll("repository_name");
        try {
            while(repository_name_all.next()){
                repository_name_list.add(repository_name_all.getString("name"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        String[] repository_name = new String[repository_name_list.size()];
        repository_name_list.toArray(repository_name);
        JComboBox comboBox_addAccount_chooseLocation = new JComboBox(repository_name);
        GridBagConstraints gbc_comboBox_addAccount_chooseLocation = new GridBagConstraints();
        gbc_comboBox_addAccount_chooseLocation.fill = GridBagConstraints.BOTH;
        gbc_comboBox_addAccount_chooseLocation.gridx = 2;
        gbc_comboBox_addAccount_chooseLocation.gridy = 4;
        panel.add(comboBox_addAccount_chooseLocation, gbc_comboBox_addAccount_chooseLocation);
        radioButton_addAccount_shopkeeper.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(radioButton_addAccount_shopkeeper.isSelected()){
                    JFrame windowToAddShop = new JFrame("新建店面");
                    JPanel panel = new JPanel();
                    panel.setLayout(new BorderLayout());
                    windowToAddShop.add(panel);
                    Box verBox = Box.createVerticalBox();
                    Box horizontalBox1 = Box.createHorizontalBox();
                    Box horizontalBox2 = Box.createHorizontalBox();
                    panel.add(verBox,BorderLayout.CENTER);
                    JLabel label = new JLabel("店铺名称");
                    JTextField textField = new JTextField();
                    JButton confirm = new JButton("确  定");
                    JButton cancel = new JButton("取  消");
                    cancel.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            windowToAddShop.dispose();
                        }
                    });
                    confirm.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            new_repository_name=textField.getText();
                            db.executeCreateNewTable(new_repository_name,"repository");
                            db.executeCreateNewTable(new_repository_name+"_order","order");
                            db.executeCreateNewTable(new_repository_name+"_item_order","item_order");
                            db.executeQuery("repository_name(name)",
                                    "'"+new_repository_name+"'");
                            comboBox_addAccount_chooseLocation.addItem(new_repository_name);
                            comboBox_addAccount_chooseLocation.setSelectedItem(new_repository_name);
                            windowToAddShop.dispose();
                        }
                    });
                    horizontalBox1.add(label);
                    horizontalBox1.add(textField);
                    horizontalBox2.add(confirm);
                    horizontalBox2.add(Box.createHorizontalStrut(50));
                    horizontalBox2.add(cancel);
                    verBox.add(horizontalBox1);
                    verBox.add(horizontalBox2);

                    windowToAddShop.setVisible(true);
                    windowToAddShop.setBounds(400,600,400,300);
                    windowToAddShop.setResizable(false);
                }

            }
        });


        JRadioButton radioButton_addAccount_employee = new JRadioButton(panel_sellStirng);
        horizontalBox_addAccount_choosePosition.add(radioButton_addAccount_employee);
        radioButton_addAccount_employee.setSelected(true);


        ButtonGroup group = new ButtonGroup();
        group.add(radioButton_addAccount_employee);
        group.add(radioButton_addAccount_shopkeeper);

        /***工作地点***/
        JLabel label_addAccount_location = new JLabel(palceString);
        label_addAccount_location.setHorizontalAlignment(SwingConstants.CENTER);
        GridBagConstraints gbc_label_addAccount_location = new GridBagConstraints();
        gbc_label_addAccount_location.fill = GridBagConstraints.BOTH;
        gbc_label_addAccount_location.insets = new Insets(0, 0, 0, 5);
        gbc_label_addAccount_location.gridx = 1;
        gbc_label_addAccount_location.gridy = 4;
        panel.add(label_addAccount_location, gbc_label_addAccount_location);

//        ArrayList<String> repository_name_list=new ArrayList<String>();
//        ResultSet repository_name_all=db.executeFindAll("repository_name");
//        try {
//            while(repository_name_all.next()){
//                repository_name_list.add(repository_name_all.getString("name"));
//            }
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//        String[] repository_name = new String[repository_name_list.size()];
//        repository_name_list.toArray(repository_name);
//        JComboBox comboBox_addAccount_chooseLocation = new JComboBox(repository_name);
//        GridBagConstraints gbc_comboBox_addAccount_chooseLocation = new GridBagConstraints();
//        gbc_comboBox_addAccount_chooseLocation.fill = GridBagConstraints.BOTH;
//        gbc_comboBox_addAccount_chooseLocation.gridx = 2;
//        gbc_comboBox_addAccount_chooseLocation.gridy = 4;
//        panel.add(comboBox_addAccount_chooseLocation, gbc_comboBox_addAccount_chooseLocation);

        /***底部按钮***/
        Box horizontalBox = Box.createHorizontalBox();
        panel_total.add(horizontalBox, BorderLayout.SOUTH);

        Component horizontalGlue_left = Box.createHorizontalGlue();
        horizontalBox.add(horizontalGlue_left);

        JButton button_addAccount_save = new JButton(saveString);
        button_addAccount_save.addActionListener(new ActionListener() {
            @Override

            public void actionPerformed(ActionEvent e) {
                if(radioButton_addAccount_employee.isSelected()){
                    db.executeQuery("login(user_name,user_password,phonenum,authority,belongto)",
                            "'"+textField_addAccount_usernameDisplay.getText() +"','123456','"
                                    +textField_addAccount_phoneNumberDisplay.getText()+"','employee','"
                                    +comboBox_addAccount_chooseLocation.getSelectedItem()+"'");
                }
                else if(radioButton_addAccount_shopkeeper.isSelected()){

                    db.executeQuery("login(user_name,user_password,phonenum,authority,belongto)",
                            "'"+textField_addAccount_usernameDisplay.getText() +"','123456','"
                                    +textField_addAccount_phoneNumberDisplay.getText()+"','shopkeeper','"
                                    +new_repository_name+"'");

                }

                dispose();
            }
        });
        horizontalBox.add(button_addAccount_save);

        Component rigidArea = Box.createRigidArea(new Dimension(70, 80));
        horizontalBox.add(rigidArea);

        JButton button_addAccount_cancel = new JButton(cancel);
        button_addAccount_cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        horizontalBox.add(button_addAccount_cancel);

        Component horizontalGlue_right = Box.createHorizontalGlue();
        horizontalBox.add(horizontalGlue_right);
    }
}
