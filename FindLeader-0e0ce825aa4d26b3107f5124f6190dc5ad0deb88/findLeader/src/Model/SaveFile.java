package Model;

public class SaveFile extends MemberDAO{
	
	private int stageNum;
	private int saveScore;
	
	public int getStageNum() {
		return stageNum;
	}
	public void setStageNum(int stageNum) {
		this.stageNum = stageNum;
	}
	public int getSaveScore() {
		return saveScore;
	}
	public void setSaveScore(int saveScore) {
		this.saveScore = saveScore;
	}
	
	public void updateScore(int stage, int hp){
		this.stageNum = stage;
		this.saveScore = hp;
	}
	
}
