package iteration_3_yyq;


import Bean.DBBean;
import GUI.MyJPanel;
import GUI.windowsForAddEmployeeAccount;
import op.returnVector;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.List;

/**
 * 在 功能3 中 统计客户的功能
 * 在 功能3 中 统计货品的店长部分的功能
 * 加了查询功能的 MyJpanel
 *
 */
public class fun_3 extends JPanel{

    // 属性
    private DBBean db;
    private String belongto;

    // UI布局
    // 上面的查找部分
    private JLabel find_label;
    private JTextField find_textfield;
    private JButton find_button;
    // 下面的表格显示部分
    private MyJPanel table;
    // 借款状态里面新加的显示
    private JTextField unpaid;
    private JTextField paid;
    // 经理选仓库
    private JComboBox comboBox;
    private List[] comboBoxString;



    /**
     * 初始化位置信息，并不加数据
     * @param db 关联的数据库
     * @param belongTo 属于哪个点
     * @param header 表格头
     */
    public fun_3(DBBean db, String belongTo, Vector header, ResourceBundle resourceBundle, String use){
        this.db = db;
        this.belongto = belongTo;

        // 上面查找部分的摆放
        find_label = new JLabel("请输入查找关键字");
        find_textfield = new JTextField(""); // @yzj 设大小
        find_button = new JButton("查找");
        Box find_box = Box.createHorizontalBox();
        find_box.add(find_label); find_box.add(find_textfield); find_box.add(find_button);
        this.add(find_box, BorderLayout.NORTH);

        // 下面table的摆放
        table = new MyJPanel(header, 0, 0);
        this.add(table, BorderLayout.CENTER);

        if (use.equals("payment")){
            Box down = Box.createHorizontalBox();
            down.add(new JLabel("未结款"));
            unpaid = new JTextField("");
            down.add(unpaid);
            down.add(new JLabel("已付款款"));
            paid = new JTextField("");
            down.add(paid);
            this.add(down, BorderLayout.SOUTH);
        }


        // 监听
        find_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (find_textfield.getText().equals("")){
                    if (use.equals("item")) {
                        table.setData(fun_3_item(belongto), resourceBundle);
                    } else if (use.equals("customer")){
                        table.setData(fun_3_customer(belongto), resourceBundle);
                    } else if (use.equals("payment")){
                        Vector data = fun_4_payment(belongto);
                        float unpaidnum = 0; float paidnum = 0;
                        for (Object temp :data){
                            if (((Vector)temp).get(2).equals("待收款")) unpaidnum+=Float.parseFloat((String)((Vector)temp).get(3));
                            if (((Vector)temp).get(2).equals("已完成")) paidnum+=Float.parseFloat((String)((Vector)temp).get(3));
                        }
                        table.setData(data, resourceBundle);
                        unpaid.setText(String.valueOf(unpaidnum));
                        paid.setText(String.valueOf(paidnum));
                    }

                }
                else {
                    if (use.equals("item")){
                        table.setData(fun_3_item(find_textfield.getText(), belongto), resourceBundle);
                    } else if (use.equals("customer")){
                        table.setData(fun_3_customer(find_textfield.getText(), belongto), resourceBundle);
                    } else if (use.equals("payment")){
                        Vector data = fun_4_payment(find_textfield.getText(), belongto);
                        float unpaidnum = 0; float paidnum = 0;
                        for (Object temp :data){
                            if (((Vector)temp).get(2).equals("待收款")) unpaidnum+=Float.parseFloat((String)((Vector)temp).get(3));
                            if (((Vector)temp).get(2).equals("已完成")) paidnum+=Float.parseFloat((String)((Vector)temp).get(3));
                        }
                        table.setData(data, resourceBundle);
                        unpaid.setText(String.valueOf(unpaidnum));
                        paid.setText(String.valueOf(paidnum));
                    }
                }
            }
        });
    }


    /**
     * 初始化位置信息，并不加数据，经理
     * @param db 关联的数据库
     * @param header 表格头
     */
    public fun_3(DBBean db, Vector header, ResourceBundle resourceBundle, String use){
        this.db = db;
        this.setLayout(new BorderLayout());

        // 上面摆放

        Box horizontalBox_up = Box.createHorizontalBox();
        // 复选框
        comboBoxString = new List[1];
        comboBoxString[0] = new ArrayList();
        comboBoxString[0].addAll(windowsForAddEmployeeAccount.jcombobox_string(db, "repository_name","name"));
        comboBox = new JComboBox(comboBoxString[0].toArray());
        comboBox.setPreferredSize(new Dimension(300,30));
        horizontalBox_up.add(comboBox);
        // 查找部分
        find_label = new JLabel("    请输入查找关键字");
        find_textfield = new JTextField();
        find_button = new JButton("查 询");
        horizontalBox_up.add(find_label);
        horizontalBox_up.add(find_textfield);
        horizontalBox_up.add(find_button);
//        Box find_box = Box.createHorizontalBox();
//        find_box.add(find_label); find_box.add(find_textfield); find_box.add(find_button);
//        Box box = Box.createVerticalBox();
//        box.add(comboBox); box.add(find_box);
//        this.add(box, BorderLayout.NORTH);


        // 下面table的摆放
        table = new MyJPanel(header, 0, 0);
        this.add(table, BorderLayout.CENTER);
        table.setUp(horizontalBox_up);

        if (use.equals("payment")){
            Box horizontalBox_down = Box.createHorizontalBox();
            horizontalBox_down.add(new JLabel("未结款"));
            unpaid = new JTextField("");
            horizontalBox_down.add(unpaid);
            horizontalBox_down.add(new JLabel("已付款款"));
            paid = new JTextField("");
            horizontalBox_down.add(paid);
            table.setDown(horizontalBox_down);
        }


        // 监听
        find_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (find_textfield.getText().equals("")){
                    if (use.equals("item")) {
                       table.setData(fun_3_item(String.valueOf(comboBox.getSelectedItem())), resourceBundle);
                    } else if (use.equals("customer")){
                        table.setData(fun_3_customer(String.valueOf(comboBox.getSelectedItem())), resourceBundle);
                    } else if (use.equals("payment")){
                        Vector data = fun_4_payment(String.valueOf(comboBox.getSelectedItem()));
                        float unpaidnum = 0; float paidnum = 0;
                        for (Object temp :data){
                            if (((Vector)temp).get(2).equals("待收款")) unpaidnum+=Float.parseFloat((String)((Vector)temp).get(3));
                            if (((Vector)temp).get(2).equals("已完成")) paidnum+=Float.parseFloat((String)((Vector)temp).get(3));
                        }
                        table.setData(data, resourceBundle);
                        unpaid.setText(String.valueOf(unpaidnum));
                        paid.setText(String.valueOf(paidnum));
                    }

                }
                else {
                    if (use.equals("item")){
                       table.setData(fun_3_item(find_textfield.getText(), String.valueOf(comboBox.getSelectedItem())), resourceBundle);
                    } else if (use.equals("customer")){
                        table.setData(fun_3_customer(find_textfield.getText(), String.valueOf(comboBox.getSelectedItem())), resourceBundle);
                    } else if (use.equals("payment")){
                        Vector data = fun_4_payment(find_textfield.getText(), String.valueOf(comboBox.getSelectedItem()));
                        float unpaidnum = 0; float paidnum = 0;
                        for (Object temp :data){
                            if (((Vector)temp).get(2).equals("待收款")) unpaidnum+=Float.parseFloat((String)((Vector)temp).get(3));
                            if (((Vector)temp).get(2).equals("已完成")) paidnum+=Float.parseFloat((String)((Vector)temp).get(3));
                        }
                        table.setData(data, resourceBundle);
                        unpaid.setText(String.valueOf(unpaidnum));
                        paid.setText(String.valueOf(paidnum));
                    }
                }
            }
        });
    }


    /**
     * 为表格更新数据
     * @param data 数据
     * @param resourceBundle 字典
     */
    public void setData(Vector data, ResourceBundle resourceBundle){
        table.setData(data, resourceBundle);
    }



    /**
     * 功能3的客户买了什么统计，查一个店，查一个客户
     * @param belongto 哪个店
     * @param customer 那个客户
     * @return 二维数组（客户名，买了什么，买了几个）
     */
    public Vector fun_3_customer(String customer, String belongto){
        try{
            // 查客户在这个点里面的单
            ResultSet res = db.executeFind(customer, belongto+"_order", "name");
            List<String> order = new ArrayList<>();
            while(res.next()){
                order.add(res.getString("id"));
            }
            // 查中间表看看买了什么
            Vector vec = new Vector();
            // 他买了那些订单
            for (String id : order){
                res = db.executeFind(id, belongto+"_item_order", "order_id");
                // 这一单中买了那些东西
                while(res.next()){
                    // 这个东西是不是已经加到结果中了，如果加了，那么不用新加一行，如果没有，需要新加一行
                    boolean isin = false;
                    String name = res.getString("product_name");
                    int num = res.getInt("num");
                    for (Object temp1 : vec){
                        if (name.equals(((Vector)temp1).get(1))) {
                            isin = true;
                            ((Vector)temp1).set(2, (int)((Vector)temp1).get(2)+num);
                            break;
                        }
                    }
                    if (!isin) {
                        Vector temp = new Vector(Arrays.asList(customer, name, num));
                        vec.add(temp);
                    }
                }
            }
            if (vec.size() == 0) {
                Vector ret = new Vector();
                ret.add(null);
                return ret;
            }
            return vec;
        } catch (SQLException e) {
            System.out.println("从数据库中统计客户买了什么出问题");
            return null;
        }
    }


    /**
     * 功能三 对该店所有客户的统计
     * @param belongto 哪个店
     * @return 二维数组
     */
    public Vector fun_3_customer(String belongto){
        try{
            Vector show = new Vector();
            ResultSet res = db.executeFindAll("customermanager");
            List<String> temp = new ArrayList<>();
            while(res.next()){ temp.add(res.getString("name")); }
            for (String temp1 : temp){
                for (Object temp2 : fun_3_customer(temp1, belongto))
                    if (temp2 != null) show.add(temp2);
            }
            return show;
        } catch (SQLException e) {System.out.println("统计该店总客户出错");}
        return null;
    }



    /**
     * 功能3的商品买了多少，退了多少统计，查一个店，一个商品
     * @param belongto 哪个店
     * @param item 商品名
     * @return 二维数组（商品名，售出多少，退货多少）
     */
    public Vector fun_3_item(String item, String belongto) {
        try {
            // 查 中间表 确定东西在那个订单
            ResultSet res = db.executeFind(item, belongto + "_item_order", "product_name");
            List<String> order_id = new ArrayList<>();
            List<String> num = new ArrayList<>();
            while(res.next()){
                order_id.add(res.getString("order_id"));
                num.add(res.getString("num"));
            }
            // 根据订单的状态 对货物进行统计
            int out = 0;
            int back = 0;
            for (int i = 0; i < order_id.size(); i++) {
                res = db.executeFind(order_id.get(i), belongto+"_order", "id");
                while (res.next()) {
                    if (res.getString("state").equals("已完成")) out += Integer.parseInt(num.get(i));
                    if (res.getString("state").equals("已退货")) back += Integer.parseInt(num.get(i));
                    if (res.getString("state").equals("退货中")) back += Integer.parseInt(num.get(i));
                }
            }
            return new Vector(Arrays.asList(new Vector(Arrays.asList(item, out, back))));
        } catch (SQLException e) {
            System.out.println("sqlwar");
            return null;
        }
    }


    /**
     * 功能三 对该店所有货物的统计
     * @param belongto 哪个店
     * @return 二维数组
     */
    public Vector fun_3_item(String belongto){
        try{
            Vector show = new Vector();
            ResultSet resultSet = db.executeFindAll(belongto);
            List<String> temp = new ArrayList<>();
            while(resultSet.next()){ temp.add(resultSet.getString("name")); }
            for (String product : temp){
                if (fun_3_item(product, belongto) == null);
                else show.add(fun_3_item(product, belongto).get(0));
            }
            return show;
        } catch (SQLException e) {System.out.println("统计该店总客户出错");}
        return null;
    }

    /**
     * 功能三的商品统计，查所有店，一个商品
     * @param item 查那个物品
     * @return
     */
    public Vector fun_3_item_all(String item){
        try{
            // 查所有的店
            List<String> repository = new ArrayList<>();
            ResultSet res = db.executeFindAll("repository_name");
            while(res.next()) repository.add(res.getString("name"));
            // 统计这个货物 在每个店的销售状况
            int out = 0; int back = 0;
            for (String store : repository){
                Vector onestore = (Vector) fun_3_item(item, store).get(0);
                out+=(int)(onestore.get(1));
                back+=(int)(onestore.get(2));
            }
            return new Vector(Arrays.asList(new Vector(Arrays.asList(item, out, back))));
        } catch (SQLException throwables) {
           System.out.println("功能三商品查询：所有店的一个商品出错");
        }
        return null;
    }


    /**
     * 功能三的商品统计，查所有店，所有商品
     * @return
     */
    public Vector fun_3_item_all(){
        try {
            Vector vec = new Vector();
            // 从大库统计所有商品
            ResultSet res = db.executeFindAll("repository_all");
            while(res.next()){ vec.add(fun_3_item_all(res.getString("name")).get(0)); }
            return vec;
        } catch (SQLException throwables) {
            System.out.println("功能三商品查询：所有店的所有商品出错");
        }
        return null;
    }





    /**
     * 客户结款信息的统计，一个客户，一个店
     * @return 二维数组（客户名，订单id，订单状态，订单总金额）
     */
    public Vector fun_4_payment(String customer, String belongto){
        // 根据名字查他的订单
        ResultSet res = db.executeFind(customer, belongto+"_order", "name");
        // 看每个订单的状态
        Vector vec = new Vector();
        try{
            while(res.next()){
                if (res.getString("state").equals("已完成") || res.getString("state").equals("待收款")){
                    Vector temp = new Vector();
                    temp.add(customer);
                    temp.add(res.getString("id"));
                    temp.add(res.getString("state"));
                    temp.add(res.getString("price_all"));
                    vec.add(temp);
                }
            }
        }catch (SQLException e){}
        return vec;
    }

    /**
     * 查这个店中所有客户的借款信息
     * @param belongto 哪个店
     * @return 二维数组
     */
    public Vector fun_4_payment(String belongto){
        try{
            Vector show = new Vector();
            ResultSet res = db.executeFindAll("customermanager");
            List<String> temp = new ArrayList<>();
            while(res.next()){ temp.add(res.getString("name")); }
            for (String temp1 : temp){
                for (Object temp2 : fun_4_payment(temp1, belongto))
                    if (temp2 != null) show.add(temp2);
            }
            return show;
        } catch (SQLException e) {System.out.println("统计该店总客户出错");}
        return null;
    }


    public static void main(String[] argv) throws SQLException{
        DBBean db = new DBBean();
        fun_3 a = new fun_3(new DBBean(), "repository1", new Vector<>(Arrays.asList("id", "name", "outprice", "outprice_wholesale")),
                null, "item");
        System.out.println(a.fun_3_item("car", "repository1"));
    }


}


