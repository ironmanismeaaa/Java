package com.scnu.show;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.scnu.controller.GameListener;
import com.scnu.controller.GameThread;

/**
 * fetch test
 * 
 * @说明 游戏窗体 主要实现的功能：关闭，显示，最大最小化
 * @author 钟苑阳
 * @功能说明 需要嵌入面板,启动主线程等等
 * 
 * @分析 1.面板绑定到窗体 2.监听绑定 3.游戏主线程启动 4.显示窗体
 */
public class GameJFrame extends JFrame {
	public static int GameX = 720;// GAMEX
	public static int GameY = 1020;
	private JPanel jPanel = null; // 正在现实的面板
	private KeyListener keyListener = null;// 键盘监听
	private MouseMotionListener mouseMotionListener = null; // 鼠标监听
	private MouseListener mouseListener = null;
	private Thread thead = null; // 游戏主线程
	private Image backgroudImage;
	private Container container = this.getContentPane();
	private JButton jButton;
//	private BackgroundPanel bPanel;

	public GameJFrame() {
		init();
	}

	public void init() {
		this.setSize(GameX, GameY); // 设置窗体大小
		this.setTitle("飞机大战");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 设置退出并且关闭
		this.setLocationRelativeTo(null);// 屏幕居中显示
		// 。。。。
	}
	
	/**
	 * 启动方法
	 */
	public void start() {
		if (jPanel != null) {
			this.add(jPanel);
		}
		// if (this.bPanel != null) {
		// this.add(bPanel);
		// }
		if (keyListener != null) {
			this.addKeyListener(keyListener);
		}
		if (thead != null) {
			thead.start();// 启动线程
		}
		// this.show();
		this.setVisible(true);// 显示界面
		// 如果jp 是 runnable的 子类实体对象
		// 如果这个判定无法进入就是 instanceof判定为 false 那么 jpanel没有实现runnable接口
		if (this.jPanel instanceof Runnable) {
			// 已经做类型判定，强制类型转换不会出错
			// new Thread((Runnable)this.jPanel).start();
			Runnable run = (Runnable) this.jPanel;
			Thread th = new Thread(run);
			th.start();//
			System.out.println("是否启动");
		}
		
	}

	
	public JButton getjButton() {
		return jButton;
	}

	public void setjButton(JButton jButton) {
		this.jButton = jButton;
	}
	
	public void setjPanel(JPanel jPanel) {
		this.jPanel = jPanel;
	}

	public void setKeyListener(KeyListener keyListener) {
		this.keyListener = keyListener;
	}

	public void setMouseMotionListener(MouseMotionListener mouseMotionListener) {
		this.mouseMotionListener = mouseMotionListener;
	}

	public void setMouseListener(MouseListener mouseListener) {
		this.mouseListener = mouseListener;
	}

	public void setThead(Thread thead) {
		this.thead = thead;
	}

	public Container getContainer() {
		return container;
	}

