package com.javaJF;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

//图形展示面板
public class Graph_Draw extends JPanel{
			
	public static Graphics2D gx;	//共享画笔
	
	public Graph_Draw() {
		// TODO 自动生成的构造函数存根
		setBorder(BorderFactory.createTitledBorder("Rapper"));	//设置面板边框
		setBackground(new Color(248,248,255));	//设置背景色
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);	
		gx=(Graphics2D) getGraphics();
		gx.setColor(Color.green);	//画笔颜色绿色
	} 
	
}
