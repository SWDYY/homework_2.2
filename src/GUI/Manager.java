package GUI;

import Bean.DBBean;
import iteration_3_yyq.fun_3;
import op.returnVector;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.Vector;

/**
 * 经理
 */
public class Manager extends JFrame {

    private String myJPanel_productString;//货品
    private String panel_customerStirng;//客户
    private String stock_String;//库存
    private String inStockStirng;//进货
    private String check_stockString;//清点库存
    private String product_sendStirng;//货品调配
    private String AccountSring;//账户
    private String panel_orderListSring;//订单列表
    private String panel_personalAccountSring;//个人账户
    private String statistics_all; // 统计
    private String statistics_stock; // 库存统计
    private String statistics_sale; //销售统计
    private String statistics_payment; // 客户付款情况
    private String statistics_purchase; // 客户购买情况

    private Manager show = this;
    private JTabbedPane tabbedPane_all;
    private DBBean db = null;  // 数据库
    private MyJPanel product;  // 货品
    private MyJPanel customer;  // 客户
    private MyJPanel order;  // 订单列表
    private MyJPanel stock_in = null;  // 进货
    private MyJPanel stock_check;  // 清点
    private MyJPanel stock_trans;  // 货品调配
    private MyJPanel stock_statistics; //库存统计
    private fun_3 sale_statistics; //销售统计
    private fun_3 payment_statistics; // 客户付款情况
    private fun_3 purchase_statistics; // 客户购买情况
    private MyJPanel customersFund_statistics; //客户资金统计
    private MyJPanel account; // 用户
    private JPanel stock;  // 库存总面板
    private JPanel statistics; //统计总面板
    private JPanel panel_revenue_statistics; //营收统计面板
    private panelForPersonalAccount panel_personalAccount;

    private init_box init = null;  // 初始化语言
    private String belongto = "repository_all";

    private MyJPanel[] alltable;

