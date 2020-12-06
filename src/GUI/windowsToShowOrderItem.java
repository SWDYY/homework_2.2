package GUI;


import Bean.DBBean;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.Vector;

public class windowsToShowOrderItem extends JFrame {


    public windowsToShowOrderItem(ResourceBundle resourceBundle, DBBean db, int id, String belongto){

        Vector<Vector<Object>>data = new Vector<>();
        ResultSet temp = db.executeFind(String.valueOf(id), belongto+"_item_order", "order_id");
        try{
            while(temp.next()){
                Vector<Object> temp1 = new Vector<>();
                temp1.add(temp.getString("item_name"));
                temp1.add(temp.getString("num"));
                data.add(temp1);
            }
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        JTable show = new JTable();
        // todo @sxz
        Vector<Object> name = new Vector();
        name.add("name"); name.add("num");
        DefaultTableModel_noEditable model = new DefaultTableModel_noEditable(data, name, 99);

        JTable table = new JTable(model);
        JScrollPane jScrollPane = new JScrollPane();
        jScrollPane.setViewportView(table);
        jScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        JPanel panel = new JPanel();
        panel.add(jScrollPane, BorderLayout.CENTER);

        this.getContentPane().add(panel);
    }
}
