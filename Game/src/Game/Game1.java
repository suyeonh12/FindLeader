package Game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Game1 {
    public static void main(String[] args) {
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

        Random random = new Random();
        Friend correctFriend = friends.get(random.nextInt(friends.size()));
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
                System.out.println("\n시간 초과! 다음 힌트로 넘어갑니다.");
            } else if (answer.equals(correctFriend.getName())) {
                points = 10 - hintIndex * 2;
                System.out.println("정답입니다! 점수: " + points + "점");
                isCorrect = true;
                break;
            } else {
                System.out.println("정답이 아닙니다.");
            }

            hintIndex++;
        }

        if (!isCorrect) {
            System.out.println("모든 힌트를 사용했지만 정답을 맞추지 못했습니다.");
            System.out.println("정답은 " + correctFriend.getName() + "이었습니다.");
            points = 0;
        }

        System.out.println("\n게임 종료!");
        System.out.println("최종 점수: " + points + "점");

        try {
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
