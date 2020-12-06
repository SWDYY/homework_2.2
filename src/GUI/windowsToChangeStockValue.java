package GUI;

import Bean.DBBean;
import op.returnVector;
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
    private JTextField textField_changeGoodsValue_goodsWholeDisplay;
    private JTextField textField_changeGoodsValue_goodsIDDisplay;
    private JTextField textField_changeGoodsValue_goodsNumberDisplay;

    private windowsToChangeStockValue a = this;

    public windowsToChangeStockValue(ResourceBundle resourceBundle,DBBean db, MyJPanel table, String belongto, String name) {
        changeFigureString = resourceBundle.getString("changeFigureString");// 修改货物属性
        productIDString = resourceBundle.getString("productIDString");// 货物ID
        product_nameString = resourceBundle.getString("product_nameString");// 货品名称
        productNumString = resourceBundle.getString("productNumString");//// 货物数量
        productInpriceString = resourceBundle.getString("productInpriceString");//// 商品进价
        productOutpriceString = resourceBundle.getString("productOutpriceString");//// 商品售价
        button_account_changeString = resourceBundle.getString("button_account_changeString");// 修改
        cancel = resourceBundle.getString("cancel");// 取   消

        setTitle(changeFigureString);

        JPanel panel_changeGoodsValue = new JPanel();
        panel_changeGoodsValue.setBorder(new EmptyBorder(30, 30, 30, 30));
        getContentPane().add(panel_changeGoodsValue, BorderLayout.CENTER);
        panel_changeGoodsValue.setLayout(new GridLayout(6, 2, 0, 10));

        // ID
        JLabel Label_changeGoodsValue_goodsID = new JLabel(productIDString);
        Label_changeGoodsValue_goodsID.setHorizontalAlignment(SwingConstants.CENTER);
        panel_changeGoodsValue.add(Label_changeGoodsValue_goodsID);
        textField_changeGoodsValue_goodsIDDisplay = new JTextField();
        textField_changeGoodsValue_goodsIDDisplay.setEditable(false);
        panel_changeGoodsValue.add(textField_changeGoodsValue_goodsIDDisplay);
        textField_changeGoodsValue_goodsIDDisplay.setColumns(10);

        // 名称
        JLabel Label_changeGoodsValue_goodsName = new JLabel(product_nameString);
        Label_changeGoodsValue_goodsName.setHorizontalAlignment(SwingConstants.CENTER);
        panel_changeGoodsValue.add(Label_changeGoodsValue_goodsName);
        textField_changeGoodsValue_goodNameDisplay = new JTextField();
        textField_changeGoodsValue_goodNameDisplay.setColumns(10);
        textField_changeGoodsValue_goodNameDisplay.setEditable(false);
        panel_changeGoodsValue.add(textField_changeGoodsValue_goodNameDisplay);

        // 数量
        JLabel Label_changeGoodsValue_goodsNumber = new JLabel(productNumString);
        Label_changeGoodsValue_goodsNumber.setHorizontalAlignment(SwingConstants.CENTER);
        panel_changeGoodsValue.add(Label_changeGoodsValue_goodsNumber);
        textField_changeGoodsValue_goodsNumberDisplay = new JTextField();
        panel_changeGoodsValue.add(textField_changeGoodsValue_goodsNumberDisplay);
        textField_changeGoodsValue_goodsNumberDisplay.setColumns(10);

        // 进价
        JLabel Label_changeGoodsValue_goodsInprice = new JLabel(productInpriceString);
        Label_changeGoodsValue_goodsInprice.setHorizontalAlignment(SwingConstants.CENTER);
        panel_changeGoodsValue.add(Label_changeGoodsValue_goodsInprice);
        textField_changeGoodsValue_goodsInpriceDisplay = new JTextField();
        panel_changeGoodsValue.add(textField_changeGoodsValue_goodsInpriceDisplay);
        textField_changeGoodsValue_goodsInpriceDisplay.setColumns(10);

        // 售价（零售）
        JLabel Label_changeGoodsValue_goodsOutprice = new JLabel(productOutpriceString);
        Label_changeGoodsValue_goodsOutprice.setHorizontalAlignment(SwingConstants.CENTER);
        panel_changeGoodsValue.add(Label_changeGoodsValue_goodsOutprice);
        textField_changeGoodsValue_goodsOutpriceDisplay = new JTextField();
        panel_changeGoodsValue.add(textField_changeGoodsValue_goodsOutpriceDisplay);
        textField_changeGoodsValue_goodsOutpriceDisplay.setColumns(10);

        // 售价（批发）
        // todo @sxz
        JLabel Label_changeGoodsValue_goodsWhole = new JLabel("总售价");
        Label_changeGoodsValue_goodsWhole.setHorizontalAlignment(SwingConstants.CENTER);
        panel_changeGoodsValue.add(Label_changeGoodsValue_goodsWhole);
        textField_changeGoodsValue_goodsWholeDisplay = new JTextField();
        panel_changeGoodsValue.add(textField_changeGoodsValue_goodsWholeDisplay);
        textField_changeGoodsValue_goodsWholeDisplay.setColumns(10);

        // 读货品的数据
        ResultSet findres = db.executeFind(name, belongto, "name");
        try{
            while (findres.next()) {
                textField_changeGoodsValue_goodsIDDisplay.setText(findres.getString("id"));
                textField_changeGoodsValue_goodNameDisplay.setText(name);
                textField_changeGoodsValue_goodsNumberDisplay.setText(findres.getString("num"));
                textField_changeGoodsValue_goodsInpriceDisplay.setText(findres.getString("inprie"));
                textField_changeGoodsValue_goodsOutpriceDisplay.setText(findres.getString("outprice"));
                textField_changeGoodsValue_goodsWholeDisplay.setText(findres.getString("outprice_wholesale"));
            }
        } catch (SQLException throwables){
            throwables.printStackTrace();
        }

        JButton Button_changeGoodsValue_change = new JButton(button_account_changeString);
        panel_changeGoodsValue.add(Button_changeGoodsValue_change);
        Button_changeGoodsValue_change.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 改数据库
                db.executeUpdate(textField_changeGoodsValue_goodsIDDisplay.getText(),belongto,"id",textField_changeGoodsValue_goodsNumberDisplay.getText(),"num");
                db.executeUpdate(textField_changeGoodsValue_goodsIDDisplay.getText(),belongto,"id",textField_changeGoodsValue_goodsInpriceDisplay.getText(),"inprie");
                db.executeUpdate(textField_changeGoodsValue_goodsIDDisplay.getText(),belongto,"id",textField_changeGoodsValue_goodsOutpriceDisplay.getText(),"outprice");
                db.executeUpdate(textField_changeGoodsValue_goodsIDDisplay.getText(),belongto,"id",textField_changeGoodsValue_goodsWholeDisplay.getText(),"outprice_wholesale");

                //table刷新
                table.setData(returnVector.FromDBReadAll(db, belongto, table.getTableName()));
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
