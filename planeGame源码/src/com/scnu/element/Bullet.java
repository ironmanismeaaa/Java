package com.scnu.element;

import java.awt.Graphics;

import javax.swing.ImageIcon;

/**
 * @˵�� �ӵ��࣬�̳л���
 * @author Lin
 * @���� �̳л������ԣ�����3�����ԣ�int ATK ������ int vx ˮƽ�ٶ�ʸ�� int vy ��ֱ�ٶ�ʸ��
 */
public class Bullet extends ElementBase {
	private int ATK;
	private int vx;

	/**
	 * @˵�� �չ��췽��
	 */
	public Bullet() {
	}
	/**
	 * @˵�� �ӵ�����ʾ����
	 */
	@Override
	public void show(Graphics g) {
		g.drawImage(this.getImageIcon().getImage(), this.getX(), this.getY(), this.getW(), this.getH(), null);
	}

	/**
	 * @˵�� �ӵ��Ĵ�������
	 * @param str
	 *            �ַ���������ʽ��"x,y,ATK,imagepath,vx,vy,w,h"; ��ʡ��w,h, ��ȡͼƬĬ�ϴ�С
	 * @return �����ӵ�����
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
	 * @˵�� �ƶ�����
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
