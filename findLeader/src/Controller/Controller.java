package Controller;

import Model.MemberDAO;
import Model.MemberDTO;

public class Controller {
	
	MemberDAO dao = new MemberDAO();
	
	int result = 0; // int형 결과값의 초기 데이터
	
	public int Con_join(String nick, String pw) {
		MemberDTO dto = new MemberDTO(nick, pw);
		result = dao.join(dto);
		return result;
	}

	public int Con_login(String nick, String pw) {
		MemberDTO dto = new MemberDTO(nick,pw);
		result = dao.login(dto);
		return result;
	}
	
	// 경비원과 결투를 벌인다
	public int Con_fight(int hp) { // 결투를 시작한다
		System.out.println("야생의 경비원이 나타났다");
		result = dao.fight(hp);
		if (result > 0) {
			System.out.println("결투에서 승리했습니다! 점수"+dao.fight(hp)+"획득!");
		}
		if (result <= 0) {
			System.out.println("패배하였습니다. GAME OVER");
		}
		return result;
	}

}
