import java.util.Scanner;

public class Student {
	String userId = "1110193";

	public static void main(String[] args) {
		Student st = new Student();
		st.StudentMenu();
	}
	
	public void StudentMenu(){
		String selectedMenuItem;
		Scanner MenuItemScan = new Scanner(System.in);
		System.out.println("|-----------------------------|\n");
		System.out.println("|----------학생 메인 메뉴----------|\n");
		System.out.println("| 1. 책 검색하기 |\n");
		System.out.println("| 2. 대출 현황 |\n");
		System.out.println("|-----------------------------|\n");
		System.out.println(" 메뉴를 선택하세요: ");
		selectedMenuItem = MenuItemScan.nextLine();
		
		switch(selectedMenuItem){
		case "1":
			search();
			break;
		case "2":
			CheckBorrowedList();
			break;
		default:
			System.out.println("지원되지 않는 메뉴 번호입니다.");
			break;
		}
		MenuItemScan.close();
	}

	private void search(){
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
		
	private void CheckBorrowedList(){
		Scanner scncheck = new Scanner(System.in);
		
		BorrowBook borrowedbook = new BorrowBook();
		borrowedbook.CheckBorrowedBookList(userId);
		
		System.out.print("반납을 원하시면 1번을 누르세요. 원하지 않으시면 아무 키를 눌러주세요");
		String checkbookmenu = scncheck.nextLine();
		switch(checkbookmenu){
		case "1":
			System.out.print("반납을 원하는 책의 번호를 입력해주세요. ");
			String selectedbook = scncheck.nextLine();
			borrowedbook.CheckoutBorrowedBook(userId, selectedbook);
			break;
		default:
			System.out.println("이전 메뉴로 돌아가겠습니다.");
			break;
		}
		Student st = new Student();
		st.StudentMenu();
	}
}
