package com.javaJF;

import java.awt.Color;
import java.awt.Font;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.Processor.MyProcessor;
import com.pojo.MyGrape;

public class Graph_SQL extends JPanel {

	//数据库信息文本形式展示区
	public static JTextArea jTextArea = new JTextArea(6,145);

	public Graph_SQL() {
		
		//面板排版
		setBorder(BorderFactory.createTitledBorder("跳"));	//设置边框，纯属爱好
		JScrollPane jp = new JScrollPane();					//滚动面板，搭配文本区一起使用
			
		//美化文本展示区
		jTextArea.setFont(new Font("楷体", Font.BOLD, 16));
		jTextArea.setBackground(Color.LIGHT_GRAY);
		/**
		 * 注意：下面这句设置面板可见，否则不会展示出来
		 */
		jp.setViewportView(jTextArea);	
		this.add(jp);
				
		showShapes();	//首次创建展示MySQL信息，方体具体实现看下面
	}
	
	/**
	 * MySQL数据展示
	 */
	public static void showShapes() {
		
		jTextArea.setEditable(true);	//文本区设置为可编辑
		jTextArea.setText(null);		//清空文本，我要写东西
		
		//自定义处理类MyProcessor的seleceAll查询所有，得到List<MyGrape>集合
		for (MyGrape myGraph : new MyProcessor().seleceAll()) 	
			jTextArea.append(myGraph.toString()+"\r\n");		//文本区添加自定义图形类MyGrape的属性
		
		
		jTextArea.setEditable(false);	//文本区设置只读	（正宗关门打狗棍法）
	}
	
}
