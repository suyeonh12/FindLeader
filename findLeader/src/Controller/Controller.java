package Controller;

import java.util.Scanner;

import Model.MemberDAO;
import Model.MemberDTO;
<<<<<<< HEAD
import Model.Ranking;
import Model.SaveFile;
=======
import Model.scenario;
>>>>>>> f4b10f781e41e7d52c7c5bc037a6bad676f35f61

public class Controller {
	Scanner sc = new Scanner(System.in);
	scenario scene = new scenario();
	MemberDAO dao = new MemberDAO();
	MemberDTO dto = new MemberDTO();
<<<<<<< HEAD
	Ranking rank = new Ranking();
	SaveFile save = new SaveFile();
=======
>>>>>>> f4b10f781e41e7d52c7c5bc037a6bad676f35f61
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
<<<<<<< HEAD
					Con_stage1();
					Con_save();
					Con_stage2();
					Con_save();
					Con_stage3();
					Con_addRank();
					Con_viewRank();
=======
>>>>>>> f4b10f781e41e7d52c7c5bc037a6bad676f35f61
					break;
				} else {
					System.out.println("로그인 실패ㅠ 다시 입력해주세요.");
					continue;
				}
<<<<<<< HEAD
			} else if (input == 3) {
				// 불러오기
				Con_saveLoad();
			} else if (input == 4) {
				// 랭킹확인
				Con_viewRank();
				break;
			} else if (input == 5) {
				// 종료
				System.out.println("게임 종료!");
				break;
			} else {
=======

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
>>>>>>> f4b10f781e41e7d52c7c5bc037a6bad676f35f61
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
		save.setSave(dto.getNick(),1,result);
		return result;
	}

	public int Con_stage2() {
		result = dao.Con_stage2(result);
		save.setSave(dto.getNick(),2,result);
		return result;
	}

	// 경비원과 결투를 벌인다
	public int Con_stage3() { // 결투를 시작한다
<<<<<<< HEAD
		result = dao.Con_stage3(result); //남은 hp
		rank.setEndScore(result);
		save.setSave(dto.getNick(),3,result);
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
=======
		result = dao.Con_stage3(result);
>>>>>>> f4b10f781e41e7d52c7c5bc037a6bad676f35f61
		return result;
	}
	
	/**
	 */
	public void Con_save() {
		save.saveData();
	}
	
	public void Con_saveLoad() {
		save.loadData();
	}

}
