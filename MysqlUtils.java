import java.io.*;
import java.sql.*;
 
public class MysqlUtils {
	String _host;
	String _port;
	String _user;
	String _pass;
	String _database;
	Connection con = null;
	ResultSet rs;
 
	public MysqlUtils(String host, String port, String user, String pass, String database){
		_host = host;
		_port = port;
		_user = user;
		_pass = pass;
		_database = database;
	}
 
	public void connectDB() throws SQLException{
		String url = "jdbc:mysql://" + _host + "/" + _database + "?user=" + _user + "&password=" + _pass;
		con = DriverManager.getConnection(url);
	}
	
	public void disconnectDB() throws SQLException{
		if(con!=null){
			con.close();
		}
	}
	
	public ResultSet query(String sql) throws SQLException  { // 결과값 있는 명령문 select
		
		Statement st = con.createStatement();
		st.executeQuery(sql);		
		rs = st.getResultSet();
		
		return rs;
		
	}
	
	public int update(String sql) throws SQLException  {  //결과값 없는 명령문 insert delete update
		
		Statement st = con.createStatement();
		return  st.executeUpdate(sql);
	}
 
 
	public void refresh() throws SQLException{
		if(!con.isValid(4)){
			this.connectDB();
		}
	}
	
	
	public ResultSet getResultSet(){
		return rs;
	}
	
	public Connection getConnection(){
		return con;
	}
 
}
