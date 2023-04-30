package com.javaJF;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.listener.MyKeyListener;
import com.listener.MyMouseListener;

public class Graph_Inf extends JPanel{

    //图形信息框组件
    public static JComboBox<String> jComboBox;				//图形类型选择
	public static JTextField x = new JTextField("X",10);	//x坐标
	public static JTextField y = new JTextField("Y",10);	//y坐标
	public static JTextField w = new JTextField("W",10);	//w
	public static JTextField h = new JTextField("H",10);	//h
	public static JButton jB_N = new JButton("清除信息");	//清除按钮
	public static JButton jB_Y = new JButton("确认绘画");	//绘画按钮
	public static JButton jB_clear = new JButton("清空画板"); //清屏按钮
	public static JButton jB_save = new JButton("保存此图形");//保存图形至数据库按钮
	public static JButton jB_brush = new JButton("————-"); //画笔颜色选择按钮
	public static JTextField jtf = new JTextField();		 //图形编号输入框
	

	public Graph_Inf() {
				
		//初始化图形类型选择框
		String[] str = {"圆形","矩形","直线"};
		jComboBox = new JComboBox<>(str);

		//面板排版设置
		setLayout(new GridLayout(17,1,5,5));
		setBorder(BorderFactory.createTitledBorder("唱"));	//设置边框，纯属爱好
		this.add(jComboBox);
		this.add(x);
		this.add(y);
		this.add(w);
		this.add(h);
		
		
		this.add(jB_N);
		jB_N.setBorder(BorderFactory.createRaisedBevelBorder());
		jB_N.setFont(new Font("楷体",  1,  15));
		jB_N.setBackground(Color.yellow);
		jB_N.addMouseListener(new MyMouseListener());	//鼠标点击监听
		
		
		this.add(jB_Y);
		jB_Y.setBorder(BorderFactory.createRaisedBevelBorder());
		jB_Y .setFont(new Font("楷体",  1,  15));
		jB_Y.setBackground(Color.CYAN);
		jB_Y.addMouseListener(new MyMouseListener());	//鼠标点击监听
		
		this.add(jB_clear);
		jB_clear.setBorder(BorderFactory.createRaisedBevelBorder());
		jB_clear.setFont(new Font("楷体",  1,  15));
		jB_clear.setBackground(Color.pink);
		jB_clear.addMouseListener(new MyMouseListener());	//鼠标点击监听
	
		this.add(jB_save);
		jB_save.setBorder(BorderFactory.createRaisedBevelBorder());
		jB_save.setFont(new Font("楷体",  1,  15));
		jB_save.setBackground(Color.ORANGE);
		jB_save.addMouseListener(new MyMouseListener());	//鼠标点击监听
		
		//加了点空行只为好看
		int i=2;
		while(i>0) {
			this.add(new JLabel());
			i--;
		}
		
		JLabel jl = new JLabel("画笔颜色:");
		jl.setFont(new Font("宋体", Font.CENTER_BASELINE, 16));
		this.add(jl);
		this.add(jB_brush);
		jB_brush.setBorder(BorderFactory.createRaisedBevelBorder());
		jB_brush.setFont(new Font("楷体",  1,  15));
		jB_brush.setBackground(Color.red);
		jB_brush.addMouseListener(new MyMouseListener());	//鼠标点击监听
		
		//加了点空行只为好看
		i=2;
		while(i>0) {
			this.add(new JLabel());
			i--;
		}
			
		
		JLabel jt = new JLabel("输入图形ID:");
		jt.setFont(new Font("宋体", Font.CENTER_BASELINE, 16));
		this.add(jt);
		this.add(jtf);
		jtf.setHorizontalAlignment(JTextField.CENTER);
		jtf.setFont(new Font("楷体",  1,  20));
		jtf.addKeyListener(new MyKeyListener());	//键盘输入监听
	}
	
}
