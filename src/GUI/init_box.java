package GUI;

import Bean.DBBean;
import op.returnVector;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.Vector;

import static op.op.StateConvert;


public class init_box {

    private ResourceBundle resourceBundle;
    private String but_addString;//+ 新增货物
    private String button_addItemString;//新增商品
    private String searchCustomerString;//查找客户:
    private String haveFound_customerString;//+ 查找到的客户:
    private String customer_class_String;//客户类型:
    private String total_String;//合计:
    private String saveString;//保  存
    private String yuan_String;//元
    private String confirmString;//确   认
    private String order_IDString;//订单号:
    private String product_nameString;//货品名称:
    private String button_account_deleteString;//删   除
    private String change_product_inStockString;//修改库存货品信息:
    private String label_account_searchTitleString;//客户姓名
    private String button_account_searchString;//查询
    private String button_account_changeString;//修   改
    private String changeEmployeeAccount;//修改员工账户
    private String button_addCustomerString;  // 新增客户

    public init_box(ResourceBundle resourceBundle) {
        this.resourceBundle=resourceBundle;
        but_addString = resourceBundle.getString("but_addString");//+ 新增货物
        button_addCustomerString = resourceBundle.getString("button_addCustomerString");//新增客户
        searchCustomerString = resourceBundle.getString("searchCustomerString");//查找客户:
        haveFound_customerString = resourceBundle.getString("haveFound_customerString");//+ 查找到的客户:
        customer_class_String = resourceBundle.getString("customer_class_String");//客户类型:
        total_String = resourceBundle.getString("total_String");//合计:
        saveString = resourceBundle.getString("saveString");//保  存
        yuan_String = resourceBundle.getString("yuan_String");//元
        confirmString = resourceBundle.getString("confirmString");//确   认
        order_IDString = resourceBundle.getString("order_IDString");//订单号:
        product_nameString = resourceBundle.getString("product_nameString");//货品名称:
        button_account_deleteString = resourceBundle.getString("button_account_deleteString");//删   除
        change_product_inStockString = resourceBundle.getString("change_product_inStockString");//修改库存货品信息:
        label_account_searchTitleString = resourceBundle.getString("label_account_searchTitleString");//客户姓名
        button_account_searchString = resourceBundle.getString("button_account_searchString");//查询
        button_account_changeString = resourceBundle.getString("button_account_changeString");//修   改
        changeEmployeeAccount = resourceBundle.getString("changeEmployeeAccount");//修改员工账户
    }

