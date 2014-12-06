package dbtest2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
//rebase master
//Manage clas
//master
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
        
        System.out.println("-------Manage Menu------------");
        System.out.println("1. Register the book");
        System.out.println("2. Search the book");
        System.out.println("3. Delete the book");
        System.out.println("4. Update book information");
        System.out.println("-----------------------");
        System.out.print("Select the menu => ");
        
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
            System.out.println("The number you press is not apply.");
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
		System.out.println("writer: ");
		writer = scn2.next();
		System.out.println("publisher: ");
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
		
		System.out.print("book registeration is done.If you want to go back to menu press1 => ");
        
        int rg = scn2.nextInt();
        switch (rg) {
        case 1:
        	Manage();
            break;
        default:
            System.out.println("Insert is wrong.");
            break;
        }	
	}
	
	private static void Search() {
		Scanner scn3 = new Scanner(System.in);
		
        System.out.print(" If you want to go back to menu press1=> ");
        
        int sear = scn3.nextInt();
        switch (sear) {
        case 1:
        	Manage();
            break;
        default:
            System.out.println("Insert is wrong.");
            break;
        }		
	}
	
	private static void Delete() {
		Scanner scn4 = new Scanner(System.in);
		
        System.out.print("If you want to go back to menu press1=> ");
        
        int del = scn4.nextInt();
        switch (del) {
        case 1:
        	Manage();
            break;
        default:
            System.out.println("Insert is wrong.");
            break;
        }		
	}
	
	private static void Update() {
		Scanner scn5 = new Scanner(System.in);
		
        System.out.print("If you want to go back to menu press1 => ");
        
        int up = scn5.nextInt();
        switch (up) {
        case 1:
        	Manage();
            break;
        default:
            System.out.println("Insert is wrong.");
            break;
        }		
	}

}

