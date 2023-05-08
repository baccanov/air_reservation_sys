package com.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

public class FileOperations {
	
	
	/*
	 * 将注册的信息写入文本
	 */
	public void write(String[] message)throws IOException
	{
		File file=new File("storage/User.txt");
		String messagesum="";
		for (int i=0; i<2; i++)  //将信息格式化存储
			messagesum+=message[i]+"~";
        if(!file.exists())
            file.createNewFile();
        FileOutputStream out=new FileOutputStream(file,true); //建立输出对象，true表示追加       
        StringBuffer sb=new StringBuffer();      //创建字符串流
        sb.append(messagesum+"\n");				//向字符串流中添加信息
         out.write(sb.toString().getBytes("gb2312"));         //将字符串流中的信息写入文本
        out.close();			//关闭
	}
	
	//在登录时 验证账号是否存在
	public boolean  check1(String countname,String pwd) throws IOException{
			
			File file=new File("storage/User.txt");   //创建文件类
	        if(!file.exists()||file.isDirectory()) //判断文件是否存在
	            //throw new FileNotFoundException();
	        	file.createNewFile();
	        BufferedReader br=new BufferedReader(new FileReader(file)); //创建读入缓冲流，按行读入
	        String temp=null;   
	       // StringBuffer sb=new StringBuffer();   
	        temp=br.readLine();   //先读取一行
	        while(temp!=null){
	        	String sbstring = temp.toString();   //转化为string
	        	int n = sbstring.length();            //测字符串长度
	        	String []message = new String[5];     //按~拆分 成5个字符串数组，按账号和密码进行信息验证
	        	int k=0;
	        	
	        	for (int i=0; i<2; i++)
	        		message[i]="";
	        	//我们在写入用户时用~分割， 所以我们利用~在分割开来
	        	for (int i=0; i<n; i++)
	        	{
	        		if(sbstring.charAt(i)=='~')
	        		{
	        			//System.out.println("@"+message[k]);
	        			k++;
	        		}
	        		else 
	        		{
	        			message[k] += sbstring.charAt(i);
	        		}
	        	}
	        	if (countname.equals(message[0])&&pwd.equals(message[1]))//比较账户密码是否相等
	        		return true;
	            temp=br.readLine();  //读取下一行
	        }
	        return false;
		}
	

