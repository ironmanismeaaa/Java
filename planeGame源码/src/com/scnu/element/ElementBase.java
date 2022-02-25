package com.scnu.element;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;

/**
 * @˵�� ����Ԫ�صĳ������
 * @author Lin
 * @���� x����,y����,w���,h�߶�,imageIconͼ��,live����״̬ , �ܹ�6������
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
	 * @˵�� ��װ�Ĺ��췽����ͨ��ָ���ַ�����ʽ���ι���Ԫ��
	 * @param str
	 * @return
	 */
	public ElementBase createElement(String str) {
		return null;
	}

	/**
	 * @˵�� �ƶ�����������ѡ������д
	 * @param str
	 */
	public void move() {
	}

	/**
	 * @˵�� ��ʾԪ�صĳ��󷽷�,���������д
	 * @param g
	 */
	public abstract void show(Graphics g);

	/**
	 * @˵�� ���������������д�����ӵ�Ԫ�ؽ�������б������
	 * @param List<ElementBase>
	 *            el ���ڴ���ӵ�Ԫ�ص�List
	 * @�д��Ż�
	 */
//	public void attack(int gametime) {
//		
//	}
	
	/**
	 * @˵�� �޸��˲�������
	 * @author ��Է��
	 * @param el
	 */
	public void attack(List<ElementBase> el) {
		
	}

	/**
	 * @˵�� ��ȡԪ�ؾ��η���
	 * @return
	 */
	public Rectangle getRectangle() {
		return new Rectangle(x, y, w, h);
	}

	/**
	 * @˵�� ����Rectangle����ײ��ⷽ������ⱾԪ��������һ��Ԫ���Ƿ���ײ
	 * @param eb
	 *            ��һ��Ԫ��
	 * @return ����ײ������true������false
	 */
	public boolean isCollide(ElementBase eb) {
		return this.getRectangle().intersects(eb.getRectangle());
	}

	public void keyClick(boolean b1, int key) {

	}

	public void die() {

	}
	public final void model(List<ElementBase> list){ //��������д
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
