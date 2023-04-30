package com.listener;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import com.Processor.MyProcessor;
import com.Processor.RunDraw;
import com.javaJF.DrawGraphics;
import com.javaJF.Graph_Draw;
import com.javaJF.Graph_Inf;


public class MyMouseListener implements MouseListener{
	
	private MyProcessor myProcessor = new MyProcessor();
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO 自动生成的方法存根
		
		//判断触发组件是否为按钮
		if(e.getSource() instanceof JButton) {
			
			// 获取按钮名字
			JButton button = (JButton) e.getSource();
	        String name = button.getText(); 
	        
	        //获取Graph_Inf面板用户输入图形的属性
	        String shape = (String)Graph_Inf.jComboBox.getSelectedItem();
	        String xi = Graph_Inf.x.getText().trim();
	        String yi = Graph_Inf.y.getText().trim();
	        String wi = Graph_Inf.w.getText().trim();
	        String hi = Graph_Inf.h.getText().trim();
	        int x = xi.matches("[0-9]+")?Integer.valueOf(xi):0;
	        int y = yi.matches("[0-9]+")?Integer.valueOf(yi):0;
	        int w = wi.matches("[0-9]+")?Integer.valueOf(wi):0;
	        int h = hi.matches("[0-9]+")?Integer.valueOf(hi):0;
	        
	        if(shape.equals("圆形")) 	//圆形的话只要一个半径足以，半径就看w吧
	        	h=w;
	        
        	//判断是哪个小按钮搞得动静
	        switch(name) {
	        case "清除信息":
	        	//将Graph_Inf面板的图形信息清空
	        	Graph_Inf.x.setText("");	
	        	Graph_Inf.y.setText("");
	        	Graph_Inf.w.setText("");
	        	Graph_Inf.h.setText("");
	        	break;
	        	
	        case "————-":
	        	//弹出画笔颜色选择器
	        	DrawGraphics.md.setVisible(true);
	        	break;
	        	
	        case "确认绘画":		
	        	//创建线程，开画
	        	RunDraw run = new RunDraw(x, y, w, h, shape);
	        	new Thread(run,"画图线程").start();
	        	break;
	        	
	        case "清空画板":		
	        	//只需调用Graph_Draw的repaint方法即可完成清屏功能（Graph_Draw是DrawGraphics主窗体的static属性）
	        	DrawGraphics.gd.repaint();
	        	break;
	        	
	        case "删除":
	        	//获取ID输入框的内容
	        	String id = Graph_Inf.jtf.getText().trim();
	        	if(id.matches("[0-9]+")) {
	        		//弹出确认删除对话框
	        		int answer = JOptionPane.showInternalConfirmDialog(null,  "真的要抛弃我么...", "删除操作",  
	        				JOptionPane.YES_NO_CANCEL_OPTION); 
	        		
	        		if(answer == 0) {	//选是为0，取消为1
	        			
	        			//调用处理类MyProcessor的deleteGrape方法根据id删除数据
	        			myProcessor.deleteGrape(Integer.valueOf(id));	
	        			//弹出成功提示框
	        			ImageIcon icon = new ImageIcon("src/myImage/pobi.png");
	        			JOptionPane.showMessageDialog(null, "  删除成功，不爱了","删除数据",JOptionPane.OK_CANCEL_OPTION ,icon);
	        		}
	        			
	        	}else {
	        		ImageIcon icon = new ImageIcon("src/myImage/pobi.png");	
	        		JOptionPane.showMessageDialog(null, "  数据有错，长点心！","删除操作",JOptionPane.OK_CANCEL_OPTION ,icon);
	        	}
	        	break;
	        	
	        case "修改":

	        	//获取ID输入框的内容
	        	String Id = Graph_Inf.jtf.getText().trim();
	        	
	        	//判断Id是否合法
	        	if(Id.matches("[0-9]+")) {
	        		int answer = JOptionPane.showConfirmDialog(null,  "是否焕然一新", "修改操作",  
	        				JOptionPane.YES_NO_CANCEL_OPTION); 
	        		if(answer == 0) {
	        			//调用处理类MyProcessor的updateGrape方法根据id修改数据
	        			myProcessor.updateGrape(Integer.valueOf(Graph_Inf.jtf.getText()),x,y,w,h,shape);
	        			//弹出成功提示框
	        			ImageIcon icon = new ImageIcon("src/myImage/pobi.png");
	        			JOptionPane.showMessageDialog(null, "  修改成功，很难不爱","修改数据",JOptionPane.OK_CANCEL_OPTION ,icon);
	        		
	        		}
	        		
	        	}else {
	        		//修改失败提醒
	        		ImageIcon icon = new ImageIcon("src/myImage/pobi.png");	
	        		JOptionPane.showMessageDialog(null, "  修改失败！","修改操作",JOptionPane.OK_CANCEL_OPTION ,icon);
	        	}
	        	break;
	        case "保存此图形":
	        	//最后是保存信息按钮，直接调用处理类MyProcessor的SQLSaveShape方法根据添加数据到MySQL
	        	boolean b = myProcessor.SQLSaveShape(x, y, w, h, shape);
	        	if(b) {
	        		ImageIcon icon = new ImageIcon("src/myImage/ggBoy.png");	
	        		JOptionPane.showMessageDialog(null, "  添加成功，有点帅！","保存信息",JOptionPane.OK_CANCEL_OPTION ,icon);
	        	}else {
	        		ImageIcon icon = new ImageIcon("src/myImage/xiaodd.png");	
	        		JOptionPane.showMessageDialog(null, "  手气不佳，操作失败","保存信息",JOptionPane.WARNING_MESSAGE ,icon);
	        	}
	        	break;
	        }
		}
	}

	
	/**
	 * 鼠标按下触发
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO 自动生成的方法存根

		if(e.getSource() instanceof JButton) {
			
			// 获取按钮名字
			JButton button = (JButton) e.getSource();
	        String name = button.getText(); 
	        
	        //判断是否是画笔颜色选择器的确认键
	        if("确定".equals(name)) {

	        	//获取输入的RGB字符串
	        	String rgb = DrawGraphics.md.jt.getText().trim();
	        	
	        	//字符串是否合格
	        	if(rgb.matches("[0-9]+,*，*[0-9]+,*，*[0-9]+")) {
	        		
	        		//MyProcessor处理字符串返回RGB数据数组
	        		int[] array = myProcessor.getColorRGB(rgb);
	        		
		        	//设置画笔颜色
	        		Color c = new Color(array[0],array[1],array[2]);
		    		Graph_Draw.gx.setColor(c);
		    		Graph_Inf.jB_brush.setBackground(c);
	        	}
	        }
	        
	        //关闭颜色选择器
	        DrawGraphics.md.setVisible(false);
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO 自动生成的方法存根
	}

}
