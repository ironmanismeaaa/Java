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
	 * @˵�� ͨ��container��Ӱ�ť
	 * @���� ����رյ�ǰjframe�����µ�jframe��ʼ��Ϸ
	 * @author ��Է��
	 * @param gj
	 */
	public static void addStartButton(GameJFrame gj) {
		// ��Ӱ�ť
		Container contentPane = gj.getContentPane();
		// Container contentPane = gj.getContainer();

		contentPane.setLayout(new FlowLayout());

		JButton button = new JButton("�����ť��ʼ��Ϸ��");
		// ���ð�ť��Ĭ��ͼƬ
		button.setIcon(new ImageIcon("src/com/scnu/show/button_normal.png"));

		// ���ð�ť�����ʱ��ͼƬ
		button.setPressedIcon(new ImageIcon("src/com/scnu/show/button_press.png"));

		button.setBorderPainted(false); // �����Ʊ߿�
		button.setContentAreaFilled(false); // ������Ĭ�ϰ�ť����
		button.setFocusPainted(false); // ������ͼƬ��������Χ�Ľ������

		Font font = new Font("΢���ź�", Font.BOLD, 20);
		button.setFont(font);
		button.setForeground(Color.red);

		// button.setVerticalAlignment(JButton.CENTER);
		button.setVerticalTextPosition(JButton.BOTTOM);

		button.setHorizontalTextPosition(JButton.CENTER);

		contentPane.add(button);
		button.addActionListener(new ActionListener() {// ��ť����¼���ʵ�ֵ����ť���߼�����
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("��ť�������");

				gj.dispose();// �رյ�ǰjframe

				GameJFrame gj2 = new GameJFrame();// �����µ�jframe
				/** ʵ������壬ע�뵽jframe�� */
				GameMainJPanel jp2 = new GameMainJPanel();// ������Ϸ���

				// ʵ��������
				GameListener listener = new GameListener();
				// ʵ�������߳�
				GameThread th = new GameThread();

				gj2.setjPanel(jp2);
				gj2.setKeyListener(listener);
				gj2.setThead(th);
				gj2.start();

			}
		});
	}

	/**
	 * @˵�� ��Ϸ��ʼǰ�ı�������ť
	 * @author ��Է��
	 * @param gj
	 * @param jButton
	 * @param container
	 * @param GameX
	 * @param GameY
	 */
	public static void setBackground(GameJFrame gj, JButton jButton, Container container, int GameX, int GameY) {

		jButton = new JButton("�����ť��ʼ��Ϸ��");
		jButton.setBounds(GameX / 2 - 300 / 2, GameY / 2 - 300, 300, 300);
		// ���ð�ť��Ĭ��ͼƬ
		jButton.setIcon(new ImageIcon("src/com/scnu/show/kaishi_0.png"));

		// ���ð�ť�����ʱ��ͼƬ
		jButton.setPressedIcon(new ImageIcon("src/com/scnu/show/kaishi_1.png"));

		jButton.setBorderPainted(false); // �����Ʊ߿�
		jButton.setContentAreaFilled(false); // ������Ĭ�ϰ�ť����
		jButton.setFocusPainted(false); // ������ͼƬ��������Χ�Ľ������

		
		//��������
		Font font = new Font("΢���ź�", Font.BOLD, 20);
		jButton.setFont(font);
		jButton.setForeground(Color.red);

		// button.setVerticalAlignment(JButton.CENTER);
		jButton.setVerticalTextPosition(JButton.BOTTOM);//���־���
		jButton.setHorizontalTextPosition(JButton.CENTER);

		container.add(jButton);
		jButton.addActionListener(new ActionListener() {// ��ť����¼���ʵ�ֵ����ť���߼�����
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("��ť�������");

				gj.dispose();// �رյ�ǰjframe

				GameJFrame gj2 = new GameJFrame();// �����µ�jframe
				/** ʵ������壬ע�뵽jframe�� */
				GameMainJPanel jp2 = new GameMainJPanel();// ������Ϸ���

				// ʵ��������
				GameListener listener = new GameListener();
				// ʵ�������߳�
				GameThread th = new GameThread();

				gj2.setjPanel(jp2);
				gj2.setKeyListener(listener);
				gj2.setThead(th);
				gj2.start();

			}
		});

		// ���ñ���
		JLabel lblBackground = new JLabel(); // ����һ����ǩ�������
		// URL resource = this.getClass().getResource("8.png"); // ��ȡ����ͼƬ·��
		ImageIcon icon = new ImageIcon("images/background/11.png"); // ��������ͼƬ����
		lblBackground.setIcon(icon); // ���ñ�ǩ���Ҫ��ʾ��ͼ��
		lblBackground.setBounds(0, 0, GameX, GameY); // �����������ʾλ�ü���С
		// this.getContentPane().add(lblBackground); // �������ӵ������
		container.add(lblBackground);
	}

}
