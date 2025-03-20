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
		dto.setNick(nick);
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
	
	//랭킹 글자칸수 정렬
	public static String padRight(String text, int length) {
	    int textWidth = 0;
	    StringBuilder result = new StringBuilder();

	    for (char ch : text.toCharArray()) {
	        // 한글은 2칸, 영문은 1칸으로 계산
	        if (Character.toString(ch).matches("[가-힣]")) {
	            textWidth += 2;  // 한글은 2칸
	        } else {
	            textWidth += 1;  // 영문은 1칸
	        }
	        result.append(ch);
	    }

	    // 부족한 공간만큼 공백 추가하여 정확한 너비 맞추기
	    int padding = length - textWidth;
	    result.append(" ".repeat(Math.max(0, padding)));

	    return result.toString();
	}

	/**
	 * 랭킹출력
	 * @param result 
	 * @return
	 */
	public int list() {
		ArrayList<Ranking> resultList = new ArrayList<Ranking>();
		
		getConn(); //DB접속
		
		String sql = "SELECT NICK, SCORE, ENDTIME FROM TB_RANKING ORDER BY SCORE DESC";
		
		try {
			psmt = conn.prepareStatement(sql); // 샘플쿼리장착
			rs = psmt.executeQuery(); // 실행메소드
			
			result = 1;
			System.out.println("========== 팀장을 찾아서 랭킹 ==========");
			System.out.printf("%-4s %-15s %-6s %s%n", "순위", "닉네임", "점수", "날짜/시간");
			while( rs.next() ) {
				String nick = rs.getString("nick");
				int score = rs.getInt("score");
				String endTime = rs.getString("endtime");
				
				resultList.add(new Ranking(nick,score,endTime));
			}
			for (int i = 0; i < resultList.size(); i++) {
			    // 순위: 4칸, 닉네임: 16칸, 점수: 6칸, 날짜/시간: 고정된 길이
			    System.out.printf("%-4d %-15s %-6d %s%n",
			        (i + 1),                         
			        padRight(resultList.get(i).dto.getNick(), 15),  // 닉네임을 20칸 맞춤
			        resultList.get(i).getEndScore(),
			        resultList.get(i).getEndTime()
			    );
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return result;
	}
	
	
}
