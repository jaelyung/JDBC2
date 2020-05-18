package com.human.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class DBConn {
	//.getInstance()
	//new DBConn(); �۾��� ���� ���ϰ�, ��ü�� �ѹ��� ������ �� �ְ��ϱ�
	private DBConn() {} //�ܺο��� ���� �Ұ��� ���ο��� �ѹ��� �����ϰ� ��
	private static Connection dbConn=null;
	private static Statement st=null;
	private static ResultSet rs=null;
	private static Scanner sc=new Scanner(System.in);
	
	public static ResultSet statementQuery(String sql) {
		try {
			if(st==null) {
				st=dbConn.createStatement();
			}
			//4.ResultSet ������ (��û�� ��������)
			rs=st.executeQuery(sql);			
			
		}  catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	
	public static int statementUpdate(String sql) {
		int resultValue=0;
		
		//dbConn=DBConn.getInstance();
//		DBConn.getInstance();
		try {
			if(st==null) {
				st=dbConn.createStatement();
			}
			//���α׷������� auto commit �⺻��
			resultValue=st.executeUpdate(sql);
//			if(n==1) {//n�� ����� ����
//				System.out.println("������");
//			}else {
//				System.out.println("��������");
//			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
//			DBConn.dbClose();
		}
		return resultValue;
	}
	//insert, select ...  DB�� �ݺ������� ���� �ݴ� �۾��� �ڵ�� �ۼ�
	public static Connection getInstance() {
		if(dbConn==null) {//��ü�� �ƿ� ������
			try {
				//1.����� �����ͺ��̽�  ����̺� ���
				Class.forName("oracle.jdbc.driver.OracleDriver");
				System.out.println("����̹� �ε��Ϸ�");
				//2.�ش� �����ͺ��̽��� ���� url, user, pw
				String url="jdbc:oracle:thin:@localhost:1521:xe";
				String user="human";
				String pw="human";
				dbConn=DriverManager.getConnection(url, user, pw);
				System.out.println("�����ͺ��̽� ���Ӽ���");
				
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return dbConn;
	}
	public static void dbClose() {
		try {
			if(rs!=null) rs.close();
			if(st!=null) st.close();//Statement�� ���� �ݴ´�
			if(dbConn!=null) dbConn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			rs=null;
			st=null;
			dbConn=null;
		}
	}
	
	public static int inputInt() {
		System.out.print("�����Է�>>");
		return Integer.parseInt(sc.nextLine());		
	}
	public static double inputDouble() {
		System.out.print("�Ǽ��Է�>>");
		return Double.parseDouble(sc.nextLine());
	}
	public static String inputString() {
		System.out.print("���ڿ��Է�>>");
		return sc.nextLine();
	}
	public static Date inputDate() {//�Է¹��� ���ڿ��� �ð����� ����
		Date returnValue=null;
		SimpleDateFormat transFormat= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		System.out.print("��¥�Է�(yyyy-mm-dd hh24:mi:ss)>>");
		String inputString=sc.nextLine();
		
		try {
			returnValue=transFormat.parse(inputString);//���ڿ��� �ð���ü�� ����
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return returnValue;
	}
	
	public static String dateToString(Date d) {
		DateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return df.format(d);
	}
	
	
	
	
	
}
