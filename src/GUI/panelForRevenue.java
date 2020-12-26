package GUI;

import Bean.DBBean;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class panelForRevenue extends JPanel{
    private JLabel label_revenue_purchaseAmount;  //进货金额
    private JLabel label_revenue_salesFigures;  //销售金额
    private JLabel label_revenue_overstockPrice;  //库存积压金额
    private JLabel label_revenue_profitAmount;  //盈利金额

    private JTextField textField_revenue_purchaseAmount;
    private JTextField textField_revenue_salesFigures;
    private JTextField textField_revenue_overstockPrice;
    private JTextField textField_revenue_profitAmount;
    private float int_revenue_purchaseAmount=0;
    private float int_revenue_salesFigures=0;
    private float int_revenue_overstockPrice=0;
    private float int_revenue_profitAmount=0;

    public panelForRevenue(String repository, DBBean db){

        this.setBorder(new EmptyBorder(30,30,30,30));
        Box verticalBox = Box.createVerticalBox();
        Box horizontalBox1 = Box.createHorizontalBox();
        Box horizontalBox2 = Box.createHorizontalBox();
        Box horizontalBox3 = Box.createHorizontalBox();
        Box horizontalBox4 = Box.createHorizontalBox();

        label_revenue_purchaseAmount = new JLabel("    进货金额   ");
        label_revenue_purchaseAmount.setFont(new Font("仿宋",Font.BOLD+Font.ITALIC,26));
        label_revenue_salesFigures = new JLabel(  "    销售金额   ");
        label_revenue_salesFigures.setFont(new Font("仿宋",Font.BOLD+Font.ITALIC,26));
        label_revenue_overstockPrice = new JLabel("  库存积压金额  ");
        label_revenue_overstockPrice.setFont(new Font("仿宋",Font.BOLD+Font.ITALIC,26));
        label_revenue_profitAmount = new JLabel(  "    盈利金额   ");
        label_revenue_profitAmount.setFont(new Font("仿宋",Font.BOLD+Font.ITALIC,26));

        try {
            if(repository.equals("repository_all")){
                ResultSet all_repository=db.executeFindAll("repository_name");
                while(all_repository.next()){
                    String repository_name=all_repository.getString("name");
                    ResultSet all_order=db.executeFindAll(repository_name+"_order");
                    while(all_order.next()){
                        if(all_order.getString("state").equals("已完成")){
                            int_revenue_profitAmount=int_revenue_profitAmount+Float.valueOf(all_order.getString(
                                    "profit"));
                            int_revenue_salesFigures=int_revenue_salesFigures+Float.valueOf(all_order.getString(
                                    "price_all"));
                        }
                    }
                }
                ResultSet rest_item=db.executeFindAll("restitem_all");
                while(rest_item.next()){
                    int_revenue_overstockPrice=int_revenue_overstockPrice+Float.valueOf(rest_item.getString(
                            "num"))*Float.valueOf(rest_item.getString("inprice"));
                }

            }
            else{
                ResultSet all_order=db.executeFindAll(repository+"_order");
                while(all_order.next()){
                    if(all_order.getString("state").equals("已完成")){
                        int_revenue_profitAmount=int_revenue_profitAmount+Float.valueOf(all_order.getString(
                                "profit"));
                        int_revenue_salesFigures=int_revenue_salesFigures+Float.valueOf(all_order.getString(
                                "price_all"));
                    }
                }
                ResultSet rest_item=db.executeFindAll(repository);
                while(rest_item.next()){
                    int_revenue_overstockPrice=int_revenue_overstockPrice+Float.valueOf(rest_item.getString(
                            "num"))*Float.valueOf(rest_item.getString("inprice"));
                }
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        int_revenue_purchaseAmount=int_revenue_salesFigures+int_revenue_overstockPrice-int_revenue_profitAmount;


        textField_revenue_purchaseAmount = new JTextField();
        textField_revenue_purchaseAmount.setEditable(false);
        textField_revenue_purchaseAmount.setText(String.valueOf(int_revenue_purchaseAmount));
        textField_revenue_salesFigures = new JTextField();
        textField_revenue_salesFigures.setEditable(false);
        textField_revenue_salesFigures.setText(String.valueOf(int_revenue_salesFigures));
        textField_revenue_overstockPrice = new JTextField();
        textField_revenue_overstockPrice.setEditable(false);
        textField_revenue_overstockPrice.setText(String.valueOf(int_revenue_overstockPrice));
        textField_revenue_profitAmount = new JTextField();
        textField_revenue_profitAmount.setEditable(false);
        textField_revenue_profitAmount.setText(String.valueOf(int_revenue_profitAmount));



        horizontalBox1.add(Box.createHorizontalGlue());
        horizontalBox1.add(label_revenue_purchaseAmount);
        horizontalBox1.add(textField_revenue_purchaseAmount);
        horizontalBox1.add(Box.createHorizontalGlue());

        horizontalBox2.add(Box.createHorizontalGlue());
        horizontalBox2.add(label_revenue_salesFigures);
        horizontalBox2.add(textField_revenue_salesFigures);
        horizontalBox2.add(Box.createHorizontalGlue());

        horizontalBox3.add(Box.createHorizontalGlue());
        horizontalBox3.add(label_revenue_overstockPrice);
        horizontalBox3.add(textField_revenue_overstockPrice);
        horizontalBox3.add(Box.createHorizontalGlue());

        horizontalBox4.add(Box.createHorizontalGlue());
        horizontalBox4.add(label_revenue_profitAmount);
        horizontalBox4.add(textField_revenue_profitAmount);
        horizontalBox4.add(Box.createHorizontalGlue());

        verticalBox.add(Box.createRigidArea(new Dimension(600,50)));
        verticalBox.add(horizontalBox1);
        verticalBox.add(Box.createVerticalStrut(20));
        verticalBox.add(horizontalBox2);
        verticalBox.add(Box.createVerticalStrut(20));
        verticalBox.add(horizontalBox3);
        verticalBox.add(Box.createVerticalStrut(20));
        verticalBox.add(horizontalBox4);
        verticalBox.add(Box.createRigidArea(new Dimension(600,50)));

        this.add(verticalBox);
    }
}
