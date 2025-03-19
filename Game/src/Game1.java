package Game;

import java.util.*;

public class Game1 {
    public static void main(String[] args) {
        // 친구 데이터 설정
        List<Friend> friends = Arrays.asList(
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
        );

        // 게임 시작
        Scanner sc = new Scanner(System.in);
        Random random = new Random();
        Friend correctFriend = friends.get(random.nextInt(friends.size()));

        System.out.println("[GAME1 친구 이름 맞추기]");
        System.out.println("총 힌트는 5개입니다. 정답을 맞춰보세요!\n");

        int points = 0;
        boolean isCorrect = false; // 초기값 false, 틀렸을 때마다 실행
        int hintIndex = 0; // 사용된 힌트 번호 추적

        // 힌트를 다 쓰고 점수가 0점으로 처리될 때까지
        while (hintIndex < correctFriend.getHints().length) {
            // 새로운 inputTime 객체를 생성하고 스레드 시작
            inputTime inputTime = new inputTime();
            inputTime.start(); // 스레드 시작

            // 힌트 출력
            System.out.println("힌트 " + (hintIndex + 1) + ": " + correctFriend.getHints()[hintIndex]);

            String answer = null;

            // 사용자가 답을 입력받는 부분
            inputTime.getAnswer(); // 답을 입력받기 위한 메소드 호출

            // 5초 동안 답을 기다리고 시간이 초과되면 다음 힌트로 넘어가기
            while (!inputTime.isAnswered() && !inputTime.isTimeout()) {
                try {
                    Thread.sleep(100); // 0.1초마다 상태 체크
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            // 시간이 초과되지 않고, 답을 입력했을 경우
            if (inputTime.isAnswered()) {
                answer = inputTime.getAnswerValue();
            }

            // 시간 초과 시
            if (inputTime.isTimeout()) {
                System.out.println("시간 초과! 답을 입력할 수 없습니다.");
            } else if (answer != null && answer.equals(correctFriend.getName())) {
                points = 10 - hintIndex * 2; // 점수 계산
                System.out.println("정답입니다! 점수: " + points + "점");
                isCorrect = true;
                break;
            } else {
                System.out.println("정답이 아닙니다.");
            }

            // 다음 힌트로 넘어가기 전에 상태 초기화
            hintIndex++; // 힌트 번호 증가
        }

        // 힌트를 다 소진했을 때 0점 처리
        if (!isCorrect) {
            System.out.println("모든 힌트를 사용했지만 정답을 맞추지 못했습니다.");
            System.out.println("정답은 " + correctFriend.getName() + "이였습니다.");
            points = 0; // 힌트 소진 후 점수는 0점
        }

        
        sc.close();  // scanner 반환
    }
}
