package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import java.util.Scanner;

import javax.naming.spi.DirStateFactory.Result;

public class MemberDAO {
	
	//필요 유틸
	Scanner sc = new Scanner(System.in);
	Random ran = new Random();

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
	
	//세번째 게임: 경비원을 이겨라!
	public int fight() {
		int count = 1;
		int Playerhp = 80;
		int Enemyhp = 80;
		while (true) {
			int com_choice = ran.nextInt(3) + 1;
			System.out.println(count + "번째 턴입니다.");
			System.out.println("경비원의 공격! 공격을 회피하세요!\n [1] 왼쪽 [2] 오른쪽");
			int choice = sc.nextInt();
			if (com_choice != 1) {
				System.out.println("경비원의 공격을 피했습니다");
			}
			if (com_choice == 1) {
				System.out.println("경비원의 공격을 피하지 못했습니다. '플레이어' hp -10");
				Playerhp -= 10;
			}
			System.out.println("플레이어 HP : " + Playerhp + " 경비원 HP : " + Enemyhp);
			if (Playerhp <= 0) {
				break;
			}
			System.out.println("당신의 공격! 어느쪽으로 공격하시겠습니까? \n [1] 왼쪽 [2] 오른쪽");
			choice = sc.nextInt();
			if (com_choice != 1) {
				System.out.println("공격이 적중했습니다! '경비원' hp - 10");
				Enemyhp -= 10;
			}
			if (com_choice == 1) {
				System.out.println("경비원이 공격을 피했습니다");
			}
			System.out.println("플레이어 HP : " + Playerhp + " 경비원 HP : " + Enemyhp);
			if (Enemyhp <= 0) {
				System.out.println("승리했습니다!");
				break;
			}
			count++;
		}
		return Playerhp;
	}

}
