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
		System.out.println("¦£¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¤");
		System.out.println("¦¢    [1]¿¹¾à [2]¿¹¾àÃë¼Ò [0]ÀÌÀü   	¦¢");
		System.out.println("¦¦¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¥");
		
		System.out.print("[opt] : ");
		opt = sc.nextInt();
		switch(opt) {
		case 1: // [1]¿¹¾à
			menu1();
			break;
		case 2: // [2]¿¹¾àÃë¼Ò
			Cancel();
			break;
		case 0: // [0]ÀÌÀü
			break;
		default:
			System.out.println("¦£¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¤");
			System.out.println("¦¢         Àß¸øµÈ ÀÔ·ÂÀÔ´Ï´Ù.      	¦¢");
			System.out.println("¦¦¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¥");
			break;
		}
	}
	
	void Cancel() {
		String doctorname = null;
		Time Time = null;
		while(true) {
		System.out.println("¦£¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¤");
		System.out.println("¦¢           ¿¹¾à Ãë¼Ò¸¦ ½ÇÇàÇÕ´Ï´Ù.         	¦¢");
		System.out.println("¦¢  È¯ÀÚ ÀÌ¸§°ú ÁÖ¹Îµî·Ï¹øÈ£¸¦ ÀÔ·ÂÇØÁÖ¼¼¿ä(-Æ÷ÇÔ)	¦¢");
		System.out.println("¦¦¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¥");
		
		System.out.print("[ÀÌ¸§] : ");
		name = sc.next();
		System.out.print("[ÁÖ¹Îµî·Ï¹øÈ£] : ");
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
				System.out.println("¦£¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¤");
				System.out.println("¦¢  µ¿ÀÏÇÑ ¿¹¾àÀÌ ¾ø½À´Ï´Ù. ´Ù½Ã ÀÔ·ÂÇÏ¿©ÁÖ¼¼¿ä.  	¦¢");
				System.out.println("¦¦¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¥");		     
		         continue;
		         }
		         else {
		         sql = "UPDATE doctor SET Reserv = '°¡´É' WHERE DoctorName = '" + doctorname + "'" + "AND Time = '" + Time + "'";
		         pstmt = db.conn.prepareStatement(sql);
		         pstmt.executeUpdate();
		         sql = "DELETE FROM Wait WHERE name = '" + name + "' AND ResidentNum = '";
		         pstmt = db.conn.prepareStatement(sql + ResidentNum + "'");
		         pstmt.executeUpdate();
				 
				 	System.out.println("¦£¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¤");
					System.out.println("¦¢      ¿¹¾à Ãë¼Ò°¡ ¿Ï·áµÇ¾ú½À´Ï´Ù.   	¦¢");
					System.out.println("¦¦¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¥");
				 
		         break;
		         }
		} catch (SQLException e) {
			// TODO ÀÚµ¿ »ý¼ºµÈ catch ºí·Ï
			e.printStackTrace();
		}
		}
	}
	void Check() {

		System.out.println("¦£¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¤");
		System.out.println("¦¢  È¯ÀÚ ÀÌ¸§°ú ÁÖ¹Îµî·Ï¹øÈ£¸¦ ÀÔ·ÂÇØÁÖ¼¼¿ä(-Æ÷ÇÔ)	¦¢");
		System.out.println("¦¦¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¥");
		
		System.out.print("[ÀÌ¸§] : ");
		name = sc.next();
		System.out.print("[ÁÖ¹Îµî·Ï¹øÈ£] : ");
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
						
						System.out.println("¦£¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¤");
						System.out.println("¦¢[ÀÌ¸§] 		: " + name + "   	 ¦¢");
						System.out.println("¦¢[ÁÖ¹Îµî·Ï¹øÈ£]	: " + ResidentNum + " ¦¢");
						System.out.println("¦¢[ÁÖ¼Ò] 		: " + address + "		 ¦¢");
						System.out.println("¦¢[¼ºº°] 		: " + gender + "	 	 ¦¢");
						System.out.println("¦¢[³ªÀÌ] 		: " + age + "   	 	 ¦¢");
						System.out.println("¦¢[ID] 		: " + no + "   		 ¦¢");
						System.out.println("¦¦¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¥");
						
						System.out.println("¦£¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¤");
						System.out.println("¦¢            ÀçÁøÀÔ´Ï´Ù.         	 ¦¢");
						System.out.println("¦¦¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¥");
						
					}
					else{
							
						System.out.println("¦£¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¤");
						System.out.println("¦¢            ÃÊÁøÀÔ´Ï´Ù.         	 ¦¢");
						System.out.println("¦¦¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¥");
						
						System.out.println("¦£¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¤");
						System.out.println("¦¢       È¯ÀÚÀÇ ÁÖ¼Ò¸¦ ÀÔ·ÂÇØÁÖ¼¼¿ä.	 ¦¢");
						System.out.println("¦¦¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¥");
						System.out.print("[ÁÖ¼Ò] : ");
						address = sc.next();
							
							new Patient(name, address, ResidentNum);
						}


				} catch (SQLException e) {
					e.printStackTrace();
				}
	}
	
	void menu1() { // ¿¹¾à
		String speciality;
		String doctorname = null;
		String time;
		
		Check();
		pstmt = null;
		rs = null;
		
		System.out.println("¦£¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¤");
		System.out.println("¦¢         Áø·á°ú¸¦ ÀÔ·ÂÇÏ¼¼¿ä.      	 ¦¢");
		System.out.println("¦¢                           	 ¦¢");
		System.out.println("¦¢    [³»°ú] [¿Ü°ú] [¾È°ú] [ºñ´¢±â°ú]	 ¦¢");
		System.out.println("¦¦¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¥");
		
		System.out.print("[Áø·á°ú] : ");
		speciality = sc.next();
		
		String sql = "SELECT * FROM doctor WHERE Time = '09:00' AND speciality = '";
		
		 try {
			pstmt = db.conn.prepareStatement(sql + speciality + "'");
			rs = pstmt.executeQuery();
			
			System.out.println("¦£¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¤");
			System.out.println("¦¢       Àü¹®ÀÇ ¸ñ·ÏÀ» Ãâ·ÂÇÕ´Ï´Ù.     	 ¦¢");
			System.out.println("¦¢    Áø·á¹Þ°í ½ÍÀº Àü¹®ÀÇ¸¦ ¼±ÅÃÇÏ¼¼¿ä.	 ¦¢");
			System.out.println("¦¦¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¥");
			
			System.out.print("[");
			while(rs.next()) {
				doctorname = rs.getString("DoctorName");
				System.out.print(" " + doctorname + " ");
			}
			System.out.println("]");
		} catch (SQLException e) {
			// TODO ÀÚµ¿ »ý¼ºµÈ catch ºí·Ï
			e.printStackTrace();
		}
		
		System.out.print("\n[Àü¹®ÀÇ] : ");
		doctorname = sc.next();
		
		sql = "SELECT * FROM doctor WHERE DoctorName = '";
		outer:
		while(true) {
		 try {
			pstmt = db.conn.prepareStatement(sql + doctorname + "'");
			rs = pstmt.executeQuery();
			
			System.out.println("¦£¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¤");
			System.out.println("¦¢      Áø·á ¿¹¾à ½Ã°£À» Ãâ·ÂÇÕ´Ï´Ù.  	 ¦¢");
			System.out.println("¦¦¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¥");
			
			System.out.print("[");
			while(rs.next()) {
				String test = " " + rs.getString("Time") + " = " + rs.getString("Reserv");
				if(rs.getString("Reserv").equals("ºÒ°¡´É")) ColorConsole.red(test);
				else System.out.print(test);
			}
			System.out.println(" ]");
		} catch (SQLException e) {
			// TODO ÀÚµ¿ »ý¼ºµÈ catch ºí·Ï
			e.printStackTrace();
		}
		 	
		 	System.out.println("¦£¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¤");
			System.out.println("¦¢      ¿øÇÏ´Â ½Ã°£À» ¼±ÅÃÇØÁÖ¼¼¿ä.  	 ¦¢");
			System.out.println("¦¦¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¥");
		 
			System.out.print("[00:00] = ");
			time = sc.next();

			String sql2 = "SELECT * FROM doctor WHERE DoctorName = '"+doctorname + "' AND Time = '";
			
			 try {
				pstmt = db.conn.prepareStatement(sql2 + time + "'");
				rs = pstmt.executeQuery();
				while(rs.next()) {
				if(rs.getString("Reserv").equals("ºÒ°¡´É")) {
					
					System.out.println("¦£¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¤");
					System.out.println("¦¢       ÀÌ¹Ì ¿¹¾àµÈ ½Ã°£ÀÔ´Ï´Ù.    	¦¢");
					System.out.println("¦¢ ¿¹¾à °¡´ÉÇÑ ½Ã°£À» ´Ù½Ã ÀÔ·ÂÇÏ¿©ÁÖ¼¼¿ä.	¦¢");
					System.out.println("¦¦¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¥");
					
					//System.out.println("ÀÌ¹Ì ¿¹¾àµÈ ½Ã°£ÀÔ´Ï´Ù. ¿¹¾à °¡´ÉÇÑ ½Ã°£À» ´Ù½Ã ÀÔ·ÂÇÏ¿©ÁÖ¼¼¿ä.");
					break;
				}
				else if(rs.getString("Reserv").equals("°¡´É")) {
					sql = "UPDATE doctor SET Reserv = 'ºÒ°¡´É' WHERE DoctorName = '" + doctorname + "'" + "AND Time = '" + time + "'";
					pstmt = db.conn.prepareStatement(sql);
					
					System.out.println("¦£¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¤");
					System.out.println("¦¢        ¿¹¾àÀÌ ¿Ï·áµÇ¾ú½À´Ï´Ù!    	¦¢");
					System.out.println("¦¦¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¥");
					
		        	int result = pstmt.executeUpdate();
		        	
		        	if(result == 1) {
		        		System.out.println("³Ö¾úµû");
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
		            	System.out.println("½ÇÆÐ");
		        	}
					break outer;
				}
				}
				

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	void menu2() { // Áø·á
		Date time = new Date();
		String time1 = format.format(time);
		
		String speciality;
		String doctorname;
		int cnt = 0;
		do {
			System.out.println("¦£¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¤");
			System.out.println("¦¢     [1] ¹æ¹® Áø·á [2] ±ÝÀÏ Áø·á È¯ÀÚ ¸í´Ü [0] ÀÌÀü	¦¢");
			System.out.println("¦¦¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¥");
			System.out.print("[opt] : ");
			opt = sc.nextInt();
			
			switch(opt) {
			case 1:
				Check();
				
				System.out.println("¦£¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¤");
				System.out.println("¦¢         Áø·á°ú¸¦ ÀÔ·ÂÇÏ¼¼¿ä.      	 ¦¢");
				System.out.println("¦¢                           	 ¦¢");
				System.out.println("¦¢    [³»°ú] [¿Ü°ú] [¾È°ú] [ºñ´¢±â°ú]	 ¦¢");
				System.out.println("¦¦¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¥");
				
				System.out.print("[Áø·á°ú] : ");
				speciality = sc.next();
				
				String sql = "SELECT * FROM doctor WHERE Time = '09:00' AND speciality = '";
				
				System.out.println("¦£¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¤");
				System.out.println("¦¢       Àü¹®ÀÇ ¸ñ·ÏÀ» Ãâ·ÂÇÕ´Ï´Ù.     	 ¦¢");
				System.out.println("¦¢    Áø·á¹Þ°í ½ÍÀº Àü¹®ÀÇ¸¦ ¼±ÅÃÇÏ¼¼¿ä.	 ¦¢");
				System.out.println("¦¦¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¥");
				
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
					// TODO ÀÚµ¿ »ý¼ºµÈ catch ºí·Ï
					e.printStackTrace();
				}
				
				System.out.print("\n[Àü¹®ÀÇ] : ");
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
	        		System.out.println("¦£¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¤");
					System.out.println("¦¢      Áø·á µî·ÏÀÌ ¿Ï·áµÇ¾ú½À´Ï´Ù.    	¦¢");
					System.out.println("¦¦¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¥");
	        		
	        	}catch(Exception e) {
	            	System.out.println("½ÇÆÐ");
	        	}
				
				break;
			case 2:
        		try {
        			sql = "SELECT * FROM Wait ORDER BY DoctorName ASC, Time ASC";
					pstmt = db.conn.prepareStatement(sql);
					rs = pstmt.executeQuery();
				} catch (SQLException e) {
					// TODO ÀÚµ¿ »ý¼ºµÈ catch ºí·Ï
					e.printStackTrace();
				}
        		
        		System.out.println("¦£¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¤");
        		System.out.println("¦¢       [2] ±ÝÀÏ Áø·á È¯ÀÚ ¸í´Ü	 ¦¢");
				System.out.println("¦¢    1.³»°ú 2.¿Ü°ú 3.¾È°ú 4.ºñ´¢±â°ú	 ¦¢");
				System.out.println("¦¦¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¥");
        		System.out.print("[opt] : ");
        		opt = sc.nextInt();
        		
        		switch(opt) {
        		case 1:
        			try {
        				sql = "SELECT * FROM Doctor WHERE speciality = '³»°ú' AND DoctorName = 'ÀÌ±â¹Î'";
    					pstmt = db.conn.prepareStatement(sql);
    					rs = pstmt.executeQuery();
    					while(rs.next()) {
    						if(rs.isFirst()) {
    						System.out.println("[Àü¹®ÀÇ] : " + rs.getString("doctorname"));
    						break;
    						}
    					}
    					sql = "SELECT * FROM Wait WHERE speciality = '³»°ú' ORDER BY DoctorName ASC, Time ASC";
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
        					System.out.println("¦£¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¤");
    						System.out.println("¦¢         ´ë±â¸í´ÜÀÌ ¾ø½À´Ï´Ù.    	¦¢");
    						System.out.println("¦¦¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¥");
    						}
            			}catch(SQLException e) {
            				e.printStackTrace();
            			}
        			break;
        		case 2:
        			try {
        				sql = "SELECT * FROM Doctor WHERE speciality = '¿Ü°ú' AND DoctorName = '°íÅÂ±Ç'";
    					pstmt = db.conn.prepareStatement(sql);
    					rs = pstmt.executeQuery();
    					while(rs.next()) {
    						if(rs.isFirst()) {
    						System.out.println("[Àü¹®ÀÇ] : " + rs.getString("doctorname"));
    						break;
    						}
    					}
    					sql = "SELECT * FROM Wait WHERE speciality = '¿Ü°ú' ORDER BY DoctorName ASC, Time ASC";
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
    						System.out.println("¦£¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¤");
    						System.out.println("¦¢         ´ë±â¸í´ÜÀÌ ¾ø½À´Ï´Ù.    	¦¢");
    						System.out.println("¦¦¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¥");
    						}
            			}catch(SQLException e) {
            				e.printStackTrace();
            			}
        			break;
        		case 3:
        			try {
        				sql = "SELECT * FROM Doctor WHERE speciality = '¾È°ú' AND DoctorName = 'ÀÌÈ¿¼®'";
    					pstmt = db.conn.prepareStatement(sql);
    					rs = pstmt.executeQuery();
    					while(rs.next()) {
    						if(rs.isFirst()) {
    						System.out.println("[Àü¹®ÀÇ] : " + rs.getString("doctorname"));
    						break;
    						}
    					}
    					sql = "SELECT * FROM Wait WHERE speciality = '¾È°ú' ORDER BY DoctorName ASC, Time ASC";
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
    					System.out.println("¦£¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¤");
						System.out.println("¦¢         ´ë±â¸í´ÜÀÌ ¾ø½À´Ï´Ù.    	¦¢");
						System.out.println("¦¦¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¥");
						}
            			}catch(SQLException e) {
            				e.printStackTrace();
            			}
        			break;
        		case 4:
        			try {
        				sql = "SELECT * FROM Doctor WHERE speciality = 'ºñ´¢±â°ú' AND DoctorName = '°­¿ì¼®'";
    					pstmt = db.conn.prepareStatement(sql);
    					rs = pstmt.executeQuery();
    					while(rs.next()) {
    						if(rs.isFirst()) {
    						System.out.println("[Àü¹®ÀÇ] : " + rs.getString("doctorname"));
    						break;
    						}
    					}
    					sql = "SELECT * FROM Wait WHERE speciality = 'ºñ´¢±â°ú' AND DoctorName = '°­¿ì¼®' ORDER BY DoctorName ASC, Time ASC";
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
        					System.out.println("¦£¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¤");
    						System.out.println("¦¢         ´ë±â¸í´ÜÀÌ ¾ø½À´Ï´Ù.    	¦¢");
    						System.out.println("¦¦¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¥");
    						}
            			}catch(SQLException e) {
            				e.printStackTrace();
            			}
        			break;
        		case 0:
        			break;
        		default:
        			System.out.println("Àß¸øµÈ ÀÔ·ÂÀÔ´Ï´Ù.");
        			break;
        		}
				break;
			case 0:
				break;
			default:
				System.out.println("Àß¸øµÈ ÀÔ·ÂÀÔ´Ï´Ù.");
				break;
			}
			
		}while(opt != 0);
	}
	
	void menu3() { // ±â·Ï
		do {
		System.out.println("¦£¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¤");
		System.out.println("¦¢   [1]°ú º° ¸ÅÃâ [2]º´¿ø ÃÑ ¸ÅÃâ [3] ÀÏÀÏ È¯ÀÚ ¼ö [4]È¯ÀÚ ±â·Ï Á¶È¸ [5]ÀÇ»ç Á¤º¸ [6] ÀÇ»ç ¿µÀÔ [7]Å×½ºÆ® [0]ÀÌÀü 	¦¢");
		System.out.println("¦¦¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¥");	
			
		System.out.print("[opt] : ");
		opt = sc.nextInt();
		switch(opt) {
		case 1:
			System.out.println("¦£¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¤");
			System.out.println("¦¢          [1]°ú º° ¸ÅÃâ	        ¦¢");
			System.out.println("¦¦¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¥");
			mng.Pay();
			break;
		case 2:
			System.out.println("¦£¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¤");
			System.out.println("¦¢          [2]º´¿ø ÃÑ ¸ÅÃâ	        ¦¢");
			System.out.println("¦¦¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¥");
			mng.AllPay();
			break;
		case 3:
			System.out.println("¦£¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¤");
			System.out.println("¦¢          [3]ÀÏÀÏ È¯ÀÚ ¼ö     	¦¢");
			System.out.println("¦¦¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¥");
			mng.patientpeople();
			break;
		case 4:
			System.out.println("¦£¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¤");
			System.out.println("¦¢         [4]È¯ÀÚ ±â·Ï Á¶È¸     	¦¢");
			System.out.println("¦¦¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¥");
			mng.PatientInfo();
			break;
		case 5:
			System.out.println("¦£¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¤");
			System.out.println("¦¢          [5]ÀÇ»ç Á¤º¸         	¦¢");
			System.out.println("¦¦¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¥");
			mng.DoctorInfo();
			break;
		case 6:
			System.out.println("¦£¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¤");
			System.out.println("¦¢          [6]ÀÇ»ç ¿µÀÔ         	¦¢");
			System.out.println("¦¦¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¥");
			mng.AddDoctor();
			break;
		case 7:
			System.out.println("¦£¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¤");
			System.out.println("¦¢          [7]Å× ½º Æ®         	¦¢");
			System.out.println("¦¦¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¥");
			new Med("ÀÌ±â¹Î");
			new Gsr("°íÅÂ±Ç");
			new Eye("ÀÌÈ¿¼®");
			new Uro("°­¿ì¼®");
			break;
		case 0:
			break;
		default:
			System.out.println("¦£¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¤");
			System.out.println("¦¢         Àß¸øµÈ ÀÔ·ÂÀÔ´Ï´Ù.      	¦¢");
			System.out.println("¦¦¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¥");
			break;
		}
		}while(opt != 0);
	}}
