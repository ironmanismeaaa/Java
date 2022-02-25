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
 * @˵�� BOSS��
 * @author Lin
 * @���� �� �� �� �� �� �� �� �� ��  4 ���� �� ��int vx��ˮƽ�ٶȣ���int vy����ֱ�ٶȣ�,int hp������ֵ��, String
 *     bulletType �� �� �� �� (�� �ӵ���ű�ʾ�����磺"Bullet_1"��"Bullet_2")
 * @ע�� bulletType ���ӵ����ֵ� X,Y ����������� BOSS ͼƬ���ϽǶ��Ե�ƫ����; W �� H ��ʡ��Ĭ��ΪͼƬ��С
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
	 * @˵�� �չ��췽��
	 */
	public BOSS() {}

	/**
	 * @˵�� ��ʾ����
	 */
	@Override
	public void show(Graphics g) {
		g.drawImage(this.getImageIcon().getImage(), this.getX(), this.getY(), this.getW(), this.getH(), null);
	}

	/**
	 * @˵�� �ƶ�����
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
	 * @˵�� ��������
	 * @param List<ElementBase>
	 *            el ���ڴ���ӵ�Ԫ�ص�List
	 * @�д��Ż�
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
	
	//BOSS���ԣ�
	//X,Y,W,H,VX,VY,imageIconͼ��,live����״̬,hp,bulletType

	

	/**
	 * @˵�� Ԫ�ش�������
	 * @param str
	 *            �ַ���������ʽ:"x,y,hp,vx,vy,imagepath,bulletType,w,h"
	 * @���� ����ͨ����ȡ�����ļ��ķ�ʽ������
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
