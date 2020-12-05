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
    private String belongto;

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
    public Shopkeeper(ResourceBundle resourceBundle ,DBBean db, String belongTo){
        this.db=db;
        this.belongto = belongTo;
        init_box init = new init_box(resourceBundle);
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
        name_product.add("ID"); name_product.add("Name"); name_product.add("Outprice");
        product = new MyJPanel(name_product, 0,0);
        tabbedPane_all.add(product);
        tabbedPane_all.addTab(myJPanel_productString, product);


        /***客户Panel***/
        Vector<Object> name_customer = new Vector<>();
        // todo @sxz
        // todo returnVector.getname 不能调这个函数，因为这个调了之后字典里面加不上，也就是做不了多语言
        name_customer.add("ID"); name_customer.add("name");
        name_customer.add("phonenumber"); name_customer.add("classification");
        customer = new MyJPanel(name_customer, 0,0);
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
        // todo @sxz
        Vector<Object> name_nochange = new Vector<>();
        name_nochange.add("ID"); name_nochange.add("Name"); name_nochange.add("price_all"); name_nochange.add("State");
        Vector<Object> name_havechange = new Vector<>();
        name_havechange.add("ID"); name_havechange.add("Name"); name_havechange.add("price_all"); name_havechange.add("State"); name_havechange.add("StateChange");

        /***开销售单secondPanel***/
        Vector<Object> name_order_new = new Vector<>();
        // todo @sxz
        name_order_new.add("物品名"); name_order_new.add("单价");
        name_order_new.add("数量"); name_order_new.add("总价");
        order_new = new MyJPanel(name_order_new, 0,1);
        // todo 注释，店长店员用一个
        tabbedPane_sell.add(order_new);
        tabbedPane_sell.addTab(panel_addingOrderStirng, order_new);

        /***待审核列表secondPanel***/
        order_uncheck = new MyJPanel(name_havechange, 1,2);
        tabbedPane_sell.add(order_uncheck);
        tabbedPane_sell.addTab(To_be_ReviewedStirng, order_uncheck);

        /***待付款列表secondPanel***/
        order_unpaid = new MyJPanel(name_havechange, 2,3);
        tabbedPane_sell.add(order_unpaid);
        tabbedPane_sell.addTab(unpaidStirng, order_unpaid);

        /***已完成列表secondPanel***/
        order_finish = new MyJPanel(name_havechange, 3,4);
        tabbedPane_sell.add(order_finish);
        tabbedPane_sell.addTab(finishedString, order_finish);

        /***待退货列表secondPanel***/
        order_returning = new MyJPanel(name_havechange, 4,5);
        tabbedPane_sell.add(order_returning);
        tabbedPane_sell.addTab(unReturnSring, order_returning);

        /***已退货列表secondPanel***/
        order_returned = new MyJPanel(name_nochange, 5,0);
        tabbedPane_sell.add(order_returned);
        tabbedPane_sell.addTab(returnedSring, order_returned);

        /***查看订单列表secondPanel***/
        order_all = new MyJPanel(name_nochange, 0,0);
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
        // todo @sxz
        name_stock_in.add("物品名"); name_stock_in.add("数量");
        name_stock_in.add("进价"); name_stock_in.add("售价");
        name_stock_in.add("售价（批发）");
        // todo 店长与经理
        stock_in = new MyJPanel(name_stock_in, 0,0);
        tabbedPane_stock.add(stock_in);
        tabbedPane_stock.addTab(inStockStirng, stock_in);

        /***清点库存secondPanel***/
        Vector<Object> name_stock_check = new Vector<>();
        // todo @sxz
        name_stock_check.add("id"); name_stock_check.add("name");
        name_stock_check.add("num"); name_stock_check.add("outprice");
        name_stock_check.add("outprice_wholesale"); name_stock_check.add("inprie");
        stock_check = new MyJPanel(name_stock_check, 0,0);
        tabbedPane_stock.add(stock_check);
        tabbedPane_stock.addTab(check_stockString, stock_check);


        /***账户Panel***/
        Vector<Object> name_account = new Vector<>();
        // todo @sxz
        name_account.add("ID"); name_account.add("员工名");
        name_account.add("账户"); name_account.add("密码");
        account = new MyJPanel(name_account, 0,0);
        tabbedPane_all.add(account);
        tabbedPane_all.addTab(AccountSring, account);


        /***个人账户Panel***/
        panel_personalAccount = new panelForPersonalAccount(resourceBundle, db);
        tabbedPane_all.add(panel_personalAccount);
        tabbedPane_all.addTab(panel_personalAccountSring, panel_personalAccount);


        // 设置box
        MyJPanel[] related = {order_all, order_uncheck, order_unpaid, order_finish,
                               order_returning, order_returned/*, stock_check*/};
        // todo 更新清单时库存更新
        customer.setUp(init.customer(customer, db));
        Box[] temp = init.order_new(order_new, db, new MyJPanel[]{order_uncheck, order_all}, belongto);
        order_new.setUp(temp[0]); order_new.setDown(temp[1]);
        order_uncheck.setDown(init.order_check_but(order_uncheck, db, related, belongto));
        order_unpaid.setDown(init.order_check_but(order_unpaid, db, related, belongto));
        order_finish.setDown(init.order_check_but(order_finish, db, related, belongto));
        order_returning.setDown(init.order_check_but(order_returning, db, related, belongto));
        order_all.setUp(init.order_check(order_all, db, related, belongTo));
        temp = init.stock_in(stock_in, db, stock_check, belongto);
        stock_in.setUp(temp[0]); stock_in.setDown(temp[1]);
        stock_check.setUp(init.stock_check(stock_check, db));
        // todo 账户的按钮


        // 读数据
        product.setData(returnVector.FromDBReadAll(db, belongTo, name_product));
        customer.setData(returnVector.FromDBReadAll(db, "customermanager", name_customer));
        for (int i = 1; i < 6; i++){
            related[i].setData(returnVector.FromDBRead(db, belongTo+"_order", name_nochange, op.op.StateConvert(related[i].getNow()), "State"));
        }
        order_all.setData(returnVector.FromDBReadAll(db, belongTo+"_order", name_nochange));
        stock_check.setData(returnVector.FromDBReadAll(db, belongTo, name_stock_check));
        // todo 员工管理的数据库没写。。。。。account.setData();
    }

    public String getBelongto() { return this.belongto; }


}
