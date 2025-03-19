
import java.util.Random;
import java.util.Scanner;

public class Game2 {
	
	private static final String[] choices = {"가위", "바위", "보"};
	  
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
    	Random ran = new Random();
    	
    	int totalScore = 10;
    	int myWins = 0;
    	int opponentWins = 0;
    	int draws = 0;
    	
    	
    	System.out.println("=======두 번째 게임 시작=======");
    	for(int round = 1; round <= 3; round++) {
    		System.out.println("-------"+round+ "번 째 판 -------");
    		System.out.println("안내면 진다 가위, 바위, 보~!");
        	
        	String myChoice = sc.nextLine();
        	String opponentChoice = choices[ran.nextInt(3)];
        	System.out.println("친구의 선택 : "+ opponentChoice);
        	
        	if(myChoice.equals(opponentChoice)) {
        		System.out.println("비겼습니다 ㅠㅠ(점수는 무효!)");
        		draws++;
        	} else if((myChoice.equals("가위") && opponentChoice.equals("보") )||
        			  (myChoice.equals("바위") && opponentChoice.equals("가위") )||
        			  (myChoice.equals("보") && opponentChoice.equals("주먹") )) {
        		System.out.println("당신의 승리!!");
        		myWins++;
        	} else {
        		System.out.println("패배했습니다 ㅠㅠㅜ");
        		opponentWins++;
        	}

    		System.out.println("남은 기회는 " + (3- round )+"번");
    	}
    	
    		int allRounds = 3- draws;
    		
    		if(myWins == allRounds ) {
    			totalScore = 10;
    		} else if (myWins == allRounds-1 ) {
    			totalScore = 7;
    		} else if (myWins == allRounds-2 ) {
    			totalScore = 4;
    		} else {
    			totalScore = 1;
    		}
    	
    	System.out.println("======= 게 임 종 료 =======");
    	System.out.println("나의 승리: "+ myWins+ "번");
    	System.out.println("친구의 승리: "+ opponentWins+"번");
    	System.out.println("비긴 게임: "+ draws+ "번");
    	System.out.println("최종 점수: "+ totalScore+ "점");
    	


	}

}
