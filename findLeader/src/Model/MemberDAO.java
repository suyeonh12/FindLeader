package Model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import javax.naming.spi.DirStateFactory.Result;

import Controller.Controller;

public class MemberDAO {
scenario scene = new scenario();
	MemberDTO dto = new MemberDTO();

	private static final String[] choices = { "가위", "바위", "보" };
	// 필요 유틸
	Scanner sc = new Scanner(System.in);
	Random ran = new Random();

	// DB연결 관련 초기값 세팅
	Connection conn = null;
	PreparedStatement psmt = null;
	ResultSet rs = null;
	int hp = 100;
	int result = 0; // int형 result 초기값 세팅

	// DB 접속
	public void getConn() {

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			String url = "jdbc:oracle:thin:@project-db-campus.smhrd.com:1523:xe";
			String user = "hapjeong_24SW_DS_p1_4";
			String password = "smhrd4";

			conn = DriverManager.getConnection(url, user, password);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void close() {
		try {
			if (rs != null)
				rs.close();
			if (psmt != null)
				psmt.close();
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void gamestart() {
		System.out.println("\r\n"
				+ ":::::::::: ::::::::::: ::::    ::: :::::::::   :::        ::::::::::     :::     :::::::::  :::::::::: :::::::::  \r\n"
				+ ":+:            :+:     :+:+:   :+: :+:    :+:  :+:        :+:          :+: :+:   :+:    :+: :+:        :+:    :+: \r\n"
				+ "+:+            +:+     :+:+:+  +:+ +:+    +:+  +:+        +:+         +:+   +:+  +:+    +:+ +:+        +:+    +:+ \r\n"
				+ ":#::+::#       +#+     +#+ +:+ +#+ +#+    +:+  +#+        +#++:++#   +#++:++#++: +#+    +:+ +#++:++#   +#++:++#:  \r\n"
				+ "+#+            +#+     +#+  +#+#+# +#+    +#+  +#+        +#+        +#+     +#+ +#+    +#+ +#+        +#+    +#+ \r\n"
				+ "#+#            #+#     #+#   #+#+# #+#    #+#  #+#        #+#        #+#     #+# #+#    #+# #+#        #+#    #+# \r\n"
				+ "###        ########### ###    #### #########   ########## ########## ###     ### #########  ########## ###    ### \r\n"
				+ "");
		System.out.println("<사람찾아드립니다 Team Project>");
		System.out.println("***** 팀장을 찾아서🔍 *****");
	}

//	public void gameselect(int ) {
//		
//	}

	public int join(MemberDTO dto) {
		getConn(); // DB접근

		String sql = "INSERT INTO MEMBER VALUES (?,?)";

		try {
			System.out.print("닉네임 입력 >> ");
			String nick = sc.next();
			System.out.print("비밀번호 입력 >> ");
			String pw = sc.next();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, nick);
			psmt.setString(2, pw);

			result = psmt.executeUpdate();
			if (result > 0) {
				System.out.println("가입완료!");
			} else {
				System.out.println("중복된 닉네임입니다. 다시 입력해주세요.");
			}
		} catch (SQLException e) {
			// e.printStackTrace();
//			System.out.println("중복된 닉네임입니다. 다시 입력해주세요.");
		} finally {
			close();
		}
		return result;
	}

	public int login(MemberDTO dto) {
		getConn(); // DB접근

		String sql = "SELECT * FROM MEMBER WHERE NICK=? AND PW=?";

		try {
			System.out.print("닉네임 입력 >> ");
			String nick = sc.next();
			dto.setNick(nick);
			System.out.print("비밀번호 입력 >> ");
			String pw = sc.next();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, nick);
			psmt.setString(2, pw);

			rs = psmt.executeQuery();

			if (rs.next()) {
				result = 1;
			} else {
				result = 0;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return result;
	}
	
	
	public int Con_stage1() {
		int hp = 100; // 나의 HP

		System.out.println("팀장.. 누구한테 물어봐야하지? 누구랑 친했더라...");
		System.out.println("게임 START!");
		System.out.println("팀장과 가장 친한 친구를 찾아라!");

		List<Friend> friends = new ArrayList<>(Arrays.asList(
				new Friend("김호성",
						new String[] { "이 친구는 97년생이에요.", "이 친구는 김씨에요.", "이 친구는 남자에요.", "이 친구는 도시락을 싸와요.",
								"이 친구는 흡연자에요." }),
				new Friend("옥진석",
						new String[] { "이 친구는 남자에요.", "이 친구는 97년생이에요.", "이 친구는 팀장이에요.", "이 친구는 문단속을 잘해요.",
								"이 친구는 성이 특이해요." }),
				new Friend("한수연", new String[] { "이 친구는 집이 멀어요.", "이 친구는 아침에 일찍 와요.", "이 친구는 머리가 짧아요.", "이 친구는 여자에요.",
						"이 친구는 지금 팀장이에요." })));

		// random = new Random();
		Friend correctFriend = friends.get(ran.nextInt(friends.size()));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("[GAME1 친구 이름 맞추기]");
		System.out.println("총 힌트는 5개입니다. 정답을 맞춰보세요!\n");

		int points = 0;
		boolean isCorrect = false;
		int hintIndex = 0;

		while (hintIndex < correctFriend.getHints().length) {
			System.out.println("힌트 " + (hintIndex + 1) + ": " + correctFriend.getHints()[hintIndex]);
			System.out.print("정답을 입력하세요 (5초 내): ");

			String answer = null;
			long startTime = System.currentTimeMillis();

			try {
				while (System.currentTimeMillis() - startTime < 5000) {
					if (br.ready()) {
						answer = br.readLine().trim();
						break;
					}
					Thread.sleep(100);
				}
			} catch (IOException | InterruptedException e) {
				e.printStackTrace();
			}

			if (answer == null) {
				hp -= 5;
				System.out.println("\n시간 초과! 다음 힌트로 넘어갑니다. HP: " + hp);
			} else if (answer.equals(correctFriend.getName())) {
				// points = hintIndex * 2;
				System.out.println("정답입니다! HP: " + hp);
				scene.scene_chapter_1_1(correctFriend.getName());
				isCorrect = true;
				break;
			} else {
				hp -= 3;
				System.out.println("정답이 아닙니다. HP: " + hp);
				scene.scene_chapter_1_2(correctFriend.getName());
			}
			hintIndex++;
		}

		if (!isCorrect) {
			System.out.println("모든 힌트를 사용했지만 정답을 맞추지 못했습니다.");
			System.out.println("정답은 " + correctFriend.getName() + "이었습니다.");
			points = 0;
		}

		System.out.println("\n게임 종료!");
		System.out.println("남은 HP: " + hp);
		return hp;
	}
	

	public int Con_stage2(int hp) {
		System.out.println("게임 2 시작");
		
		int totalScore = 10;
		int myWins = 0;
		int opponentWins = 0;
		int draws = 0;
		int roundCount = 1;

		System.out.println();
		System.out.println("=======두 번째 게임 시작=======");
		for (int round = 1; round <= 3; round++) {
			System.out.println("-------" + roundCount + "번 째 판 -------");
			System.out.println("안내면 진다 가위, 바위, 보~!");
			String myChoice = sc.next(); // 사용자 입력 받기
			String opponentChoice = choices[ran.nextInt(3)];
			System.out.println("친구의 선택 : " + opponentChoice);

			if (myChoice.equals(opponentChoice)) {
				System.out.println("비겼습니다 ㅠㅠ(점수는 무효!) 남은 HP: " + hp);
				round--;
				draws++;
			} else if ((myChoice.equals("가위") && opponentChoice.equals("보"))
					|| (myChoice.equals("바위") && opponentChoice.equals("가위"))
					|| (myChoice.equals("보") && opponentChoice.equals("주먹"))) {
				hp += 3;
				System.out.println("당신의 승리!! 남은 HP: " + hp);
				myWins++;
			} else {
				hp -= 5;
				System.out.println("패배했습니다 ㅠㅠㅜ 남은 HP: " + hp);
				opponentWins++;
			}
			roundCount++;

			System.out.println("남은 기회는 " + (3 - round) + "번");
		}

//    		int allRounds = 3 - draws;
//    		
//    		if(myWins == allRounds ) {
//    			totalScore = 10;
//    		} else if (myWins == allRounds-1 ) {
//    			totalScore = 7;
//    		} else if (myWins == allRounds-2 ) {
//    			totalScore = 4;
//    		} else {
//    			totalScore = 1;
//    		}

		System.out.println();
		System.out.println("======= 게 임 종 료 =======");
		System.out.println("나의 승리: " + myWins + "번");
		System.out.println("친구의 승리: " + opponentWins + "번");
		System.out.println("비긴 게임: " + draws + "번");
		System.out.println("남은 HP: " + hp);
		System.out.println();
		return hp;
	}

	// 세번째 게임: 경비원을 이겨라!
	public int Con_stage3(int hp) {
		scene.scene_chapter_3_intro(dto.getNick());
		int count = 1;
		int Playerhp = hp;
		int Enemyhp = Playerhp-20;
		while (true) {
			int com_choice = ran.nextInt(3) + 1;
			System.out.println(count + "번째 턴입니다.");
			System.out.println("경비원의 공격! 공격을 회피하세요!\n [1] 왼쪽 [2] 오른쪽");
			int choice = sc.nextInt();
			if (choice != 1 && choice != 2) {
				System.out.println("숫자를 잘못 입력했습니다.");
				continue;
			}
			if (com_choice != 1) {
				System.out.println("경비원의 공격을 피했습니다");
			} else {
				System.out.println("경비원의 공격을 피하지 못했습니다. "+dto.getNick()+" HP -10");
				Playerhp -= 10;
			}
			System.out.println(dto.getNick()+ " HP : " + Playerhp + " 경비원 HP : " + Enemyhp);
			if (Playerhp <= 0) {
				scene.scene_chapter_3_2(dto.getNick());
				break;
			}
			System.out.println(dto.getNick()+"의 공격! 어느쪽으로 공격하시겠습니까? \n [1] 왼쪽 [2] 오른쪽");
			choice = sc.nextInt();
			if (choice != 1 && choice != 2) {
				System.out.println("숫자를 잘못 입력했습니다.");
				continue;
			}
			if (com_choice != 1) {
				System.out.println("공격이 적중했습니다! '경비원' HP - 10");
				Enemyhp -= 10;
			} else {
				System.out.println("경비원이 공격을 피했습니다");
			}
			System.out.println(dto.getNick()+" HP : " + Playerhp + " 경비원 HP : " + Enemyhp);
			if (Enemyhp <= 0) {
				System.out.println("결투에서 승리했습니다! 점수" + Playerhp + "획득!");
				scene.scene_chapter_3_1(dto.getNick());
				break;
			}

			count++;
		}
		return Playerhp;
	}

}
