package GUI;

import Bean.DBBean;
//import op.op;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;
import java.util.Vector;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

/**
 * 库存中，新增货物按钮按下后的弹窗
 */
public class windowsToCreateItemForManager extends JFrame {

    private String add_newItemString;// 新增入库商品
    private String product_nameString;// 货物名称
    private String productNumString;// 货物数量
    private String productInpriceString;// 商品进价
    private String productOutpriceString;// 商品售价
    private String targetStockString;// 目的仓库
    private String confirmString;// 确认
    private String cancel;// 取消

    private JTextField textField_addItem_nameDisplay;
    private JTextField textField_addItem_numberDisplay;
    private JTextField textField_addItem_inpriceDisplay;
    private JTextField textField_addItem_outpriceDisplay;
    private windowsToCreateItemForManager a = this;

    public windowsToCreateItemForManager(ResourceBundle resourceBundle, int op,DBBean db) {
        add_newItemString = resourceBundle.getString("add_newItemString");// 新增入库商品
        product_nameString = resourceBundle.getString("product_nameString");// 货物名称
        productNumString = resourceBundle.getString("productNumString");// 货物数量
        productInpriceString = resourceBundle.getString("productInpriceString");// 商品进价
        productOutpriceString = resourceBundle.getString("productOutpriceString");// 商品售价
        targetStockString = resourceBundle.getString("targetStockString");// 目的仓库
        confirmString = resourceBundle.getString("confirmString");// 确认
        cancel = resourceBundle.getString("cancel");// 取消
        //public windowsToCreateItemForManager(JTable table, Vector<Vector<Object>> data, Vector<Object> name, JTextField out) {
        setTitle(add_newItemString);

        JPanel panel_total = new JPanel();
        panel_total.setLayout(new BorderLayout());
        this.add(panel_total);
        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(30, 0, 0, 40));
        panel_total.add(panel, BorderLayout.CENTER);
        GridBagLayout gbl_panel = new GridBagLayout();
        gbl_panel.columnWidths = new int[]{30, 100, 168, 0};
        gbl_panel.rowHeights = new int[]{40, 40, 40, 40, 40, 0};
        gbl_panel.columnWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
        gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        panel.setLayout(gbl_panel);

        // 商品名称的label
        JLabel label_addItem_name = new JLabel(product_nameString);
        label_addItem_name.setFont(new Font("微软雅黑", Font.BOLD, 15));
        label_addItem_name.setHorizontalAlignment(SwingConstants.CENTER);
        GridBagConstraints gbc_label_addItem_name = new GridBagConstraints();
        gbc_label_addItem_name.fill = GridBagConstraints.BOTH;
        gbc_label_addItem_name.insets = new Insets(0, 0, 5, 5);
        gbc_label_addItem_name.gridx = 1;
        gbc_label_addItem_name.gridy = 0;
        panel.add(label_addItem_name, gbc_label_addItem_name);
        // 商品名称的TestField
        textField_addItem_nameDisplay = new JTextField();
        GridBagConstraints gbc_textField_addItem_nameDisplay = new GridBagConstraints();
        gbc_textField_addItem_nameDisplay.fill = GridBagConstraints.BOTH;
        gbc_textField_addItem_nameDisplay.insets = new Insets(0, 0, 5, 0);
        gbc_textField_addItem_nameDisplay.gridx = 2;
        gbc_textField_addItem_nameDisplay.gridy = 0;
        panel.add(textField_addItem_nameDisplay, gbc_textField_addItem_nameDisplay);
        textField_addItem_nameDisplay.setColumns(10);

        // 商品数量的label
        JLabel label_addItem_number = new JLabel(productNumString);
        label_addItem_number.setFont(new Font("微软雅黑", Font.BOLD, 15));
        label_addItem_number.setHorizontalAlignment(SwingConstants.CENTER);
        GridBagConstraints gbc_label_addItem_number = new GridBagConstraints();
        gbc_label_addItem_number.fill = GridBagConstraints.BOTH;
        gbc_label_addItem_number.insets = new Insets(0, 0, 5, 5);
        gbc_label_addItem_number.gridx = 1;
        gbc_label_addItem_number.gridy = 1;
        panel.add(label_addItem_number, gbc_label_addItem_number);
        // 商品数量的Textfield
        textField_addItem_numberDisplay = new JTextField();
        GridBagConstraints gbc_textField_addItem_numberDisplay = new GridBagConstraints();
        gbc_textField_addItem_numberDisplay.fill = GridBagConstraints.BOTH;
        gbc_textField_addItem_numberDisplay.insets = new Insets(0, 0, 5, 0);
        gbc_textField_addItem_numberDisplay.gridx = 2;
        gbc_textField_addItem_numberDisplay.gridy = 1;
        panel.add(textField_addItem_numberDisplay, gbc_textField_addItem_numberDisplay);
        textField_addItem_numberDisplay.setColumns(10);

