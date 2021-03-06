import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.*;

import com.mysql.cj.exceptions.RSAException;
import com.mysql.cj.protocol.FullReadInputStream;

public class Menu {
	Scanner sc = new Scanner(System.in);
	Management mng = new Management();
	SimpleDateFormat format = new SimpleDateFormat ( "HH:mm:ss");
	DB db = new DB();
	int opt;
	int patientnum;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	String name;
	String ResidentNum;
	String address;
	String gender;
	int age, no;

	void Reservation() {
		System.out.println("┌───────────────────────────────┐");
		System.out.println("│    [1]예약 [2]예약취소 [0]이전   	│");
		System.out.println("└───────────────────────────────┘");
		
		System.out.print("[opt] : ");
		opt = sc.nextInt();
		switch(opt) {
		case 1: // [1]예약
			menu1();
			break;
		case 2: // [2]예약취소
			Cancel();
			break;
		case 0: // [0]이전
			break;
		default:
			System.out.println("┌───────────────────────────────┐");
			System.out.println("│         잘못된 입력입니다.      	│");
			System.out.println("└───────────────────────────────┘");
			break;
		}
	}
	
	void Cancel() {
		String doctorname = null;
		Time Time = null;
		while(true) {
		System.out.println("┌───────────────────────────────────────┐");
		System.out.println("│           예약 취소를 실행합니다.         	│");
		System.out.println("│  환자 이름과 주민등록번호를 입력해주세요(-포함)	│");
		System.out.println("└───────────────────────────────────────┘");
		
		System.out.print("[이름] : ");
		name = sc.next();
		System.out.print("[주민등록번호] : ");
		ResidentNum = sc.next();
		
		String sql = "SELECT * FROM Wait WHERE name = '" + name + "' AND ResidentNum = '";
		
		 try {
			pstmt = db.conn.prepareStatement(sql + ResidentNum + "'");
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				doctorname = rs.getString("DoctorName");
				Time = rs.getTime("Time");
			}
			if(doctorname == null) {
				System.out.println("┌───────────────────────────────────────┐");
				System.out.println("│  동일한 예약이 없습니다. 다시 입력하여주세요.  	│");
				System.out.println("└───────────────────────────────────────┘");		     
		         continue;
		         }
		         else {
		         sql = "UPDATE doctor SET Reserv = '가능' WHERE DoctorName = '" + doctorname + "'" + "AND Time = '" + Time + "'";
		         pstmt = db.conn.prepareStatement(sql);
		         pstmt.executeUpdate();
		         sql = "DELETE FROM Wait WHERE name = '" + name + "' AND ResidentNum = '";
		         pstmt = db.conn.prepareStatement(sql + ResidentNum + "'");
		         pstmt.executeUpdate();
				 
				 	System.out.println("┌───────────────────────────────┐");
					System.out.println("│      예약 취소가 완료되었습니다.   	│");
					System.out.println("└───────────────────────────────┘");
				 
		         break;
		         }
		} catch (SQLException e) {
			// TODO 자동 생성된 catch 블록
			e.printStackTrace();
		}
		}
	}
	void Check() {

		System.out.println("┌───────────────────────────────────────┐");
		System.out.println("│  환자 이름과 주민등록번호를 입력해주세요(-포함)	│");
		System.out.println("└───────────────────────────────────────┘");
		
		System.out.print("[이름] : ");
		name = sc.next();
		System.out.print("[주민등록번호] : ");
		ResidentNum = sc.next();
		
		String sql = "SELECT * FROM Patient WHERE ResidentNum = '";
		
				 try {
					pstmt = db.conn.prepareStatement(sql + ResidentNum + "'");
					rs = pstmt.executeQuery();

					if(rs.next() == true) {
						name = rs.getString("name");
						ResidentNum = rs.getNString("ResidentNum");
						address = rs.getString("address");
						gender = rs.getString("gender");
						age = rs.getInt("age");
						no = rs.getInt("no");
						
						System.out.println("┌────────────────────────────────┐");
						System.out.println("│[이름] 		: " + name + "   	 │");
						System.out.println("│[주민등록번호]	: " + ResidentNum + " │");
						System.out.println("│[주소] 		: " + address + "		 │");
						System.out.println("│[성별] 		: " + gender + "	 	 │");
						System.out.println("│[나이] 		: " + age + "   	 	 │");
						System.out.println("│[ID] 		: " + no + "   		 │");
						System.out.println("└────────────────────────────────┘");
						
						System.out.println("┌────────────────────────────────┐");
						System.out.println("│            재진입니다.         	 │");
						System.out.println("└────────────────────────────────┘");
						
					}
					else{
							
						System.out.println("┌────────────────────────────────┐");
						System.out.println("│            초진입니다.         	 │");
						System.out.println("└────────────────────────────────┘");
						
						System.out.println("┌────────────────────────────────┐");
						System.out.println("│       환자의 주소를 입력해주세요.	 │");
						System.out.println("└────────────────────────────────┘");
						System.out.print("[주소] : ");
						address = sc.next();
							
							new Patient(name, address, ResidentNum);
						}


				} catch (SQLException e) {
					e.printStackTrace();
				}
	}
	
	void menu1() { // 예약
		String speciality;
		String doctorname = null;
		String time;
		
		Check();
		pstmt = null;
		rs = null;
		
		System.out.println("┌────────────────────────────────┐");
		System.out.println("│         진료과를 입력하세요.      	 │");
		System.out.println("│                           	 │");
		System.out.println("│    [내과] [외과] [안과] [비뇨기과]	 │");
		System.out.println("└────────────────────────────────┘");
		
		System.out.print("[진료과] : ");
		speciality = sc.next();
		
		String sql = "SELECT * FROM doctor WHERE Time = '09:00' AND speciality = '";
		
		 try {
			pstmt = db.conn.prepareStatement(sql + speciality + "'");
			rs = pstmt.executeQuery();
			
			System.out.println("┌────────────────────────────────┐");
			System.out.println("│       전문의 목록을 출력합니다.     	 │");
			System.out.println("│    진료받고 싶은 전문의를 선택하세요.	 │");
			System.out.println("└────────────────────────────────┘");
			
			System.out.print("[");
			while(rs.next()) {
				doctorname = rs.getString("DoctorName");
				System.out.print(" " + doctorname + " ");
			}
			System.out.println("]");
		} catch (SQLException e) {
			// TODO 자동 생성된 catch 블록
			e.printStackTrace();
		}
		
		System.out.print("\n[전문의] : ");
		doctorname = sc.next();
		
		sql = "SELECT * FROM doctor WHERE DoctorName = '";
		outer:
		while(true) {
		 try {
			pstmt = db.conn.prepareStatement(sql + doctorname + "'");
			rs = pstmt.executeQuery();
			
			System.out.println("┌────────────────────────────────┐");
			System.out.println("│      진료 예약 시간을 출력합니다.  	 │");
			System.out.println("└────────────────────────────────┘");
			
			System.out.print("[");
			while(rs.next()) {
				String test = " " + rs.getString("Time") + " = " + rs.getString("Reserv");
				if(rs.getString("Reserv").equals("불가능")) ColorConsole.red(test);
				else System.out.print(test);
			}
			System.out.println(" ]");
		} catch (SQLException e) {
			// TODO 자동 생성된 catch 블록
			e.printStackTrace();
		}
		 	
		 	System.out.println("┌────────────────────────────────┐");
			System.out.println("│      원하는 시간을 선택해주세요.  	 │");
			System.out.println("└────────────────────────────────┘");
		 
			System.out.print("[00:00] = ");
			time = sc.next();

			String sql2 = "SELECT * FROM doctor WHERE DoctorName = '"+doctorname + "' AND Time = '";
			
			 try {
				pstmt = db.conn.prepareStatement(sql2 + time + "'");
				rs = pstmt.executeQuery();
				while(rs.next()) {
				if(rs.getString("Reserv").equals("불가능")) {
					
					System.out.println("┌───────────────────────────────┐");
					System.out.println("│       이미 예약된 시간입니다.    	│");
					System.out.println("│ 예약 가능한 시간을 다시 입력하여주세요.	│");
					System.out.println("└───────────────────────────────┘");
					
					//System.out.println("이미 예약된 시간입니다. 예약 가능한 시간을 다시 입력하여주세요.");
					break;
				}
				else if(rs.getString("Reserv").equals("가능")) {
					sql = "UPDATE doctor SET Reserv = '불가능' WHERE DoctorName = '" + doctorname + "'" + "AND Time = '" + time + "'";
					pstmt = db.conn.prepareStatement(sql);
					
					System.out.println("┌───────────────────────────────┐");
					System.out.println("│        예약이 완료되었습니다!    	│");
					System.out.println("└───────────────────────────────┘");
					
		        	int result = pstmt.executeUpdate();
		        	
		        	if(result == 1) {
		        		System.out.println("넣었따");
		        	}
		        	try {
		        		sql = "SELECT * FROM Patient WHERE ResidentNum = '";
		        		pstmt = db.conn.prepareStatement(sql + ResidentNum + "'");
		        		rs = pstmt.executeQuery();

						while(rs.next()) {
							name = rs.getString("name");
							ResidentNum = rs.getNString("ResidentNum");
							address = rs.getString("address");
							gender = rs.getString("gender");
							age = rs.getInt("age");
							no = rs.getInt("no");
						}
						sql = "insert into Wait values(?,?,?,?,?,?,?,?,?,?)";
		        		pstmt = db.conn.prepareStatement(sql);
		        		pstmt.setString(1, name);
		        		pstmt.setString(2, address);
		        		pstmt.setString(3, ResidentNum);
		        		pstmt.setString(4, gender);
		        		pstmt.setInt(5, age);
		        		pstmt.setInt(6, no);
		        		pstmt.setString(7, speciality);
		        		pstmt.setString(8, doctorname);
		        		pstmt.setString(9, time);
		        		pstmt.setInt(10, (int)(Math.random()*100000));
		        		pstmt.executeUpdate();
		        	}catch(Exception e) {
		            	System.out.println("실패");
		        	}
					break outer;
				}
				}
				

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	void menu2() { // 진료
		Date time = new Date();
		String time1 = format.format(time);
		
		String speciality;
		String doctorname;
		int cnt = 0;
		do {
			System.out.println("┌───────────────────────────────────────────────┐");
			System.out.println("│     [1] 방문 진료 [2] 금일 진료 환자 명단 [0] 이전	│");
			System.out.println("└───────────────────────────────────────────────┘");
			System.out.print("[opt] : ");
			opt = sc.nextInt();
			
			switch(opt) {
			case 1:
				Check();
				
				System.out.println("┌────────────────────────────────┐");
				System.out.println("│         진료과를 입력하세요.      	 │");
				System.out.println("│                           	 │");
				System.out.println("│    [내과] [외과] [안과] [비뇨기과]	 │");
				System.out.println("└────────────────────────────────┘");
				
				System.out.print("[진료과] : ");
				speciality = sc.next();
				
				String sql = "SELECT * FROM doctor WHERE Time = '09:00' AND speciality = '";
				
				System.out.println("┌────────────────────────────────┐");
				System.out.println("│       전문의 목록을 출력합니다.     	 │");
				System.out.println("│    진료받고 싶은 전문의를 선택하세요.	 │");
				System.out.println("└────────────────────────────────┘");
				
				 try {
					pstmt = db.conn.prepareStatement(sql + speciality + "'");
					rs = pstmt.executeQuery();
					
					System.out.print("[");
					while(rs.next()) {
						doctorname = rs.getString("DoctorName");
						System.out.print(" " + doctorname + " ");
					}
					System.out.println("]");
				} catch (SQLException e) {
					// TODO 자동 생성된 catch 블록
					e.printStackTrace();
				}
				
				System.out.print("\n[전문의] : ");
				doctorname = sc.next();
				
				try {
	        		sql = "SELECT * FROM Patient WHERE ResidentNum = '";
	        		pstmt = db.conn.prepareStatement(sql + ResidentNum + "'");
	        		rs = pstmt.executeQuery();

					while(rs.next()) {
						name = rs.getString("name");
						ResidentNum = rs.getNString("ResidentNum");
						address = rs.getString("address");
						gender = rs.getString("gender");
						age = rs.getInt("age");
						no = rs.getInt("no");
					}
					sql = "insert into Wait values(?,?,?,?,?,?,?,?,?,?)";
	        		pstmt = db.conn.prepareStatement(sql);
	        		pstmt.setString(1, name);
	        		pstmt.setString(2, address);
	        		pstmt.setString(3, ResidentNum);
	        		pstmt.setString(4, gender);
	        		pstmt.setInt(5, age);
	        		pstmt.setInt(6, no);
	        		pstmt.setString(7, speciality);
	        		pstmt.setString(8, doctorname);
	        		pstmt.setString(9, time1);
	        		pstmt.setInt(10, (int)(Math.random()*100000));
	        		pstmt.executeUpdate();
	        		System.out.println("┌───────────────────────────────┐");
					System.out.println("│      진료 등록이 완료되었습니다.    	│");
					System.out.println("└───────────────────────────────┘");
	        		
	        	}catch(Exception e) {
	            	System.out.println("실패");
	        	}
				
				break;
			case 2:
        		try {
        			sql = "SELECT * FROM Wait ORDER BY DoctorName ASC, Time ASC";
					pstmt = db.conn.prepareStatement(sql);
					rs = pstmt.executeQuery();
				} catch (SQLException e) {
					// TODO 자동 생성된 catch 블록
					e.printStackTrace();
				}
        		
        		System.out.println("┌────────────────────────────────┐");
        		System.out.println("│       [2] 금일 진료 환자 명단	 │");
				System.out.println("│    1.내과 2.외과 3.안과 4.비뇨기과	 │");
				System.out.println("└────────────────────────────────┘");
        		System.out.print("[opt] : ");
        		opt = sc.nextInt();
        		
        		switch(opt) {
        		case 1:
        			try {
        				sql = "SELECT * FROM Doctor WHERE speciality = '내과' AND DoctorName = '이기민'";
    					pstmt = db.conn.prepareStatement(sql);
    					rs = pstmt.executeQuery();
    					while(rs.next()) {
    						if(rs.isFirst()) {
    						System.out.println("[전문의] : " + rs.getString("doctorname"));
    						break;
    						}
    					}
    					sql = "SELECT * FROM Wait WHERE speciality = '내과' ORDER BY DoctorName ASC, Time ASC";
    					pstmt = db.conn.prepareStatement(sql);
    					rs = pstmt.executeQuery();
    					boolean result = rs.isBeforeFirst();
    					if(result){
    						while(rs.next()) {
    						System.out.print("[");
    						System.out.print(rs.getString("name") + " ");
    						System.out.print(rs.getString("Time"));
    						System.out.print("]" + " ");
    						}
    						System.out.println();
    						}
    					else {
        					System.out.println("┌───────────────────────────────┐");
    						System.out.println("│         대기명단이 없습니다.    	│");
    						System.out.println("└───────────────────────────────┘");
    						}
            			}catch(SQLException e) {
            				e.printStackTrace();
            			}
        			break;
        		case 2:
        			try {
        				sql = "SELECT * FROM Doctor WHERE speciality = '외과' AND DoctorName = '고태권'";
    					pstmt = db.conn.prepareStatement(sql);
    					rs = pstmt.executeQuery();
    					while(rs.next()) {
    						if(rs.isFirst()) {
    						System.out.println("[전문의] : " + rs.getString("doctorname"));
    						break;
    						}
    					}
    					sql = "SELECT * FROM Wait WHERE speciality = '외과' ORDER BY DoctorName ASC, Time ASC";
    					pstmt = db.conn.prepareStatement(sql);
    					rs = pstmt.executeQuery();
    					boolean result = rs.isBeforeFirst();
    					if(result){
    						while(rs.next()) {
    						System.out.print("[");
    						System.out.print(rs.getString("name") + " ");
    						System.out.print(rs.getString("Time"));
    						System.out.print("]" + " ");
    						}
    						System.out.println();
    						}
    					else {
    						System.out.println("┌───────────────────────────────┐");
    						System.out.println("│         대기명단이 없습니다.    	│");
    						System.out.println("└───────────────────────────────┘");
    						}
            			}catch(SQLException e) {
            				e.printStackTrace();
            			}
        			break;
        		case 3:
        			try {
        				sql = "SELECT * FROM Doctor WHERE speciality = '안과' AND DoctorName = '이효석'";
    					pstmt = db.conn.prepareStatement(sql);
    					rs = pstmt.executeQuery();
    					while(rs.next()) {
    						if(rs.isFirst()) {
    						System.out.println("[전문의] : " + rs.getString("doctorname"));
    						break;
    						}
    					}
    					sql = "SELECT * FROM Wait WHERE speciality = '안과' ORDER BY DoctorName ASC, Time ASC";
    					pstmt = db.conn.prepareStatement(sql);
    					rs = pstmt.executeQuery();
    					boolean result = rs.isBeforeFirst();
    					if(result){
    						while(rs.next()) {
    						System.out.print("[");
    						System.out.print(rs.getString("name") + " ");
    						System.out.print(rs.getString("Time"));
    						System.out.print("]" + " ");
    						}
    						System.out.println();
    						}
    					else {
    					System.out.println("┌───────────────────────────────┐");
						System.out.println("│         대기명단이 없습니다.    	│");
						System.out.println("└───────────────────────────────┘");
						}
            			}catch(SQLException e) {
            				e.printStackTrace();
            			}
        			break;
        		case 4:
        			try {
        				sql = "SELECT * FROM Doctor WHERE speciality = '비뇨기과' AND DoctorName = '강우석'";
    					pstmt = db.conn.prepareStatement(sql);
    					rs = pstmt.executeQuery();
    					while(rs.next()) {
    						if(rs.isFirst()) {
    						System.out.println("[전문의] : " + rs.getString("doctorname"));
    						break;
    						}
    					}
    					sql = "SELECT * FROM Wait WHERE speciality = '비뇨기과' AND DoctorName = '강우석' ORDER BY DoctorName ASC, Time ASC";
    					pstmt = db.conn.prepareStatement(sql);
    					rs = pstmt.executeQuery();
    					boolean result = rs.isBeforeFirst();
    					if(result){
    						while(rs.next()) {
    						System.out.print("[");
    						System.out.print(rs.getString("name") + " ");
    						System.out.print(rs.getString("Time"));
    						System.out.print("]" + " ");
    						}
    						System.out.println();
    						}
    					else {
        					System.out.println("┌───────────────────────────────┐");
    						System.out.println("│         대기명단이 없습니다.    	│");
    						System.out.println("└───────────────────────────────┘");
    						}
            			}catch(SQLException e) {
            				e.printStackTrace();
            			}
        			break;
        		case 0:
        			break;
        		default:
        			System.out.println("잘못된 입력입니다.");
        			break;
        		}
				break;
			case 0:
				break;
			default:
				System.out.println("잘못된 입력입니다.");
				break;
			}
			
		}while(opt != 0);
	}
	
	void menu3() { // 기록
		do {
		System.out.println("┌───────────────────────────────────────────────────────────────────────────────────────────────┐");
		System.out.println("│   [1]과 별 매출 [2]병원 총 매출 [3] 일일 환자 수 [4]환자 기록 조회 [5]의사 정보 [6] 의사 영입 [7]테스트 [0]이전 	│");
		System.out.println("└───────────────────────────────────────────────────────────────────────────────────────────────┘");	
			
		System.out.print("[opt] : ");
		opt = sc.nextInt();
		switch(opt) {
		case 1:
			System.out.println("┌───────────────────────────────┐");
			System.out.println("│          [1]과 별 매출	        │");
			System.out.println("└───────────────────────────────┘");
			mng.Pay();
			break;
		case 2:
			System.out.println("┌───────────────────────────────┐");
			System.out.println("│          [2]병원 총 매출	        │");
			System.out.println("└───────────────────────────────┘");
			mng.AllPay();
			break;
		case 3:
			System.out.println("┌───────────────────────────────┐");
			System.out.println("│          [3]일일 환자 수     	│");
			System.out.println("└───────────────────────────────┘");
			mng.patientpeople();
			break;
		case 4:
			System.out.println("┌───────────────────────────────┐");
			System.out.println("│         [4]환자 기록 조회     	│");
			System.out.println("└───────────────────────────────┘");
			mng.PatientInfo();
			break;
		case 5:
			System.out.println("┌───────────────────────────────┐");
			System.out.println("│          [5]의사 정보         	│");
			System.out.println("└───────────────────────────────┘");
			mng.DoctorInfo();
			break;
		case 6:
			System.out.println("┌───────────────────────────────┐");
			System.out.println("│          [6]의사 영입         	│");
			System.out.println("└───────────────────────────────┘");
			mng.AddDoctor();
			break;
		case 7:
			System.out.println("┌───────────────────────────────┐");
			System.out.println("│          [7]테 스 트         	│");
			System.out.println("└───────────────────────────────┘");
			new Med("이기민");
			new Gsr("고태권");
			new Eye("이효석");
			new Uro("강우석");
			break;
		case 0:
			break;
		default:
			System.out.println("┌───────────────────────────────┐");
			System.out.println("│         잘못된 입력입니다.      	│");
			System.out.println("└───────────────────────────────┘");
			break;
		}
		}while(opt != 0);
	}}
