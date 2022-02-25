package com.scnu.element;

import java.awt.Graphics;
import javax.swing.ImageIcon;

/**
 * @˵�� ������
 * @author Lin
 * @���� �̳л������ԣ�����������
 *
 */
public class Background extends ElementBase {

	/**
	 * @˵�� �չ��췽��
	 */
	public Background() {
	}

	/**
	 * @˵�� ��ʾ����
	 */
	@Override
	public void show(Graphics g) {
		g.drawImage(this.getImageIcon().getImage(), this.getX(), this.getY(), this.getW(), this.getH(), null);
	}

	/**
	 * @˵�� ���췽��
	 * @param str
	 *            �ַ���������ʽ��"imagePath,w,h"����ʡ��w,h����Ĭ��ΪͼƬ�����
	 * @���� loader�����ȡ�����ļ�������������������
	 */
	@Override
	public ElementBase createElement(String str) {
		this.setX(0);
		this.setY(0);
		String[] arr = str.split(",");
		this.setImageIcon(new ImageIcon(arr[0]));
		if (arr.length == 3) {
			this.setW(Integer.parseInt(arr[1]));
			this.setH(Integer.parseInt(arr[2]));
		} else {
			this.setW(this.getImageIcon().getIconWidth());
			this.setH(this.getImageIcon().getIconHeight());
		}
		return this;
	}

}
