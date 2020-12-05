package GUI;

import Bean.DBBean;
import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ResourceBundle;
import java.util.Vector;

/**
 * 店长类构造GUI
 */
public class Shopkeeper extends JFrame {

    private String myJPanel_productString;//货品
    private String panel_customerStirng;//客户
    private String panel_sellStirng;//销售
    private String panel_addingOrderStirng;//开销售单
    private String panel_orderListSring;//订单列表
    private String To_be_ReviewedStirng;//待审核
    private String unpaidStirng;//待付款
    private String finishedString;//已完成
    private String unReturnSring;//待退货
    private String returnedSring;//已退货
    private String stock_String;//库存
    private String inStockStirng;//进货
    private String check_stockString;//清点库存
    private String AccountSring;//账户
    private String panel_personalAccountSring;//个人账户

    private Shopkeeper show = this;

    private JTabbedPane tabbedPane_all;
    private JTabbedPane tabbedPane_sell;
    private JTabbedPane tabbedPane_stock;

    private JPanel panel_sell;
    private JPanel panel_stock;
    private panelForPersonalAccount panel_personalAccount;

    private DBBean db;

    private MyJPanel product; // 货品
    private MyJPanel customer;  // 客户
    private MyJPanel order_new;  // 开销售单
    private MyJPanel order_uncheck;  // 待审核
    private MyJPanel order_unpaid;  // 未付款
    private MyJPanel order_finish;  // 已完成
    private MyJPanel order_returning;  // 未退货
    private MyJPanel order_returned;  // 已退货
    private MyJPanel order_all;  // 订单列表
    private MyJPanel stock_in;  // 库存进货
    private MyJPanel stock_check;  // 库存清点
    private MyJPanel account;  // 账户

