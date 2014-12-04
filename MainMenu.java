package dbtest2;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MainMenu {
	
	public static Connection makeConnection(){
        String url = "jdbc:mysql://localhost:3307/booktest";
        String id = "root";
        String pw = "gina1768";
        Connection con = null;
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("드라이버 적재 성공 ... 데이터베이스에 연결중입니다.");
            con = DriverManager.getConnection(url, id, pw);
            System.out.println("데이터베이스 연결 성공");
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
		
		System.out.println("|-----------------------------|\n");
		System.out.println("| 도서관 도서 관리 프로그램   |\n");
		System.out.println("|-----------------------------|\n");
		System.out.println("| 1. 로그인                   |\n");
		System.out.println("| 2. 가입하기                 |\n");
		System.out.println("|-----------------------------|\n");
		System.out.println("메뉴를 선택하세요: ");
		
		selectedMenuItem = MenuItemScan.nextLine();
		
		switch(selectedMenuItem){
		case "1":
			login();
			break;
		case "2":
			registerStudent();
			break;
		default:
			System.out.println("지원되지 않는 메뉴 번호입니다.");
			break;		
		}
		
		MenuItemScan.close();		
	}

	private void login(){
		String id = null;
		String password = null;
		Scanner logindatascan = new Scanner(System.in);
		
		System.out.println("아이디와 비밀번호를 입력해주세요\n");
		System.out.println("ID: ");
		id = logindatascan.nextLine();
		System.out.println("Password: ");
		password = logindatascan.nextLine();
		
		System.out.println("id: " + id +  "  password: " + password);
		
		goBackMenu();
		
		logindatascan.close();				
	}
	
	private void registerStudent(){
		String id;
		String password;
		String name;
		String major;		
		Scanner registerdatascan = new Scanner(System.in);
		
		System.out.println("새로운 학생 계정 생성하기\n아이디는 학번으로 입력해주세요.\n");
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
            // 1. 연결 객체 만들기
            Connection con = makeConnection();// 만약 이 문장이 try문 밖에 있으면 makeConnection()이 실행은 되지만
            // Connection con에 저장하고. 아랫줄의 con.createStatement()에서 사용할 con과 연동이 되지 않을 수 있다!!(주의)
            // 2. statment 객체 만들기
            Statement stmt = con.createStatement();
            // 3. ResultSet 만들기
            String sql1 = "select * from student_db";
            ResultSet rs = stmt.executeQuery(sql1);//프로시저 랭귀지 sql. 쿼리문만 바꾸는 랭귀지 기법
            // 4. 데이터 추출하기.
            while(rs.next()){
                String st_id = rs.getString("studentid");
                String st_pw = rs.getString("studentpw");
                String st_name = rs.getString("studentname");
                String st_major = rs.getString("studentmajor");
                
                
                System.out.println(st_id+"\t"+st_pw+"\t"+st_name+"\t"+st_major);
            }
            
            String sql2 = "Insert into student_db values('"+id+"','"+password+"','"+name+"','"+major+"')";
            stmt.execute(sql2);
            rs = stmt.executeQuery(sql1);
            while(rs.next()){
                String st_id = rs.getString("studentid");
                String st_pw = rs.getString("studentpw");
                String st_name = rs.getString("studentname");
                String st_major = rs.getString("studentmajor");
                
                System.out.println(st_id+"\t"+st_pw+"\t"+st_name+"\t"+st_major);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
				
		goBackMenu();
		
		registerdatascan.close();
	}
	
	private void goBackMenu(){
		int selectedMenu;
		Scanner goMenuscan = new Scanner(System.in);
		
		System.out.println("\n\n메인 메뉴로 돌아가시겠습니까? 원하시면 1을 입력해주세요.");
		selectedMenu = goMenuscan.nextInt();
		if(selectedMenu == 1){
			MainMenu.main(null);
		}
		else
			System.out.println("1이 아닙니다."); //정하기
	}

}
