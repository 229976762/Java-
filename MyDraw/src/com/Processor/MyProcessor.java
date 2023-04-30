package com.Processor;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.javaJF.Graph_SQL;
import com.pojo.MyGrape;
import com.pojo.MyIndex;
import com.util.MySql;


//数据处理类
public class MyProcessor {
		
	private MySql mySql = new MySql();
	
	
	/*
 	 * 从数据库读取图形构造Shape
 	 */
	public List<MyGrape>  seleceAll() {
		return mySql.seleceAll();
	
	}
	
	
	
	/**
 	 * 保存图形信息到数据库
 	 */
	public boolean SQLSaveShape(int x, int y, int w, int h, String shape) {
		boolean b = mySql.SQLSaveShape(x, y, w, h, shape);
		
 		Graph_SQL.showShapes();	//数据回显
 		
 		return b;
	}
	
	
	/**
 	 * 查询操作
 	 */
 	public MyGrape selectGrape(Integer id) {
 		
 		return mySql.selectGrape(id);
 		
 	}
 	
 	
 	/**
 	 * 修改操作
 	 */
 	public boolean updateGrape(int id,int x, int y, int w, int h, String shape) {
 		
 		boolean b = mySql.updateGrape(id, x, y, w, h, shape);

 		Graph_SQL.showShapes();
 		
 		return b;
 	}
 	
 	
 	/**
 	 * 删除操作
 	 */
 	public boolean deleteGrape(Integer id) {
 		
 		boolean b = mySql.deleteGrape(id);
 		
 		Graph_SQL.showShapes();
 		
 		return b;
 		
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
					x_f=endBigger?x_f+1:x_f-1;
					if(endBigger)
						y_f=(y-y1)/(x-x1)+y_f;
					else
						y_f=(-1)*(y-y1)/(x-x1)+y_f;
				}else {
					y_f=endBigger?y_f+1:y_f-1;
					if(endBigger)
						x_f=(x-x1)/(y-y1)+x_f;
					else
						x_f=(-1)*(x-x1)/(y-y1)+x_f;
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
