import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;


public class StudentBook {
	private MysqlUtils mysql = new MysqlUtils("localhost", "3306", "root", "1111", "test");// "library");
	
	void SearchBook(String selectBookTitle){
		try{
			mysql.connectDB();
			String showData = " select * from book WHERE title LIKE '%"+selectBookTitle+"%' ";
			ResultSet rs = mysql.query(showData);
			
			if(rs.next()) {				
				System.out.println("[search result]");
				rs.beforeFirst();
				System.out.println("___________________________________________________________________________________");
				System.out.println(String.format("  %s  ", "book_number") + "|" 
				                 + String.format(" %20s ", "title") + "|"  
				                 + String.format(" %20s ", "writer") + "|"  
				                 + String.format(" %20s ", "publisher"));
				System.out.println("___________________________________________________________________________________");
				while(rs.next()) {
					System.out.println(String.format("     %5s     ", rs.getString("book_number")) + "|" 
			                 		 + String.format(" %20s ",  rs.getString("title")) + "|"  
			                         + String.format(" %20s ", rs.getString("writer")) + "|"  
			                         + String.format(" %20s ", rs.getString("publisher")));
				}
				System.out.println("___________________________________________________________________________________");
			} else { 
				System.out.println("검색 결과가 없습니다."); 
			}
			System.out.println("");

		} catch(SQLException e1){
			//e1.printStackTrace();
			System.out.println("search failed");
		} 
	}
	
	void showBookData(String booknumber){
		try{
			mysql.connectDB();
			String showData = " select * from book WHERE book_number LIKE '"+booknumber+"' ";;
			ResultSet rs = mysql.query(showData);
			System.out.println("[" + booknumber+"번의 책 정보]");
			if(rs.next()) {						
				rs.beforeFirst();
				System.out.println("___________________________________________________________________________________");
				System.out.println(String.format("  %s  ", "book_number") + "|" 
				                 + String.format(" %20s ", "title") + "|"  
				                 + String.format(" %20s ", "writer") + "|"  
				                 + String.format(" %20s ", "publisher"));
				System.out.println("___________________________________________________________________________________");
				while(rs.next()) {
					System.out.println(String.format("     %5s     ", rs.getString("book_number")) + "|" 
			                 		 + String.format(" %20s ",  rs.getString("title")) + "|"  
			                         + String.format(" %20s ", rs.getString("writer")) + "|"  
			                         + String.format(" %20s ", rs.getString("publisher")));
				}
				System.out.println("___________________________________________________________________________________");
			} else { 
				System.out.println("잘못된 책 번호 입니다."); 
			}
			System.out.println("");

		} catch(SQLException e1){
			//e1.printStackTrace();
			System.out.println("search failed");
		} 
	}


	
	
}
