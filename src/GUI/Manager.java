package GUI;

import Bean.DBBean;
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
        name_product.add("ID"); name_product.add("Name");
        name_product.add("Outpirce");
        product = new MyJPanel(name_product, 0,0);
        tabbedPane_all.add(product);
        tabbedPane_all.addTab(myJPanel_productString, product);

        /***客户Panel***/
        Vector<Object> name_customer = new Vector<>();
        // todo @sxz
        // todo returnVector.getname 不能调这个函数，因为这个调了之后字典里面加不上，也就是做不了多语言
        name_customer.add("ID"); name_customer.add("name");
        name_customer.add("phonenumber"); name_customer.add("classification");
        customer = new MyJPanel(name_customer, 0, 0);
        tabbedPane_all.add(customer);
        tabbedPane_all.add(panel_customerStirng, customer);

        /***账单Panel***/
        Vector<Object> name_order = new Vector<>();
        name_order.add("表头需改");
        name_order.add("2");
        name_order.add("3");
        name_order.add("4");
        order = new MyJPanel(name_order, 0,0);
//        order.setUp(init.order_check(order, db));
        tabbedPane_all.addTab(panel_orderListSring, order);

        /***库存Panel***/
        stock = new JPanel();
        stock.setPreferredSize( new Dimension(920, 600));
        stock.setLayout(new BorderLayout());
        tabbedPane_all.add(stock);
        tabbedPane_all.addTab(stock_String, stock);
        JTabbedPane tabbedPane_stock = new JTabbedPane(JTabbedPane.SCROLL_TAB_LAYOUT);
        stock.add(tabbedPane_stock);

        /***进货***/
        Vector<Object> name_stock_in = new Vector<>();
        name_stock_in.add("表头需改");
        name_stock_in.add("2");
        name_stock_in.add("3");
        name_stock_in.add("4");
        // todo 店长与经理
        stock_in = new MyJPanel(name_stock_in, 0,0);
        Box[] temp = init.stock_in(stock_in, db, stock_check, "respo");
        stock_in.setUp(temp[0]);
        stock_in.setUp(temp[1]);
        tabbedPane_stock.add(stock_in);
        tabbedPane_stock.addTab(inStockStirng, stock_in);

        /***清点库存***/
        Vector<Object> name_stock_check = new Vector<>();
        name_stock_check.add("表头需改");
        name_stock_check.add("2");
        name_stock_check.add("3");
        name_stock_check.add("4");
        stock_check = new MyJPanel(name_stock_check, 0,0);
        stock_check.setUp(init.stock_check(stock_check, db));
        tabbedPane_stock.add(stock_check);
        tabbedPane_stock.addTab(check_stockString, stock_check);

        /***货品调配***/
        Vector<Object> name_stock_transfor = new Vector<>();
        name_stock_transfor.add("表头需改");
        name_stock_transfor.add("2");
        name_stock_transfor.add("3");
        name_stock_transfor.add("4");
        stock_trans = new MyJPanel(name_stock_transfor, 0,0);
        stock_trans.setUp(init.stock_trans(stock_trans, db));
        tabbedPane_stock.add(stock_trans);
        tabbedPane_stock.addTab(product_sendStirng, stock_trans);

        /***账户***/
        Vector<Object> name_account = new Vector<>();
        name_account.add("表头需改");
        name_account.add("2");
        name_account.add("3");
        name_account.add("4");
        account = new MyJPanel(name_account, 0,0);
        account.setUp(init.account(account, db));
        tabbedPane_all.add(account);
        tabbedPane_all.addTab(AccountSring, account);

        /***个人账户Panel***/
        panel_personalAccount = new panelForPersonalAccount(resourceBundle, db);
        tabbedPane_all.add(panel_personalAccount);
        tabbedPane_all.addTab(panel_personalAccountSring, panel_personalAccount);
    }

    public String getBelongto() { return "res"; }

}
