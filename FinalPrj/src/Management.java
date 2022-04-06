import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

import com.mysql.cj.ParseInfo;

public class Management {
	Scanner sc = new Scanner(System.in);
	DB db = new DB();
	String name;
	
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	public void AddDoctor() {
//		int num = doctor.size();
		
		System.out.println("¦£¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¤");
		System.out.println("¦¢ ÀÇ»ç¸¦ ¿µÀÔÇÒ Áø·á°ú¸¦ ¼±ÅÃÇÏ¿© ÁÖ¼¼¿ä.	 ¦¢");
		System.out.println("¦¢   1.³»°ú 2.¿Ü°ú 3.¾È°ú 4.ºñ´¢±â°ú	 ¦¢");
		System.out.println("¦¦¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¥");
		System.out.print("[opt] : ");
		int opt = sc.nextInt();
		
		switch(opt) {
		case 1:
			System.out.println("¦£¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¤");
			System.out.println("¦¢        ³»°ú¸¦ ¼±ÅÃÇÏ¼Ì½À´Ï´Ù.    	 ¦¢");
			System.out.println("¦¢  ¿µÀÔÇÒ ÀÇ»çÀÇ ÀÌ¸§À» ÀÔ·ÂÇÏ¿© ÁÖ¼¼¿ä.	 ¦¢");
			System.out.println("¦¦¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¥");
			System.out.print("[ÀÌ¸§] : ");
			sc.nextLine();
			name = sc.nextLine();
			
			new Med(name);
			System.out.println("¦£¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¤");
			System.out.println("¦¢    ³»°ú ÀÇ»ç ¿µÀÔÀÌ ¿Ï·áµÇ¾ú½À´Ï´Ù!   ¦¢");
			System.out.println("¦¦¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¥");
			break;
		case 2:
			System.out.println("¦£¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¤");
			System.out.println("¦¢       ¿Ü°ú¸¦ ¼±ÅÃÇÏ¼Ì½À´Ï´Ù.    	 ¦¢");
			System.out.println("¦¢  ¿µÀÔÇÒ ÀÇ»çÀÇ ÀÌ¸§À» ÀÔ·ÂÇÏ¿© ÁÖ¼¼¿ä.	 ¦¢");
			System.out.println("¦¦¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¥");
			System.out.print("[ÀÌ¸§] : ");
			sc.nextLine();
			name = sc.nextLine();
			
			new Gsr(name);
			System.out.println("¦£¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¤");
			System.out.println("¦¢    ¿Ü°ú ÀÇ»ç ¿µÀÔÀÌ ¿Ï·áµÇ¾ú½À´Ï´Ù!   ¦¢");
			System.out.println("¦¦¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¥");
			break;
		case 3:
			System.out.println("¦£¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¤");
			System.out.println("¦¢       ¾È°ú¸¦ ¼±ÅÃÇÏ¼Ì½À´Ï´Ù.    	 ¦¢");
			System.out.println("¦¢  ¿µÀÔÇÒ ÀÇ»çÀÇ ÀÌ¸§À» ÀÔ·ÂÇÏ¿© ÁÖ¼¼¿ä.	 ¦¢");
			System.out.println("¦¦¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¥");
			System.out.print("[ÀÌ¸§] : ");
			sc.nextLine();
			name = sc.nextLine();
			
			new Eye(name);
			System.out.println("¦£¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¤");
			System.out.println("¦¢    ¾È°ú ÀÇ»ç ¿µÀÔÀÌ ¿Ï·áµÇ¾ú½À´Ï´Ù!   ¦¢");
			System.out.println("¦¦¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¥");
			break;
		case 4:
			System.out.println("¦£¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¤");
			System.out.println("¦¢       ºñ´¢±â°ú¸¦ ¼±ÅÃÇÏ¼Ì½À´Ï´Ù.    	 ¦¢");
			System.out.println("¦¢  ¿µÀÔÇÒ ÀÇ»çÀÇ ÀÌ¸§À» ÀÔ·ÂÇÏ¿© ÁÖ¼¼¿ä.	 ¦¢");
			System.out.println("¦¦¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¥");
			System.out.print("[ÀÌ¸§] : ");
			sc.nextLine();
			name = sc.nextLine();
			
			new Uro(name);
			System.out.println("¦£¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¤");
			System.out.println("¦¢  ºñ´¢±â°ú ÀÇ»ç ¿µÀÔÀÌ ¿Ï·áµÇ¾ú½À´Ï´Ù! 	¦¢");
			System.out.println("¦¦¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¥");
			break;
		default:
			System.out.println("¦£¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¤");
			System.out.println("¦¢         Àß¸øµÈ ÀÔ·ÂÀÔ´Ï´Ù.      	¦¢");
			System.out.println("¦¦¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¥");
			break;
		}
	}

	DecimalFormat formatter = new DecimalFormat("###,###");
	
