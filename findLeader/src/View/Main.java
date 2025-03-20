package View;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import Controller.Controller;
import Model.Friend;
import oracle.jdbc.rowset.OracleWebRowSetXmlReader;

public class Main {
	
	private static final String[] choices = {"가위", "바위", "보"}; //게임2에 쓰는 거

	public static void main(String[] args) {

		// 공통 필수 유틸 , 변수
		Scanner sc = new Scanner(System.in);
		Random ran = new Random();
		int result = 0;

		Controller control = new Controller();
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
		System.out.print("[1]가입하기 [2]게임시작 [3]불러오기 [4]랭킹확인 [5]종료 >> ");
		int input = sc.nextInt();

		if (input == 1) { // 가입하기

			do {
				System.out.print("닉네임 입력 >> ");
				String nick = sc.next();
				System.out.print("비밀번호 입력 >> ");
				String pw = sc.next();

				// 가입 로직 실행
				result = control.Con_join(nick, pw);

				if (result > 0) {
					System.out.println("가입완료!");
					break;
				}
			} while (true);

		} else if (input == 2) { // 게임시작
			do {
				// 로그인
				System.out.print("닉네임 입력 >> ");
				String nick = sc.next();
				System.out.print("비밀번호 입력 >> ");
				String pw = sc.next();
				// 로그인 로직 실행
				result = control.Con_login(nick, pw);
				if (result > 0) {
					System.out.println("환영합니다, 게임 시작!");
					break;
				} else {
					System.out.println("로그인 실패ㅠ 다시 입력해주세요.");
				}
			} while (true);

			int hp = 100; // 나의 HP

			// 게임1 시작(스마트인재개발원, 팀장의 가장 친한 친구 찾기)
			System.out.println("팀장.. 누구한테 물어봐야하지? 누구랑 친했더라...");
			System.out.println("게임 START!");
			System.out.println("팀장과 가장 친한 친구를 찾아라!");
			
	        List<Friend> friends = new ArrayList<>(Arrays.asList(
	                new Friend("김호성", new String[]{
	                    "이 친구는 97년생이에요.",
	                    "이 친구는 김씨에요.",
	                    "이 친구는 남자에요.",
	                    "이 친구는 도시락을 싸와요.",
	                    "이 친구는 흡연자에요."
	                }),
	                new Friend("옥진석", new String[]{
	                    "이 친구는 남자에요.",
	                    "이 친구는 97년생이에요.",
	                    "이 친구는 팀장이에요.",
	                    "이 친구는 문단속을 잘해요.",
	                    "이 친구는 성이 특이해요."
	                }),
	                new Friend("한수연", new String[]{
	                    "이 친구는 집이 멀어요.",
	                    "이 친구는 아침에 일찍 와요.",
	                    "이 친구는 머리가 짧아요.",
	                    "이 친구는 여자에요.",
	                    "이 친구는 지금 팀장이에요."
	                })
	            ));

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
	                	hp-=5;
	                    System.out.println("\n시간 초과! 다음 힌트로 넘어갑니다. HP: " + hp);
	                } else if (answer.equals(correctFriend.getName())) {
	                    //points = hintIndex * 2;
	                    System.out.println("정답입니다! HP: " + hp);
	                    isCorrect = true;
	                    break;
	                } else {
	                	hp-=3;
	                    System.out.println("정답이 아닙니다. HP: " + hp);
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

//	            try {
//	                br.close();
//	            } catch (IOException e) {
//	                e.printStackTrace();
//	            }

			// 게임2 시작(가장친한친구와 가위바위보 -> 이기면 회사로, 지면 집으로 ? )
			System.out.println("게임 2 시작");
	    	
	    	int totalScore = 10;
	    	int myWins = 0;
	    	int opponentWins = 0;
	    	int draws = 0;
	    	int roundCount = 1;
	    	
	    	System.out.println();
	    	System.out.println("=======두 번째 게임 시작=======");
	    	for(int round = 1; round <= 3; round++) {
	    		System.out.println("-------"+roundCount+ "번 째 판 -------");
	    		System.out.println("안내면 진다 가위, 바위, 보~!");
	    		String myChoice = sc.next(); // 사용자 입력 받기
	        	String opponentChoice = choices[ran.nextInt(3)];
	        	System.out.println("친구의 선택 : "+ opponentChoice);
	        	
	        
	        	if(myChoice.equals(opponentChoice)) {
	        		System.out.println("비겼습니다 ㅠㅠ(점수는 무효!) 남은 HP: " + hp);
	        		round--;
	        	} else if((myChoice.equals("가위") && opponentChoice.equals("보") )||
	        			  (myChoice.equals("바위") && opponentChoice.equals("가위") )||
	        			  (myChoice.equals("보") && opponentChoice.equals("주먹") )) {
	        		hp+=3;
	        		System.out.println("당신의 승리!! 남은 HP: " + hp);
	        		myWins++;
	        	} else {
	        		hp-=5;
	        		System.out.println("패배했습니다 ㅠㅠㅜ 남은 HP: " + hp);
	        		opponentWins++;
	        	}
	        	roundCount++;

	    		System.out.println("남은 기회는 " + (3- round )+"번");
	    	}
	    	
//	    		int allRounds = 3 - draws;
//	    		
//	    		if(myWins == allRounds ) {
//	    			totalScore = 10;
//	    		} else if (myWins == allRounds-1 ) {
//	    			totalScore = 7;
//	    		} else if (myWins == allRounds-2 ) {
//	    			totalScore = 4;
//	    		} else {
//	    			totalScore = 1;
//	    		}
	    	
	    	System.out.println();
	    	System.out.println("======= 게 임 종 료 =======");
	    	System.out.println("나의 승리: "+ myWins+ "번");
	    	System.out.println("친구의 승리: "+ opponentWins+"번");
	    	System.out.println("비긴 게임: "+ draws+ "번");
	    	System.out.println("남은 HP: "+ hp);
	    	System.out.println();

			// 게임3 시작(팀장을 찾아간 회사에서 경비원과 fight)         
			// 경비원과 결투를 벌인다
			int fight = control.Con_fight(hp);

			// 게임종료 후 점수계산해서 저장
			//result = control.Con_Save(nick, hp, date);

			// 랭킹출력

		} else if (input == 3) { // 불러오기

		} else if (input == 4) { // 랭킹확인

		} else if (input == 5) { // 종료
			System.out.println("===== 게임 종료 =====");
		} else {
			System.out.println("잘못된 입력입니다. 다시 입력하세요.");
		}
	}

}
