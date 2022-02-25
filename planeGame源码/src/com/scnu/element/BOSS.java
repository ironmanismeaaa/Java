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
 * @说明 BOSS类
 * @author Lin
 * @属性 继 承 基 类 属 性 ， 增 加  4 个属 性 ：int vx（水平速度），int vy（竖直速度）,int hp（生命值）, String
 *     bulletType 子 弹 类 型 (用 子弹编号表示，例如："Bullet_1"、"Bullet_2")
 * @注意 bulletType 中子弹出现的 X,Y 坐标是相对于 BOSS 图片左上角而言的偏移量; W 与 H 若省略默认为图片大小
 * 
 */
public class BOSS extends ElementBase {
	private int vx;
	private int hp;
	private String bulletType;
	private int lastT=15;
	private int rT=0;
	private int rV=-2;
	private int atkTime=0;
	public int getAtkTime() {
		return atkTime;
	}

	public void setAtkTime(int atkTime) {
		this.atkTime = atkTime;
	}

	Random r = new Random();
	/**
	 * @说明 空构造方法
	 */
	public BOSS() {}

	/**
	 * @说明 显示方法
	 */
	@Override
	public void show(Graphics g) {
		g.drawImage(this.getImageIcon().getImage(), this.getX(), this.getY(), this.getW(), this.getH(), null);
	}

	/**
	 * @说明 移动方法
	 */
	@Override
	public void move() {
		if(this.getY()-this.getH()<0){
			this.setY(this.getY() + this.getVy());
		}
		else{
			if(rT==lastT){
				rT=0;
				lastT=lastT+r.nextInt(10)-5;
				rV=r.nextInt(6)-3;
			}
			else{
				vx=rV;
				if(this.getX()>Math.abs(vx)||this.getX()<1020-Math.abs(vx)){
					this.setX(this.getX() + vx);
				}			
				rT++;
			}	
		}
		vx=0;
	}

	/**
	 * @说明 攻击方法
	 * @param List<ElementBase>
	 *            el 用于存放子弹元素的List
	 * @有待优化
	 */
	@Override
	public void attack(List<ElementBase> el) {
		if (this.getY() >= 0 && this.getY() <= 1020 && this.getAtkTime() % 25 == 0) {
			Properties pro = new Properties();
			try {
				pro.load(new FileInputStream("src/com/scnu/text/BulletsConfig.properties"));
				ElementBase base = new Bullet().createElement(pro.getProperty(bulletType));
				base.setX(base.getX() + this.getX() + (int) (0.5 * this.getW()) - 10);
				base.setY(base.getY() + this.getY() + this.getH());
				// el.add(base);
				ElementManager.getManager().addElement(base, GameElement.BULLET);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		this.setAtkTime(this.getAtkTime()+1);
	}
	
	//BOSS属性：
	//X,Y,W,H,VX,VY,imageIcon图像,live生存状态,hp,bulletType

	

	/**
	 * @说明 元素创建方法
	 * @param str
	 *            字符串参数格式:"x,y,hp,vx,vy,imagepath,bulletType,w,h"
	 * @其他 可以通过读取配置文件的方式来传参
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
		bulletType = par[6] ;
		
		if (par.length == 9) {
			this.setW(Integer.parseInt(par[7]));
			this.setH(Integer.parseInt(par[8]));
		} else {
			this.setW(this.getImageIcon().getIconWidth());
			this.setH(this.getImageIcon().getIconHeight());
		}
		return this;
	}
	
	public int getVx() {
		return vx;
	}

	public void setVx(int vx) {
		this.vx = vx;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public String getBulletType() {
		return bulletType;
	}

	public void setBulletType(String bulletType) {
		this.bulletType = bulletType;
	}

}
