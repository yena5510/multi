package dbtest2;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MainMenu {
	 String user_id;
     String user_pw;
     String user_name;
     String user_major;
     String user_type;    
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
    	MainMenu menu = new MainMenu();
		menu.MainMenu();
        // TODO Auto-generated method stub
        
        
    }
    private void MainMenu() {
		String selectedMenuItem;
		Scanner MenuItemScan = new Scanner(System.in);
		
		System.out.println("|--------------------------------|\n");
		System.out.println("| Library book management system |\n");
		System.out.println("|--------------------------------|\n");
		System.out.println("|           1. Login             |\n");
		System.out.println("|           2. Sing in           |\n");
		System.out.println("|--------------------------------|\n");
		System.out.println("press number : ");
		
		selectedMenuItem = MenuItemScan.nextLine();
		
		switch(selectedMenuItem){
		case "1":
			login();
			break;
		case "2":
			registerStudent();
			break;
		default:
			System.out.println("The number is wrong.");
			break;		
		}
		
		MenuItemScan.close();		
	}

	private void login(){
		String id = null;
		String password = null;
		Scanner logindatascan = new Scanner(System.in);
		
		System.out.println("Press ID and Password\n");
		System.out.println("ID: ");
		id = logindatascan.nextLine();
		System.out.println("Password: ");
		password = logindatascan.nextLine();
		try {
	          
            Connection con = makeConnection();
            Statement stmt = con.createStatement();           
            String sql = "Select * from user where id="+id+";";
            ResultSet rs =stmt.executeQuery(sql); 
            while(rs.next()){
                user_id = rs.getString("id");
                user_pw = rs.getString("password");
                user_name = rs.getString("name");
                user_major = rs.getString("major");
                user_type = rs.getString("type");                
                //System.out.println(st_id+"\t"+st_pw+"\t"+st_name+"\t"+st_major+"\t"+st_type);
                //System.out.println(st_id + id + st_pw + password );
            } 
            if((user_id.equals(id))&&(user_pw.equals(password))){
            	if(user_type.equals(0))
            		goStudentMenu();
            	else
            		goManageMenu();
            	
            }
            else
            {
            	System.out.print("ID and password are not match");
                 goBackMenu();
                 logindatascan.close(); //이부분 잘 모르겠어><
            }
            	
            	
           
        } catch (SQLException e) {
            e.printStackTrace();
        }
						
	}
	
	private void registerStudent(){
		String id;
		String password;
		String name;
		String major;		
		Scanner registerdatascan = new Scanner(System.in);
		
		System.out.println("make new student account\nID must be same as your student number .\n");
		System.out.println("ID: ");
		id = registerdatascan.next();
		System.out.println("Password: ");
		password = registerdatascan.next();
		System.out.println("Name: ");
		name = registerdatascan.next();
		System.out.println("Major: ");
		major = registerdatascan.next();
		
		System.out.println(id+ " " + password + "  " + name + " " + major + "  ");
		try {
          
            Connection con = makeConnection();
            Statement stmt = con.createStatement();           
            String sql2 = "Insert into user values('"+id+"','"+password+"','"+name+"','"+major+"',0)";
            stmt.execute(sql2);            
        } catch (SQLException e) {
            e.printStackTrace();
        }
				
		goBackMenu();
		
		registerdatascan.close();
	}
	
	private void goBackMenu(){
		int selectedMenu;
		Scanner goMenuscan = new Scanner(System.in);
		
		System.out.println("\n\nDo you want to go abck to MainMenu? If you want press 1.");
		selectedMenu = goMenuscan.nextInt();
		if(selectedMenu == 1){
			MainMenu.main(null);
		}
		else
			System.out.println("Number is not 1."); 
	}
	
	private void goStudentMenu(){
		Student st = new Student();
    	st.StudentMenu();
	}
	private void goManageMenu(){
		Manage mg = new Manage();
		mg.Manage();  
	}

}
