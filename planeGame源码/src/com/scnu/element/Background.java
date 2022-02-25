package com.scnu.element;

import java.awt.Graphics;
import javax.swing.ImageIcon;

/**
 * @说明 背景类
 * @author Lin
 * @属性 继承基类属性，无新增属性
 *
 */
public class Background extends ElementBase {

	/**
	 * @说明 空构造方法
	 */
	public Background() {
	}

	/**
	 * @说明 显示方法
	 */
	@Override
	public void show(Graphics g) {
		g.drawImage(this.getImageIcon().getImage(), this.getX(), this.getY(), this.getW(), this.getH(), null);
	}

	/**
	 * @说明 创造方法
	 * @param str
	 *            字符串参数格式："imagePath,w,h"，若省略w,h，则默认为图片宽与高
	 * @其他 loader负责读取配置文件并传参数到本函数中
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
