package Controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import Model.MemberDAO;
import Model.MemberDTO;
import Model.Ranking;

public class Controller {
	Scanner sc = new Scanner(System.in);
	MemberDAO dao = new MemberDAO();
	MemberDTO dto = new MemberDTO();
	Ranking rank = new Ranking();
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
			} else if (input == 2) {
				result = dao.login(dto);
				if (result > 0) {
					System.out.println("환영합니다, 게임 시작!");
					Con_stage1();
					Con_stage2();
					Con_stage3();
					Con_addRank();
					Con_viewRank();
					break;
				} else {
					System.out.println("로그인 실패ㅠ 다시 입력해주세요.");
					continue;
				}
			} else if (input == 3) {
				// 불러오기
				System.out.println("저장된 데이터가 있는가?");
			} else if (input == 4) {
				// 랭킹확인
				Con_viewRank();
				break;
			} else if (input == 5) {
				// 종료
				System.out.println("게임 종료!");
				break;
			} else {
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
		result = dao.Con_stage3(result); //남은 hp
		rank.setEndScore(result);
		return result;
	}
	
	/**
	 * 게임기록 저장
	 */
	public int Con_addRank() {      
		result = rank.addRank(dto.getNick(),rank.getEndScore());
		return result;
	}
	
	/**
	 * 랭킹출력
	 */
	public int Con_viewRank() {
		result = rank.list();
		return result;
	}

}