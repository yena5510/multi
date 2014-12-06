package dbtest2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Manage {   
	public static Connection makeConnection(){
        String url = "jdbc:mysql://localhost:3307/library";
        String id = "root";
        String pw = "gina1768";
        Connection con = null;
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
           // System.out.println("success ... Connecting database.");
            con = DriverManager.getConnection(url, id, pw);
           // System.out.println("Success to connect database");
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        return con;
    }
	
	public static void main(String[] args) {
		Manage mg = new Manage();
		mg.Manage();       
    }
	
	public static void Manage() {
		Scanner scn = new Scanner(System.in);
        
        System.out.println("-------Menu------------");
        System.out.println("1. 책 등록");
        System.out.println("2. 책 검색");
        System.out.println("3. 책 삭제");
        System.out.println("4. 책 정보 업데이트");
        System.out.println("-----------------------");
        System.out.print("메뉴를 선택하세요 => ");
        
        int menu = scn.nextInt();

        switch (menu) {
        case 1:
        	Register();
            break;
        case 2:
        	Search();
            break;
        case 3:
        	Delete();
            break;
        case 4:
        	Update();
            break;
        default:
            System.out.println("지원되지 않는 메뉴 번호입니다.");
            break;
        }       
	}

	private static void Register() {
		String book_number;
		String title;
		String writer;
		String publisher;
		
		
		Scanner scn2 = new Scanner(System.in);
		
		System.out.println("make new book\n");
		System.out.println("Book number: ");
		book_number = scn2.next();
		System.out.println("book title: ");
		title = scn2.next();
		System.out.println("Name: ");
		writer = scn2.next();
		System.out.println("Major: ");
		publisher = scn2.next();

		System.out.println(book_number+ " " + title + "  " + writer + " " + publisher + "  ");
        
		
		try {
	          
            Connection con = makeConnection();
            Statement stmt = con.createStatement();           
            String sql2 = "Insert into book values('"+book_number+"','"+ title+"','"+writer+"','"+publisher+"',NULL)";
            stmt.execute(sql2);            
        } catch (SQLException e) {
            e.printStackTrace();
        }	
		
		System.out.print("book registeration is done.menu로 돌아가고 싶으면 1를 입력하세요 => ");
        
        int rg = scn2.nextInt();
        switch (rg) {
        case 1:
        	Manage();
            break;
        default:
            System.out.println("잘못된 입력입니다.");
            break;
        }	
	}
	
	private static void Search() {
		Scanner scn3 = new Scanner(System.in);
		
        System.out.print("menu로 돌아가고 싶으면 1를 입력하세요 => ");
        
        int sear = scn3.nextInt();
        switch (sear) {
        case 1:
        	Manage();
            break;
        default:
            System.out.println("잘못된 입력입니다.");
            break;
        }		
	}
	
	private static void Delete() {
		Scanner scn4 = new Scanner(System.in);
		
        System.out.print("menu로 돌아가고 싶으면 1를 입력하세요 => ");
        
        int del = scn4.nextInt();
        switch (del) {
        case 1:
        	Manage();
            break;
        default:
            System.out.println("잘못된 입력입니다.");
            break;
        }		
	}
	
	private static void Update() {
		Scanner scn5 = new Scanner(System.in);
		
        System.out.print("menu로 돌아가고 싶으면 1를 입력하세요 => ");
        
        int up = scn5.nextInt();
        switch (up) {
        case 1:
        	Manage();
            break;
        default:
            System.out.println("잘못된 입력입니다.");
            break;
        }		
	}

}

