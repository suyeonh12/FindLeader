package Model;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Ranking extends MemberDAO {
	private int endScore;
	private String endTime;
	
	public Ranking(String nick, int score, String endtime) {
		super();
		this.endScore = score;
		this.endTime = endtime;
	}
	public Ranking() {
	}

	public int getEndScore() {
		return endScore;
	}
	public int getEndScore(int hp) {
		this.endScore = hp;
		return endScore;
	}	
	public void setEndScore(int endScore) {
		this.endScore = endScore;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String now) {
		this.endTime = now;
	}
	
	/**
	 * 게임 종료 후 기록 저장
	 * @return 랭킹에 정보 추가가 됐으면 1, 아니면 0
	 */
	public int addRank(String nick, int hp) {
		getConn(); //DB접근
		
		String sql = "INSERT INTO TB_RANKING VALUES (rank_seq.NEXTVAL,?,?,SYSDATE)";
		try {
			// rank = new Ranking();
			MemberDTO dto = new MemberDTO();			
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, nick);
			psmt.setInt(2, hp);
			result = psmt.executeUpdate();
//			if(result > 0) {
//				System.out.println("랭킹 업데이트 완료!");
//			}else {
//				System.out.println("랭킹 업데이트 실패ㅠ");
//			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return result;
	}

	/**
	 * 랭킹출력
	 * @param result 
	 * @return
	 */
//	public void viewRank(int result) {		
//		getConn(); //DB접근
//		
//		String sql = "SELECT NICK,SCORE,ENDTIME FROM RANKING ORDER BY SCORE DESC LIMIT 50";
//		try {
//			psmt = conn.prepareStatement(sql);
//			rs = psmt.executeQuery();
//			
//			System.out.println("========== 팀장을 찾아서 전체랭킹 ==========");
//			if(rs.next()) {
//				while(rs.next()) {
//					System.out.println(this.getNick()+"/t"+this.getEndScore()+"/t"+this.getEndTime());
//				}
//			}else {
//				System.out.println("아직 랭킹이 없어요!");
//			}
//
//			
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			close();
//		}
//	}
	
	public int list() {
		ArrayList<Ranking> resultList = new ArrayList<Ranking>();
		
		getConn(); //DB접속
		
		String sql = "SELECT NICK, SCORE, ENDTIME FROM TB_RANKING ORDER BY SCORE DESC";
		
		try {
			psmt = conn.prepareStatement(sql); // 샘플쿼리장착
			rs = psmt.executeQuery(); // 실행메소드
			
			if(rs.next()) {
				result = 1;
				System.out.println("========== 팀장을 찾아서 랭킹 ========== ");
				System.out.println("닉네임\t점수\t날짜/시간");
				while( rs.next() ) {
					String nick = rs.getString("nick");
					int score = rs.getInt("score");
					String endTime = rs.getString("endtime");
					
					resultList.add(new Ranking(nick,score,endTime));

					System.out.println(nick+"\t"+score+"\t"+endTime);
				}	
			}else {
				result = 0;
				System.out.println("========== 팀장을 찾아서 랭킹 ========== "
						+ "\n"
						+ "\n"
						+ "\n"
						+ "      아직 랭킹이 없어요!      "
						+ "\n"
						+ "\n"
						+ "\n");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return result;
	}
	
	
}
