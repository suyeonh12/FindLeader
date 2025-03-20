package Controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import Model.MemberDAO;
import Model.MemberDTO;
import Model.Ranking;
import Model.SaveFile;

public class Controller {
	
	MemberDAO dao = new MemberDAO();
	
	int result = 0; // int형 결과값의 초기 데이터
	
	/**
	 * 회원가입 메소드
	 * @param nick
	 * @param pw
	 * @return
	 */
	public int Con_join(String nick, String pw) {
		MemberDTO dto = new MemberDTO(nick, pw);
		result = dao.join(dto);
		return result;
	}

	/**
	 * 로그인 메소드
	 * @param nick
	 * @param pw
	 * @return
	 */
	public int Con_login(String nick, String pw) {
		MemberDTO dto = new MemberDTO(nick,pw);
		result = dao.login(dto);
		return result;
	}
	
	/**
	 * 게임3: 경비원과 결투
	 * @param hp
	 * @return
	 */
	// 경비원과 결투를 벌인다
	public int Con_fight(int hp) { // 결투를 시작한다
		System.out.println("야생의 경비원이 나타났다");
		result = dao.fight(hp);
		if (result > 0) {
			System.out.println("결투에서 승리했습니다! 남은 HP : "+result);
		}
		if (result <= 0) {
			System.out.println("패배하였습니다. GAME OVER");
		}
		return result;
	}
	
	/**
	 * 게임기록 저장
	 */
	public int Con_addRank(String nick, int hp) {
		Ranking rank = new Ranking();
		//현재시간 yyyy-MM-dd HH:mm:ss 형식으로 저장
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String formattedDate = now.format(formatter);
        
		rank.setNick(nick);
		rank.setEndScore(hp);
		rank.setEndTime(formattedDate);
		
		result = rank.addRank();
		
		if(result > 0) {
			System.out.println("랭킹 업데이트 완료!");
		}else {
			System.out.println("랭킹 업데이트 실패ㅠ");
		}
		return 0;
	}
	
	/**
	 * 랭킹출력
	 */
//	public void Con_printRank() {
//		rank.printRank();
//		
//	}
	
	

}
