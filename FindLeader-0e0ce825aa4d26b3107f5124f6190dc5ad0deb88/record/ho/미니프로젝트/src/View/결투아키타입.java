package View;

import java.util.Random;
import java.util.Scanner;

public class 결투아키타입 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Random random = new Random();
		int count = 1;
		int playerhp = 80;
		int enemyhp = 80;
		System.out.println("야생의 경비원이 나타났다");
		while (true) {
			int com_choice = random.nextInt(3) + 1;
			System.out.println(count + "번째 턴입니다.");
			System.out.println("경비원의 공격! 공격을 회피하세요!\n [1] 왼쪽 [2] 오른쪽");
			int choice = sc.nextInt();
			if (com_choice != 1) {
				System.out.println("경비원의 공격을 피했습니다");
			}
			if (com_choice == 1) {
				System.out.println("경비원의 공격을 피하지 못했습니다. '플레이어' hp -10");
				playerhp -= 10;
			}
			System.out.println("플레이어 HP : " + playerhp + " 경비원 HP : " + enemyhp);
			if (playerhp <= 0) {
				System.out.println("게임오버");
				break;
			} else if (enemyhp <= 0) {
				System.out.println("승리!");
				break;
			}
			System.out.println("당신의 공격! 어느쪽으로 공격하시겠습니까? /n [1] 왼쪽 [2] 오른쪽");
			choice = sc.nextInt();
			if (com_choice != 1) {
				System.out.println("공격이 적중했습니다! '경비원' hp - 10");
				enemyhp -= 10;
			}
			if (com_choice == 1) {
				System.out.println("경비원이 공격을 피했습니다");
			}
			System.out.println("플레이어 HP : " + playerhp + " 경비원 HP : " + enemyhp);
			if (playerhp <= 0) {
				System.out.println("게임오버");
				break;
			} else if (enemyhp <= 0) {
				System.out.println("승리!");
				break;
			}

			count++;
		}
		// while문 끝나고 남은 플레이어 HP를 DATABASE에 입력
//		String sql = "update 팀프로젝트 set ranking where nick == ?";
//		prst = conn.prepareStatement(sql);
//		prst.setString(1, dto.getNick());

	}

}
