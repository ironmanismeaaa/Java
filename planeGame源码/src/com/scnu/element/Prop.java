package com.scnu.element;

import java.awt.Graphics;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
/**
 * @说明	道具类
 * @author Lin
 * @属性	增加String proType代表道具类型，取值范围是{"hp+1","hp+2","hp-1","speed+1"}
 * Date createTime（创造道具的时间）
 * int duration（道具持续时间默认5秒消失）
 * @总计 3个新增属性
 */
public class Prop extends ElementBase{
	private String propType = null;

	private int vx=0;
	/**
	 * @说明	空构造方法
	 */
	public Prop() {}
	
	/**
	 * @说明	复制构造方法
	 * @param pp 另一个Prop对象
	 */
	public Prop(Prop pp){
		this.setX(pp.getX());
		this.setY(pp.getY());
		this.setH(pp.getH());
		this.setW(pp.getW());
		this.setImageIcon(pp.getImageIcon());
		this.setPropType(pp.getPropType());
	}
	@Override
	public void move() {
		this.setX(this.getX() + vx);
		this.setY(this.getY() + this.getVy());
		if (this.getY() >= 1020) {
			this.setLive(false);
			return;
		}
	}
	/**
	 * @说明	元素创造方法
	 * @param str 字符串参数格式："x,y,proType,imagePath,w,h",
	 * proType代表道具类型，取值范围是{"hp+1","hp+2","hp-1","speed+1"}
	 */
	@Override
	public ElementBase createElement(String str) {
		String[] arr = str.split(",");
		this.setX(Integer.parseInt(arr[0]));
		this.setY(Integer.parseInt(arr[1]));
		this.propType = arr[2];
		this.setVy(Integer.parseInt(arr[3]));
		this.setImageIcon(new ImageIcon(arr[4]));
		if(arr.length == 7){
			this.setW(Integer.parseInt(arr[5]));
			this.setH(Integer.parseInt(arr[6]));
		}
		else{
			this.setW(this.getImageIcon().getIconWidth());
			this.setH(this.getImageIcon().getIconHeight());
		}
		return this;
	}

	/**
	 * @说明	显示方法
	 */
	@Override
	public void show(Graphics g) {
		g.drawImage(this.getImageIcon().getImage(), this.getX(), this.getY(), 
				this.getW(), this.getH(), null);
	}

	public String getPropType() {
		return propType;
	}

	public void setPropType(String propType) {
		this.propType = propType;
	}


	
	
}
