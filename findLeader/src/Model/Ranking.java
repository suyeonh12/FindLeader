package Model;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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
	public int addRank() {
		getConn(); //DB접근
		
		String sql = "INSERT INTO RANKING VALUES (rank_seq.NEXTVAL,?,?,SYSDATE)";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, this.getNick());
			psmt.setInt(2, this.getEndScore());
			result = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return result;
	}

	/**
	 * 랭킹출력
	 * @return
	 */
//	public int printRank() {
//		getConn(); //DB접근
//		
//		String sql = "SELECT NICK,SCORE,ENDTIME FROM RANKING ORDER BY SCORE DESC LIMIT 50";
//		try {
//			psmt = conn.prepareStatement(sql);
//			rs = psmt.executeQuery();
//			
//			System.out.println("========== 팀장을 찾아서 전체랭킹 ==========");
//			while(rs.next()) {
//				System.out.println(this.getNick()+"/t"+this.getEndScore()+"/t"+this.getEndTime());
//			}
//			
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			close();
//		}
//		return result;
//	}
	
	
	public ArrayList<Ranking> rank() {
		ArrayList<Ranking> resultList = new ArrayList<>();
		getConn(); //DB접속
		
		String sql = "SELECT NICK,SCORE,ENDTIME FROM RANKING ORDER BY SCORE DESC LIMIT 50";
		
		try {
			psmt = conn.prepareStatement(sql); // 샘플쿼리장착
			rs = psmt.executeQuery(); // 실행메소드
			
			while( rs.next() ) {
				String nick = rs.getString("nick");
				int score = rs.getInt("score");
				String endtime = rs.getString("endtime");
				
				resultList.add(new Ranking(nick,score,endtime));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return resultList;
	}
	
	
}
