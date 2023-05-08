package com.jpan;

import javax.swing.JPanel;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.border.TitledBorder;

import com.util.FileOperations;

import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;

public class AirManage extends JPanel {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;

	/**
	 * Create the panel.
	 */
	public AirManage() {
		setLayout(null);
		this.setBounds(10, 10, 717, 383);
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "\u822A\u73ED\u7BA1\u7406", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(26, 10, 659, 112);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u822A\u73ED\u53F7");
		lblNewLabel.setBounds(5, 24, 45, 15);
		panel.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(60, 21, 109, 21);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("\u8D77\u59CB\u70B9");
		lblNewLabel_1.setBounds(5, 52, 45, 15);
		panel.add(lblNewLabel_1);
		
		textField_1 = new JTextField();
		textField_1.setBounds(60, 49, 109, 21);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("\u7EC8\u70B9\u7AD9");
		lblNewLabel_2.setBounds(5, 80, 45, 15);
		panel.add(lblNewLabel_2);
		
		textField_2 = new JTextField();
		textField_2.setBounds(60, 77, 109, 21);
		panel.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("\u51FA\u53D1\u65F6\u95F4");
		lblNewLabel_3.setBounds(179, 24, 54, 15);
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("\u5230\u7AD9\u65F6\u95F4");
		lblNewLabel_4.setBounds(179, 52, 54, 15);
		panel.add(lblNewLabel_4);
		
		textField_3 = new JTextField();
		textField_3.setBounds(243, 21, 87, 21);
		panel.add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setBounds(243, 49, 87, 21);
		panel.add(textField_4);
		textField_4.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("\u603B\u7968\u91CF");
		lblNewLabel_5.setBounds(179, 80, 54, 15);
		panel.add(lblNewLabel_5);
		
		textField_5 = new JTextField();
		textField_5.setBounds(243, 77, 87, 21);
		panel.add(textField_5);
		textField_5.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("\u822A\u73ED\u53F7\uFF08\u64CD\u4F5C\uFF09");
		lblNewLabel_6.setBounds(396, 21, 92, 15);
		panel.add(lblNewLabel_6);
		
		textField_6 = new JTextField();
		textField_6.setBounds(482, 21, 125, 21);
		panel.add(textField_6);
		textField_6.setColumns(10);
		
		JButton btnNewButton = new JButton("\u589E\u52A0\u822A\u73ED");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//增加航班
				String flight_num = textField.getText();
				String startStop = textField_1.getText();
				String endStop = textField_2.getText();
				String startTime = textField_3.getText();
				String endTime = textField_4.getText();
				String ticketNum = textField_5.getText();
				if(flight_num.equals("")) {
					JOptionPane.showMessageDialog(null,"请输入航班号","Title",JOptionPane.PLAIN_MESSAGE);
				}else if(startStop.equals("")) {
					JOptionPane.showMessageDialog(null,"请输入起始站","Title",JOptionPane.PLAIN_MESSAGE);
				}else if(endStop.equals("")) {
					JOptionPane.showMessageDialog(null,"请输入终点站","Title",JOptionPane.PLAIN_MESSAGE);
				}else if(startTime.equals("")) {
					JOptionPane.showMessageDialog(null,"请输入出发时间","Title",JOptionPane.PLAIN_MESSAGE);
				}else if(endTime.equals("")) {
					JOptionPane.showMessageDialog(null,"请输入到站时间","Title",JOptionPane.PLAIN_MESSAGE);
				}else if(ticketNum.equals("")) {
					JOptionPane.showMessageDialog(null,"请输入总票量","Title",JOptionPane.PLAIN_MESSAGE);
				}else {
					
					Pattern pattern = Pattern.compile("[0-9]*\\.?[0-9]+");
					Matcher isNum = pattern.matcher(ticketNum);
					if(!isNum.matches()) {
						JOptionPane.showMessageDialog(null,"票量应为整数","Title",JOptionPane.PLAIN_MESSAGE);
					}else {
						JOptionPane.showMessageDialog(null,"添加成功","Title",JOptionPane.PLAIN_MESSAGE);
						//加入插入语句 文件读写
						String get_data="\n"+flight_num+"/"+startStop+"/"+endStop+"/"+startTime
						+"/"+endTime+"/"+ticketNum+"/"+ticketNum;
						FileOperations.write_txt("storage/flight.txt",get_data);
						
					}
				}
			}
		});
		btnNewButton.setBounds(385, 46, 93, 23);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("\u5220\u9664\u822A\u73ED");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//删除航班
				String Flight_be_Deleted = textField_6.getText();
				if(!Flight_be_Deleted.equals("")) {
					FileOperations.delete_txt_line("storage/flight.txt",Flight_be_Deleted, false);
					JOptionPane.showMessageDialog(null, "删除成功", "提示", 0);
					//refresh_table();刷新表格
				}else {
					JOptionPane.showMessageDialog(null, "请输入要删除的航班号", "提示", 0);
				}
			}
		});
		btnNewButton_1.setBounds(514, 48, 93, 23);
		panel.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("\u66F4\u6539\u822A\u73ED");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//修改航班
			}
		});
		btnNewButton_2.setBounds(385, 76, 93, 23);
		panel.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("\u641C\u7D22\u822A\u73ED");
		btnNewButton_3.setBounds(514, 76, 93, 23);
		panel.add(btnNewButton_3);
		
	}
}
