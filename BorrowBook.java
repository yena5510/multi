
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;


public class BorrowBook {
	private MysqlUtils mysql = new MysqlUtils("localhost", "3306", "root", "1111", "library");
	
	/*public static void main(String[] args){
		BorrowBook book = new BorrowBook();
		//book.BorrowBook("1115669", "4");
		//book.CheckBorrowedBookList("1110193");
		book.CheckoutBorrowedBook("1110193", "2");
	}	*/

	private void BorrowBook(String userId, String selectBooknumber){	 //책 빌리기	
		try{
			mysql.connectDB();
			String updateSQL = "UPDATE book SET borrow = "+ userId + " WHERE book_number = "+ selectBooknumber;
			mysql.update(updateSQL);
			System.out.println(selectBooknumber + "번 책이 반납되었습니다.");
		} catch(SQLException e1){
			//e1.printStackTrace();
			System.out.println("borrow failed");
		} finally{			
		}		
	}
	
	void CheckBorrowedBookList(String userId){ //대출한 책 확인하기
		try{
			mysql.connectDB();
			
			String sql = "SELECT * FROM book WHERE borrow = " + userId;
			ResultSet selectBook = mysql.query(sql);
			System.out.println(userId + "님이 대출하신 책의 목록입니다.");
			System.out.println("________________________");
			System.out.println("book_number  " + " title");
			System.out.println("------------------------");
			
			while(selectBook.next()){
				int number = Integer.parseInt(selectBook.getString("book_number"));
				System.out.println(" " + String.format("%05d", number) + "        " + selectBook.getString("title"));
			}	
			System.out.println("________________________");
		} catch(SQLException e1){
			//e1.printStackTrace();
			System.out.println("booklist failed");
		} finally{			
		}	
	}
 
	void CheckoutBorrowedBook(String userId, String selectBooknumber){   //반납하기 
		try{
			mysql.connectDB();
			String updateSQL = "UPDATE book SET borrow = NULL WHERE book_number = "+ selectBooknumber +" AND borrow = " + userId;
			mysql.update(updateSQL);
			System.out.println(selectBooknumber + "번 책이 반납되었습니다.");
		} catch(SQLException e1){
			//e1.printStackTrace();
			System.out.println("checkout failed");
		} finally{			
		}	
	}


}
