package com.view;

import java.awt.EventQueue;
import java.awt.Toolkit;
import java.util.List;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;

import com.jpan.AirManage;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Label;
import java.awt.SystemColor;
import java.awt.Color;

public class OrderWindows {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OrderWindows window = new OrderWindows();
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
	public OrderWindows() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBackground(SystemColor.textHighlight);
		frame.setBounds(100, 100, 753, 442);
		frame.getContentPane().setLayout(null);

		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("img/title.png"));
		frame.setTitle("航班信息");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//单独关闭
		frame.setResizable(false);//禁止窗口放大
		frame.setVisible(true);
		
		
		//Label label = new Label("New label");
		JTabbedPane tabPane = new JTabbedPane(JTabbedPane.TOP);
		tabPane.add("航班管理",new AirManage());
		tabPane.setBounds(10, 10, 717, 383);
		tabPane.setBackground(SystemColor.activeCaption);
		frame.getContentPane().add(tabPane);

	    
	    
	
		
	}
}
