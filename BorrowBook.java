import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;


public class BorrowBook {
	private MysqlUtils mysql = new MysqlUtils("localhost", "3306", "root", "1111", "library");

	int CheckBook(String userId, String selectBooknumber){
		int check = 1; //1이면 대출 가능, 2이면 대출 불가능
		try{
			mysql.connectDB();
			
			String checkBorrowedSQL = "SELECT * FROM book WHERE book_number = "+ selectBooknumber;
			ResultSet checkRs = mysql.query(checkBorrowedSQL);	
			
			if(checkRs.next()){
				String result = checkRs.getString("borrow"); //null 대출 가능 / 같으면 대출 한거고 / 다르면 대출 못하고
				if(result != null){
					check = 2;
					if(result.equals(userId))
						System.out.println("이미 대출 하셨습니다. ");
					else{
						System.out.println("대출 불가능합니다. ");
					}
				}
			}				

		} catch(SQLException e1){
			//e1.printStackTrace();
			System.out.println("borrow failed");
		} 	
		
		return check;
	}
	
	void BorrowBook(String userId, String selectBooknumber){	 //책 빌리기	
		try{
			mysql.connectDB();
			if(CheckBook(userId, selectBooknumber) == 1){
				String updateSQL = "UPDATE book SET borrow = "+ userId + " WHERE book_number = "+ selectBooknumber;
				if(mysql.update(updateSQL) > 0 )
					System.out.println(selectBooknumber + "번 책이 대출되었습니다.");
				else
					System.out.println(selectBooknumber + "번 책은 없습니다.");
			}

		} catch(SQLException e1){
			//e1.printStackTrace();
			System.out.println("borrow failed");
		} finally{			
		}		
	}
	
	int CheckBorrowedBookList(String userId){ //대출한 책 확인하기
		int result = 1;
		try{
			mysql.connectDB();			
			String sql = "SELECT * FROM book WHERE borrow = " + userId;
			ResultSet selectBook = mysql.query(sql);				
			if(selectBook.next()){
				System.out.println(userId + "님이 대출하신 책의 목록입니다.");
				System.out.println("________________________");
				System.out.println("book_number  " + " title");
				System.out.println("------------------------");
				selectBook.beforeFirst();
				while(selectBook.next()){
					int number = Integer.parseInt(selectBook.getString("book_number"));
					System.out.println(" " + String.format("%d", number) + "        " + selectBook.getString("title"));
				}	
				System.out.println("________________________");
			}	
			else{
				result = 2;
				System.out.println(userId + "님이 대출하신 책은 없습니다.");
			}				
			
		} catch(SQLException e1){
			//e1.printStackTrace();
			System.out.println("booklist failed");
		} finally{			
		}
		
		return result;
	}
 
	void CheckoutBorrowedBook(String userId, String selectBooknumber){   //반납하기 
		try{
			mysql.connectDB();
			String updateSQL = "UPDATE book SET borrow = NULL WHERE book_number = "+ selectBooknumber +" AND borrow = " + userId;
			if(mysql.update(updateSQL) > 0 ){
				System.out.println(selectBooknumber + "번 책이 반납되었습니다.");
			}
			else{
				System.out.println(selectBooknumber + "번 책은 없습니다.");
			}
		} catch(SQLException e1){
			//e1.printStackTrace();
			System.out.println("checkout failed");
		} finally{			
		}	
	}
}
