package View;

import java.util.Scanner;
import Controller.Controller;
import oracle.jdbc.rowset.OracleWebRowSetXmlReader;

public class Main {

	public static void main(String[] args) {
		
		//공통 필수 유틸 , 변수
		Scanner sc = new Scanner(System.in);
		Controller control = new Controller();
		

		while (true) {
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
					int result = control.Con_join(nick,pw);
					
					if(result > 0) {
						System.out.println("가입완료!");
						break;
					}
				}while(true);
				
			} else if (input == 2) { // 게임시작
				do {
					//로그인
					System.out.print("닉네임 입력 >> ");
					String nick = sc.next();
					System.out.print("비밀번호 입력 >> ");
					String pw = sc.next();
					//로그인 로직 실행
					int result = control.Con_login(nick,pw);
					if(result > 0) {
						System.out.println("환영합니다, 게임 시작!");
						break;						
					}else {
						System.out.println("로그인 실패ㅠ 다시 입력해주세요.");
					}
				}while(true);
				
				int score = 0; //게임점수!
				
				//게임1 시작(스마트인재개발원, 팀장의 가장 친한 친구 찾기)
				System.out.println("게임 1 시작");
				
				//게임2 시작(가장친한친구와 가위바위보 -> 이기면 회사로, 지면 집으로 ? )
				System.out.println("게임 2 시작");
				
				//게임3 시작(팀장을 찾아간 회사에서 경비원과 fight)
				System.out.println("게임 3 시작");
				
				//게임종료 후 점수계산해서 저장
				
				//랭킹출력
				break;

			} else if (input == 3) { // 불러오기

			} else if (input == 4) { // 랭킹확인
				

			} else if (input == 5) { // 종료
				System.out.println("===== 게임 종료 =====");
				break;
			} else {
				System.out.println("잘못된 입력입니다. 다시 입력하세요.");
			}
		}
	}

}
