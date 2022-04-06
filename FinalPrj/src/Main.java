import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	
   	public static void PrintLine() {
		System.out.println("---------------------------------------");
	}
	
	public static void main(String[] args) {
		DB db = new DB();
	      Scanner sc = new Scanner(System.in);
	      Menu mn = new Menu();
	      int opt;
	      
	      
	      do {
				System.out.println("┌───────────────────────────────┐");
				System.out.println("│                           	│");
				System.out.println("│       모블병원 예약진료 시스템   	│");
				System.out.println("│                           	│");
				System.out.println("└───────────────────────────────┘");
				
				System.out.println("┌───────────────────────────────┐");
				System.out.println("│  (1)예약 (2)진료 (3)관리 (0)종료	│");
				System.out.println("└───────────────────────────────┘");
				
				System.out.print("(opt) : ");
				opt = sc.nextInt();
				
				switch(opt) {
				case 1: // 환자 예약시스템
					System.out.println("┌───────────────────────────────┐");
					System.out.println("│  (1) 환자 예약 시스템을 실행합니다.	│");
					System.out.println("└───────────────────────────────┘");
					// 진료과 선택
					mn.Reservation();
					break;
				case 2:
					System.out.println("┌───────────────────────────────┐");
					System.out.println("│  (2) 환자 진료 시스템을 실행합니다.	│");
					System.out.println("└───────────────────────────────┘");
					
					mn.menu2();
					break;
				case 3:
					System.out.println("┌───────────────────────────────┐");
					System.out.println("│  (3) 관리 시스템을 실행합니다.   	│");
					System.out.println("└───────────────────────────────┘");
					mn.menu3();
					break;
				default:
					System.out.println("┌───────────────────────────────┐");
					System.out.println("│         잘못된 입력입니다.      	│");
					System.out.println("└───────────────────────────────┘");
					break;
				case 0:
					System.out.println("┌───────────────────────────────┐");
					System.out.println("│       프로그램을 종료합니다.      	│");
					System.out.println("└───────────────────────────────┘");
					break;
				}
			} while(opt != 0);

	   }

}
