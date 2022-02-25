package com.scnu.element;

import java.awt.Graphics;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.Random;

import javax.swing.ImageIcon;

import com.scnu.manager.ElementManager;
import com.scnu.manager.GameElement;

/**
 * @说明 普通敌人类
 * @author Lin
 * @属性 继承了基类属性，新增属性： int vx 水平速度矢量; int vy 竖直速度矢量; int hp 生命值; String bulletType
 *     子弹类型(用 子弹编号表示，例如："Bullet_1"、"Bullet_2")
 * @注意 bulletType 中子弹出现的 X,Y 坐标是相对于 BOSS 图片左上角而言的偏移量; W 与 H 若省略默认为图片大小
 * @其他 本类存在复制构造函数 Enemy ( Enemy en )
 */
public class Enemy extends ElementBase {
	private int hp;
	private int vx;
	private String bulletType;
	private int atkTime=0;
	Random r = new Random();
	/**
	 * @说明 空构造方法
	 */
	public Enemy() {
	}

	@Override
	public void show(Graphics g) {
		g.drawImage(this.getImageIcon().getImage(), this.getX(), this.getY(), this.getW(), this.getH(), null);
	}

	/**
	 * @说明 敌人元素构造方法，利用静态配置方法,通过读取文件来读取敌人的属性
	 * @param str
	 *            字符串参数格式:"x,y,hp,vx,vy,imagepath,bulletType,w,h"
	 */
	@Override
	public ElementBase createElement(String str) {
		String[] par = str.split(",");
		this.setX(Integer.parseInt(par[0]));
		this.setY(Integer.parseInt(par[1]));
		this.hp = Integer.parseInt(par[2]);
		this.vx = Integer.parseInt(par[3]);
		this.setVy(Integer.parseInt(par[4]));
		this.setImageIcon(new ImageIcon(par[5]));
		bulletType = par[6];
		if (par.length == 9) {
			this.setW(Integer.parseInt(par[7]));
			this.setH(Integer.parseInt(par[8]));
		} else {
			this.setW(this.getImageIcon().getIconWidth());
			this.setH(this.getImageIcon().getIconHeight());
		}
		this.setX(r.nextInt(25)*r.nextInt(25)+50);
		return this;
	}

	/**
	 * @说明 复制构造函数
	 * @param ee
	 *            另外一个 Enemy 实例
	 */
	public Enemy(Enemy ee) {
		this.setX(ee.getX());
		this.setY(ee.getY());
		this.setH(ee.getH());
		this.setW(ee.getW());
		this.setImageIcon(ee.getImageIcon());
		this.setBulletType(ee.getBulletType());
		this.setVx(ee.getVx());
		this.setVy(ee.getVy());
		this.setHp(ee.getHp());
	}

	/**
	 * @说明 移动方法
	 */
	@Override
	public void move() {
		this.setX(this.getX() + vx);
		this.setY(this.getY() + this.getVy());
	}

	/**
	 * @说明 攻击方法
	 * @用配置文件配合bulletType来产生子弹
	 */
	@Override
	public void attack(List<ElementBase> el) {
		if(this.getY()>=0&&this.getY()<=1020&&this.getAtkTime()%20==0){
		Properties pro = new Properties();
		try {
			pro.load(new FileInputStream("src/com/scnu/text/BulletsConfig.properties"));
			ElementBase base = new Bullet().createElement(pro.getProperty(bulletType));
			base.setX(base.getX()+this.getX()+(int)(0.5*this.getW())-12);
			base.setY(base.getY()+this.getY()+this.getH()+2);
//			el.add(base);
			ElementManager.getManager().addElement(base, GameElement.BULLET);	
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		}
		this.setAtkTime(this.getAtkTime()+1);
	}

	public int getAtkTime() {
		return atkTime;
	}

	public void setAtkTime(int atkTime) {
		this.atkTime = atkTime;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getVx() {
		return vx;
	}

	public void setVx(int vx) {
		this.vx = vx;
	}

	public String getBulletType() {
		return bulletType;
	}

	public void setBulletType(String bulletType) {
		this.bulletType = bulletType;
	}

}
