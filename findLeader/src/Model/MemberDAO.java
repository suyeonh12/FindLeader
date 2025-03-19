package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.spi.DirStateFactory.Result;

public class MemberDAO {

	// DB연결 관련 초기값 세팅
	Connection conn = null;
	PreparedStatement psmt = null;
	ResultSet rs = null;
	int result = 0; // int형 result 초기값 세팅

	// DB 접속
	public void getConn() {

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			String url = "jdbc:oracle:thin:@project-db-campus.smhrd.com:1523:xe";
			String user = "hapjeong_24SW_DS_p1_4";
			String password = "smhrd4";

			conn = DriverManager.getConnection(url, user, password);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void close() {
		try {
			if (rs != null)
				rs.close();
			if (psmt != null)
				psmt.close();
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	public int join(MemberDTO dto) {
		getConn(); //DB접근
		
		String sql = "INSERT INTO MEMBER VALUES (?,?)";
		
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, dto.getNick());
			psmt.setString(2, dto.getPw());
			
			result = psmt.executeUpdate();
			
		} catch (SQLException e) {
			//e.printStackTrace();
			System.out.println("중복된 닉네임입니다. 다시 입력해주세요.");
		} finally {
			close();
		}
		return result;
	}
	
	public int login(MemberDTO dto) {
		getConn(); //DB접근
		
		String sql = "SELECT * FROM MEMBER WHERE NICK=? AND PW=?";
		
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, dto.getNick());
			psmt.setString(2, dto.getPw());
			
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				result = 1;
			}else {
				result = 0;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return result;
	}


}
