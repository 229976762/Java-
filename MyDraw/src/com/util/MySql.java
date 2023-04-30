package com.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import com.javaJF.Graph_SQL;
import com.pojo.MyGrape;

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
     public Connection getConnection() throws Exception {
    	 Class.forName(driver);//加载MySQL驱动
         Connection conn = DriverManager.getConnection(url, user, password);
         return conn;
     }


     /*
 	 * 从数据库读取图形构造Shape
 	 */
 	public List<MyGrape>  seleceAll() {
 		List<MyGrape> shapeList = new ArrayList<>();
 		try {
 			//自定义数据库连接类MySql获取连接池Connection
 			Connection conn = getConnection();
 			
 			String sql = "select * from tb_shape";	
 	        PreparedStatement ps = conn.prepareStatement(sql);	//sql预编译，下面所有方法同理
 	        
 			ResultSet rs = ps.executeQuery();//查询结果集
 			while(rs.next()){
 				int id = rs.getInt("id");
 		        String shape = rs.getString("shape");
 		        int X = rs.getInt("X");
 		        int Y = rs.getInt("Y");
 		        int WIDTH = rs.getInt("WIDTH");
 		        int HIEGHT = rs.getInt("HIEGHT");
 		        
 		        //封装对象
 		        shapeList.add(new MyGrape(id,X,Y,WIDTH,HIEGHT,shape));
 		    }
 			
 			//一定要记得关闭连接，养成好习惯
 			ps.close();
 			conn.close();
 		} catch (Exception e) {
 			// TODO 自动生成的 catch 块
 			e.printStackTrace();
 		}
 		
 		return shapeList;
 	}
 	
 	
 	/**
 	 * 保存图形信息到数据库
 	 */
 	public boolean SQLSaveShape(int x, int y, int w, int h, String shape) {
 		try {
 			Connection conn = getConnection();
 									
 			String al = "ALTER TABLE `tb_shape` AUTO_INCREMENT =1"; //保证数据插入时id是跟上一条数据是连续递增的
 			PreparedStatement ts = conn.prepareStatement(al);
 			ts.execute();
 			
 			String sql = "insert into tb_shape values(null,?,?,?,?,?)";
 	        PreparedStatement ps = conn.prepareStatement(sql);
 	        ps.setString(1,shape);
 	        ps.setInt(2,x);
 	        ps.setInt(3,y);
 	        ps.setInt(4,w);
 	        ps.setInt(5,h);
 	        ps.executeUpdate();
 	         	        
 	        ts.close();
 	        ps.close();
 			conn.close();
 	        return true;
 		} catch (Exception e) {
 			// TODO 自动生成的 catch 块
 			e.printStackTrace();
 			return false;
 		}
 		
 	}
 	
 	
 	/**
 	 * 查询操作
 	 */
 	public MyGrape selectGrape(Integer id) {
 		try {
 			Connection conn = getConnection();
 			
 			String sql = "select * from tb_shape where id = ?";
 	        PreparedStatement ps = conn.prepareStatement(sql);
 	        ps.setInt(1,id);
 	        
 			ResultSet rs = ps.executeQuery();
 			if(rs.next()){
 		        String shape = rs.getString("shape");
 		        int X = rs.getInt("X");
 		        int Y = rs.getInt("Y");
 		        int WIDTH = rs.getInt("WIDTH");
 		        int HIEGHT = rs.getInt("HIEGHT");
 		        
 		        return new MyGrape(id,X,Y,WIDTH,HIEGHT,shape);
 		    }
 			
 			ps.close();
 			conn.close();
 		} catch (Exception e) {
 			// TODO 自动生成的 catch 块
 			e.printStackTrace();
 		}
 		return null;
 	}
 	
 	
 	/**
 	 * 修改操作
 	 */
 	public boolean updateGrape(int id,int x, int y, int w, int h, String shape) {
 		try {
 			Connection conn = getConnection();
 			
 			String sql = "update tb_shape set shape=?,X=?,Y=?,WIDTH=?,HIEGHT=? where id=?";
 	        PreparedStatement ps = conn.prepareStatement(sql);
 	        ps.setString(1,shape);
 	        ps.setInt(2,x);
 	        ps.setInt(3,y);
 	        ps.setInt(4,w);
 	        ps.setInt(5,h);
 	        ps.setInt(6,id);
 	        ps.executeUpdate();
 	        
 	        ps.close();
 			conn.close();
 	        return true;
 		} catch (Exception e) {
 			// TODO 自动生成的 catch 块
 			e.printStackTrace();
 		}
 		return false;

 	}
 	
 	/**
 	 * 删除操作
 	 */
 	public boolean deleteGrape(Integer id) {
 		try {
 			Connection conn = getConnection();
 			
 			String sql = "delete from tb_shape where id=?";
 	        PreparedStatement ps = conn.prepareStatement(sql);
 	        ps.setInt(1,id);
 	        ps.executeUpdate();
 	        
 	        
 	        ps.close();
 			conn.close();
 	        return true;
 		} catch (Exception e) {
 			// TODO 自动生成的 catch 块
 			e.printStackTrace();
 		}
 		return false;

 	}

}
