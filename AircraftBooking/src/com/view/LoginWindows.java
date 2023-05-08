package com.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;

import com.dao.UserDaoImpl;
import com.dao.UserDaoI;


import java.awt.SystemColor;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class LoginWindows {

	private JFrame frame;
	private JTextField textField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					LoginWindows window = new LoginWindows();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LoginWindows() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("img/title.png"));
		frame.setTitle("飞机订票管理系统");
		//窗体大小
		frame.setVisible(true);
		frame.setBounds(100, 100, 839, 565);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);//禁止窗口放大
		
		JLabel lblNewLabel = new JLabel("账号");
		lblNewLabel.setFont(new Font("微软雅黑", Font.BOLD, 15));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(246, 204, 54, 18);
		frame.getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(316, 204, 207, 21);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("密码");
		lblNewLabel_1.setForeground(Color.BLACK);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("微软雅黑", Font.BOLD, 15));
		lblNewLabel_1.setBounds(246, 268, 54, 18);
		frame.getContentPane().add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("登录");
		btnNewButton.setBackground(SystemColor.inactiveCaptionBorder);
		btnNewButton.setForeground(new Color(0, 0, 0));
		btnNewButton.setFont(new Font("微软雅黑", Font.BOLD, 16));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//按钮点击事件
				//获取账号密码 进行登录
				String account =textField.getText();
				String password=new String (passwordField.getPassword());//获取密码 返回的是数组 要初始化成字符串
				// 调用登录功能
				 UserDaoImpl ud = new UserDaoI();
	 
				boolean flag = ud.isLogin(account, password);
				if (flag) {
					JOptionPane.showMessageDialog(null, "登录成功!", "标题", JOptionPane.INFORMATION_MESSAGE);
					
					ud.Login(account, password);
					frame.dispose();//点击按钮时frame1销毁,new一个frame2
		            new WelcomeWindows();
				} else {
					
					JOptionPane.showMessageDialog(null, "用户名或者密码有误,登录失败", "标题", JOptionPane.INFORMATION_MESSAGE);
				}
				
			}
		});
		btnNewButton.setBounds(349, 318, 131, 31);
		frame.getContentPane().add(btnNewButton);
		
		
		
		passwordField = new JPasswordField();
		passwordField.setBounds(316, 268, 207, 21);
		frame.getContentPane().add(passwordField);
		
		JLabel lblNewLabel_4 = new JLabel("飞机订票管理系统");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setForeground(Color.RED);
		lblNewLabel_4.setFont(new Font("微软雅黑", Font.BOLD, 32));
		lblNewLabel_4.setBounds(26, 80, 775, 88);
		frame.getContentPane().add(lblNewLabel_4);
		
		
		
		JButton btnNewButton_1 = new JButton("注册");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//打开一个注册窗口
				frame.dispose();
	            new RegisterWindows();
			}
		});
		btnNewButton_1.setForeground(Color.BLACK);
		btnNewButton_1.setFont(new Font("微软雅黑", Font.BOLD, 16));
		btnNewButton_1.setBackground(SystemColor.inactiveCaptionBorder);
		btnNewButton_1.setBounds(349, 370, 131, 31);
		frame.getContentPane().add(btnNewButton_1);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon("img/Login.jpg"));
		lblNewLabel_3.setBounds(0, 0, 823, 526);
		frame.getContentPane().add(lblNewLabel_3);
	}
}
