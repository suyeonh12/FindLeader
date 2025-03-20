package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Random;
import java.util.Scanner;

import Controller.Controller;

public class Member_DAO {
	Random random = new Random();
	LocalDate date = LocalDate.now();
	Scanner sc = new Scanner(System.in);
	Connection conn = null; // DB 연결
	PreparedStatement prst = null; // sql 구문 세팅/ 실행
	int result = 0; // return되는 int 형태의 결과 저장
	ResultSet rs = null; // return되는 테이블 형태의 결과를 저장

//	Controller control = new Controller();
//	public void getConn() {
//		try {// DB드라이버 로드
//			Class.forName("oracle.jdbc.driver.OracleDriver");
//
//			// DB연결에 필요한 설정값
//			String url = "jdbc:oracle:thin:@project-db-campus.smhrd.com:1523:xe";
//			String user = "hapjeong_24SW_DS_p1_4";
//			String passward = "smhrd4";
//			conn = DriverManager.getConnection(url, user, passward);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

//	public void close() {// 객체 반납 메소드
//		try {
//			if (prst != null) {
//				prst.close();
//			}
//			if (conn != null) {
//				conn.close();
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}

//	public int join(MemberDTO dto) { // 회원가입 쿼리 메소드
//		// DB접근
//		getConn();
//		// DB쿼리문실행
//		String sql = "insert into member VALUES (?,?)";
//		try {
//			prst = conn.prepareStatement(sql);
//			prst.setString(1, dto.getNick());
//			prst.setString(2, dto.getPw());
//			result = prst.executeUpdate();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			close();
//		}
//		return result;
//	}

	public int fight() {
		int count = 1;
		int Playerhp = 80;
		int Enemyhp = 80;
		while (true) {
			int com_choice = random.nextInt(3) + 1;
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
				System.out.println("결투에서 패배했습니다");

				break;
			}
			System.out.println("당신의 공격! 어느쪽으로 공격하시겠습니까? /n [1] 왼쪽 [2] 오른쪽");
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
				System.out.println("결투에서 승리했습니다");
				break;
			}

			count++;
		}
		int score = Playerhp;
		return score;
	}
//	public void scoreupdate() {
//		getConn();
//		
//		String sql = 
//		
//	}
}
