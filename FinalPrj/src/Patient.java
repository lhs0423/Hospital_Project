import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Patient {
	String name;		// 환자이름
	String address;		// 주소
	String ResidentNum;	// 주민등록번호
	int age;			// 나이
	DB db = new DB();
	public Patient() { } // 기본생성자
	
	Patient(String name, String address, String ResidentNum){
		String gender = null;
		this.name = name;
		this.address = address;
		this.ResidentNum = ResidentNum;
		int age = Integer.parseInt(ResidentNum.substring(0, 2)); // 주민등록번호 앞 2자리 
		
		char ch = ResidentNum.charAt(7); // 주민등록번호 뒷자리 첫번 째 번호
		if(ch == '1' || ch == '3') gender = "남성";
		else if(ch == '2' || ch == '4') gender ="여성";
		
		if(ch == '1' || ch == '2') { // 1900년생 남성,여성일 경우
			age = 2022 - (1900+age) + 1;
			this.age = age;
		}
		else if(ch == '3' || ch == '4') { // 2000년생 남성,여성일 경우
			age = 2022 - (2000+age) + 1;
			this.age = age;
		}

        String sql = "insert into Patient values(?,?,?,?,?,?)";
        
        PreparedStatement pstmt = null;
        
        try {
			pstmt = db.conn.prepareStatement(sql);
        	pstmt.setString(1, name);
        	pstmt.setString(2, address);
        	pstmt.setString(3, ResidentNum);
        	pstmt.setString(4, gender);
        	pstmt.setInt(5, age);
        	pstmt.setString(6, null);
        	
        	int result = pstmt.executeUpdate();
        	if(result == 1) {
        		//System.out.println("넣었따");
        	}
        }catch(Exception e) {
        	System.out.println("실패");
        } finally{
        	try {
        		if(pstmt != null & !pstmt.isClosed()) {
        			pstmt.close();
        		}
        	}catch(Exception e2) {}
        }
		
	}
}

