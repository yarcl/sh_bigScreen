package com.xqkj.util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Properties;


public class JdbcTemplate {
	//  链接数据库参数
	private  static  String  driver;
	private  static  String  url;
	private  static  String  username;
	private  static  String  password;
	//  通过静态块  加载  配置文件 而且 值加载一次
	static{
		InputStream in = JdbcTemplate.class.getClassLoader().getResourceAsStream("db.properties");
		Properties  pro = new Properties();
		//  把文件流的形式配置信息加载  属性配置工具中
		try {
			pro.load(in);
			driver  = pro.getProperty("driver");
			url = pro.getProperty("url");
			username = pro.getProperty("username");
			password = pro.getProperty("password");

			//  注册驱动
			Class.forName(driver);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//  链接数据库所使用接口变量
	Connection conn;

	PreparedStatement  ps;

	Statement  st;

	ResultSet  rs;

	/**
	 * 得到链接
	 */
	public   Connection  getConnection(){
		try {
			conn =   DriverManager.getConnection(url,username,password);
			conn.setAutoCommit(false);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	/**
	 * 得到一个 预状态通道
	 */
	private  void  getPreStatement(String sql){
		this.getConnection();
		try {
			ps = conn.prepareStatement(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 得到状态通道
	 */
	private  void   getStatement(){
		this.getConnection();
		try {
			st = conn.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 在状态通道中 实现批处理操作
	 */
	public boolean executeBatch(List sqls) {
		this.getStatement();
		try {
			for (int i = 0; i < sqls.size(); i++) {
				st.addBatch(sqls.get(i).toString());
			}
			st.executeBatch();
			this.myCommit();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			this.myRollback();
		} finally{
			this.closeRes();
		}
		return false;
	}

	/**
	 * 在预编译的通道的基础上 处理sql 语句  select  .....
	 * @throws SQLException
	 */
	public   ResultSet   query(String sql,List  params) throws SQLException{
		this.getPreStatement(sql);
		this.bind(params);
		//  执行预编译通道sql 语句
		rs =  ps.executeQuery();
		return  rs;
	}
	/**
	 * 为预编译通道中 问号赋值的
	 * @param params
	 * @throws SQLException
	 */
	private  void  bind(List  params) throws SQLException{
		// 绑定参数
		if(params != null && !params.isEmpty()){
			for (int i = 0; i < params.size(); i++) {
				ps.setString(i+1,params.get(i).toString());
			}
		}
	}
	/**
	 * 在预编译通道中 执行数据操作
	 * @return
	 */
	public   boolean  updateData(String sql,List params){
		this.getPreStatement(sql);
		try {
			this.bind(params);
			// 执行sql 语句
			ps.executeUpdate();
			this.myCommit();
			return  true;
		} catch (SQLException e) {
			e.printStackTrace();
			this.myRollback();
		} finally{
			this.closeRes();
		}
		return false;
	}
	/**
	 * 基于状态通道下 sql 查询
	 * @throws SQLException
	 */
	public   ResultSet  query(String sql) throws SQLException{
		this.getStatement();
		rs= st.executeQuery(sql);
		return  rs;
	}
	public  void  myCommit(){
		try {
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public  void  myRollback(){
		try {
			conn.rollback();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 关闭资源方法
	 */
	public   void   closeRes(){
		try {
			if(rs!= null){
				rs.close();
			}
			if(st != null){
				st.close();
			}
			if(ps != null){
				ps.close();
			}
			if(conn != null){
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
