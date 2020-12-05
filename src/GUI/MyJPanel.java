package GUI;

import java.awt.BorderLayout;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import javax.swing.Box;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class MyJPanel extends JPanel{

    private Box up = null;  // 上方的盒子

    private MyJPanel show = this;
    private JScrollPane ScrollPane;
    private JTable table;
    private DefaultTableModel model;
    private Vector<Object> name;

    private Box down = null;  // 下方的盒子

    // 以下为状态改变的部分
    private Set<Integer> change;  // 哪几行可改
    private int now;  // 当前状态
    private int next;  // 目的状态


    /**
     * 新建一个MyPanel，形式为：中间的table,下方的box,无上方的box
     * 当box为空时，不添加
     * 设置表格的表头，并将有关组件关联
     * @param name 表头信息
     */
    public MyJPanel(Vector<Object> name, int from, int to) {
        super();
        // 设置大小布局
        this.setSize(300, 150);
        this.setLayout(new BorderLayout(0, 0));

        // 添加中间的table
        // 添加ScrollPane
        this.ScrollPane = new JScrollPane();
        this.ScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        // 将滑动table放到panel中
        this.add(ScrollPane, BorderLayout.CENTER);
        // 新建tablemodel，并设置表名、内容，
        this.name = name;
        now = from;
        next = to;
        this.model = new DefaultTableModel_noEditable(new Vector<>(), this.name, 99);
        this.change = new HashSet<>();
        // 新建table并于tablemodel相关联
        this.table = new JTable();
        this.table.setModel(this.model);
        // 在ScollPane中显示Table
        this.ScrollPane.setViewportView(this.table);

    }



    /**
     * 返回表头
     * @return 返回表头
     */
    public Vector<Object> getTableName(){ return this.name; }

    /**
     * 返回状态转移
     * @return 第一项为当前状态，第二项为目标状态
     */
    public int[] getStateChange(){ return new int[]{now, next}; };

    /**
     * 返回tablemodel
     * @return tablemodel
     */
    public DefaultTableModel getModel() {
        return model;
    }

    /**
     * 返回标记的列
     * @return 标记的列
     * todo 防御拷贝
     */
    public Set<Integer> getChange(){ return this.change; }


    public String getNow() {
        return String.valueOf(now);
    }
    public String getNext() {
        return String.valueOf(next);
    }

    // 设置上下的box
    public void setUp(Box box){ up = box; this.add(box, BorderLayout.NORTH); }
    public void setDown(Box box) { down = box; this.add(box, BorderLayout.SOUTH); }

    /**
     * 设置table的数据，并重新展示关联
     * @param data 更新的数据
     */
    public void setData(Vector<Vector<Object>> data) {
        // 更新表格中的信息
        if (now == 0 && next == 0) this.model = new DefaultTableModel_noEditable(data, this.name, 99);
        else {
            this.model =new DefaultTableModel_noEditable(data, name, 3);
            this.table.setModel(this.model);
            TableColumn tcm = table.getColumnModel().getColumn(name.size()-1);
            tcm.setCellEditor(table.getDefaultEditor(Boolean.class));
            tcm.setCellRenderer(table.getDefaultRenderer(Boolean.class));
            model.addTableModelListener(new TableModelListener() {
                                            @Override
                                            public void tableChanged(TableModelEvent e) {
                                                if(e.getColumn()==name.size()-1){
                                                    Vector<Object> temp = (Vector) ((Vector<Object>)model.getDataVector()).get(e.getFirstRow());
                                                    if((Boolean) data.get(e.getFirstRow()).get(name.size()-1))
                                                        change.add(Integer.parseInt(temp.get(0).toString()));
                                                    else
                                                        change.remove(Integer.parseInt(temp.get(0).toString()));
                                                }
                                            } });
        }
        // 重新展示
        this.table.setModel(this.model);
        this.ScrollPane.setViewportView(this.table);
    }

}