import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DB {
	 private static final String driver="com.mysql.cj.jdbc.Driver";
	 private static final String url="jdbc:MySQL://localhost:3306/test?autoReconnect=true&useUnicode=true&characterEncoding=euckr";
	 private static final String user="root";
	 private static final String pwd="1234";
	 Connection conn = null;
	 
	 
	 public DB() {
     try {
        Class.forName(driver);
//        System.out.println("MySQL 드라이버 로딩 성공");
        conn = DriverManager.getConnection(url,user,pwd);
//        System.out.println("Connection 생성 성공");
        
//        System.out.println("Statement  객체 생성 성공");
     }catch(Exception e) {
        System.out.println("로딩 실패");
        try {
     	   conn.close();
        }catch(SQLException e1) {}
     }
}
}
