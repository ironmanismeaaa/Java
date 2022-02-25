package com.scnu.controller;

import java.util.List;
import java.util.Map;

import com.scnu.element.BOSS;
import com.scnu.element.Bullet;
import com.scnu.element.ElementBase;
import com.scnu.element.Enemy;
import com.scnu.element.Player;
import com.scnu.element.Prop;
import com.scnu.manager.ElementManager;
import com.scnu.manager.GameElement;
import com.scnu.manager.GameLoad;

public class GameThread extends Thread {
	private ElementManager em;
	public static boolean flag = false;

	public GameThread() {
		em = ElementManager.getManager();
	}

	public void run() {
		while (true) {
			gameLoad();
			gameRun();
			gameOver();
			try {
				sleep(50);
			} catch (InterruptedException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
	}

	private void gameRun() {
		while (true) {// 用于控制关卡结束
			Map<GameElement, List<ElementBase>> all = em.getGameElements();
			List<ElementBase> enemys = em.getElementsByKey(GameElement.ENEMY);
			List<ElementBase> boss = em.getElementsByKey(GameElement.BOSS);
			List<ElementBase> player = em.getElementsByKey(GameElement.PLAY);
			List<ElementBase> bullet = em.getElementsByKey(GameElement.BULLET);
			List<ElementBase> prop = em.getElementsByKey(GameElement.PROP);
			moveandupdate(all, bullet);

			ElementPK(player, bullet, 1);
			ElementPK(enemys, bullet, 2);
			ElementPK(boss, bullet, 3);
			ElementPK(boss, player, 4);
			ElementPK(enemys, player, 5);
			ElementPK(prop, player, 6);

			try {
				sleep(50);
			} catch (InterruptedException e) {

				e.printStackTrace();
			}

		}

	}

	public void ElementPK(List<ElementBase> list1, List<ElementBase> list2, int k) {
		switch (k) {
		case 1:
			for (int i = 0; i < list1.size(); i++) {
				Player lista = (Player) list1.get(i);
				for (int j = 0; j < list2.size(); j++) {
					Bullet listb = (Bullet) list2.get(j);
					if (listb.getVy() > 0) {
						if (lista.isCollide(listb)) {
							if (lista.getHp() > 0 - listb.getATK()) {
								lista.setHp(lista.getHp() - listb.getATK());
							} else {
								lista.setLive(false);
							}
							listb.setLive(false);
						}
					}
				}
			}
			break;
		case 2:
			for (int i = 0; i < list1.size(); i++) {
				Enemy lista = (Enemy) list1.get(i);
				for (int j = 0; j < list2.size(); j++) {
					Bullet listb = (Bullet) list2.get(j);
					if (listb.getVy() < 0) {
						if (lista.isCollide(listb)) {
							if (lista.getHp() - listb.getATK() > 0) {
								lista.setHp(lista.getHp() - listb.getATK());
							} else {
								lista.setLive(false);
							}
							listb.setLive(false);
						}
					}
				}
			}
			break;
		case 3:
			for (int i = 0; i < list1.size(); i++) {
				BOSS lista = (BOSS) list1.get(i);

				for (int j = 0; j < list2.size(); j++) {
					Bullet listb = (Bullet) list2.get(j);
					if (listb.getVy() < 0) {
						if (lista.isCollide(listb)) {
							if (lista.getHp() > 0 - listb.getATK()) {
								lista.setHp(lista.getHp() - listb.getATK());
							} else {
								lista.setHp(0);
								lista.setLive(false);
							}
							listb.setLive(false);
						}
					}
				}
				if (lista.getImageIcon().toString().equals("images/boss/18.png") && (!lista.isLive())) {
					flag = true;
				}
			}

			break;
		case 4:
			for (int i = 0; i < list1.size(); i++) {
				BOSS lista = (BOSS) list1.get(i);
				for (int j = 0; j < list2.size(); j++) {
					Player listb = (Player) list2.get(j);
					if (lista.isCollide(listb)) {
						listb.setLive(false);
					}
				}
			}
			break;
		case 5:
			for (int i = 0; i < list1.size(); i++) {
				Enemy lista = (Enemy) list1.get(i);
				for (int j = 0; j < list2.size(); j++) {
					Player listb = (Player) list2.get(j);
					if (lista.isCollide(listb)) {
						if (listb.getHp() > 3) {
							listb.setHp(lista.getHp() - 3);
							lista.setLive(false);
						} else {
							listb.setLive(false);
							lista.setLive(false);
						}
						
					}
				}
			}
			break;
		case 6:
			for (int i = 0; i < list1.size(); i++) {
				Prop lista = (Prop) list1.get(i);
				for (int j = 0; j < list2.size(); j++) {
					Player listb = (Player) list2.get(j);
					if (lista.isCollide(listb)) {
						switch (lista.getPropType()) {
						case "addBlood10":
							if (listb.getHp() != 10) {
								listb.setHp(listb.getHp() + 1);
							}
							lista.setLive(false);
							break;
						case "addBlood30":
							if (listb.getHp() <= 7) {
								listb.setHp(listb.getHp() + 3);
							} else {
								listb.setHp(10);
							}
							lista.setLive(false);
							break;
						case "addBlood100":
							listb.setHp(10);
							lista.setLive(false);
							break;
						case "addAtkType":
							System.out.println(listb.getBulletType());
							if (listb.getBulletType().equals("Bullet_1")) {
								listb.setBulletType("Bullet_1.1");
								listb.setRate(0.2);
								System.out.println(listb.getBulletType());
							} else if (listb.getBulletType() == "Bullet_1.1") {
								listb.setBulletType("Bullet_1.2");
							}
							lista.setLive(false);
							break;
						}
					}
				}

			}
			break;
		}

	}

	public void moveandupdate(Map<GameElement, List<ElementBase>> all, List<ElementBase> listB) {
		for (GameElement ge : GameElement.values()) {
			List<ElementBase> list = all.get(ge);
			for (int i = 0; i < list.size(); i++) {
				ElementBase obj = list.get(i);
				if (!obj.isLive()) {
					obj.die();
					list.remove(i--);
					continue;
				}
				obj.model(listB);
			}
		}
	}

	private void gameOver() {

	}

	private void gameLoad() {
		GameLoad.loadImg();
		GameLoad.loadPlay();
		GameLoad.loadEnemy();
		GameLoad.loadBoss();
		GameLoad.loadProp();
	}
}
