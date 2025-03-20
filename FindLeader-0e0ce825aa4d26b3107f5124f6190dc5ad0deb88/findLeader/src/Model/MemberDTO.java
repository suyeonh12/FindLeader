package Model;

public class MemberDTO {
	//회원정보
	private String nick; //닉네임
	private String pw; //비번
	
	public MemberDTO(){
		
	}
	
	public MemberDTO(String nick, String pw) {
		this.nick = nick;
		this.pw = pw;
	}
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	
	
}
