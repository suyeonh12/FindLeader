package Model;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class Sound {
    private static final String[] choices = {"가위", "바위", "보"};

    // 음원을 재생하는 메서드 (비동기적으로 재생)
    public static void playSound(String soundFilePath) {
        new Thread(() -> {
            try {
                // WAV 파일 경로를 사용하여 오디오 스트림을 로드
                File soundFile = new File(soundFilePath);
               // System.out.println("파일 경로: " + soundFilePath);

                // 파일이 존재하는지 확인
                if (!soundFile.exists()) {
                    //System.out.println("파일이 존재하지 않습니다!");
                    return;
                }

                //System.out.println("파일이 존재합니다. 오디오 스트림을 로드합니다.");
                AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundFile);

                // Clip 객체 생성 (오디오 재생을 위한 객체)
                Clip clip = AudioSystem.getClip();

                // 오디오 스트림을 Clip 객체에 로드
                clip.open(audioStream);
                //System.out.println("오디오 파일 로드 완료.");

                // 사운드 재생
                clip.start();
                //System.out.println("사운드 재생 시작");

                // 사운드가 끝날 때까지 대기 (소리가 제대로 나는지 확인하기 위해 대기 시간 늘리기)
                Thread.sleep(clip.getMicrosecondLength() / 1000);  // 마이크로초 단위 길이를 밀리초로 변환하여 대기

                // Clip 종료 후 자원 해제
                clip.close();
                //System.out.println("오디오 재생 종료");
            } catch (UnsupportedAudioFileException e) {
                //System.out.println("지원되지 않는 오디오 파일 형식입니다.");
                e.printStackTrace();
            } catch (IOException e) {
                //System.out.println("파일을 읽는 도중 문제가 발생했습니다.");
                e.printStackTrace();
            } catch (LineUnavailableException e) {
                //System.out.println("오디오 라인을 사용할 수 없습니다.");
                e.printStackTrace();
            } catch (InterruptedException e) {
               // System.out.println("스레드가 중단되었습니다.");
                e.printStackTrace();
            }
        }).start();  // 비동기적으로 실행
    }

//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        Random ran = new Random();
//
//        int totalScore = 10;
//        int myWins = 0;
//        int opponentWins = 0;
//        int draws = 0;
//
//        // 게임 시작 음원 재생
//        String gameStartFilePath = "C:\\Users\\smhrd\\Desktop\\gameStart.mp3.wav"; // 게임 시작 음원
//        playSound(gameStartFilePath);
//
//        System.out.println("=======두 번째 게임 시작=======");
//        
//        for (int round = 1; round <= 3; round++) {
//            System.out.println("-------" + round + "번 째 판 -------");
//            System.out.println("안내면 진다 가위, 바위, 보~!");
//
//            String myChoice = sc.nextLine();
//            String opponentChoice = choices[ran.nextInt(3)];
//            System.out.println("친구의 선택 : " + opponentChoice);
//
//            // 승리, 패배에 따른 음원 재생
//            if (myChoice.equals(opponentChoice)) {
//                System.out.println("비겼습니다 ㅠㅠ(점수는 무효!)");
//                draws++;
//                // 비겼을 때 음원
//                String drawSoundFilePath = "C:\\Users\\smhrd\\Desktop\\박수.wav"; // 비겼을 때 음원
//                playSound(drawSoundFilePath);
//            } else if ((myChoice.equals("가위") && opponentChoice.equals("보")) ||
//                    (myChoice.equals("바위") && opponentChoice.equals("가위")) ||
//                    (myChoice.equals("보") && opponentChoice.equals("바위"))) {
//                System.out.println("당신의 승리!!");
//                myWins++;
//                // 승리했을 때 음원
//                String winSoundFilePath = "C:\\Users\\smhrd\\Desktop\\박수.wav"; // 승리 음원
//                playSound(winSoundFilePath);
//            } else {
//                System.out.println("패배했습니다 ㅠㅠㅜ");
//                opponentWins++;
//                // 패배했을 때 음원
//                String loseSoundFilePath = "C:\\Users\\smhrd\\Desktop\\패배.wav"; // 패배 음원
//                playSound(loseSoundFilePath);
//            }
//
//            System.out.println("남은 기회는 " + (3 - round) + "번");
//        }
//
//        int allRounds = 3 - draws;
//
//        if (myWins == allRounds) {
//            totalScore = 10;
//        } else if (myWins == allRounds - 1) {
//            totalScore = 7;
//        } else if (myWins == allRounds - 2) {
//            totalScore = 4;
//        } else {
//            totalScore = 1;
//        }
//
//        // 게임 종료 음원 재생
//        String gameEndFilePath = "C:\\Users\\smhrd\\Desktop\\gameEnd.mp3.wav"; // 게임 종료 음원
//        playSound(gameEndFilePath);
//
//        System.out.println("======= 게임 종료 =======");
//        System.out.println("나의 승리: " + myWins + "번");
//        System.out.println("친구의 승리: " + opponentWins + "번");
//        System.out.println("비긴 게임: " + draws + "번");
//        System.out.println("최종 점수: " + totalScore + "점");
//    }
}
