package Controller;

import java.util.Scanner;

import Model.MemberDAO;
import Model.MemberDTO;
import Model.scenario;

public class Controller {
	Scanner sc = new Scanner(System.in);
	scenario scene = new scenario();
	MemberDAO dao = new MemberDAO();
	MemberDTO dto = new MemberDTO();
	int result = 0; // int형 결과값의 초기 데이터
	int totalscore = 0; // 최종 점수

	public void Con_gamestart() {
		dao.gamestart();
	}

	public void Con_main() {
		while (true) {
			System.out.print("[1]가입하기 [2]게임시작 [3]불러오기 [4]랭킹확인 [5]종료 >> ");
			int input = sc.nextInt();
			if (input == 1) {
				result = dao.join(dto);
					continue;
				}
			
			if (input == 2) {
				result = dao.login(dto);
				if (result > 0) {
					System.out.println("환영합니다, 게임 시작!");
					break;
				} else {
					System.out.println("로그인 실패ㅠ 다시 입력해주세요.");
					continue;
				}

//				if (input == 3) {
//					// 불러오기
//				}
//				if (input == 4) {
//					// 랭킹확인
//				}
//				if (input == 5) {
//					// 종료
//				}
			}else {
				System.out.println("잘못 입력하였습니다.");
				continue;
			}
		}
	}

//	public int Con_join() {
//		MemberDTO dto = new MemberDTO();
//		result = dao.join(dto);
//		return result;
//	}

//	public int Con_login(String nick, String pw) {
//		MemberDTO dto = new MemberDTO(nick, pw);
//		result = dao.login(dto);
//		return result;
//	}
	public void Con_opening() {
		scene.opening(dto.getNick());
		scene.scene_chapter_1(dto.getNick());
	}
	

	public int Con_stage1() {
		result = dao.Con_stage1();
		return result;
	}

	public int Con_stage2() {
		result = dao.Con_stage2(result);
		return result;
	}

	// 경비원과 결투를 벌인다
	public int Con_stage3() { // 결투를 시작한다
		result = dao.Con_stage3(result);
		return result;
	}

}