    /**
     * 店长
     */
    public Shopkeeper(ResourceBundle resourceBundle ,DBBean db){
        this.db=db;
        myJPanel_productString = resourceBundle.getString("myJPanel_productString");//货品
        panel_customerStirng = resourceBundle.getString("panel_customerStirng");//客户
        panel_sellStirng = resourceBundle.getString("panel_sellStirng");//销售
        panel_addingOrderStirng = resourceBundle.getString("panel_addingOrderStirng");//开销售单
        panel_orderListSring = resourceBundle.getString("panel_orderListSring");//订单列表
        To_be_ReviewedStirng = resourceBundle.getString("To_be_ReviewedStirng");//待审核
        unpaidStirng = resourceBundle.getString("unpaidStirng");//待付款
        finishedString = resourceBundle.getString("finishedString");//已完成
        unReturnSring = resourceBundle.getString("unReturnSring");//待退货
        returnedSring = resourceBundle.getString("returnedSring");//已退货
        stock_String = resourceBundle.getString("stock_String");//库存
        inStockStirng = resourceBundle.getString("inStockStirng");//进货
        check_stockString = resourceBundle.getString("check_stockString");//清点库存
        AccountSring = resourceBundle.getString("AccountSring");//账户
        panel_personalAccountSring = resourceBundle.getString("panel_personalAccountSring");//个人账户

        menubar menu = new menubar(resourceBundle);
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
        name_product.add("ID");
        name_product.add("Name");
        name_product.add("Outprice");
        product = new MyJPanel(name_product, 0,0);
        tabbedPane_all.add(product);
        tabbedPane_all.addTab(myJPanel_productString, product);

        /***客户Panel***/
        Vector<Object> name_customer = new Vector<>();
        name_customer.add("ID");
        name_customer.add("Name");
        name_customer.add("PhoneNumber");
        name_customer.add("address");
        customer = new MyJPanel(name_customer, 0,0);
        customer.setUp(new init_box(resourceBundle).customer(customer, db));
        tabbedPane_all.add(customer);
        tabbedPane_all.add(panel_customerStirng, customer);

        /***销售Panel***/
        panel_sell = new JPanel();
        panel_sell.setPreferredSize( new Dimension(920, 600));
        panel_sell.setLayout(new BorderLayout());
        tabbedPane_all.add(panel_sell, SwingConstants.NORTH);
        tabbedPane_all.addTab(panel_sellStirng, panel_sell);
        tabbedPane_sell = new JTabbedPane();
        panel_sell.add(tabbedPane_sell);

        /***开销售单secondPanel***/
        Vector<Object> name_order_new = new Vector<>();
        name_customer.add("表头需该");
        name_customer.add("2");
        name_customer.add("3");
        name_customer.add("4");
        order_new = new MyJPanel(name_order_new, 0,0);
        // todo 注释，店长店员用一个
//        Box[] temp = new init_box(resourceBundle).order_new(order_new, db, "respository1");
//        order_new.setUp(temp[0]);
//        order_new.setDown(temp[1]);
        tabbedPane_sell.add(order_new);
        tabbedPane_sell.addTab(panel_addingOrderStirng, order_new);


        /***待审核列表secondPanel***/
        Vector<Object> name_unchecked = new Vector<>();
        name_unchecked.add("表头需该");
        name_unchecked.add("2");
        name_unchecked.add("3");
        name_unchecked.add("4");
        order_uncheck = new MyJPanel(name_unchecked, 0,0);
//        order_uncheck.setDown(new init_box(resourceBundle).order_check_but(order_uncheck, db));
        tabbedPane_sell.add(order_uncheck);
        tabbedPane_sell.addTab(To_be_ReviewedStirng, order_uncheck);

        /***待付款列表secondPanel***/
        Vector<Object> name_order_unpaid = new Vector<>();
        name_order_unpaid.add("表头需该");
        name_order_unpaid.add("2");
        name_order_unpaid.add("3");
        name_order_unpaid.add("4");
        order_unpaid = new MyJPanel(name_order_unpaid, 0,0);
//        order_unpaid.setDown(new init_box(resourceBundle).order_check_but(order_unpaid, db));
        tabbedPane_sell.add(order_unpaid);
        tabbedPane_sell.addTab(unpaidStirng, order_unpaid);

        /***已完成列表secondPanel***/
        Vector<Object> name_order_finish = new Vector<>();
        name_order_finish.add("表头需该");
        name_order_finish.add("2");
        name_order_finish.add("3");
        name_order_finish.add("4");
        order_finish = new MyJPanel(name_order_finish, 0,0);
//        order_finish.setDown(new init_box(resourceBundle).order_check_but(order_finish, db));
        tabbedPane_sell.add(order_finish);
        tabbedPane_sell.addTab(finishedString, order_finish);

        /***待退货列表secondPanel***/
        Vector<Object> name_order_returning = new Vector<>();
        name_order_returning.add("表头需该");
        name_order_returning.add("2");
        name_order_returning.add("3");
        name_order_returning.add("4");
        order_returning = new MyJPanel(name_order_returning, 0,0);
//        order_returning.setDown(new init_box(resourceBundle).order_check_but(order_returning, db));
        tabbedPane_sell.add(order_returning);
        tabbedPane_sell.addTab(unReturnSring, order_returning);

        /***已退货列表secondPanel***/
        Vector<Object> name_order_returned = new Vector<>();
        name_order_returned.add("表头需该");
        name_order_returned.add("2");
        name_order_returned.add("3");
        name_order_returned.add("4");
        order_returned = new MyJPanel(name_order_returned, 0,0);
        tabbedPane_sell.add(order_returned);
        tabbedPane_sell.addTab(returnedSring, order_returned);

        /***查看订单列表secondPanel***/
        Vector<Object> name_order = new Vector<>();
        name_order.add("表头需改");
        name_order.add("2");
        name_order.add("3");
        name_order.add("4");
        order_all = new MyJPanel(name_order, 0,0);
//        order_all.setUp(new init_box(resourceBundle).order_check(order_all, db));
        tabbedPane_all.addTab(panel_orderListSring, order_all);

        /***库存Panel***/
        panel_stock = new JPanel();
        panel_stock.setPreferredSize( new Dimension(920, 600));
        panel_stock.setLayout(new BorderLayout());
        tabbedPane_all.add(panel_stock);
        tabbedPane_all.addTab(stock_String, panel_stock);
        tabbedPane_stock = new JTabbedPane(JTabbedPane.SCROLL_TAB_LAYOUT);
        panel_stock.add(tabbedPane_stock);

        /***进货secondPanel***/
        Vector<Object> name_stock_in = new Vector<>();
        name_stock_in.add("表头需改");
        name_stock_in.add("2");
        name_stock_in.add("3");
        name_stock_in.add("4");
        // todo 店长与经理
        stock_in = new MyJPanel(name_stock_in, 0,0);
        Box[] temp = new init_box(resourceBundle).stock_in_manager(stock_in, db);
        stock_in.setUp(temp[0]);
        stock_in.setUp(temp[1]);
        tabbedPane_stock.add(stock_in);
        tabbedPane_stock.addTab(inStockStirng, stock_in);

        /***清点库存secondPanel***/
        Vector<Object> name_stock_check = new Vector<>();
        name_stock_check.add("表头需改");
        name_stock_check.add("2");
        name_stock_check.add("3");
        name_stock_check.add("4");
        stock_check = new MyJPanel(name_stock_check, 0,0);
        stock_check.setUp(new init_box(resourceBundle).stock_check(stock_check, db));
        tabbedPane_stock.add(stock_check);
        tabbedPane_stock.addTab(check_stockString, stock_check);

        /***账户Panel***/
        Vector<Object> name_account = new Vector<>();
        name_account.add("表头需改");
        name_account.add("2");
        name_account.add("3");
        name_account.add("4");
        // todo 上面的按钮
        account = new MyJPanel(name_account, 0,0);
        tabbedPane_all.add(account);
        tabbedPane_all.addTab(AccountSring, account);

        /***个人账户Panel***/
        panel_personalAccount = new panelForPersonalAccount(resourceBundle);
        tabbedPane_all.add(panel_personalAccount);
        tabbedPane_all.addTab(panel_personalAccountSring, panel_personalAccount);
    }

}
