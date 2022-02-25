package com.scnu.show;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import com.scnu.controller.GameThread;
import com.scnu.element.ElementBase;
import com.scnu.element.Player;
import com.scnu.manager.ElementManager;
import com.scnu.manager.GameElement;

/**
 * @说明 游戏的主要面板
 * 
 * @功能说明 主要进行元素的显示，同时进行界面的刷新(多线程)
 * 
 * @题外话 状态变换通过设置变量state的值来实现
 * 
 * @多线程刷新 1.本类实现线程接口
 * 
 */
public class GameMainJPanel extends JPanel implements Runnable {

	// 联动管理器
	private ElementManager em;

	// 状态图
	public static BufferedImage start; // 启动图
	public static BufferedImage pause; // 暂停图
	public static BufferedImage gameover; // 游戏结束图

	// 设置状态变量
	// public static final int START = 0; // 启动状态
	public static final int RUNNING = 1; // 运行状态
	public static final int PAUSE = 2; // 暂停状态
	public static final int GAME_OVER = 3; // 游戏结束状态
	public int state = RUNNING; // 当前状态(默认为启动状态)

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	static { // 初始化静态资源(图片)
		try {
			start = ImageIO.read(GameMainJPanel.class.getResource("gameStart.png"));
			pause = ImageIO.read(GameMainJPanel.class.getResource("gamePause.png"));
			gameover = ImageIO.read(GameMainJPanel.class.getResource("gameover.jpg"));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public GameMainJPanel() {
		init();
	}

	public void init() {
		em = ElementManager.getManager();// 得到元素管理器对象
	}

	/**
	 * paint方法是进行绘画元素。 绘画时是有固定的顺序，先绘画的图片会在底层，后绘画的图片会覆盖先绘画的 约定：本方法只执行一次,想实时刷新需要使用
	 * 多线程
	 */
	@Override // 用于绘画的 Graphics 画笔 专门用于绘画的
	public void paint(Graphics g) {

		super.paint(g);

		paintState(g);
		if (Player.hp > 1) {

			paintElements(g);
		}
	}

	/** 绘画元素，飞机、地图等 */
	private void paintElements(Graphics g) {
		Map<GameElement, List<ElementBase>> all = em.getGameElements();
		// GameElement.values();//隐藏方法 返回值是一个数组,数组的顺序就是定义枚举的顺序
		for (GameElement ge : GameElement.values()) {
			List<ElementBase> list = all.get(ge);
			for (int i = 0; i < list.size(); i++) {
				ElementBase obj = list.get(i);// 读取为基类
				obj.show(g);// 调用每个类的自己的show方法完成自己的显示
			}
		}
	}

	/** 画状态 */
	public void paintState(Graphics g) {
		switch (state) { // 根据当前状态的不同来画不同的图
		case RUNNING: // 启动状态时
			ImageIcon img = new ImageIcon("images/background/6.jpg");
			g.drawImage(img.getImage(), 0, 0, 720, 1020, null); // 画启动图
			// state=RUNNING;
			paintLive(g);
			// System.out.println(Player.hp);
			if (Player.hp <= 1 || GameThread.flag == true) {
				state = GAME_OVER;
			}
			break;
		case PAUSE: // 暂停状态时
			g.drawImage(pause, 0, 0, null); // 画暂停图
			break;
		case GAME_OVER: // 游戏结束状态时
			g.drawImage(gameover, 0, 0, null); // 画游戏结束图
			break;
		}
	}

	@Override
	public void run() { // 接口实现
		while (true) {
			// System.out.println("多线程运动");
			this.repaint();
			// 一般情况下，多线程都会使用一个休眠,控制速度
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private static void paintLive(Graphics g) {
		// 绘制空心血条方框
		g.setColor(new Color(190, 195, 199));
		g.drawRect(285, 22, 151, 25);
		// 绘制实心血条方框
		g.setColor(new Color(234, 75, 53));
		g.fillRect(286, 22, Player.hp * 15, 24);
		// 绘制血条数值阴影
		g.setColor(Color.black);
		g.setFont(new Font("黑体", Font.PLAIN, 18));
		g.drawString("" + Player.hp * 10, 345, 42);
		// 绘制血条数值
		g.setColor(Color.white);
		g.setFont(new Font("黑体", Font.PLAIN, 18));
		g.drawString("" + Player.hp * 10, 343, 40);
		// 绘制血量过低警告
		if (Player.hp * 10 <= 30) {
			// 绘制生命值过低提示阴影
			g.setColor(Color.black);
			g.setFont(new Font("黑体", Font.PLAIN, 20));
			g.drawString("警告：生命值过低！", 237, 480);
			// 绘制生命值过低提示
			g.setColor(Color.red);
			g.setFont(new Font("黑体", Font.PLAIN, 20));
			g.drawString("警告：生命值过低！", 235, 480);
		}
	}

}
