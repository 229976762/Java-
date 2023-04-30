package com.javaJF;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.listener.MyMouseListener;

//自定义画笔颜色选择框
public class MyBrush_Dialog extends JDialog{

	public JTextField jt = new JTextField("",12);	//RGB数据输入框
	
	public MyBrush_Dialog(DrawGraphics fr) {
		//对话框排版
		super(fr,"画笔颜色",true);	//阻塞主窗体
		this.setSize(250,150);		//对话框大小
		this.setLocationRelativeTo(null);	//居中展示
		
		Container c = getContentPane();		//对话框的主容器
		c.setLayout(new BorderLayout());
		JPanel p1 = new JPanel(new FlowLayout(FlowLayout.CENTER,10,10));	//RGB信息收集面板
		JLabel jl = new JLabel("RGB(255,255,255)");
		jl.setFont(new Font("楷体",  1,  13));
		p1.add(jl);
		p1.add(jt);
		
		
		JPanel p2 = new JPanel();		//确定、取消按钮面板
		JButton jb1 = new JButton("确定");
		JButton jb2 = new JButton("取消");
		jb1.addMouseListener(new MyMouseListener());
		jb2.addMouseListener(new MyMouseListener());
		p2.add(jb1,BorderLayout.CENTER);
		p2.add(new JLabel("      ")); //仅此排版美化
		p2.add(jb2,BorderLayout.CENTER);
		
		c.add(p1,BorderLayout.CENTER);	//RGB信息收集面板
		c.add(p2,BorderLayout.SOUTH);	//确定、取消按钮面板
		
	}
}
