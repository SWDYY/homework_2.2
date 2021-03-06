package GUI;

import Bean.DBBean;
import op.returnVector;
import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.AWTEventListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.Vector;

/**
 * 销售员
 */
public class Employee extends JFrame {
    //language
    private String myJPanel_productString;//货品
    private String panel_customerStirng;//客户
    private String panel_sellStirng;//销售
    private String panel_addingOrderStirng;//开销售单
    private String panel_cashieringString;//收银
    private String panel_orderListSring;//订单列表
    private String panel_personalAccountSring;//个人账户

    private String belongto = null;  // 属于哪个店
    private DBBean db;  // 关联的数据库
    private init_box init = null;
    private MyJPanel alltable[];

    private JTabbedPane tabbedPane_all;
    private JTabbedPane tabbedPane_sell;
    private Employee show = this;
    private JPanel panel_sell;
    private panelForPersonalAccount panel_personalAccount;

    private MyJPanel product;  // 商品
    private MyJPanel customer;  // 客户
    private MyJPanel order_new;  // 开销售单
    private MyJPanel order_unpaid;  // 未付款
    private MyJPanel order_all;  // 订单列表、


    public Employee(ResourceBundle resourceBundle, DBBean db, String belongTo){

        init = new init_box(resourceBundle, db);
        belongto = belongTo;
        this.db =db;

        myJPanel_productString = resourceBundle.getString("myJPanel_productString");//货品
        panel_customerStirng = resourceBundle.getString("panel_customerStirng");//客户
        panel_sellStirng = resourceBundle.getString("panel_sellStirng");//销售
        panel_addingOrderStirng = resourceBundle.getString("panel_addingOrderStirng");//开销售单
        panel_cashieringString = resourceBundle.getString("panel_cashieringString");//收银
        panel_orderListSring = resourceBundle.getString("panel_orderListSring");//订单列表
        panel_personalAccountSring = resourceBundle.getString("panel_personalAccountSring");//个人账户

        try{
        org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();}
        catch(Exception e){}
        JPanel totalPanel = new JPanel();
        totalPanel.setPreferredSize( new Dimension(920, 600));
        totalPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        tabbedPane_all = new JTabbedPane(JTabbedPane.SCROLL_TAB_LAYOUT);
        totalPanel.setLayout(new BorderLayout());

        totalPanel.add(tabbedPane_all);
        show.add(totalPanel);

        /***货品Panel***/
        Vector<Object> name_product = new Vector<>(Arrays.asList("id", "name", "outprice"));
        product = new MyJPanel(name_product, 0, 0);
        tabbedPane_all.add(product);
        tabbedPane_all.addTab(myJPanel_productString, product);

        /***客户Panel***/
        Vector<Object> name_customer = new Vector<>(Arrays.asList("id", "name", "phonenum", "classification"));
        customer = new MyJPanel(name_customer, 0, 0);
        tabbedPane_all.add(customer);
        tabbedPane_all.add(panel_customerStirng, customer);

        /***销售Panel***/
        panel_sell = new JPanel();
        panel_sell.setPreferredSize( new Dimension(920, 600));
        panel_sell.setLayout(new BorderLayout());
        tabbedPane_all.add(panel_sell, SwingConstants.NORTH);
        tabbedPane_all.addTab(panel_sellStirng, panel_sell);
        tabbedPane_sell = new JTabbedPane(JTabbedPane.SCROLL_TAB_LAYOUT);
        panel_sell.add(tabbedPane_sell);

        /***开销售单***/
        Vector<Object> name_order_new = new Vector<>(Arrays.asList("product_name", "outprice", "num", "label_restore_totalBuyingPriceTitle"));
        order_new = new MyJPanel(name_order_new, 0, 3);
        tabbedPane_sell.add(order_new);
        tabbedPane_sell.addTab(panel_addingOrderStirng, order_new);

        /***未收款（零售）***/
        Vector<Object> name_order_unpaid = new Vector<>();
        // todo
//        name_order_unpaid.add("这里展示付款二维码+付款完成的按钮");
        order_unpaid = new MyJPanel(name_order_unpaid, 0,0);
        tabbedPane_sell.add(order_unpaid);
        tabbedPane_sell.addTab(panel_cashieringString, order_unpaid);

        /***查看订单列表secondPanel***/
        Vector<Object> name_order = new Vector<>(Arrays.asList("id", "name", "price_all"));
        order_all = new MyJPanel(name_order, 0,0);
        tabbedPane_sell.addTab(panel_orderListSring, order_all);

        /***个人账户***/
        panel_personalAccount = new panelForPersonalAccount(resourceBundle, db);
        tabbedPane_all.add(panel_personalAccount);
        tabbedPane_all.addTab(panel_personalAccountSring, panel_personalAccount);


        // 设置box
        customer.setUp(init.customer(customer, db));
        Box[] temp = init.order_new(order_new, db, new MyJPanel[]{order_all}, belongto);
        order_new.setUp(temp[0]); order_new.setDown(temp[1]);
        order_all.setUp(init.order_check(order_all, db, new MyJPanel[]{order_all}, new String[]{belongto}));
        order_all.setClickable(true, resourceBundle, db, belongto);

        // 设置数据
        product.setData(returnVector.FromDBReadAll(db, belongTo, name_product), resourceBundle);
        customer.setData(returnVector.FromDBReadAll(db, "customermanager", name_customer), resourceBundle);
        order_new.setData(new Vector<>(), resourceBundle);
        order_all.setData(returnVector.FromDBReadAll(db, belongTo+"_order", name_order), resourceBundle);

        alltable = new MyJPanel[]{order_all, null, null, null, null, null,
                null, product, customer, null};

        menubar menu = new menubar(resourceBundle,this, db, new Object[]{belongto, alltable, null});
        this.setJMenuBar(menu);

        this.addKeyListener(new MyListener(belongTo, alltable, db, init, null));

    }

    public String getBelongto() { return this.belongto; }

}
