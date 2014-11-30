import java.util.Scanner;

public class Manage {
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
		Scanner scn2 = new Scanner(System.in);
		
        System.out.print("menu로 돌아가고 싶으면 1를 입력하세요 => ");
        
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
