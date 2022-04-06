import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

public class Doctor{
	String speciality;
	String DoctorName;
	
	int hour = 10;
	String min = "00";
	TreeMap<String, String> HM;
	
	DB db = new DB();
	
	Doctor(String DoctorName, String speciality){
		this.DoctorName = DoctorName;
		this.speciality = speciality;
		
		HM = new TreeMap<>();
		HM.put("09:00", "가능");
		HM.put("09:30", "가능");
		for(int i=0; i<4; i++) {
			String str = hour +":"+ min;
			HM.put(str, "가능");
			if(min == "00") {
				min = "30";
			}else {
				min = "00";
				hour++;
			}
		}
		
		hour = 14;
		min = "00";
		for(int i = 0; i < 8; i++) {
			String str = hour + ":"+ min;
			HM.put(str, "가능");
			if(min == "00") {
				min = "30";
			}else {
				min = "00";
				hour++;
			}
		}
        int result = 0;
        String sql = "insert into Doctor values(?,?,?,?)";
        
		PreparedStatement pstmt = null;
        try {
        	pstmt = db.conn.prepareStatement(sql);
        	for(String s : HM.keySet()) {
        	pstmt.setString(1, speciality);
        	pstmt.setString(2, DoctorName);
        	pstmt.setString(3, HM.ceilingKey(s));
        	pstmt.setString(4, HM.get(s));
        	result = pstmt.executeUpdate();
			}
        	
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

class Med extends Doctor{ // 내과(Internal Medicine)
	Med(String name){
		super(name, "내과");
	}
	@Override
	public String toString() {
		return "내과(Internal Medicine)";
	}
}

class Gsr extends Doctor{ // 외과(General Surgery)
	
	Gsr(String name){
		super(name, "외과");
	}
	
	@Override
	public String toString() {
		return "외과(General Surgery)";
	}
}

class Eye extends Doctor{ // 안과(Ophthalmology)
	Eye(String name){
		super(name, "안과");
	}
	
	@Override
	public String toString() {
		return "안과(Ophthalmology)";
	}
}

class Uro extends Doctor{ // 비뇨기과(Urology)

	Uro(String name){
		super(name, "비뇨기과");
	}
	
	@Override
	public String toString() {
		return "비뇨기과(Urology)";
	}
}