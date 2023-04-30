package com.pojo;


//图形类
public class MyGrape {
	
	/*
	 * 自定义图形的属性，暂时只支持圆形、矩形、直线，小开发大乐趣
	 * id编号
	 * 图形名称shape
	 * x坐标
	 * y坐标
	 * 宽width
	 * 高height
	 */
	private int id;
	private String shape;
	private int x;
	private int y;
	private int width;
	private int height;
	
	
	public MyGrape(int id,int x, int y, int width, int height, String shape) {
		super();
		this.id = id;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.shape = shape;
	}
	 
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public String getShape() {
		return shape;
	}
	public void setShape(String shape) {
		this.shape = shape;
	}

	@Override
	public String toString() {
		return "MyGraph [id=" + id + ", shape=" + shape + ", x=" + x + ", y=" + y + ", width=" + width + ", height=" + height
				+ "]";
	}



}
