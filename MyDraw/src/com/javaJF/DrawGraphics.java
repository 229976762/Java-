package com.javaJF;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Toolkit;

import javax.swing.JFrame;

import com.pojo.MyGrape;

//主窗体
public class DrawGraphics extends JFrame{
	
	//定义窗口大小
	private final int width = 1200;
	private final int height = 1000;
	//获取屏幕大小
    private final int WINDOW_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width;
    private final int WINDOW_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height;
    
    //共享画板
	public static Graph_Draw gd;
	
	//画笔选择对话框
	public static MyBrush_Dialog md;
	
	
	public DrawGraphics() {
		Container c = getContentPane();
		setTitle("泰裤辣"); //标题
		setBounds((WINDOW_WIDTH-width)/2, (WINDOW_HEIGHT-height)/2, width, height);		//窗口位置
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		//窗口关闭即中断程序
		setLayout(new BorderLayout());
		setResizable(false);
		
		//图形展示区
		gd = new Graph_Draw();
		c.add(gd,BorderLayout.CENTER);
		
		//图形信息框
		Graph_Inf gi = new Graph_Inf();
		c.add(gi,BorderLayout.EAST);
		
	    
		//数据库信息框
		Graph_SQL gs = new Graph_SQL();
		c.add(gs,BorderLayout.SOUTH);
		
		//初始化颜色选择对话框
		md = new MyBrush_Dialog(DrawGraphics.this);
	}
}
