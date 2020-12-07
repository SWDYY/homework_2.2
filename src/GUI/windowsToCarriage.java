package GUI;

import Bean.DBBean;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class windowsToCarriage extends JFrame{
    private JPanel totalpanel;
    private JPanel panel;
    private Box horizontalBox;
    private JButton button_carriage_confirm;
    private JButton button_carriage_cancel;
    private JLabel label_carriage_itemName;
    private JLabel label_carriage_itemNumber;
    private JTextField textField_carriage_itemNameDisplay;
    private JTextField textField_carriage_itemNumberDisplay;


    windowsToCarriage(DBBean db, String[] repository,MyJPanel table){
        setTitle("选择货品");
        totalpanel = new JPanel();
        this.add(totalpanel);
        totalpanel.setLayout(new BorderLayout());
        panel = new JPanel();
        horizontalBox = Box.createHorizontalBox();
        totalpanel.add(panel,BorderLayout.CENTER);
        GridBagLayout gbl_panel = new GridBagLayout();
        gbl_panel.columnWidths = new int[]{40, 100, 178, 0};
        gbl_panel.rowHeights = new int[]{30, 40, 40, 24, 0};
        gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
        gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        panel.setLayout(gbl_panel);

        totalpanel.add(horizontalBox,BorderLayout.SOUTH);
        panel.setBorder(new EmptyBorder(40,0,0,50));

        /***货物名称***/
        //货物名称label
        label_carriage_itemName = new JLabel("货物名称");
        label_carriage_itemName.setHorizontalAlignment(SwingConstants.CENTER);

        GridBagConstraints gbc_label_carriage_itemName = new GridBagConstraints();
        gbc_label_carriage_itemName.fill = GridBagConstraints.BOTH;
        gbc_label_carriage_itemName.insets = new Insets(0, 0, 5, 5);
        gbc_label_carriage_itemName.gridx = 1;
        gbc_label_carriage_itemName.gridy = 1;
        panel.add(label_carriage_itemName, gbc_label_carriage_itemName);


        //货物名称textField
        textField_carriage_itemNameDisplay = new JTextField();
        GridBagConstraints gbc_textField_carriage_itemNameDisplay = new GridBagConstraints();
        gbc_textField_carriage_itemNameDisplay.fill = GridBagConstraints.BOTH;
        gbc_textField_carriage_itemNameDisplay.insets = new Insets(0, 0, 5, 0);
        gbc_textField_carriage_itemNameDisplay.gridx = 2;
        gbc_textField_carriage_itemNameDisplay.gridy = 1;
        panel.add(textField_carriage_itemNameDisplay, gbc_textField_carriage_itemNameDisplay);
        textField_carriage_itemNameDisplay.setColumns(10);


        /***货物数量***/
        //货物数量label
        label_carriage_itemNumber = new JLabel("货物数量");
        label_carriage_itemNumber.setHorizontalAlignment(SwingConstants.CENTER);

        GridBagConstraints gbc_label_carriage_itemNumber = new GridBagConstraints();
        gbc_label_carriage_itemNumber.fill = GridBagConstraints.BOTH;
        gbc_label_carriage_itemNumber.insets = new Insets(0, 0, 5, 5);
        gbc_label_carriage_itemNumber.gridx = 1;
        gbc_label_carriage_itemNumber.gridy = 2;
        panel.add(label_carriage_itemNumber, gbc_label_carriage_itemNumber);

        //货物数量textField
        textField_carriage_itemNumberDisplay = new JTextField();
        GridBagConstraints gbc_textField_carriage_itemNumberDisplay = new GridBagConstraints();
        gbc_textField_carriage_itemNumberDisplay.fill = GridBagConstraints.BOTH;
        gbc_textField_carriage_itemNumberDisplay.insets = new Insets(0, 0, 5, 0);
        gbc_textField_carriage_itemNumberDisplay.gridx = 2;
        gbc_textField_carriage_itemNumberDisplay.gridy = 2;
        panel.add(textField_carriage_itemNumberDisplay, gbc_textField_carriage_itemNumberDisplay);
        textField_carriage_itemNumberDisplay.setColumns(10);


        button_carriage_confirm = new JButton("确   认");
        button_carriage_confirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                // TODO: 输入检查
                //未找到商品名
                //以下true false代替表达式
                Integer all_num=-1;
                int num=0;
                ResultSet tmp=db.executeFind(textField_carriage_itemNameDisplay.getText(), repository[0],"name");
                    if(!tmp.next()){
                        JTextArea area = new JTextArea();
                        area.setText("未找到指定商品！");
                        JOptionPane.showConfirmDialog(null,area,"ERROR!",JOptionPane.PLAIN_MESSAGE);
                    }
                    else {
                        all_num = Integer.valueOf(String.valueOf(tmp.getObject("num")));
                        String outprice = tmp.getString("outprice");
                        String inprice = tmp.getString("inprice");
                        String outprice_wholesale = tmp.getString("outprice_wholesale");
                        num = Integer.valueOf(textField_carriage_itemNumberDisplay.getText());
                        System.out.println("num" + num);
                        System.out.println("all_num" + all_num);

                        //商品库存数量不足
                        if (all_num < num) {
                            JTextArea area = new JTextArea();
                            area.setText("指定商品库存数量不足！");
                            JOptionPane.showConfirmDialog(null, area, "ERROR!", JOptionPane.PLAIN_MESSAGE);
                        } else {
                            // TODO: @wkr
                            db.executeUpdate("'" + textField_carriage_itemNameDisplay.getText() +
                                    "'", repository[0], "name", String.valueOf(all_num - num), "num");
                            Integer all_num_to = -1;
                            ResultSet tmp_to = db.executeFind(textField_carriage_itemNameDisplay.getText(), repository[1], "name");
                            String new_num_to = null;
                            if (tmp_to.next()) {
                                all_num_to = Integer.valueOf(tmp_to.getString("num"));
                                new_num_to = String.valueOf(all_num_to + num);
                                db.executeUpdate("'" + textField_carriage_itemNameDisplay.getText() +
                                        "'", repository[1], "name", new_num_to, "num");
                            } else {

                                db.executeQuery(repository[1] + "(name,outprice,inprice,num,outprice_wholesale)",
                                        "'"+textField_carriage_itemNameDisplay.getText() + "'," + outprice + "," + inprice
                                                + "," + String.valueOf(num) + "," + outprice_wholesale);

                            }
                            Vector<String> data=new Vector<String>();
                            data.add(textField_carriage_itemNameDisplay.getText());
                            data.add(textField_carriage_itemNumberDisplay.getText());
                            table.getModel().addRow(data);
                            dispose();
                        }
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });

        button_carriage_cancel = new JButton("取   消");
        button_carriage_cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        horizontalBox.add(Box.createHorizontalGlue());
        horizontalBox.add(button_carriage_confirm);
        horizontalBox.add(Box.createRigidArea(new Dimension(80,100)));
        horizontalBox.add(button_carriage_cancel);
        horizontalBox.add(Box.createHorizontalGlue());
    }
}
