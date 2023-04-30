package com.Processor;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.javaJF.Graph_SQL;
import com.pojo.MyGrape;
import com.pojo.MyIndex;
import com.util.MySql;


//数据处理类
public class MyProcessor {
		
	
	/*
	 * 从数据库读取图形构造Shape
	 */
	public List<MyGrape>  seleceAll() {
		List<MyGrape> shapeList = new ArrayList<>();
		try {
			//自定义数据库连接类MySql获取连接池Connection
			Connection conn = MySql.getConnection();
			
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
			Connection conn = MySql.getConnection();
									
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
	        
	        Graph_SQL.showShapes();
	        
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
			Connection conn = MySql.getConnection();
			
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
			Connection conn = MySql.getConnection();
			
			String sql = "update tb_shape set shape=?,X=?,Y=?,WIDTH=?,HIEGHT=? where id=?";
	        PreparedStatement ps = conn.prepareStatement(sql);
	        ps.setString(1,shape);
	        ps.setInt(2,x);
	        ps.setInt(3,y);
	        ps.setInt(4,w);
	        ps.setInt(5,h);
	        ps.setInt(6,id);
	        ps.executeUpdate();
	        
	        Graph_SQL.showShapes();
	        
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
			Connection conn = MySql.getConnection();
			
			String sql = "delete from tb_shape where id=?";
	        PreparedStatement ps = conn.prepareStatement(sql);
	        ps.setInt(1,id);
	        ps.executeUpdate();
	        
	        Graph_SQL.showShapes();
	        
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
	 * 根据图形坐标计算函数
	 * 
	 * 因为是要按点绘图，所以需要图形内的所有点坐标，配合线程实现慢动作画图功能
	 * 下面算法是计算点坐标集合的，算法不是很好，凑合着看吧
	 */
	public List<MyIndex> index(String name, int x, int y, int x1, int y1,boolean xIsMax, boolean endBigger) {
		
		//创建自定义坐标类MyIndex集合list，用于存放所有的点坐标
		List<MyIndex> list = new ArrayList<>();
		int x_f = x;
		int y_f = y;
		

		if(name.equals("圆形")) {
			
			double i=0;
			while(i<360.0) {
				double c = Math.toRadians(i);
		        double sin = Math.sin(c);
		        double cos = Math.cos(c);
		        
		        double sx = cos*x1+x;
		        double sy = sin*x1+y;
		        
		        list.add(new MyIndex((int)sx, (int)sy));
		        
		        i ++;
			}
			
		}else if(name.equals("矩形")) {
			
			for(y_f = y;y_f<=y1;y_f=y_f+3) 
				for(x_f = x;x_f<=x1;x_f=x_f+3) 
					list.add(new MyIndex(x_f, y_f));

		}else {
			while(x_f != x1 || y_f != y1) {
				
				list.add(new MyIndex(x_f, y_f));
				
				if(xIsMax) {
					x_f=endBigger?x_f+2:x_f-2;
					if(endBigger)
						y_f=2*(y-y1)/(x-x1)+y_f;
					else
						y_f=(-2)*(y-y1)/(x-x1)+y_f;
				}else {
					y_f=endBigger?y_f+2:y_f-2;
					if(endBigger)
						x_f=2*(x-x1)/(y-y1)+x_f;
					else
						x_f=(-2)*(x-x1)/(y-y1)+x_f;
				}
			}
			list.add(new MyIndex(x_f, y_f));
		}

		return list;
	}
	
	
	/**
	 * 画笔颜色RGB数据转换
	 */
	public int[] getColorRGB(String rgb) {
		
		rgb = rgb.replaceAll("，", ",");
		
		String[] str = rgb.split(",");
		int[] array = Arrays.stream(str).mapToInt(Integer::parseInt).toArray();
		
		return array;
	}
	
}
