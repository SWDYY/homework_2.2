package pos;

import Bean.DBBean;
import GUI.MyJPanel;
import language.language_convert;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.ResourceBundle;
import java.util.Vector;

public class main {

    // 多语言
    private String product_name;
    private String num;
    private String outprice;
    private String total;
    private String confirmString;

    // UI+功能
    private Box up = null;
    private JTextField up_text = null;
    private JButton up_botton = null;
    private JScrollPane jsp = null;
    private JTable table = null;
    private DefaultTableModel model = null;
    private Box down = null;
    private JTextField down_text = null;
    private JButton down_botton = null;

    private DBBean db = null;
    private String belongto = null;


    //设置全局字体
    public static void initGlobalFontSetting(Font fnt){
        FontUIResource fontRes = new FontUIResource(fnt);
        for(Enumeration keys = UIManager.getDefaults().keys(); keys.hasMoreElements();){
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            if(value instanceof FontUIResource)
                UIManager.put(key, fontRes);
        }
    }

    public static void main(String[] argv) throws Exception {
        initGlobalFontSetting(new Font("仿宋", Font.PLAIN, 15));  //统一设置字体
        org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
        main a = new main(null, new DBBean(), "repository1");
    }


    public main(ResourceBundle resourceBundle, DBBean db, String belongTo){

        this.db = db;
        this.belongto = belongTo;
        // 多语言
        resourceBundle = language_convert.language_convertChinese();
        product_name = resourceBundle.getString("product_name");
        num = resourceBundle.getString("num");
        outprice = resourceBundle.getString("outprice");
        total = resourceBundle.getString("total");
        confirmString = resourceBundle.getString("confirmString");

        // UI摆放
        JFrame jf = new JFrame("pos");
        JPanel jp = new JPanel();
        table = new JTable();
        init();
        jf.add(up, BorderLayout.NORTH);
//        jp.add(up);
        jf.add(jsp, BorderLayout.CENTER);
        jf.add(down, BorderLayout.SOUTH);

        jf.pack();
        jf.setVisible(true);



    }


    private void init() {
        up = Box.createHorizontalBox();
//        up.add(new JLabel(product_name));
        JTextField temp123 = new JTextField("         "+product_name);
        temp123.setEditable(false);
        temp123.setEnabled(false);
        temp123.setPreferredSize(new Dimension(10,30));
        up.add(temp123);

        up_text = new JTextField("");
        up.add(up_text);
        up_botton = new JButton(confirmString);
        up.add(up_botton);

        Vector name = new Vector();
        name.add(product_name);
        name.add(num);
        name.add(outprice);
        name.add(total);
        model = new DefaultTableModel(new Vector(), name);
        table = new JTable(model);
        jsp = new JScrollPane(table);

        down = Box.createHorizontalBox();
        down_text = new JTextField("0");
        down_text.setEditable(false);
        down.add(down_text);
        down_botton = new JButton(confirmString);
        down.add(down_botton);

        // 添加一个货品
        up_botton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String name = up_text.getText();
                    ResultSet res = db.executeFind(up_text.getText(), belongto, "name");
                    if (res.next()) {
                        boolean flag = true;
                        for (int i = 0; i < model.getRowCount(); i++) {
                            // 前面买过一个
                            if (name.equals(model.getValueAt(i, 0))) {
                                flag = false;
                                // 改数量
                                int oldnum = Integer.parseInt(model.getValueAt(i, 1).toString());
                                model.setValueAt(oldnum + 1, i, 1);
                                // 改这一个物品的总价
                                float price = Float.parseFloat(model.getValueAt(i, 2).toString());
                                model.setValueAt((oldnum + 1) * price, i, 3);
                                // 该这一单的总价
                            }
                        }
                        // 之前没买过
                        if (flag) {
                            Vector temp = new Vector();
                            temp.add(up_text.getText());
                            temp.add(1);
                            temp.add(res.getFloat("outprice"));
                            temp.add(res.getFloat("outprice"));
                            model.addRow(temp);
                        }
                        down_text.setText(String.valueOf(price_all()));
                    }
                } catch (SQLException err) {
                    System.out.println("查询错误");
                }

            }
        });

        // 保存
        down_botton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (model.getRowCount() == 0) {}
                else {
                    // 插入订单
                    db.executeQuery(belongto + "_order(Name,State,price_all,profit)",
                            "'" + "零售" + "'," +
                                    "'" + "已付款" + "'," +
                                    price_all() + "," +
                                    profit());
                    // 加关联表
                    for (Object temp : model.getDataVector()) {
                        Vector<Object> v = (Vector<Object>) temp;
                        String id = null;
                        try {
                            ResultSet temp1 = db.executeFindMAXID(belongto + "_order", "ID");
                            while (temp1.next()) id = temp1.getString("ID");
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }
                        db.executeQuery(belongto + "_item_order(order_id,product_name,num)",
                                id + "," +
                                        "'" + v.get(0) + "'," +
                                        v.get(1));
                    }
                    // 更新表
                    model.setRowCount(0);
                    down_text.setText("0");
                }
            }
        });
    }


    // 计算总价
    private float price_all(){
        float all = 0;
        Vector<Object> v = (Vector<Object>) model.getDataVector();
        if (model.getRowCount() == 0) return 0;
        for (Object temp : v){
            int num = Integer.parseInt(((Vector)temp).get(1).toString());
            float price = Float.parseFloat(((Vector)temp).get(2).toString());
            all += num * price;
        }
        return  all;
    }


    // 计算盈利
    private float profit(){
        float pay = 0;
        Vector<Object> v = (Vector<Object>) model.getDataVector();
        try{
            for (Object temp : v){
                ResultSet res = db.executeFind(((Vector)temp).get(0).toString(), belongto, "name");
                res.next();
                int num = Integer.parseInt(((Vector)temp).get(1).toString());
                float price = res.getFloat("inprice");
                pay += num * price;
            }
        }catch (SQLException err) {System.out.println("查进价错误");}
        return price_all()-pay;
    }
}
