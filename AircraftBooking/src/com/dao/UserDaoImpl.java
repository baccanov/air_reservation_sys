package com.dao;
/**
 * 针对用户进行操作的接口
 */
public interface UserDaoImpl {
	

	 
		public boolean regist(String username, String password);
		/*
		 * 用户注册功能
		 */
		public boolean isLogin(String username, String password);
		/*
		 * 用户登录时功能
		 */
		public void Login(String username, String password);
		/*
		 * 用户登录后功能
		 */
	 
}
