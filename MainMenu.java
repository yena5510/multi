import java.util.Scanner;

public class MainMenu {
	
	public static void main(String[] args){
		MainMenu menu = new MainMenu();
		menu.MainMenu();
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
		String majar;		
		Scanner registerdatascan = new Scanner(System.in);
		
		System.out.println("새로운 학생 계정 생성하기\n아이디는 학번으로 입력해주세요.\n");
		System.out.println("ID: ");
		id = registerdatascan.next();
		System.out.println("Password: ");
		password = registerdatascan.next();
		System.out.println("Name: ");
		name = registerdatascan.next();
		System.out.println("Majar: ");
		majar = registerdatascan.next();
		
		System.out.println(id+ " " + password + "  " + name + " " + majar + "  ");
				
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
