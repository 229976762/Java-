package com.Processor;

import java.awt.Color;
import java.util.List;

import com.javaJF.Graph_Draw;
import com.pojo.MyIndex;

public class RunDraw implements Runnable{

	private int x0;
	private int y0;
	private int x1;
	private int y1;
	private String name;
	private boolean xIsMax = false;
	private boolean endBigger = false;
	private MyProcessor myProcessor = new MyProcessor();
	
	
	public RunDraw( int x0, int y0, int x1, int y1, String name) {
		super();
		this.x0 = x0;
		this.y0 = y0;
		this.x1 = x1;
		this.y1 = y1;
		this.name = name;
	}

	@Override
	public void run() {
		// TODO 自动生成的方法存根
	
		//算法部分，就是计算直线从哪边开始画才合适
		if(name.equals("直线")) {
			
			//x坐标的差值若比y的大，标记变量xIsMax为true，表示x做变量
			xIsMax = Math.abs(x0-x1) > Math.abs(y0-y1)?true:false;	
			//变量坐标的终点值若比起点大，标记变量endBigger为true，表示x做正增长
			endBigger = xIsMax?(x0-x1) < 0:(y0-y1) < 0;
		}
			
		//调用处理类MyProcessor计算坐标方法得到图形内部全部坐标list，然后 休眠+绘图 即可实现我们想要的效果
		List<MyIndex> list = myProcessor.index(name ,x0 ,y0 ,x1 ,y1, xIsMax, endBigger);
		
		
		//遍历list中所有点坐标
		for (MyIndex mi : list) {			
			try {
				Thread.sleep(5);
				
				if(name.equals("圆形")) {
					//圆形的话就画线条吧，你也可以自己定义，你喜欢
					Graph_Draw.gx.drawLine(mi.getArga(), mi.getArgb(), x0, y0);
				}else{
					//矩形和直线选择画点，自认定把fillRect方法宽高设小就是点，不要争....
					Graph_Draw.gx.fillRect(mi.getArga(), mi.getArgb(), 5, 5);
				}

			} catch (InterruptedException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
	}
}
