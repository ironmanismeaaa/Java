package com.scnu.element;

import java.awt.Graphics;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
/**
 * @˵��	������
 * @author Lin
 * @����	����String proType����������ͣ�ȡֵ��Χ��{"hp+1","hp+2","hp-1","speed+1"}
 * Date createTime��������ߵ�ʱ�䣩
 * int duration�����߳���ʱ��Ĭ��5����ʧ��
 * @�ܼ� 3����������
 */
public class Prop extends ElementBase{
	private String propType = null;

	private int vx=0;
	/**
	 * @˵��	�չ��췽��
	 */
	public Prop() {}
	
	/**
	 * @˵��	���ƹ��췽��
	 * @param pp ��һ��Prop����
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
	 * @˵��	Ԫ�ش��췽��
	 * @param str �ַ���������ʽ��"x,y,proType,imagePath,w,h",
	 * proType����������ͣ�ȡֵ��Χ��{"hp+1","hp+2","hp-1","speed+1"}
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
	 * @˵��	��ʾ����
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
