package com.scnu.element;

import java.awt.Graphics;

import javax.swing.ImageIcon;

/**
 * @说明 子弹类，继承基类
 * @author Lin
 * @属性 继承基类属性，增加3个属性：int ATK 攻击力 int vx 水平速度矢量 int vy 竖直速度矢量
 */
public class Bullet extends ElementBase {
	private int ATK;
	private int vx;

	/**
	 * @说明 空构造方法
	 */
	public Bullet() {
	}
	/**
	 * @说明 子弹的显示方法
	 */
	@Override
	public void show(Graphics g) {
		g.drawImage(this.getImageIcon().getImage(), this.getX(), this.getY(), this.getW(), this.getH(), null);
	}

	/**
	 * @说明 子弹的创建方法
	 * @param str
	 *            字符串参数格式："x,y,ATK,imagepath,vx,vy,w,h"; 若省略w,h, 则取图片默认大小
	 * @return 返回子弹对象
	 */
	@Override
	public ElementBase createElement(String str) {
		String[] parm = str.split(",");
		this.setX(Integer.parseInt(parm[0]));
		this.setY(Integer.parseInt(parm[1]));
		this.ATK = Integer.parseInt(parm[2]);
		this.setImageIcon(new ImageIcon(parm[3]));
		this.vx = Integer.parseInt(parm[4]);
		this.setVy(Integer.parseInt(parm[5]));
		if (parm.length == 8) {
			this.setW(Integer.parseInt(parm[6]));
			this.setH(Integer.parseInt(parm[7]));
		} else {
			this.setW(this.getImageIcon().getIconWidth());
			this.setH(this.getImageIcon().getIconHeight());
		}
		return this;
	}

	/**
	 * @说明 移动方法
	 */
	@Override
	public void move() {
		this.setX(this.getX() + vx);
		this.setY(this.getY() + this.getVy());
		if(this.getY()<=0 || this.getY() >= 1020){
			this.setLive(false);
		}
	}

	public int getATK() {
		return ATK;
	}

	public void setATK(int aTK) {
		ATK = aTK;
	}

	public int getVx() {
		return vx;
	}

	public void setVx(int vx) {
		this.vx = vx;
	}

}
