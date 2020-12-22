package op;
import java.sql.*;
import java.util.ResourceBundle;
import java.util.Vector;
import Bean.DBBean;

public class returnVector {
    /**
     *读取数据库所有数据
     * @param db    要连接的数据库
     * @param tablename 数据库的表名
     * @param vector    表的所有列的字段名
     * @return   返回一个Vector存储所有的数据库的数据
     */
    public static Vector<Vector<Object>> FromDBReadAll(DBBean db, String tablename, Vector<Object> vector){
        Vector<Vector<Object>> res = new Vector<Vector<Object>>();
        ResultSet temp = db.executeFindAll(tablename);
        while (true) {
            try {
                if (!temp.next()) break;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            Vector<Object> v = new Vector<Object>();
            // 读表头信息
            for(int i=0;i<vector.size();i++){
                try {
                    if (String.valueOf(vector.get(i)).equals("State") && tablename.equals("ordermanager")){
                        v.add(op.StateConvert(temp.getString((String)vector.get(i))));
                    }
                    else v.add(temp.getString((String)vector.get(i)));
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            res.add(v);
        }
        return res;
    }

    /**
     * 读取数据库查询的数据
     * @param db    要连接的数据库
     * @param tablename 数据库的表名
     * @param vector    表的所有列的字段名
     * @param value    想要查询的值
     * @param index    想要查询数据库的表的哪一列的名字
     * @return   返回查询到的所有行
     */
    public static Vector<Vector<Object>> FromDBRead(DBBean db, String tablename, Vector<Object> vector,String value,String index){
        Vector<Vector<Object>> res = new Vector<Vector<Object>>();
        ResultSet temp = db.executeFind(value,tablename,index);
        while (true) {
            try {
                if (!temp.next()) break;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            Vector<Object> v = new Vector<Object>();
            // 读表头信息
            for(int i=0;i<vector.size();i++){
                try {
                    if (String.valueOf(vector.get(i)).equals("State") && tablename.equals("ordermanager")){
                        v.add(op.StateConvert(temp.getString((String)vector.get(i))));
                    }
                    else v.add(temp.getString((String)vector.get(i)));
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            res.add(v);
        }
        return res;
    }


    /**
     *
     * @param db    要连接的数据库
     * @param tablename 数据库的表名字
     * @return      返回表的所有列的字段名
     * @throws SQLException
     */
    public static Vector<Object> getHeadName(DBBean db, String tablename){
        ResultSet temp1=db.executeTablehead(tablename);
        Vector<Object> v1 = new Vector<Object>();
        while (true) {
            try {
                if (!temp1.next()) break;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            // 读表头信息
            try {
                v1.add(temp1.getString(1));
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return v1;
    }

    /**
     * 将表头名字转换为不同的语言版本
     * @param name    想要转换的表头名字
     * @param resourceBundle      想要 转换的语言
     * @return   返回一个转换完语言的表头
     * eg:result=returnVector.convertHeadNameForDifferentLanguage(new Vector<>(Arrays.asList("id","name","num"))
     * ,language_convert.language_convertEnglish());
     */
    public static Vector<Object> convertHeadNameForDifferentLanguage(Vector<Object> name, ResourceBundle resourceBundle){
        Vector<Object> result=new Vector<>();
        for(int i=0;i<name.size();i++){
            result.add(resourceBundle.getString(String.valueOf(name.get(i))));
        }
        return result;
    }


    /**
     * 这个类 用来将数据库查询的结果转成Vector
     * ResultSet -> Vector
     * 二维Vector里面存的都是 String
     */
    public static Vector ResultSetToVector(ResultSet set) {
        Vector vec = new Vector();
        try {
            while(set.next()){
                Vector temp = new Vector();
                for (int i=0; i<set.getFetchSize(); i++) temp.add(set.getString(i));
                vec.add(temp);
            }
        }catch (SQLException e) { e.printStackTrace(); }
        return vec;
    }

}