    public Manager(ResourceBundle resourceBundle,DBBean db){
        this.db=db;
        this.init = new init_box(resourceBundle, db);

        myJPanel_productString = resourceBundle.getString("myJPanel_productString");//货品
        panel_customerStirng = resourceBundle.getString("panel_customerStirng");//客户
        stock_String = resourceBundle.getString("stock_String");//库存
        inStockStirng = resourceBundle.getString("inStockStirng");//进货
        check_stockString = resourceBundle.getString("check_stockString");//清点库存
        product_sendStirng = resourceBundle.getString("product_sendStirng");//货品调配
        AccountSring = resourceBundle.getString("AccountSring");//账户
        panel_orderListSring = resourceBundle.getString("panel_orderListSring");//订单列表
        panel_personalAccountSring = resourceBundle.getString("panel_personalAccountSring");//个人账户
        statistics_all = resourceBundle.getString("statistics");
        statistics_stock = resourceBundle.getString("statistics_stock");
        statistics_sale = resourceBundle.getString("statistics_sale");
        statistics_payment = resourceBundle.getString("statistics_payment");
        statistics_purchase = resourceBundle.getString("statistics_purchase");


//        menubar menu = new menubar(resourceBundle,this, db);
        JPanel totalPanel = new JPanel();
        totalPanel.setLayout(new BorderLayout());
        totalPanel.setPreferredSize( new Dimension(920, 600));
        totalPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
//        this.setJMenuBar(menu);
        tabbedPane_all = new JTabbedPane(JTabbedPane.SCROLL_TAB_LAYOUT);
        totalPanel.add(tabbedPane_all);
        show.getContentPane().add(totalPanel);
        try{
            org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();}
        catch(Exception e){}


        /***货品Panel***/
        Vector<Object> name_product = new Vector<>(Arrays.asList("id", "name", "outprice"));
        product = new MyJPanel(name_product, 0, 0);
        tabbedPane_all.add(product);
        tabbedPane_all.addTab(myJPanel_productString, product);


        /***客户Panel***/
        Vector<Object> name_customer = new Vector<>(Arrays.asList("id", "name", "phonenum", "classification"));
        customer = new MyJPanel(name_customer, 0,0);
        tabbedPane_all.add(customer);
        tabbedPane_all.add(panel_customerStirng, customer);


        /***查看订单列表secondPanel***/
        Vector<Object> name_order = new Vector<>(Arrays.asList("id", "name", "price_all", "state"));
        order = new MyJPanel(name_order, 0,0);
        tabbedPane_all.addTab(panel_orderListSring, order);


        /***库存Panel***/
        stock = new JPanel();
        stock.setPreferredSize( new Dimension(920, 600));
        stock.setLayout(new BorderLayout());
        tabbedPane_all.add(stock);
        tabbedPane_all.addTab(stock_String, stock);
        JTabbedPane tabbedPane_stock = new JTabbedPane(JTabbedPane.SCROLL_TAB_LAYOUT);
        stock.add(tabbedPane_stock);


        /***进货secondPanel***/
        Vector<Object> name_stock_in = new Vector<>(Arrays.asList("product_name", "num", "inprice", "outprice", "outprice_wholesale"));
        stock_in = new MyJPanel(name_stock_in, 0,0);
        tabbedPane_stock.add(stock_in);
        tabbedPane_stock.addTab(inStockStirng, stock_in);

        /***清点库存***/
        Vector<Object> name_stock_check = new Vector<>(Arrays.asList("id", "name", "num", "outprice", "outprice_wholesale", "inprice"));
        stock_check = new MyJPanel(name_stock_check, 0,0);
        tabbedPane_stock.add(stock_check);
        tabbedPane_stock.addTab(check_stockString, stock_check);


        /***货品调配***/
        Vector<Object> name_stock_transfor = new Vector<>(Arrays.asList("product_name", "num"));
        stock_trans = new MyJPanel(name_stock_transfor, 0,0);
        String[] repository_str={"",""};
        stock_trans.setUp(init.stock_trans(stock_trans, db,repository_str));
        tabbedPane_stock.add(stock_trans);
        tabbedPane_stock.addTab(product_sendStirng, stock_trans);


        /***账户Panel***/
        Vector<Object> name_account = new Vector<>(Arrays.asList("user_name", "phonenum", "authority", "belongto"));
        account = new MyJPanel(name_account, 0,0);
        tabbedPane_all.add(account);
        tabbedPane_all.addTab(AccountSring, account);

        //@yzj
        /***统计Panel***/
        statistics = new JPanel();
        statistics.setPreferredSize(new Dimension(920,600));
        statistics.setLayout(new BorderLayout());
        tabbedPane_all.add(statistics);
        tabbedPane_all.addTab(statistics_all,statistics);
        JTabbedPane tabbedPane_statistics = new JTabbedPane();
        statistics.add(tabbedPane_statistics);

        /***库存统计subPanel***/
        Vector<Object> name_stock_statistics = new Vector<>(Arrays.asList("id","name","num","inprice", "total"));
        stock_statistics = new MyJPanel(name_stock_statistics,0,0);
        tabbedPane_statistics.add(stock_statistics);
        tabbedPane_statistics.addTab(statistics_stock,stock_statistics);


        /***销售统计subPanel***/
        Vector<Object> name_sale_order = new Vector<>(Arrays.asList("name","out","back"));
        sale_statistics = new fun_3(db, name_sale_order, resourceBundle, "item");
        tabbedPane_statistics.add(sale_statistics);
        tabbedPane_statistics.addTab(statistics_sale,sale_statistics);

        /***客户付款情况统计subPanel***/
        Vector<Object> payment_header = new Vector<>(Arrays.asList("name","order_id","state","price_all"));
        payment_statistics = new fun_3(db, payment_header, resourceBundle, "payment");
        tabbedPane_statistics.add(payment_statistics);
        tabbedPane_statistics.addTab(statistics_payment,payment_statistics);

        /***客户购买情况统计subPanel***/
        Vector<Object> purchase_header = new Vector<>(Arrays.asList("name","product_name","num"));
        purchase_statistics = new fun_3(db, purchase_header, resourceBundle, "customer");
        tabbedPane_statistics.add(purchase_statistics);
        tabbedPane_statistics.addTab(statistics_purchase,purchase_statistics);


        /***经营状况统计subPanel***/
        panel_revenue_statistics = new JPanel();
        panel_revenue_statistics.setLayout(new BorderLayout());
        Box horizontalBox = Box.createHorizontalBox();
        Box verticalBox = Box.createVerticalBox();
        verticalBox.add(Box.createVerticalStrut(100));
        verticalBox.add(horizontalBox);

        JLabel label_revenue_statistics = new JLabel("选择店铺    ");
        label_revenue_statistics.setFont(new Font("仿宋",Font.BOLD+Font.ITALIC,30));
        JComboBox comboBox_revenue_statistics = new JComboBox(init.comboxString[0].toArray());
        comboBox_revenue_statistics.setPreferredSize(new Dimension(300,30));
        JButton button_revenue_statistics = new JButton("确 认");
        button_revenue_statistics.setPreferredSize(new Dimension(100,30));
        horizontalBox.add(Box.createHorizontalGlue());
        horizontalBox.add(label_revenue_statistics);
        horizontalBox.add(comboBox_revenue_statistics);
        horizontalBox.add(button_revenue_statistics);
        horizontalBox.add(Box.createHorizontalGlue());
        panel_revenue_statistics.add(verticalBox,BorderLayout.NORTH);
        button_revenue_statistics.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel_revenue_statistics.add(new panelForRevenue(String.valueOf(comboBox_revenue_statistics.getSelectedItem()),db),BorderLayout.CENTER);
            }
        });


        tabbedPane_statistics.add(panel_revenue_statistics);
        tabbedPane_statistics.add("经营状况统计",panel_revenue_statistics);

        /***个人账户Panel***/
        panel_personalAccount = new panelForPersonalAccount(resourceBundle, db);
        tabbedPane_all.add(panel_personalAccount);
        tabbedPane_all.addTab(panel_personalAccountSring, panel_personalAccount);


        // 添加box
        product.setUp(init.product(product, db));
        customer.setUp(init.customer(customer, db));
        String[] orderbelongto = new String[]{"repository1"};
        order.setUp(init.order_check_Manager(order, db, orderbelongto));
        order.setClickable(true, resourceBundle, db, orderbelongto[0]);
        Box[] temp = init.stock_in(stock_in, db, new MyJPanel[]{product, stock_check}, belongto);
        stock_in.setUp(temp[0]); stock_in.setDown(temp[1]);
        stock_check.setUp(init.stock_check_manager(stock_check, db));
        account.setUp(init.account(account, db));
        stock_statistics.setUp(init.stockStatistics(stock_statistics,db, resourceBundle));
        //sale_statistics.setUp(init.)

        // 读数据
        product.setData(returnVector.FromDBReadAll(db, belongto, name_product), resourceBundle);
        customer.setData(returnVector.FromDBReadAll(db, "customermanager", name_customer), resourceBundle);
        order.setData(returnVector.FromDBReadAll(db, "repository1_order", order.getTableName()), resourceBundle);
        stock_in.setData(new Vector<>(), resourceBundle);
        stock_check.setData(returnVector.FromDBReadAll(db, belongto, name_stock_check), resourceBundle);
        stock_trans.setData(new Vector<>(), resourceBundle);
        account.setData(returnVector.FromDBReadAll(db, "login", account.getTableName()), resourceBundle);
        sale_statistics.setData(new Vector(), resourceBundle);
        payment_statistics.setData(new Vector(), resourceBundle);
        purchase_statistics.setData(new Vector(), resourceBundle);

        alltable = new MyJPanel[]{order, null, null, null, null, null,
                stock_check, product, customer, account};

        menubar menu = new menubar(resourceBundle,this, db, new Object[]{belongto, alltable, init.comboxString});
        this.setJMenuBar(menu);

        addKeyListener(new MyListener(belongto, alltable, db, init, init.comboxString));
    }

    public String getBelongto() { return "res"; }

}
