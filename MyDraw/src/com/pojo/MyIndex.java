package com.pojo;

//自定义坐标类
public class MyIndex{
	
	//表示的是横纵坐标
	private Integer arga;
    private Integer argb;

    public MyIndex(Integer arga, Integer argb) {
        this.arga = arga;
        this.argb = argb;
	}

	public Integer getArga() {
		return arga;
	}

	public void setArga(Integer arga) {
		this.arga = arga;
	}

	public Integer getArgb() {
		return argb;
	}

	public void setArgb(Integer argb) {
		this.argb = argb;
	}
    
    
}