	//读取文件某一行的方法，传入文件地址和航班号
	public static String read_txt_lines(String src,String flight_number){
		try {
	        File file = new File(src);
	        FileReader fileReader = new FileReader(file);  
	        LineNumberReader reader = new LineNumberReader(fileReader);
	        int number = 1;
	        String txt = "";
	        int lines = 0;//从第一行开始读取
	        while (txt != null) {
	            lines++;
	            txt = reader.readLine();
	            if (lines > number&&txt!=null&&!txt.trim().equals("")) {
	            	String[] data=txt.split("/");
	            	if(flight_number.trim().equals(data[0])) {
	        			reader.close();
	        			fileReader.close();
	            		return txt;
	            	}
	            }
	        }
			reader.close();
			fileReader.close();
		}catch (IOException e) {
			System.out.println("读取文件出错");
		}
		return null;
	}
	//写入文件
    public static void write_txt(String src,String message){
    	try {
	        File file = new File(src);
	        BufferedWriter out = new BufferedWriter(new FileWriter(file,true));
	        out.write(message);
	        out.flush();
	        out.close();
    	}catch (IOException e) {
			System.out.println("写入文件出错");
		}
    }
  //删除指定行，依据航班号匹配
    public static Map<String, Object> delete_txt_line(String file, String message, boolean upRemoveLine) {

        Map<String, Object> map = new HashMap<>(8);
        map.put("success", false);
        map.put("error", "");
        try {
            //获取原文件
            File oldFile = new File(file);
            if (!oldFile.isFile()) {
                map.put("error", "该文件未找到:" + file);
                return map;
            }
            //构造临时文件
            File newFile = new File(oldFile.getAbsolutePath() + ".tmp");
            BufferedReader br = new BufferedReader(new FileReader(file));
            PrintWriter pw = new PrintWriter(new FileWriter(newFile));

            String line = null;//某一行的值
            while ((line = br.readLine()) != null) {
            	String[] data=line.split("/");
                if (!data[0].trim().equals(message)) {
                    pw.println(line);
                    pw.flush();
                }
            }
            pw.close();
            br.close();
            //删除原文件
            if (!oldFile.delete()) {
                map.put("error", "该文件删除失败:" + file);
                return map;
            }
            //重命名
            if (!newFile.renameTo(oldFile)) {
                map.put("error", "该文件重命名失败:" + file);
                return map;
            }
            map.put("success", true);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            map.put("error", ex.getMessage());
        } catch (IOException ex) {
            ex.printStackTrace();
            map.put("error", ex.getMessage());
        }
        return map;
    }
  //修改指定行
    public static Map<String, Object> revise_txt_line(String file, String old_message,String new_message) {

        Map<String, Object> map = new HashMap<>(8);
        map.put("success", false);
        map.put("error", "");
        try {
            //获取原文件
            File oldFile = new File(file);
            if (!oldFile.isFile()) {
                map.put("error", "该文件未找到:" + file);
                return map;
            }
            //构造临时文件
            File newFile = new File(oldFile.getAbsolutePath() + ".tmp");
            BufferedReader br = new BufferedReader(new FileReader(file));
            PrintWriter pw = new PrintWriter(new FileWriter(newFile));

            String line = null;//某一行的值
            while ((line = br.readLine()) != null) {
                if (!old_message.equals(line)) {
                    pw.println(line);
                    pw.flush();
                }else {
                    pw.println(new_message);
                    pw.flush();
                }
            }
            pw.close();
            br.close();
            //删除原文件
            if (!oldFile.delete()) {
                map.put("error", "该文件删除失败:" + file);
                return map;
            }
            //重命名
            if (!newFile.renameTo(oldFile)) {
                map.put("error", "该文件重命名失败:" + file);
                return map;
            }
            map.put("success", true);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            map.put("error", ex.getMessage());
        } catch (IOException ex) {
            ex.printStackTrace();
            map.put("error", ex.getMessage());
        }
        return map;
    }
  //筛选航线方法
	public static void filter_txt(String src,String starting_point,String end_point,String departure_time,Queue<String[]> queue){
		try {
	        File file = new File(src); 
	        FileReader fileReader = new FileReader(file);  
	        LineNumberReader reader = new LineNumberReader(fileReader);
	        int number = 1;
	        String txt = "";
	        int lines = 0;
	        while (txt != null) {
	            lines++;
	            txt = reader.readLine();  
	            if (lines > number&&txt!=null&&!txt.trim().equals("")) {
	            	String[] data=txt.split("/");
	            	if(starting_point.trim().equals(data[1])&&end_point.trim().equals(data[2])&&departure_time.trim().equals(data[3].substring(0, 10)))
	            	queue.offer(data);
	            }
	        }
			reader.close();
			fileReader.close();
		}catch (IOException e) {
			System.out.println("读取文件出错");
		}
	}
	//关键词搜索,model:0为全部匹配，1为只匹配航班号
	public static void search_txt_line(String src,String message,Queue<String[]> queue,int model){
		try {
	        File file = new File(src); 
	        FileReader fileReader = new FileReader(file);  
	        LineNumberReader reader = new LineNumberReader(fileReader);
	        int number = 1;
	        String txt = "";
	        int lines = 0;//从第一行开始读取
	        message=message.trim();
	        while (txt != null) {
	            lines++;
	            txt = reader.readLine();  
	            if (lines > number&&txt!=null&&!txt.trim().equals("")) {
	            	String[] data=txt.split("/");
	            	if((model==0)&&(message.equals(data[0])||message.equals(data[1])||message.equals(data[2])||message.equals(data[3])||message.equals(data[4])||message.equals(data[5])))
	            		queue.offer(data);
	            	else if((model==1)&&(message.equals(data[0])))
	            		queue.offer(data);
	            }
	        }
			reader.close();
			fileReader.close();
		}catch (IOException e) {
			System.out.println("读取文件出错");
		}
	}
}
