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
 * @˵�� ��Ϸ����Ҫ���
 * 
 * @����˵�� ��Ҫ����Ԫ�ص���ʾ��ͬʱ���н����ˢ��(���߳�)
 * 
 * @���⻰ ״̬�任ͨ�����ñ���state��ֵ��ʵ��
 * 
 * @���߳�ˢ�� 1.����ʵ���߳̽ӿ�
 * 
 */
public class GameMainJPanel extends JPanel implements Runnable {

	// ����������
	private ElementManager em;

	// ״̬ͼ
	public static BufferedImage start; // ����ͼ
	public static BufferedImage pause; // ��ͣͼ
	public static BufferedImage gameover; // ��Ϸ����ͼ

	// ����״̬����
	// public static final int START = 0; // ����״̬
	public static final int RUNNING = 1; // ����״̬
	public static final int PAUSE = 2; // ��ͣ״̬
	public static final int GAME_OVER = 3; // ��Ϸ����״̬
	public int state = RUNNING; // ��ǰ״̬(Ĭ��Ϊ����״̬)

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	static { // ��ʼ����̬��Դ(ͼƬ)
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
		em = ElementManager.getManager();// �õ�Ԫ�ع���������
	}

	/**
	 * paint�����ǽ��л滭Ԫ�ء� �滭ʱ���й̶���˳���Ȼ滭��ͼƬ���ڵײ㣬��滭��ͼƬ�Ḳ���Ȼ滭�� Լ����������ִֻ��һ��,��ʵʱˢ����Ҫʹ��
	 * ���߳�
	 */
	@Override // ���ڻ滭�� Graphics ���� ר�����ڻ滭��
	public void paint(Graphics g) {

		super.paint(g);

		paintState(g);
		if (Player.hp > 1) {

			paintElements(g);
		}
	}

	/** �滭Ԫ�أ��ɻ�����ͼ�� */
	private void paintElements(Graphics g) {
		Map<GameElement, List<ElementBase>> all = em.getGameElements();
		// GameElement.values();//���ط��� ����ֵ��һ������,�����˳����Ƕ���ö�ٵ�˳��
		for (GameElement ge : GameElement.values()) {
			List<ElementBase> list = all.get(ge);
			for (int i = 0; i < list.size(); i++) {
				ElementBase obj = list.get(i);// ��ȡΪ����
				obj.show(g);// ����ÿ������Լ���show��������Լ�����ʾ
			}
		}
	}

	/** ��״̬ */
	public void paintState(Graphics g) {
		switch (state) { // ���ݵ�ǰ״̬�Ĳ�ͬ������ͬ��ͼ
		case RUNNING: // ����״̬ʱ
			ImageIcon img = new ImageIcon("images/background/6.jpg");
			g.drawImage(img.getImage(), 0, 0, 720, 1020, null); // ������ͼ
			// state=RUNNING;
			paintLive(g);
			// System.out.println(Player.hp);
			if (Player.hp <= 1 || GameThread.flag == true) {
				state = GAME_OVER;
			}
			break;
		case PAUSE: // ��ͣ״̬ʱ
			g.drawImage(pause, 0, 0, null); // ����ͣͼ
			break;
		case GAME_OVER: // ��Ϸ����״̬ʱ
			g.drawImage(gameover, 0, 0, null); // ����Ϸ����ͼ
			break;
		}
	}

	@Override
	public void run() { // �ӿ�ʵ��
		while (true) {
			// System.out.println("���߳��˶�");
			this.repaint();
			// һ������£����̶߳���ʹ��һ������,�����ٶ�
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private static void paintLive(Graphics g) {
		// ���ƿ���Ѫ������
		g.setColor(new Color(190, 195, 199));
		g.drawRect(285, 22, 151, 25);
		// ����ʵ��Ѫ������
		g.setColor(new Color(234, 75, 53));
		g.fillRect(286, 22, Player.hp * 15, 24);
		// ����Ѫ����ֵ��Ӱ
		g.setColor(Color.black);
		g.setFont(new Font("����", Font.PLAIN, 18));
		g.drawString("" + Player.hp * 10, 345, 42);
		// ����Ѫ����ֵ
		g.setColor(Color.white);
		g.setFont(new Font("����", Font.PLAIN, 18));
		g.drawString("" + Player.hp * 10, 343, 40);
		// ����Ѫ�����;���
		if (Player.hp * 10 <= 30) {
			// ��������ֵ������ʾ��Ӱ
			g.setColor(Color.black);
			g.setFont(new Font("����", Font.PLAIN, 20));
			g.drawString("���棺����ֵ���ͣ�", 237, 480);
			// ��������ֵ������ʾ
			g.setColor(Color.red);
			g.setFont(new Font("����", Font.PLAIN, 20));
			g.drawString("���棺����ֵ���ͣ�", 235, 480);
		}
	}

}
