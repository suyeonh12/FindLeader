package Music;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
	
public class Sound {
	
    public void play(String src) {
        try {
            File audioFile = new File(src); // 오디오 파일 로드
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile); // 오디오 스트림 생성
            Clip clip = AudioSystem.getClip(); // Clip 객체 생성

            clip.open(audioStream); // 오디오 파일 열기
            clip.start(); // 재생 시작

            // 클립이 종료될 때까지 대기
            while (!clip.isRunning()) Thread.sleep(10);
            while (clip.isRunning()) Thread.sleep(10);

            clip.close(); // 재생이 끝난 후 Clip 닫기
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException | InterruptedException e) {
            e.printStackTrace();
        }
    }

	
}
