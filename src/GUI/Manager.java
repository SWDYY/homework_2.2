package GUI;

import Bean.DBBean;
import op.returnVector;
import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
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

    private Manager show = this;
    private JTabbedPane tabbedPane_all;
    private DBBean db = null;  // 数据库
    private MyJPanel product;  // 货品
    private MyJPanel customer;  // 客户
    private MyJPanel order;  // 订单列表
    private MyJPanel stock_in = null;  // 进货
    private MyJPanel stock_check;  // 清点
    private MyJPanel stock_trans;  // 货品调配
    private MyJPanel account; // 用户
    private JPanel stock;  // 库存总面板
    private panelForPersonalAccount panel_personalAccount;

    private init_box init = null;  // 初始化语言
    private String belongto = "repository_all";


    public Manager(ResourceBundle resourceBundle,DBBean db){
        this.db=db;
        this.init = new init_box(resourceBundle);

        this.setBounds(450, 150, 1000, 600);
        myJPanel_productString = resourceBundle.getString("myJPanel_productString");//货品
        panel_customerStirng = resourceBundle.getString("panel_customerStirng");//客户
        stock_String = resourceBundle.getString("stock_String");//库存
        inStockStirng = resourceBundle.getString("inStockStirng");//进货
        check_stockString = resourceBundle.getString("check_stockString");//清点库存
        product_sendStirng = resourceBundle.getString("product_sendStirng");//货品调配
        AccountSring = resourceBundle.getString("AccountSring");//账户
        panel_orderListSring = resourceBundle.getString("panel_orderListSring");//订单列表
        panel_personalAccountSring = resourceBundle.getString("panel_personalAccountSring");//个人账户

        menubar menu = new menubar(resourceBundle,this, db);
        JPanel totalPanel = new JPanel();
        totalPanel.setLayout(new BorderLayout());
        totalPanel.setPreferredSize( new Dimension(920, 600));
        totalPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        this.setJMenuBar(menu);
        tabbedPane_all = new JTabbedPane(JTabbedPane.SCROLL_TAB_LAYOUT);
        totalPanel.add(tabbedPane_all);
        show.getContentPane().add(totalPanel);
        try{
            org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();}
        catch(Exception e){}


        /***货品Panel***/
        Vector<Object> name_product = new Vector<>();
        // todo @sxz
        name_product.add("ID"); name_product.add("Name");
        name_product.add("outprice"); name_product.add("outprice_wholesale");
        product = new MyJPanel(name_product, 0,0);
        tabbedPane_all.add(product);
        tabbedPane_all.addTab(myJPanel_productString, product);


        /***客户Panel***/
        Vector<Object> name_customer = new Vector<>();
        // todo @sxz
        name_customer.add("ID"); name_customer.add("name");
        name_customer.add("phonenumber"); name_customer.add("classification");
        customer = new MyJPanel(name_customer, 0,0);
        tabbedPane_all.add(customer);
        tabbedPane_all.add(panel_customerStirng, customer);


        /***查看订单列表secondPanel***/
        Vector<Object> name_order = new Vector<>();
        name_order.add("ID"); name_order.add("Name");
        name_order.add("price_all"); name_order.add("State");
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
        Vector<Object> name_stock_in = new Vector<>();
        // todo @sxz
        name_stock_in.add("物品名"); name_stock_in.add("数量");
        name_stock_in.add("进价"); name_stock_in.add("售价");
        name_stock_in.add("售价（批发）");
        stock_in = new MyJPanel(name_stock_in, 0,0);
        tabbedPane_stock.add(stock_in);
        tabbedPane_stock.addTab(inStockStirng, stock_in);

        /***清点库存***/
        Vector<Object> name_stock_check = new Vector<>();
        // todo @sxz
        name_stock_check.add("id"); name_stock_check.add("name");
        name_stock_check.add("num"); name_stock_check.add("outprice");
        name_stock_check.add("outprice_wholesale"); name_stock_check.add("inprie");
        stock_check = new MyJPanel(name_stock_check, 0,0);
        tabbedPane_stock.add(stock_check);
        tabbedPane_stock.addTab(check_stockString, stock_check);


        // todo
        /***货品调配***/
        Vector<Object> name_stock_transfor = new Vector<>();
        name_stock_transfor.add("todo");
        name_stock_transfor.add("2");
        name_stock_transfor.add("3");
        name_stock_transfor.add("4");
        stock_trans = new MyJPanel(name_stock_transfor, 0,0);
        stock_trans.setUp(init.stock_trans(stock_trans, db));
        tabbedPane_stock.add(stock_trans);
        tabbedPane_stock.addTab(product_sendStirng, stock_trans);


        /***账户Panel***/
        Vector<Object> name_account = new Vector<>();
        // todo @sxz
        name_account.add("id"); name_account.add("user_name");
        name_account.add("user_password"); name_account.add("phonenum");
        name_account.add("authority"); name_account.add("belongto");
        account = new MyJPanel(name_account, 0,0);
        tabbedPane_all.add(account);
        tabbedPane_all.addTab(AccountSring, account);


        /***个人账户Panel***/
        panel_personalAccount = new panelForPersonalAccount(resourceBundle, db);
        tabbedPane_all.add(panel_personalAccount);
        tabbedPane_all.addTab(panel_personalAccountSring, panel_personalAccount);


        // 添加box
        customer.setUp(init.customer(customer, db));
        order.setUp(init.order_check(order, db, new MyJPanel[]{order}, belongto));
        Box[] temp = init.stock_in(stock_in, db, stock_check, belongto);
        stock_in.setUp(temp[0]); stock_in.setDown(temp[1]);
        stock_check.setUp(init.stock_check(stock_check, db, belongto));
        // todo 库存转移
        account.setUp(init.account(account, db));


        // 读数据
        product.setData(returnVector.FromDBReadAll(db, belongto, name_product));
        customer.setData(returnVector.FromDBReadAll(db, "customermanager", name_customer));
        order.setData(returnVector.FromDBReadAll(db, "repository1_order", order.getTableName()));
        stock_check.setData(returnVector.FromDBReadAll(db, belongto, name_stock_check));
        account.setData(returnVector.FromDBReadAll(db, "login", account.getTableName()));

    }

    public String getBelongto() { return "res"; }

}
