import java.util.Scanner;

public class Student {

	public static void main(String[] args) {
		Student st = new Student();
		st.StudentMenu();
	}
	
	public static void StudentMenu(){
		String selectedMenuItem;
		Scanner MenuItemScan = new Scanner(System.in);
		
		System.out.println("|-----------------------------|\n");
		System.out.println("|----------학생 메인 메뉴----------|\n");
		System.out.println("| 1. 책 검색하기                               |\n");
		System.out.println("| 2. 대출 현황                                 |\n");
		System.out.println("|-----------------------------|\n");
		System.out.println(" 메뉴를 선택하세요: ");
		
		selectedMenuItem = MenuItemScan.nextLine();
		
		switch(selectedMenuItem){
		case "1":
			search();
			break;
		case "2":
			checkOut();
			break;
		default:
			System.out.println("지원되지 않는 메뉴 번호입니다.");
			break;		
		}
		
		MenuItemScan.close();	
	}
	
	private static void search(){
		Scanner scnbook = new Scanner(System.in);
		 System.out.print("책을 검색하시려면 책 제목의 일부를 입력해주세요. 메뉴로 다시 돌아가기길 원하면 숫자 1을 눌러주세요");
		 String back = scnbook.nextLine();
		 //책 제목이 들어왔을 경우 검색으로 가기
		 switch(back){
		 case "1":
			 StudentMenu();
			 break;
		default:
			System.out.println("지원되지 않는 메뉴번호입니다.");
			break;
			 
		 }	 
	}
	
	private static void checkOut(){
		Scanner scncheck = new Scanner(System.in);
		 System.out.print("대출한 책의 목록입니다. 반납을 원하시면 해당 책의 번호를 눌러주세요.");
		 String checkbooknu = scncheck.nextLine();
		 switch(checkbooknu){
		 case "1":
			 break;
		default:
			System.out.println("지원되지 않는 메뉴번호입니다.");
			break;
			 
		 }	 
	}
	
	

}
