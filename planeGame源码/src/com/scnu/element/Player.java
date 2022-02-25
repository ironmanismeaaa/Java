package com.scnu.element;

import java.awt.Graphics;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import javax.swing.ImageIcon;

import com.scnu.manager.ElementManager;
import com.scnu.manager.GameElement;

/**
 * @说明 玩家类
 * @author Lin
 * @属性 继承了元素基类属性，新增属性： int vx 水平速度矢量; int vy 竖直速度矢量; int hp 生命值 String
 *     bulletType 子弹类型(用 子弹编号表示，例如："Bullet_1"、"Bullet_2")
 */
public class Player extends ElementBase {
	private int vx;
	public static int hp=10;//血量
	private String bulletType;
	private boolean attackStatus = false;
	private int atkTime=0;
	private double rate=0.4;
	/**
	 * @说明 空构造方法
	 */
	public Player() {
	}

	public Player(int x, int y, int w, int h,int vy, ImageIcon icon) {
		super(x, y, w, h, vy, icon);
	}

	/**
	 * @说明 重写基类的显示方法
	 */
	@Override
	public void show(Graphics g) {
		g.drawImage(this.getImageIcon().getImage(), this.getX(), this.getY(), this.getW(), this.getH(), null);
	}

	/**
	 * @说明 重写基类移动方法，通过vx与vy来控制移动：x += vx, y += vy;
	 */
	@Override

	public void keyClick(boolean b1, int key) {
		if (b1) {
			switch (key) {
			case 37:
				this.setVx(-15);
				break;
			case 38:
				this.setVy(-15);
				break;
			case 39:
				this.setVx(15);
				break;
			case 40:
				this.setVy(15);
				break;
			case 32:
				attackStatus = true;
				break;
			}
		} else {
			switch (key) {
			case 37:
				this.setVx(0);
				break;
			case 38:
				this.setVy(0);
				break;
			case 39:
				this.setVx(0);
				break;
			case 40:
				this.setVy(0);
				break;
			case 32:
				attackStatus = false;
				break;
			}
		}
	}

	public void move() {
		this.setX(this.getX() + this.getVx());
		this.setY(this.getY() + this.getVy());
		if(this.getY()<=0 || this.getY() >= 1020 || this.getX() >= 720 || this.getY() <= 0){
			this.setX(this.getX() - this.getVx());
			this.setY(this.getY() - this.getVy());
		}
	}

	/**
	 * @说明 重写父类创造方法
	 * @param str
	 *            字符串参数格式要求："x,y,hp,vx,vy,imagepath,bulletType,w,h",w和h可以省略，若省略，
	 *            则取图片默认大小，imagepath为图片路径
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
		return this;
	}

	/**
	 * @说明 攻击方法
	 * @param List<ElementBase>
	 *            el 用于存放子弹元素的List
	 * @优化 读取配置文件
	 */
	@Override
	public void attack(List<ElementBase> el) {
		if(!attackStatus){
			return;
		}
		else{
			if(this.getAtkTime()%4==0){
			Properties pro = new Properties();
			try {
				pro.load(new FileInputStream("src/com/scnu/text/BulletsConfig.properties"));
				System.out.println(pro.getProperty(bulletType));
				ElementBase base = new Bullet().createElement(pro.getProperty(bulletType));

				base.setX(base.getX() + this.getX()+(int)(this.getW()*rate));
				base.setY(base.getY() + this.getY()-20);
//				el.add(base);
				ElementManager.getManager().addElement(base, GameElement.BULLET);			
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			}
			this.setAtkTime(this.getAtkTime()+1);
		}
		

	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	public int getAtkTime() {
		return atkTime;
	}

	public void setAtkTime(int atkTime) {
		this.atkTime = atkTime;
	}

	public String getBulletType() {
		return bulletType;
	}

	public void setBulletType(String bulletType) {
		this.bulletType = bulletType;
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

	public boolean isAttackStatus() {
		return attackStatus;
	}

	public void setAttackStatus(boolean attackStatus) {
		this.attackStatus = attackStatus;
	}

}
