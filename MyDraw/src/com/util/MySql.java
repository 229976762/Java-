package com.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

public class MySql {
	
	//定义接受信息的属性
    private static String driver;
    private static String url;
    private static String user;
    private static String password;
    
    
    /**
     * 使用静态代码块，在类加载时就读取配置文件信息，有且只执行一次
     */
    static {
       //使用资源绑定器获取配置文件，properties文件名为sqlConnection
    	try {
    	InputStream in = new FileInputStream("src/resource/sqlConnection.properties");
    	ResourceBundle bundle = new PropertyResourceBundle(in);
    	
    	driver=bundle.getString("driver");
        url=bundle.getString("url");
        user=bundle.getString("user");
        password=bundle.getString("password");
        
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
    }
     
    /**
     * 获取连接池Connection的方法
     * @return
     * @throws Exception
     */
     public static Connection getConnection() throws Exception {
    	 Class.forName(driver);//加载MySQL驱动
         Connection conn = DriverManager.getConnection(url, user, password);
         return conn;
     }



}
