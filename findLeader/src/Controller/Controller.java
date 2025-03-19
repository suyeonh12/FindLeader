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

}
