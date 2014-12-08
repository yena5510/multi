import java.util.Scanner;
//ssss
//ssssss
public class Student {
	String userId = "1110193";

	public static void main(String[] args) {
		Student st = new Student();
		st.StudentMenu();
	}
	
	public void StudentMenu(){
		String selectedMenuItem;
		Scanner MenuItemScan = new Scanner(System.in);
		System.out.println("|-------------------------------------|\n");
		System.out.println("|----------Student Main Menu----------|\n");
		System.out.println("|           1. Search book            |\n");
		System.out.println("|          2. current checkout        |\n"); 
		System.out.println("|-------------------------------------|\n");
		System.out.println(" Select Menu: ");
	        selectedMenuItem = MenuItemScan.nextLine();
		
		switch(selectedMenuItem){
		case "1":
			search();
			break;
		case "2":
			CheckBorrowedList();
			break;
		default:
			System.out.println("The number you press is not apply");
			break;
		}
		MenuItemScan.close();
	}

	private void search(){
		Scanner scnbook = new Scanner(System.in);
		System.out.print("If you want to search, enter the part of book title. If you want to go back, press1");
		String back = scnbook.nextLine();
		switch(back){
		case "1":
			StudentMenu();
			break;
		default:
			System.out.println("The number you pressed is not apply.");
			break;
		}
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