        // 进价的label
        JLabel label_addItem_inprice = new JLabel(productInpriceString);
        label_addItem_inprice.setFont(new Font("微软雅黑", Font.BOLD, 15));
        label_addItem_inprice.setHorizontalAlignment(SwingConstants.CENTER);
        GridBagConstraints gbc_label_addItem_inprice = new GridBagConstraints();
        gbc_label_addItem_inprice.fill = GridBagConstraints.BOTH;
        gbc_label_addItem_inprice.insets = new Insets(0, 0, 5, 5);
        gbc_label_addItem_inprice.gridx = 1;
        gbc_label_addItem_inprice.gridy = 2;
        panel.add(label_addItem_inprice, gbc_label_addItem_inprice);
        // 进价的textfield
        textField_addItem_inpriceDisplay = new JTextField();
        GridBagConstraints gbc_textField_addItem_inpriceDisplay = new GridBagConstraints();
        gbc_textField_addItem_inpriceDisplay.fill = GridBagConstraints.BOTH;
        gbc_textField_addItem_inpriceDisplay.insets = new Insets(0, 0, 5, 0);
        gbc_textField_addItem_inpriceDisplay.gridx = 2;
        gbc_textField_addItem_inpriceDisplay.gridy = 2;
        panel.add(textField_addItem_inpriceDisplay, gbc_textField_addItem_inpriceDisplay);
        textField_addItem_inpriceDisplay.setColumns(10);

        // 商品售价的label
        JLabel label_addItem_outprice = new JLabel(productOutpriceString);
        label_addItem_outprice.setFont(new Font("微软雅黑", Font.BOLD, 15));
        label_addItem_outprice.setHorizontalAlignment(SwingConstants.CENTER);
        GridBagConstraints gbc_label_addItem_outprice = new GridBagConstraints();
        gbc_label_addItem_outprice.fill = GridBagConstraints.BOTH;
        gbc_label_addItem_outprice.insets = new Insets(0, 0, 5, 5);
        gbc_label_addItem_outprice.gridx = 1;
        gbc_label_addItem_outprice.gridy = 3;
        panel.add(label_addItem_outprice, gbc_label_addItem_outprice);
        // 商品售价的Textfield
        textField_addItem_outpriceDisplay = new JTextField();
        GridBagConstraints gbc_textField_addItem_outpriceDisplay = new GridBagConstraints();
        gbc_textField_addItem_outpriceDisplay.insets = new Insets(0, 0, 5, 0);
        gbc_textField_addItem_outpriceDisplay.fill = GridBagConstraints.BOTH;
        gbc_textField_addItem_outpriceDisplay.gridx = 2;
        gbc_textField_addItem_outpriceDisplay.gridy = 3;
        panel.add(textField_addItem_outpriceDisplay, gbc_textField_addItem_outpriceDisplay);
        textField_addItem_outpriceDisplay.setColumns(10);

        if (op != 0) {
            JLabel label_addItem_focusRepository = new JLabel(targetStockString);
            label_addItem_focusRepository.setFont(new Font("微软雅黑", Font.BOLD, 15));
            label_addItem_focusRepository.setHorizontalAlignment(SwingConstants.RIGHT);
            GridBagConstraints gbc_label_addItem_focusRepository = new GridBagConstraints();
            gbc_label_addItem_focusRepository.insets = new Insets(0, 0, 0, 5);
            gbc_label_addItem_focusRepository.gridx = 1;
            gbc_label_addItem_focusRepository.gridy = 4;
            panel.add(label_addItem_focusRepository, gbc_label_addItem_focusRepository);

            JComboBox comboBox__addItem_focusRepository = new JComboBox();
            GridBagConstraints gbc_comboBox__addItem_focusRepository = new GridBagConstraints();
            gbc_comboBox__addItem_focusRepository.fill = GridBagConstraints.HORIZONTAL;
            gbc_comboBox__addItem_focusRepository.gridx = 2;
            gbc_comboBox__addItem_focusRepository.gridy = 4;
            panel.add(comboBox__addItem_focusRepository, gbc_comboBox__addItem_focusRepository);
        }


        Box horizontalBox = Box.createHorizontalBox();
        panel_total.add(horizontalBox, BorderLayout.SOUTH);

        Component horizontalGlue_left = Box.createHorizontalGlue();
        horizontalBox.add(horizontalGlue_left);

        JButton button_addItem_confirm = new JButton(confirmString);
        button_addItem_confirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //todo
                dispose();
            }
        });
        horizontalBox.add(button_addItem_confirm);

        Component rigidArea = Box.createRigidArea(new Dimension(100, 80));
        horizontalBox.add(rigidArea);

        JButton button_addItem_cancel = new JButton(cancel);
        button_addItem_cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        horizontalBox.add(button_addItem_cancel);

        Component horizontalGlue_right = Box.createHorizontalGlue();
        horizontalBox.add(horizontalGlue_right);
    }

}
