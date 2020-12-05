package GUI;

import Bean.DBBean;
//import op.returnVector;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.swing.border.EmptyBorder;
import javax.xml.crypto.Data;

public class windowsToChangeStockValue extends JFrame {

    private String changeFigureString;// 修改货物属性
    private String productIDString;// 货物ID
    private String product_nameString;// 货品名称
    private String productNumString;//// 货物数量
    private String productInpriceString;//// 商品进价
    private String productOutpriceString;//// 商品售价
    private String button_account_changeString;// 修改
    private String cancel;// 取   消

    private JTextField textField_changeGoodsValue_goodNameDisplay;
    private JTextField textField_changeGoodsValue_goodsInpriceDisplay;
    private JTextField textField_changeGoodsValue_goodsOutpriceDisplay;
    private JTextField textField_changeGoodsValue_goodsIDDisplay;
    private JTextField textField_changeGoodsValue_goodsNumberDisplay;
    private windowsToChangeStockValue a = this;

    public windowsToChangeStockValue(ResourceBundle resourceBundle,DBBean db) {
        changeFigureString = resourceBundle.getString("changeFigureString");// 修改货物属性
        productIDString = resourceBundle.getString("productIDString");// 货物ID
        product_nameString = resourceBundle.getString("product_nameString");// 货品名称
        productNumString = resourceBundle.getString("productNumString");//// 货物数量
        productInpriceString = resourceBundle.getString("productInpriceString");//// 商品进价
        productOutpriceString = resourceBundle.getString("productOutpriceString");//// 商品售价
        button_account_changeString = resourceBundle.getString("button_account_changeString");// 修改
        cancel = resourceBundle.getString("cancel");// 取   消
        //public windowsToChangeStockValue(DBBean db, JScrollPane scrollPane, ResultSet resultSet) {
        /**
         try {
         resultSet.next();
         } catch (SQLException throwables) {
         throwables.printStackTrace();
         }
         **/
        setTitle(changeFigureString);

        JPanel panel_changeGoodsValue = new JPanel();
        panel_changeGoodsValue.setBorder(new EmptyBorder(30, 30, 30, 30));
        getContentPane().add(panel_changeGoodsValue, BorderLayout.CENTER);
        panel_changeGoodsValue.setLayout(new GridLayout(6, 2, 0, 10));

        JLabel Label_changeGoodsValue_goodsID = new JLabel(productIDString);
        Label_changeGoodsValue_goodsID.setHorizontalAlignment(SwingConstants.CENTER);
        panel_changeGoodsValue.add(Label_changeGoodsValue_goodsID);

        //货物ID  显示框
        textField_changeGoodsValue_goodsIDDisplay = new JTextField();
        /**
         try {
         textField_changeGoodsValue_goodsIDDisplay.setText(String.valueOf(resultSet.getObject("id")));
         } catch (SQLException throwables) {
         throwables.printStackTrace();
         }
         **/
        textField_changeGoodsValue_goodsIDDisplay.setEditable(false);
        panel_changeGoodsValue.add(textField_changeGoodsValue_goodsIDDisplay);
        textField_changeGoodsValue_goodsIDDisplay.setColumns(10);

        JLabel Label_changeGoodsValue_goodsName = new JLabel(product_nameString);
        Label_changeGoodsValue_goodsName.setHorizontalAlignment(SwingConstants.CENTER);
        panel_changeGoodsValue.add(Label_changeGoodsValue_goodsName);

        //货物名称  显示框
        textField_changeGoodsValue_goodNameDisplay = new JTextField();
        textField_changeGoodsValue_goodNameDisplay.setColumns(10);
        textField_changeGoodsValue_goodNameDisplay.setEditable(false);

        /**
         try {
         textField_changeGoodsValue_goodNameDisplay.setText(String.valueOf(resultSet.getObject("name")));
         } catch (SQLException throwables) {
         throwables.printStackTrace();
         };
         **/
        panel_changeGoodsValue.add(textField_changeGoodsValue_goodNameDisplay);


        JLabel Label_changeGoodsValue_goodsNumber = new JLabel(productNumString);
        Label_changeGoodsValue_goodsNumber.setHorizontalAlignment(SwingConstants.CENTER);
        panel_changeGoodsValue.add(Label_changeGoodsValue_goodsNumber);

        //货物数量  显示框
        textField_changeGoodsValue_goodsNumberDisplay = new JTextField();

        /**try {
         textField_changeGoodsValue_goodsNumberDisplay.setText(String.valueOf(resultSet.getObject("num")));
         } catch (SQLException throwables) {
         throwables.printStackTrace();
         }**/
        panel_changeGoodsValue.add(textField_changeGoodsValue_goodsNumberDisplay);
        textField_changeGoodsValue_goodsNumberDisplay.setColumns(10);

        JLabel Label_changeGoodsValue_goodsInprice = new JLabel(productInpriceString);
        Label_changeGoodsValue_goodsInprice.setHorizontalAlignment(SwingConstants.CENTER);
        panel_changeGoodsValue.add(Label_changeGoodsValue_goodsInprice);

        //商品进价  显示框
        textField_changeGoodsValue_goodsInpriceDisplay = new JTextField();
        /**
         try {
         textField_changeGoodsValue_goodsInpriceDisplay.setText(String.valueOf(resultSet.getObject("inprice")));
         } catch (SQLException throwables) {
         throwables.printStackTrace();
         }
         **/
        panel_changeGoodsValue.add(textField_changeGoodsValue_goodsInpriceDisplay);
        textField_changeGoodsValue_goodsInpriceDisplay.setColumns(10);

        JLabel Label_changeGoodsValue_goodsOutprice = new JLabel(productOutpriceString);
        Label_changeGoodsValue_goodsOutprice.setHorizontalAlignment(SwingConstants.CENTER);
        panel_changeGoodsValue.add(Label_changeGoodsValue_goodsOutprice);

        //商品售价   显示框
        textField_changeGoodsValue_goodsOutpriceDisplay = new JTextField();
        /**
         try {
         textField_changeGoodsValue_goodsOutpriceDisplay.setText(String.valueOf(resultSet.getObject("outprice")));
         } catch (SQLException throwables) {
         throwables.printStackTrace();
         }
         **/
        panel_changeGoodsValue.add(textField_changeGoodsValue_goodsOutpriceDisplay);
        textField_changeGoodsValue_goodsOutpriceDisplay.setColumns(10);

        JButton Button_changeGoodsValue_change = new JButton(button_account_changeString);
        panel_changeGoodsValue.add(Button_changeGoodsValue_change);
        Button_changeGoodsValue_change.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                /**
                 db.executeUpdate(textField_changeGoodsValue_goodsIDDisplay.getText(),"itemmanager","id",textField_changeGoodsValue_goodsNumberDisplay.getText(),"num");
                 db.executeUpdate(textField_changeGoodsValue_goodsIDDisplay.getText(),"itemmanager","id",textField_changeGoodsValue_goodsInpriceDisplay.getText(),"inprice");
                 db.executeUpdate(textField_changeGoodsValue_goodsIDDisplay.getText(),"itemmanager","id",textField_changeGoodsValue_goodsOutpriceDisplay.getText(),"outprice");
                 //table刷新
                 Vector<Object> name = new Vector<>(Arrays.asList("id", "name", "num", "inprice"));
                 Vector<Vector<Object>> data = returnVector.FromDBReadAll(db,"itemmanager",name);
                 DefaultTableModel_noEditable model = new DefaultTableModel_noEditable(data, name, 5);
                 JTable table = new JTable();
                 table.setModel(model);
                 scrollPane.setViewportView(table);
                 **/
                dispose();
            }

        });

        JButton Button_changeGoodsValue_cancel = new JButton(cancel);
        Button_changeGoodsValue_cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        panel_changeGoodsValue.add(Button_changeGoodsValue_cancel);
        panel_changeGoodsValue.setVisible(true);
    }


}
