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
		System.out.print("책을 검색하시려면 책 제목의 일부를 입력해주세요. ");
		String back = scnbook.nextLine();
		//책 제목이 들어왔을 경우 검색으로 가기
		
		StudentBook sbook = new StudentBook();
		sbook.SearchBook(back);
		//////////////////////////////////////////////////////////////////////////////  이거 고치기
		System.out.print("자세히 보길 원하는 책 번호를 입력해주세요. ");
		String selectedBookNumber = scnbook.nextLine();
		sbook.showBookData(selectedBookNumber);
		///////////////////////////////////////////////////////////////////////////////
		System.out.print("대출을 원하시면 1을 입력해주세요. 이전 메뉴로 돌아가고 싶으시면 2을 입력해주세요. ");
		String menu = scnbook.nextLine();
		if(menu.equals("1")){
			BorrowBook bbook = new BorrowBook();
			bbook.BorrowBook(userId, selectedBookNumber);
		}
		StudentMenu();
	}
		
	private void CheckBorrowedList(){
		Scanner scncheck = new Scanner(System.in);
		
		BorrowBook borrowedbook = new BorrowBook();
		int exitBorrowedbook = borrowedbook.CheckBorrowedBookList(userId);
		
		if(exitBorrowedbook == 1){
			System.out.print("If you want to return the book, press 1. If you don't want anythig, press anything");
			String checkbookmenu = scncheck.nextLine();
			switch(checkbookmenu){
			case "1":
				System.out.print("Press the book number what you want to return.");
				String selectedbook = scncheck.nextLine();
				borrowedbook.CheckoutBorrowedBook(userId, selectedbook);
				break;
			default:
				System.out.println("Go back before Menu");
				break;
			}
		}
		Student st = new Student();
		st.StudentMenu();
	}
}