    /**
     * 初始化 客户 的box
     *
     * @param table 关联的table
     * @param db    关联的数据库
     * @return 初始化完成的box
     */
    public Box customer(MyJPanel table, DBBean db) {
        Box box_1 = Box.createHorizontalBox();
        Box box_2 = Box.createHorizontalBox();
        Box box_all = Box.createVerticalBox();

        // box_2
        // 新增客户按钮
        JButton button_account_add = new JButton(button_addCustomerString);
        button_account_add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                windowsToCreateCustomer add_win = new windowsToCreateCustomer(resourceBundle, db, table);
                add_win.setVisible(true);
                add_win.setBounds(470, 170, 400, 300);
                add_win.setResizable(false);
            }
        });
        box_2.add(button_account_add);

        // box_1
        // 显示客户姓名的label
        JLabel sreachLabel = new JLabel(label_account_searchTitleString);//客户姓名
        box_1.add(sreachLabel);

        // 客户输入姓名的textfield
        JTextField textField = new JTextField();
        box_1.add(textField);
        textField.setColumns(10);

        // 查询按钮
        JButton search = new JButton(button_account_searchString);
        search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (textField.getText().equals("")) table.setData(returnVector.FromDBReadAll(db, "customermanager", table.getTableName()));
                else table.setData(returnVector.FromDBRead(db, "customermanager", table.getTableName(), textField.getText(), "Name"));
            }
        });
        box_1.add(search);

        // 添加box1、2
        box_all.add(box_1);
        box_all.add(box_2);

        return box_all;
    }


    /**
     * 初始化 新建订单 的box
     *
     * @param table 关联的table
     * @param db    关联的数据库
     * @param related 关联的其他table
     * @param belongto 属于哪个店
     * @return 初始化完成的box
     */
    public Box[] order_new(MyJPanel table, DBBean db, MyJPanel[] related, String belongto) {
        // 上方的box
        Box up = Box.createVerticalBox();
        Box up_up = Box.createHorizontalBox();
        up_up.add(new JLabel(searchCustomerString));
        JTextField textField_addOrder_searchDisplay = new JTextField();
        JButton button_addOrder_search = new JButton(button_account_searchString);

        up_up.add(textField_addOrder_searchDisplay);
        up_up.add(button_addOrder_search);

        Box up_down = Box.createHorizontalBox();
        up_down.add(new JLabel(haveFound_customerString));
        JTextField textField_addOrder_foundCustomerDisplay = new JTextField();
        textField_addOrder_foundCustomerDisplay.setEditable(false);
        up_down.add(textField_addOrder_foundCustomerDisplay);
        up_down.add(new JLabel(customer_class_String));
        JTextField textField_addOrder_kindOfCustomer = new JTextField();
        textField_addOrder_kindOfCustomer.setEditable(false);
        up_down.add(textField_addOrder_kindOfCustomer);

        up.add(up_up);
        up.add(up_down);

        // 下方的box
        Box down = Box.createVerticalBox();
        Box down_up = Box.createHorizontalBox();
        // todo @sxz
        button_addItemString = "新增货物";
        JButton button_account_add = new JButton(button_addItemString);
        button_account_add.setHorizontalAlignment(SwingConstants.LEADING);
        down_up.add(button_account_add);

        Box down_down = Box.createHorizontalBox();
        down_down.add(new JLabel(total_String));
        JTextField textField_addOrder_totalPriceDisplay = new JTextField("0");
        textField_addOrder_totalPriceDisplay.setEditable(false);
        down_down.add(textField_addOrder_totalPriceDisplay);
        down_down.add(new JLabel(yuan_String));
        down_down.add(Box.createHorizontalStrut(300));
        JButton button_addOrder_save = new JButton(saveString);
        down_down.add(button_addOrder_save);
        down.add(down_up);
        down.add(down_down);

        // 查询客户按钮绑定
        button_addOrder_search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ResultSet findres = db.executeFind(textField_addOrder_searchDisplay.getText(), "customermanager", "name");
                try {
                    if (findres.next()) {
                        textField_addOrder_searchDisplay.setText("");
                        textField_addOrder_foundCustomerDisplay.setText(findres.getString("name"));
                        textField_addOrder_kindOfCustomer.setText(findres.getString("classification"));
                    }
                    else {
                        // 弹出窗口
                        JTextArea aboutarea = new JTextArea();
                        aboutarea.setText("未查找到！\n");
                        JOptionPane.showConfirmDialog(null,aboutarea,"Error!",JOptionPane.PLAIN_MESSAGE);
                        textField_addOrder_searchDisplay.setText("");
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });
        // 新增货物按钮绑定
        button_account_add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                windowsToCreateOrder winToCreateOrder = new windowsToCreateOrder(resourceBundle, db, table, belongto, textField_addOrder_totalPriceDisplay);
                winToCreateOrder.setVisible(true);
                winToCreateOrder.setBounds(470, 170, 450, 300);
                winToCreateOrder.setResizable(false);
            }
        });
        // 保存按钮绑定
        button_addOrder_save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (textField_addOrder_searchDisplay.equals("")) {
                    JTextArea aboutarea = new JTextArea();
                    aboutarea.setText("未输入客户！\n");
                    JOptionPane.showConfirmDialog(null,aboutarea,"Error!",JOptionPane.PLAIN_MESSAGE);
                }
                else {
                    // 插入订单
                    int id = db.executeQuery(belongto+"_order(Name,State,price_all,profit)",
                            "'" + textField_addOrder_foundCustomerDisplay.getText() + "'," +
                                    "'" + op.op.StateConvert(table.getNext()) + "'," +
                                    Float.parseFloat(textField_addOrder_totalPriceDisplay.getText()) + "," +
                                    String.valueOf(profit(table, db, belongto)));

                    for (Object temp : table.getModel().getDataVector()) {
                        Vector<Object> v = (Vector<Object>)temp;
                        // 中间表中插入物品
                        db.executeQuery(belongto+"_item_order(repository,order_id,item_name,num)",
                                "'"+belongto+"'," +
                                        // todo 如何得到这一单的id
                                        "'"+String.valueOf(id)+"'," +
                                        "'"+v.get(0)+"'," +
                                        v.get(2));
                        // todo 店员的时候关联库存
                    }
                    // 更新表
                    table.setData(new Vector<>());  // 本来就关联的table
                    Vector<Object> nametemp = new Vector<>();
                    nametemp.add("ID"); nametemp.add("Name"); nametemp.add("price_all"); nametemp.add("State");
                    related[0].setData(returnVector.FromDBRead(db, belongto + "_order", nametemp, op.op.StateConvert(related[0].getNow()), "State"));  // 未付款的table
                    related[1].setData(returnVector.FromDBReadAll(db, belongto + "_order", related[1].getTableName()));  // 所有订单列表
                    textField_addOrder_totalPriceDisplay.setText("0");
                }
            }
        });

        return new Box[]{up, down};
    }


    /**
     * 初始化 带按钮 的box
     *
     * @param table 关联的table
     * @param db    关联的数据库
     * @param related 关联的其他table
     * @param belongTo 属于哪个仓库
     * @return 初始化完成的box
     */
    public Box order_check_but(MyJPanel table, DBBean db, MyJPanel[] related, String belongTo) {
        Box horizontalBox_unchecked = Box.createHorizontalBox();
        JButton button_unchecked_confirm = new JButton(confirmString);
        button_unchecked_confirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i : table.getChange()){
                    System.out.println(i);
                    db.executeUpdate(String.valueOf(i), belongTo+"_order", "ID", "'"+op.op.StateConvert(table.getNext())+"'", "State");
                    if (table.getNext().equals("待付款")) {/* todo 库存减少 */}
                    if (table.getNext().equals("退货中")) {/* todo 库存增加 */ }
                }
                // table从读
                Vector<Object> nametemp = new Vector<>();
                nametemp.add("ID"); nametemp.add("Name"); nametemp.add("price_all");
                refresh(belongTo, related, db);
            }
        });
        horizontalBox_unchecked.add(button_unchecked_confirm);
        return horizontalBox_unchecked;
    }


    /**
     * 初始化 订单列表 中的box
     *
     * @param table 关联的table
     * @param db    关联的数据库
     * @param related 关联的显示table
     * @param belongto 属于哪个仓库
     * @return 初始化完成的box
     */
    public Box order_check(MyJPanel table, DBBean db, MyJPanel[] related, String belongto) {
        Box res = Box.createHorizontalBox();
        // 显示订单号的label
        JLabel label_orderList_orderSearchTitle = new JLabel(order_IDString);
        JTextField textField_orderList_orderSearchDisplay = new JTextField();
        // 查询按钮
        JButton button_orderList_Search = new JButton(button_account_searchString);
        // 删除按钮
        JButton button_orderList_delete = new JButton(button_account_deleteString);
        button_orderList_delete.setEnabled(false);

        // 查询绑监听
        button_orderList_Search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                button_orderList_delete.setEnabled(true);
                if (textField_orderList_orderSearchDisplay.getText().equals("")) table.setData(returnVector.FromDBReadAll(db, belongto+"_order", table.getTableName()));
                else table.setData(returnVector.FromDBRead(db, belongto+"_order", table.getTableName(), textField_orderList_orderSearchDisplay.getText(),"id"));
            }
        });

        // 删除绑监听
        button_orderList_delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                button_orderList_delete.setEnabled(false);
                db.executeDelete(textField_orderList_orderSearchDisplay.getText(), belongto+"_order", "ID");
                refresh(belongto, related, db);
            }
        });
        res.add(label_orderList_orderSearchTitle);
        res.add(textField_orderList_orderSearchDisplay);
        res.add(button_orderList_Search);
        res.add(button_orderList_delete);

        return res;
    }


    /**
     * 初始化 库存进货 中的box  经理
     *
     * @param table 关联的table
     * @param db    关联的数据库
     * @return 初始化完成的box
     */
    public Box[] stock_in(MyJPanel table, DBBean db, MyJPanel stock, String belongto) {
        Box up = Box.createHorizontalBox();
        Box down = Box.createHorizontalBox();

        // 上面的box
        JButton but_add = new JButton(but_addString);
        up.add(but_add);

        // 下面的box
        JButton button_restore_save = new JButton(saveString);
        down.add(new JLabel(total_String));
        JTextField textField_restore_totalBuyingPriceDisplay = new JTextField();
        down.add(textField_restore_totalBuyingPriceDisplay);
        down.add(new JLabel(yuan_String));
        down.add(Box.createHorizontalStrut(240));
        down.add(button_restore_save);

        but_add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // todo 经理添加
                windowsToCreateItemForManager winToCreateItemForManager = new windowsToCreateItemForManager(resourceBundle, db, table, belongto, textField_restore_totalBuyingPriceDisplay);//1随便写的
                winToCreateItemForManager.setVisible(true);
                winToCreateItemForManager.setBounds(470, 170, 400, 350);
                winToCreateItemForManager.setResizable(false);
            }
        });

        button_restore_save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // todo 更新表和数据库
            }
        });

        Box[] res = {up, down};
        return res;
    }



    /**
     * 初始化 库存清点 中的box
     *
     * @param table 关联的table
     * @param db    关联的数据库
     * @return 初始化完成的box
     */
    public Box stock_check(MyJPanel table, DBBean db) {
        Box res = Box.createVerticalBox();

        // 下拉菜单
        Box up = Box.createHorizontalBox();
        JComboBox comboBox_checkStock_chooseStock = new JComboBox();
        /*增加菜单项，todo*/

        // 按钮和textfield
        Box down = Box.createHorizontalBox();
        down.add(new JLabel(product_nameString));
        JTextField textField_checkStock_searchDisplay = new JTextField();
        down.add(textField_checkStock_searchDisplay);
        JButton button_checkStock_search = new JButton(button_account_searchString);
        JButton button_checkStock_change = new JButton(button_account_changeString);
        JButton button_checkStock_delete = new JButton(button_account_deleteString);
        button_checkStock_search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // todo 修改table
            }
        });
        button_checkStock_change.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                windowsToChangeStockValue winToChangeStockValue = new windowsToChangeStockValue(resourceBundle,db);
                winToChangeStockValue.setVisible(true);
                winToChangeStockValue.setBounds(470, 170, 400, 350);
                winToChangeStockValue.setResizable(false);
                winToChangeStockValue.setTitle(change_product_inStockString);
                // todo 修改数据库、table
            }
        });
        button_checkStock_delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // todo 修改数据库、table
            }
        });
        down.add(button_checkStock_search);
        down.add(button_checkStock_change);
        down.add(button_checkStock_delete);

        res.add(up);
        res.add(down);
        return res;
    }



    /**
     * 初始化 库存转移 中的box
     *
     * @param table 关联的table
     * @param db    关联的数据库
     * @return 初始化完成的box
     */
    public Box stock_trans(MyJPanel table, DBBean db) {
        Box horizontalBox_up = Box.createHorizontalBox();
        Box horizontalBox_down = Box.createHorizontalBox();
        Box verticalBox = Box.createVerticalBox();
        verticalBox.add(horizontalBox_up);
        verticalBox.add(horizontalBox_down);

        JButton button_addGoods = new JButton("+ 新建货物");
        JButton button_confirm = new JButton("开始运输");

        JLabel label_from = new JLabel("原仓库  ");
        horizontalBox_up.add(label_from);

        JComboBox comboBox_from = new JComboBox();
        horizontalBox_up.add(comboBox_from);

        Component horizontalGlue = Box.createHorizontalGlue();
        horizontalBox_up.add(horizontalGlue);

        JLabel label_op = new JLabel("to");
        label_op.setFont(new Font("宋体", Font.PLAIN, 15));
        horizontalBox_up.add(label_op);

        Component horizontalGlue_1 = Box.createHorizontalGlue();
        horizontalBox_up.add(horizontalGlue_1);

        JLabel label_to = new JLabel("目的仓库   ");
        label_to.setHorizontalAlignment(SwingConstants.CENTER);
        horizontalBox_up.add(label_to);

        JComboBox comboBox_to = new JComboBox();
        horizontalBox_up.add(comboBox_to);

        JButton button_savePath = new JButton("确  定");
        horizontalBox_up.add(button_savePath);

        horizontalBox_down.add(button_addGoods);
        horizontalBox_down.add(button_confirm);

        return verticalBox;
    }


    /**
     * 经理权限 账户页面 中的Box
     * @param table
     * @param db
     * @return
     */
    public Box account(MyJPanel table, DBBean db){
        Box verticalBox = Box.createVerticalBox();
        Box horizontal_up = Box.createHorizontalBox();
        Box horizontal_down = Box.createHorizontalBox();
        verticalBox.add(horizontal_up);
        verticalBox.add(horizontal_down);

        JLabel label_account = new JLabel("员工用户名/ID");
        JTextField textField_account = new JTextField();
        JButton button_search = new JButton("搜 索");
        JButton button_change = new JButton("修 改");
        JButton button_delete = new JButton("删 除");
        JButton button_add = new JButton("+ 新增员工账户");
        horizontal_down.add(button_add);
        horizontal_up.add(label_account);
        horizontal_up.add(textField_account);
        horizontal_up.add(button_search);
        horizontal_up.add(button_change);
        horizontal_up.add(button_delete);

        return verticalBox;
    }


    /**
     * 更新所有的订单显示和库存
     * @param belongto 哪个店
     * @param all 所有的table
     * @param db 关联的数据库
     */
    private void refresh(String belongto, MyJPanel[] all, DBBean db){
        Vector<Object> nametemp = new Vector<>();
        nametemp.add("ID"); nametemp.add("Name"); nametemp.add("price_all"); nametemp.add("State");
        int i = 0;
        for (MyJPanel temp : all) {
            if (i == 0) temp.setData(returnVector.FromDBReadAll(db, belongto+"_order", temp.getTableName()));  // 总订单
            else if (i == 6) {/* todo 库存从读 */}
            else if (temp != null) temp.setData(returnVector.FromDBRead(db, belongto+"_order", nametemp, StateConvert(temp.getNow()), "State"));
            i++;
        }
    }


    /**
     * 计算这一单的利润
     * @param table 放买了什么的table
     * @param db 数据库
     * @return 总利润
     */
    private float profit(MyJPanel table, DBBean db, String belongto)  {
        float res = 0;
        for (Object temp : table.getModel().getDataVector()){
            String name = ((Vector)temp).get(0).toString();
            float out = Float.parseFloat( ((Vector)temp).get(1).toString() );
            ResultSet temp1 = db.executeFind(name, belongto, "name");
            float in = 0;
            try {
                while( temp1.next() ) in = temp1.getFloat("inprie");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            res += (out-in)* Integer.parseInt( ((Vector)temp).get(2).toString() );
        }
        return res;
    }


}
