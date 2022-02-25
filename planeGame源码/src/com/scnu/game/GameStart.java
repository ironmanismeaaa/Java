package com.scnu.game;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.scnu.controller.GameListener;
import com.scnu.controller.GameThread;
import com.scnu.show.GameJFrame;
import com.scnu.show.GameMainJPanel;

public class GameStart {

	public static void main(String[] args) {

		GameJFrame gj = new GameJFrame();

		setBackground(gj, gj.getjButton(), gj.getContainer(), 720, 1020);
		gj.start();

	}

	/**
	 * @说明 通过container添加按钮
	 * @功能 点击关闭当前jframe，打开新的jframe开始游戏
	 * @author 钟苑阳
	 * @param gj
	 */
	public static void addStartButton(GameJFrame gj) {
		// 添加按钮
		Container contentPane = gj.getContentPane();
		// Container contentPane = gj.getContainer();

		contentPane.setLayout(new FlowLayout());

		JButton button = new JButton("点击按钮开始游戏！");
		// 设置按钮的默认图片
		button.setIcon(new ImageIcon("src/com/scnu/show/button_normal.png"));

		// 设置按钮被点击时的图片
		button.setPressedIcon(new ImageIcon("src/com/scnu/show/button_press.png"));

		button.setBorderPainted(false); // 不绘制边框
		button.setContentAreaFilled(false); // 不绘制默认按钮背景
		button.setFocusPainted(false); // 不绘制图片或文字周围的焦点虚框

		Font font = new Font("微软雅黑", Font.BOLD, 20);
		button.setFont(font);
		button.setForeground(Color.red);

		// button.setVerticalAlignment(JButton.CENTER);
		button.setVerticalTextPosition(JButton.BOTTOM);

		button.setHorizontalTextPosition(JButton.CENTER);

		contentPane.add(button);
		button.addActionListener(new ActionListener() {// 按钮点击事件，实现点击按钮的逻辑操作
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("按钮被点击了");

				gj.dispose();// 关闭当前jframe

				GameJFrame gj2 = new GameJFrame();// 开启新的jframe
				/** 实例化面板，注入到jframe中 */
				GameMainJPanel jp2 = new GameMainJPanel();// 开启游戏面板

				// 实例化监听
				GameListener listener = new GameListener();
				// 实例化主线程
				GameThread th = new GameThread();

				gj2.setjPanel(jp2);
				gj2.setKeyListener(listener);
				gj2.setThead(th);
				gj2.start();

			}
		});
	}

	/**
	 * @说明 游戏开始前的背景及按钮
	 * @author 钟苑阳
	 * @param gj
	 * @param jButton
	 * @param container
	 * @param GameX
	 * @param GameY
	 */
	public static void setBackground(GameJFrame gj, JButton jButton, Container container, int GameX, int GameY) {

		jButton = new JButton("点击按钮开始游戏！");
		jButton.setBounds(GameX / 2 - 300 / 2, GameY / 2 - 300, 300, 300);
		// 设置按钮的默认图片
		jButton.setIcon(new ImageIcon("src/com/scnu/show/kaishi_0.png"));

		// 设置按钮被点击时的图片
		jButton.setPressedIcon(new ImageIcon("src/com/scnu/show/kaishi_1.png"));

		jButton.setBorderPainted(false); // 不绘制边框
		jButton.setContentAreaFilled(false); // 不绘制默认按钮背景
		jButton.setFocusPainted(false); // 不绘制图片或文字周围的焦点虚框

		
		//字体设置
		Font font = new Font("微软雅黑", Font.BOLD, 20);
		jButton.setFont(font);
		jButton.setForeground(Color.red);

		// button.setVerticalAlignment(JButton.CENTER);
		jButton.setVerticalTextPosition(JButton.BOTTOM);//文字居中
		jButton.setHorizontalTextPosition(JButton.CENTER);

		container.add(jButton);
		jButton.addActionListener(new ActionListener() {// 按钮点击事件，实现点击按钮的逻辑操作
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("按钮被点击了");

				gj.dispose();// 关闭当前jframe

				GameJFrame gj2 = new GameJFrame();// 开启新的jframe
				/** 实例化面板，注入到jframe中 */
				GameMainJPanel jp2 = new GameMainJPanel();// 开启游戏面板

				// 实例化监听
				GameListener listener = new GameListener();
				// 实例化主线程
				GameThread th = new GameThread();

				gj2.setjPanel(jp2);
				gj2.setKeyListener(listener);
				gj2.setThead(th);
				gj2.start();

			}
		});

		// 设置背景
		JLabel lblBackground = new JLabel(); // 创建一个标签组件对象
		// URL resource = this.getClass().getResource("8.png"); // 获取背景图片路径
		ImageIcon icon = new ImageIcon("images/background/11.png"); // 创建背景图片对象
		lblBackground.setIcon(icon); // 设置标签组件要显示的图标
		lblBackground.setBounds(0, 0, GameX, GameY); // 设置组件的显示位置及大小
		// this.getContentPane().add(lblBackground); // 将组件添加到面板中
		container.add(lblBackground);
	}

}