	public void setContainer(Container container) {
		this.container = container;
	}

//	
//	/* 窗体布局: 可以讲 存档，读档。button 给大家扩展的 */
//	public void addButton() {
//		jButton = new JButton("点击按钮开始游戏！");
//		jButton.setBounds(0, 0, 300, 300);
//		// 设置按钮的默认图片
//		jButton.setIcon(new ImageIcon("src/com/scnu/show/button_normal.png"));
//
//		// 设置按钮被点击时的图片
//		jButton.setPressedIcon(new ImageIcon("src/com/scnu/show/button_press.png"));
//
//		jButton.setBorderPainted(false); // 不绘制边框
//		jButton.setContentAreaFilled(false); // 不绘制默认按钮背景
//		jButton.setFocusPainted(false); // 不绘制图片或文字周围的焦点虚框
//
//		Font font = new Font("微软雅黑", Font.BOLD, 20);
//		jButton.setFont(font);
//		jButton.setForeground(Color.red);
//
//		// button.setVerticalAlignment(JButton.CENTER);
//		 jButton.setVerticalTextPosition(JButton.BOTTOM);
//		
//		 jButton.setHorizontalTextPosition(JButton.CENTER);
//
//		this.getContentPane().add(jButton);
//		jButton.addActionListener(new ActionListener() {// 按钮点击事件，实现点击按钮的逻辑操作
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				System.out.println("按钮被点击了");
//
//				// gj.dispose();// 关闭当前jframe
//
//				GameJFrame gj2 = new GameJFrame();// 开启新的jframe
//				/** 实例化面板，注入到jframe中 */
//				GameMainJPanel jp2 = new GameMainJPanel();// 开启游戏面板
//
//				// 实例化监听
//				GameListener listener = new GameListener();
//				// 实例化主线程
//				GameThread th = new GameThread();
//
//				gj2.setjPanel(jp2);
//				gj2.setKeyListener(listener);
//				gj2.setThead(th);
//				gj2.start();
//
//			}
//		});
//	}
//
//	
//
//	/*
//	 * set注入：等大家学习ssm 通过set方法注入配置文件中读取的数据;讲配置文件 中的数据赋值为类的属性 构造注入：需要配合构造方法 spring
//	 * 中ioc 进行对象的自动生成，管理。
//	 */
//	public void setBackgroudImage(Image image) {
//		// container=this.getContentPane();
//		this.setLayout(null);
//
//		bPanel = new BackgroundPanel(image);
//		bPanel.setBounds(0, 0, GameX, GameY);
//		container.add(bPanel);
//	}
//
//	private class BackgroundPanel extends JPanel {
//		Image im;
//
//		public BackgroundPanel(Image im) {
//			this.im = im;
//			this.setOpaque(true);
//		}
//
//		// Draw the back ground.
//		@Override
//		public void paintComponent(Graphics g) {
//			super.paintComponents(g);
//			g.drawImage(im, 0, 0, GameX, GameY, null);
//
//		}
//
//	}
//
//	public void setBackground() {
//
//		jButton = new JButton("点击按钮开始游戏！");
//		jButton.setBounds(GameX / 2 - 300/2, GameY / 2 - 300/2-150, 300, 300);
//		// 设置按钮的默认图片
//		jButton.setIcon(new ImageIcon("src/com/scnu/show/button_press.png"));
//
//		// 设置按钮被点击时的图片
//		jButton.setPressedIcon(new ImageIcon("src/com/scnu/show/button_normal.png"));
//
//		jButton.setBorderPainted(false); // 不绘制边框
//		jButton.setContentAreaFilled(false); // 不绘制默认按钮背景
//		jButton.setFocusPainted(false); // 不绘制图片或文字周围的焦点虚框
//
//		Font font = new Font("微软雅黑", Font.BOLD, 20);
//		jButton.setFont(font);
//		jButton.setForeground(Color.red);
//
//		// button.setVerticalAlignment(JButton.CENTER);
//		jButton.setVerticalTextPosition(JButton.BOTTOM);
//
//		jButton.setHorizontalTextPosition(JButton.CENTER);
//
//		this.getContentPane().add(jButton);
//		jButton.addActionListener(new ActionListener() {// 按钮点击事件，实现点击按钮的逻辑操作
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				System.out.println("按钮被点击了");
//
//				// gj.dispose();// 关闭当前jframe
//
//				
//				GameJFrame gj2 = new GameJFrame();// 开启新的jframe
//				/** 实例化面板，注入到jframe中 */
//				GameMainJPanel jp2 = new GameMainJPanel();// 开启游戏面板
//
//				// 实例化监听
//				GameListener listener = new GameListener();
//				// 实例化主线程
//				GameThread th = new GameThread();
//
//				
//				gj2.setjPanel(jp2);
//				gj2.setKeyListener(listener);
//				gj2.setThead(th);
//				gj2.start();
//
//			}
//		});
//
//		// 设置背景
//		JLabel lblBackground = new JLabel(); // 创建一个标签组件对象
//		URL resource = this.getClass().getResource("8.png"); // 获取背景图片路径
//		ImageIcon icon = new ImageIcon("images/background/8.png"); // 创建背景图片对象
//		lblBackground.setIcon(icon); // 设置标签组件要显示的图标
//		lblBackground.setBounds(0, 0, GameX, GameY); // 设置组件的显示位置及大小
//		// this.getContentPane().add(lblBackground); // 将组件添加到面板中
//		this.getContentPane().add(lblBackground);
//	}

}