	public void Pay() {
		int Med_Total = 0, Gsr_Total = 0, Eye_Total = 0, Uro_Total = 0;
		
		String sql = "SELECT * FROM Wait";
		try {
			pstmt = db.conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
		while(rs.next()){
			if(rs.getString("speciality").equals("³»°ú")) Med_Total += rs.getInt("price");
			else if(rs.getString("speciality").equals("¿Ü°ú")) Gsr_Total += rs.getInt("price");
			else if(rs.getString("speciality").equals("¾È°ú")) Eye_Total += rs.getInt("price");
			else Uro_Total += rs.getInt("price");
			}
		
//		System.out.println("¦£¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¤");
//		System.out.println("¦¢          [1]°ú º° ¸ÅÃâ	        ¦¢");
//		System.out.println("¦¦¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¥");
		
		
		System.out.println("[³»   °ú] : " + formatter.format(Med_Total)+ "¿ø");
		System.out.println("[¿Ü   °ú] : " + formatter.format(Gsr_Total) + "¿ø");
		System.out.println("[¾È   °ú] : " + formatter.format(Eye_Total) + "¿ø");
		System.out.println("[ºñ´¢±â°ú] : " + formatter.format(Uro_Total) + "¿ø");
		}catch(SQLException e) {
			e.printStackTrace();
		}
}
	public void AllPay() {
		int AllPay = 0;
		String sql = "SELECT * FROM Wait";
		try {
			pstmt = db.conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
		while(rs.next()){
				AllPay += rs.getInt("price");
			}
		
		System.out.println("[ÃÑ ¸ÅÃâ] : " + formatter.format(AllPay) + "¿ø");
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void patientpeople() {
		int cnt = 0, Med_Total = 0, Gsr_Total = 0, Eye_Total = 0, Uro_Total = 0;
		String sql = "SELECT * FROM Wait";
		try {
			pstmt = db.conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
		while(rs.next()){
			if(rs.getString("speciality").equals("³»°ú")) Med_Total++;
			else if(rs.getString("speciality").equals("¿Ü°ú")) Gsr_Total++;
			else if(rs.getString("speciality").equals("¾È°ú")) Eye_Total++;
			else Uro_Total++;
			
			cnt++;
			}
		System.out.println("[³»°ú È¯ÀÚ] :  " + Med_Total + "¸í");
		System.out.println("[¿Ü°ú È¯ÀÚ] :  " + Gsr_Total + "¸í");
		System.out.println("[¾È°ú È¯ÀÚ] :  " + Eye_Total + "¸í");
		System.out.println("[ºñ´¢±â°ú È¯ÀÚ] : " + Uro_Total + "¸í");
		System.out.println("[ÃÑ È¯ÀÚ] : " + cnt + "¸í");
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void PatientInfo() {
		String sql = "SELECT * FROM Wait WHERE ResidentNum = '";
		
		System.out.println("¦£¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¤");
		System.out.println("¦¢È¯ÀÚ ÁÖ¹Îµî·Ï¹øÈ£¸¦ ÀÔ·ÂÇØÁÖ¼¼¿ä.(-Æ÷ÇÔ)	¦¢");
		System.out.println("¦¦¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¥");
		
		System.out.print("[ÁÖ¹Îµî·Ï¹øÈ£] : ");
		String searchnum = sc.nextLine();
		System.out.println();
		
		try {
			pstmt = db.conn.prepareStatement(sql + searchnum + "'");
			rs = pstmt.executeQuery();
		while(rs.next()){	
			System.out.println("¦£¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¤");
			System.out.println("¦¢[ÀÌ¸§] 		: " + rs.getString("name") + "   	 ¦¢");
			System.out.println("¦¢[ÁÖ¹Îµî·Ï¹øÈ£]	: " + rs.getString("ResidentNum") + " ¦¢");
			System.out.println("¦¢[³ªÀÌ] 		: " + rs.getString("age") + "		 ¦¢");
			System.out.println("¦¢[¼ºº°] 		: " + rs.getString("gender") + "	 	 ¦¢");
			System.out.println("¦¢[ÁÖ¼Ò] 		: " + rs.getString("address") + "   	 	 ¦¢");
			System.out.println("¦¢[Áø·á°ú]		: " + rs.getString("speciality") + "   		 ¦¢");
			System.out.println("¦¢[ÀÇ»çÀÌ¸§]	: " + rs.getString("DoctorName") + "   	 ¦¢");
			System.out.println("¦¢[Áø·á½Ã°£]	: " + rs.getString("Time") + "   	 ¦¢");
			System.out.println("¦¢[Áø·áºñ] 	: " + formatter.format(Integer.parseInt(rs.getString("price"))) + "¿ø   	 ¦¢");
			System.out.println("¦¦¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¥");
			
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void DoctorInfo() {
		
		int opt;
	
		System.out.println("¦£¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¤");
		System.out.println("¦¢    1.³»°ú 2.¿Ü°ú 3.¾È°ú 4.ºñ´¢±â°ú	 ¦¢");
		System.out.println("¦¦¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¥");
		System.out.print("[opt] : ");
		opt = sc.nextInt();
		
		switch(opt) {
		case 1: // ³»
			System.out.println("¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡");
			System.out.println("	³»°ú Àü¹®ÀÇ : ÀÌ±â¹Î	    ");
			System.out.println("	úÞ ¸ðºíº´¿ø ³»°ú ¿øÀå	    ");
			System.out.println("	¸ðºí´ëÇÐ±³ ÀÇ°ú´ëÇÐ ¼®»ç	    ");
			System.out.println("	¸ðºí´ëÇÐ±³º´¿ø ³»°ú ÀüÀÓÀÇ	");
			System.out.println("	´ëÇÑÀÓ»óÃÊÀ½ÆÄÇÐÈ¸ ÃÊÀ½ÆÄ ÀÓÁõÀÇ ");
			System.out.println("	´ëÇÑ³»°úÇÐÈ¸ Á¤È¸¿ø		");
			System.out.println("	´ëÇÑ¼ÒÈ­±â³»½Ã°æÇÐÈ¸ Æò»ýÈ¸¿ø	");
			System.out.println("	´ëÇÑ°£ÇÐÈ¸ Æò»ýÈ¸¿ø		");
			System.out.println("	¾Æ»ê Àå»ç¶û¿¬ÇÕ³»°ú ¿ªÀÓ	");
			System.out.println("¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡");
			break;
		case 2: // ¿Ü°ú
			System.out.println("¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡");
			System.out.println("	¿Ü°ú Àü¹®ÀÇ : °íÅÂ±Ç 	    ");
			System.out.println("	¸ðºí´ëÇÐ±³ ´ëÇÐ¿ø ¿Ü°ú ¹Ú»ç	    ");
			System.out.println("	¸ðºí´ëÇÐ±³º´¿ø(±³¼ö)	    ");
			System.out.println("	¸ðºí´ëÇÐ±³ÀÇ·á¿ø(¿Ü»ó¿¬±¸¼ÒÀå)	");
			System.out.println("	¸ðºí´ëÇÐ±³ ¿Ü»ó¿Ü°ú ±³¼ö ");
			System.out.println("	¸ðºí´ëÇÐ±³ ÀÇ°ú´ëÇÐ ÀÇ°úÇÐ±³½Ç ±³¼ö		");
			System.out.println("	¸ðºí´ëÇÐ±³º´¿ø ¿Ü»ó¿Ü°ú °úÀå	");
			System.out.println("	¸ðºí´ëÇÐ±³ÀÇ·á¿ø Ã·´ÜÀÇÇÐ¿¬±¸¿ø ¿Ü»ó¿¬±¸¼ÒÀå		");
			System.out.println("¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡");
			break; 
		case 3: // ¾È°ú
			System.out.println("¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡");
			System.out.println("	¾È°ú Àü¹®ÀÇ : ÀÌÈ¿¼® 	    ");
			System.out.println("	¸ðºíÀÇ·á¿ø ¸ðºíº´¿ø ¾È°ú ¼ö·ÃÀÇ ¼ö·á	    ");
			System.out.println("	¸ðºíÀÇ·á¿ø ¸ðºíº´¿ø ¾È°ú Àü°øÀÇ ¼ö·á	    ");
			System.out.println("	Á¦10 ÀüÅõºñÇà´Ü Ç×°øÀÇ¹«½ÇÀå ¹× ¾È°ú °úÀå ¿ªÀÓ	");
			System.out.println("	Çö)¸ðºíº´¿ø ¾È°úÇÐ±³½Ç ÃÑµ¿¹®È¸Àå");
			System.out.println("	Çö)¸ðºíº´¿ø º»Á¡ ´ëÇ¥¿øÀå		");
			System.out.println("	´ëÇÑ¾È°úÇÐÈ¸(KOS) Á¤È¸¿ø	");
			System.out.println("	¹Ì±¹¾È°úÇÐÈ¸(AOS) Á¤È¸¿ø		");
			System.out.println("¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡");
			break;
		case 4: // ºñ´¢±â°ú
			System.out.println("¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡");
			System.out.println("	ºñ´¢±â°ú Àü¹®ÀÇ : °­¿ì¼® 	    ");
			System.out.println("	°ËÁ¤°í½Ã 14¼¼ ÇÕ°Ý	    ");
			System.out.println("	16¼¼ ¸ðºí´ëÇÐ±³ ÀÇ°ú´ëÇÐ Á¶±âÁ¹¾÷(ÇÐÁ¡ 4.5)	    ");
			System.out.println("	19¼¼ ÇÏ¹öµå´ëÇÐ±³ ÀÇ¿¹°ú ¼®»ç¼ö·á	");
			System.out.println("	21¼¼ ¼¼ºê¶õ½ºº´¿ø ºñ´¢±â°ú ·¹Áö´øÆ®");
			System.out.println("	24¼¼ ¼¼ºê¶õ½ºº´¿ø ºñ´¢±â°ú °úÀå		");
			System.out.println("	26¼¼ ¼¼ºê¶õ½ºº´¿ø ÃÖ¿¬¼Ò ¿øÀå	");
			System.out.println("	28¼¼ Á¦3º¸º´»ç´Ü ÀÇ¹«´ë´ë Áø·á°ú »ó»ç °­¿ì¼®	");
			System.out.println("¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡");
			break;
		}
		
		
		
		
	}
}
