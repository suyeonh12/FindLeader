package Game;

public class Friend { // 친구의 정보를 저장하고 제공하는 클래스
	private String name;      // 친구의 이름
    private String[] hints;   // 친구에 대한 힌트들(여러개의 문자열)

    // 생성자: 이름과 힌트 배열을 받아서 초기화
    public Friend(String name, String[] hints) {
        this.name = name;
        this.hints = hints;
    }

    // 이름을 반환하는 메소드
    public String getName() {
        return name;
    }

    // 힌트 배열을 반환하는 메소드
    public String[] getHints() {
        return hints;
    }
}
