package com.scnu.element;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;

/**
 * @说明 所有元素的抽象基类
 * @author Lin
 * @属性 x坐标,y坐标,w宽度,h高度,imageIcon图像,live生存状态 , 总共6个属性
 */
public abstract class ElementBase {
	private int x;
	private int y;
	private int w;
	private int h;
	private int vy;
	private ImageIcon imageIcon;
	private boolean live = true;

	public ElementBase(){
		
	}
	
	public ElementBase(int x, int y, int w, int h,int vy, ImageIcon icon){
		super();
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.vy=vy;
		this.imageIcon = icon;
	}
	
	public int getVy() {
		return vy;
	}

	public void setVy(int vy) {
		this.vy = vy;
	}

	/**
	 * @说明 封装的构造方法，通过指定字符串格式传参构造元素
	 * @param str
	 * @return
	 */
	public ElementBase createElement(String str) {
		return null;
	}

	/**
	 * @说明 移动方法，子类选择性重写
	 * @param str
	 */
	public void move() {
	}

	/**
	 * @说明 显示元素的抽象方法,子类必须重写
	 * @param g
	 */
	public abstract void show(Graphics g);

	/**
	 * @说明 攻击方法，方法中创造的子弹元素将存放入列表参数中
	 * @param List<ElementBase>
	 *            el 用于存放子弹元素的List
	 * @有待优化
	 */
//	public void attack(int gametime) {
//		
//	}
	
	/**
	 * @说明 修改了参数类型
	 * @author 钟苑阳
	 * @param el
	 */
	public void attack(List<ElementBase> el) {
		
	}

	/**
	 * @说明 获取元素矩形方法
	 * @return
	 */
	public Rectangle getRectangle() {
		return new Rectangle(x, y, w, h);
	}

	/**
	 * @说明 基于Rectangle的碰撞检测方法，检测本元素与另外一个元素是否碰撞
	 * @param eb
	 *            另一个元素
	 * @return 当碰撞，返回true，否则false
	 */
	public boolean isCollide(ElementBase eb) {
		return this.getRectangle().intersects(eb.getRectangle());
	}

	public void keyClick(boolean b1, int key) {

	}

	public void die() {

	}
	public final void model(List<ElementBase> list){ //不允许重写
		move();
		attack(list);
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

	public int getW() {
		return w;
	}

	public void setW(int w) {
		this.w = w;
	}

	public int getH() {
		return h;
	}

	public void setH(int h) {
		this.h = h;
	}

	public ImageIcon getImageIcon() {
		return imageIcon;
	}

	public void setImageIcon(ImageIcon imageIcon) {
		this.imageIcon = imageIcon;
	}

	public boolean isLive() {
		return live;
	}

	public void setLive(boolean live) {
		this.live = live;
	}

	
}
