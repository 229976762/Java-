package com.listener;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JTextField;

import com.Processor.MyProcessor;
import com.javaJF.Graph_Inf;
import com.pojo.MyGrape;

public class MyKeyListener implements KeyListener{

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO 自动生成的方法存根
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	/**
	 * 键盘释放，图形ID输入框有内容
	 */
	public void keyReleased(KeyEvent e) {
		// TODO 自动生成的方法存根
				
		
		String id = Graph_Inf.jtf.getText().trim();		//获取图形ID输入框内容
		
		//判断触发组件类型 && 输入框内容是否合格(即有一个或多个数字)
		if(e.getSource() instanceof JTextField && id.matches("[0-9]+")) {
			
			MyGrape myGraph = new MyProcessor().selectGrape((Integer.valueOf(id)));	//根据id查询数据库图形信息
			
			if(myGraph == null)		//myGraph为null，数据库‘妹’找到，难办那就别办了！
				return;
			
			//图形信息回显到Graph_Inf面板，下面组件可以回去看看是神马...
			Graph_Inf.jComboBox.setSelectedItem(myGraph.getShape());
			Graph_Inf.x.setText(String.valueOf(myGraph.getX()));
			Graph_Inf.y.setText(String.valueOf(myGraph.getY()));
			Graph_Inf.w.setText(String.valueOf(myGraph.getWidth()));
			Graph_Inf.h.setText(String.valueOf(myGraph.getHeight()));
			
			Graph_Inf.jB_N.setText("修改"); 		//因为操作的是数据库当中的图形，“清除信息”按钮无用处，改了内容重用，避免再添按钮
			Graph_Inf.jB_save.setText("删除");	//与上同理
		}else {
			Graph_Inf.jB_N.setText("清除信息");		//输入框内容为""或者不合法，转成原来功能按钮
			Graph_Inf.jB_save.setText("保存此图形");
		}
	}

}
