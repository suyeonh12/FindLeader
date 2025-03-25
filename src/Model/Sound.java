package Model;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Sound {
    // Clip 객체를 외부에서 접근할 수 있도록 static으로 선언
    private static Clip clip;

    public static void playSound(String soundFilePath) {
        try {
            // getResource()로 파일을 찾음
            URL resource = Sound.class.getClassLoader().getResource(soundFilePath);
            
            if (resource == null) {
                System.out.println("오디오 파일을 찾을 수 없습니다: " + soundFilePath);
                return;
            }

            // 파일 경로를 얻고 오디오 스트림 처리
            File soundFile = new File(resource.toURI());
            
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundFile);
            clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();

            // 재생이 끝날 때까지 대기
            while (clip.isRunning()) {
                Thread.sleep(100); // 음악이 끝날 때까지 대기
            }

        } catch (UnsupportedAudioFileException e) {
            System.out.println("지원되지 않는 오디오 파일 형식입니다.");
        } catch (IOException e) {
            System.out.println("오디오 파일을 읽는 중 오류가 발생했습니다.");
        } catch (LineUnavailableException e) {
            System.out.println("오디오 장치를 사용할 수 없습니다.");
        } catch (InterruptedException e) {
            System.out.println("스레드가 중단되었습니다.");
        } catch (URISyntaxException e) {
            System.out.println("URI 구문 오류: " + e.getMessage());
        }
    }

    // 음악을 중지하는 메서드
    public static void stopSound() {
        if (clip != null && clip.isRunning()) {
            clip.stop(); // 음악 중지
            clip.close(); // 리소스 해제
        }
    }
}