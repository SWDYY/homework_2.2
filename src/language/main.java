package language;

import java.util.Locale;
import java.util.ResourceBundle;

public class main {
    //所以文件中的所有字符都必须是ASCII字码，属性(properties)文件是不能保存中文的，对于像中文这样的非ACSII字符，须先进行编码。
    public static void main(String[] args) {
        //资源包基名(包名+myproperties)  路径
        //        config.properties它跟普通java类的命名规则完全一样：
//        - 文件名和包名是区分大小写
//        - 扩展名 .properties 省略
//        - 资源文件必须位于classpath下，如果在某个包下成在定义fileName时要加上包的名称，且包名区分大小写
//        如果是Web项目，不写包路径可以，此时将资源文件放在WEB-INF\classes\目录下就可以。
        String basename_English =  "language_resource.my_English" ;
        String basename_Chinese =  "language_resource.my_Chinese" ;
        //设置语言环境
        Locale cn = Locale.CHINA; //中文
        Locale us = Locale.US; //英文
        //根据基名和语言环境加载对应的语言资源文件
        //该方法用于装载资源文件，并创建ResourceBundle实例：basename为资源包基名(且必须为完整路径)。　
        ResourceBundle myResourcesCN = ResourceBundle.getBundle(basename_Chinese,cn); //加载myproperties_zh.properties
        ResourceBundle myResourcesUS = ResourceBundle.getBundle(basename_English,us); //加载myproperties_en.properties

        //加载资源文件后， 程序就可以调用ResourceBundle实例对象的 getString方法获取指定的资源信息名称所对应的值。
        String usernameCN = myResourcesCN.getString( "panel_customer" );
        String passwordCN = myResourcesCN.getString( "myJPanel_product" );

        String usernameUS = myResourcesUS.getString( "panel_customer" );
        String passwordUS = myResourcesUS.getString( "myJPanel_product" );

        System.out.println(usernameCN+ "--" +passwordCN);
        System.out.println(usernameUS+ "--" +passwordUS);
    }
}
