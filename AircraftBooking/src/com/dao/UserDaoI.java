package com.dao;
 
import java.io.IOException;
import java.util.ArrayList;
 import com.util.FileOperations;
/**
 * 用户操作的具体实现类
 */
public class UserDaoI implements UserDaoImpl {
 
	private static ArrayList<User> array = new ArrayList<User>();
 
	@Override
	public boolean regist(String username, String password) {
		boolean flag = false;
		String []message = new String[2]; 
		
		message[0] = username;
		message[1] = password;
		try {
			new FileOperations().write(message);
			flag=true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}
 
	@Override
	public boolean isLogin(String username, String password) {// 遍历集合，获取每一个用户
		boolean flag = false;
		try {
			if (new FileOperations().check1(username,password))
			{
				flag=true;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		return flag;
	}
 
	@Override
	public void Login(String username, String password) {
		System.out.println("用户" + username + "欢迎使用飞机订票系统");
	}
}