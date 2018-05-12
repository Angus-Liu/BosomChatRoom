package server.dao;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * 数据库操作工具类
 *
 * @author dyleaf
 */
public class DBUtils {

    //数据库连接地址
    private static String URL;
    //用户名
    private static String USERNAME;
    //密码
    private static String PASSWORD;
    //mysql的驱动类
    private static String DRIVER;
    //获取配置信息的内容
    private static Properties properties;

    private static Logger logger = Logger.getLogger(DBUtils.class);

    private DBUtils() {
    }

    //使用静态块加载驱动程序
    static {
        try {
            InputStream input = Class.forName(DBUtils.class.getName()).getResourceAsStream("db-config.properties");
            properties = new Properties();
            properties.load(input);
            URL = properties.getProperty("jdbc.url");
            USERNAME = properties.getProperty("jdbc.username");
            PASSWORD = properties.getProperty("jdbc.password");
            DRIVER = properties.getProperty("jdbc.driver");
            Class.forName(DRIVER);

        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }

    //定义一个获取数据库连接的方法
    static Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    /**
     * 关闭数据库连接
     *
     * @param rs
     * @param stat
     * @param conn
     */
    static void close(ResultSet rs, Statement stat, Connection conn) {
        try {
            if (rs != null) rs.close();
            if (stat != null) stat.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
