package Model;

import java.time.LocalDate;

public class Member_DTO {

	private String nickname;
	private String pw;
	private int score;
	private int stage_num;

	private LocalDate date;

	public Member_DTO() {

	}

	public Member_DTO(String nickname, String pw) {
		this.nickname = nickname;
		this.pw = pw;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public int getStage_num() {
		return stage_num;
	}

	public void setStage_num(int stage_num) {
		this.stage_num = stage_num;
	}
}
