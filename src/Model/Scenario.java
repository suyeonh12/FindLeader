package Model;


public class Scenario {
	MemberDTO dto = new MemberDTO();
	private String nick;
	
	public void setNick(String nick) {
		this.nick =nick;
	}
	
	public String getNick() {
		return this.nick;
	}
	private int hp;
	
	public void setHp(int hp) {
		this.hp=hp;
	}
	public int getHp() {
		return this.hp;
	}
	
	public void printMessage(String message) throws InterruptedException {
	    for (char c : message.toCharArray()) {
	        System.out.print(c);
	        Thread.sleep(100);
	    }
	}
	
   public void opening(){
	    try {
	        printMessage("[오프닝]\r\n"
	            + "금요일 아침 – 팀원들의 희망\r\n"
	            + "\"드디어 금요일! 오늘만 버티면 행복한 주말이다! 야호!\"\r\n"
	            + "\r\n"
	            + "하지만, 뭔가 이상하다.\r\n"
	            + "오전 9시가 지나도 팀장님이 출근하지 않았다.\r\n"
	            + "\"어라? 늦잠 주무시는 건가?\"\r\n"
	            + "\r\n"
	            + "시간은 흘러 오후 6시.\r\n"
	            + "아직도 팀장님의 흔적은 커녕, 연락조차 닿지 않는다.\r\n"
	            + "\"뭐야, 도대체 어디 가신 거야? 이제 입·퇴실 공지는 누가 하냐고!\"\r\n"
	            + "\"안 되겠다. 내가 직접 팀장님을 찾아 나서야겠어!\"\r\n"
	            + "\r\n"
	            + "그러나 문제는 체력.\r\n"
	            + "금요일의 피로는 이미 당신을 지치게 만들었다.\r\n"
	            + "현재 체력은 HP 100. 체력이 모두 소진되기 전에 팀장님을 찾아야 한다!\r\n"
	            + "\r\n"
	            + "지금부터 당신의 특별한 금요일 모험이 시작된다!\r\n");

	    } catch (InterruptedException e) {
	        e.printStackTrace();
	    }
   }


   public void scene_chapter_1() {
	    try {
	        printMessage("[STAGE 1: 강의실 – 친구 이름 맞추기, 가위바위보]\r\n"
		            + "강의실에 도착한 당신은 팀장님의 평소 절친을 발견했다.\r\n"
		            + nick+" : \"팀장님이 어디 있는지 알아?!\"\r\n"
		            + "\r\n"
		            + "친구는 수상한 미소를 지으며 대답한다.\r\n"
		            + "친구 : \"알려주긴 할 건데, 조건이 있어. 내 이름을 맞춰봐!\"\r\n"
		            + "\r\n"
		            + nick+" : \"이름을 맞추라니, 흥미롭네! 힌트를 줘봐.\"\r\n"
		            + "친구: \"좋아, 총 5개의 힌트를 줄게. 정답을 맞추면 체력 유지, 틀리면 체력 -3, 힌트를 보고도 말하지 못하면 체력 -5다!\"\r\n"
		            + "");

	    } catch (InterruptedException e) {
	        e.printStackTrace();
	    }

	}

	
	
   public void scene_chapter_1_1(String answer) {
	    try {
	    	//정답을 맞추면
	        printMessage(answer+" : \"정답이야! 뇌 근육 좀 쓰네? \r\n"
	                  + "근데 너 요즘 너무 피곤해 보이는데… \r\n"
	                  + "나랑 놀면 체력이 조금은 회복되지 않을까? \r\n"
	                  + "가위바위보 한 판 어때? \r\n"
	            + "규칙은 간단해! 총 3판. 이기면 체력 +3, 비기면 기회 차감 없고 체력 유지, 지면 체력 -5.\"\r\n"
	            + "");

	    } catch (InterruptedException e) {
	        e.printStackTrace();
	    }
   }

   public void scene_chapter_1_2 (String answer) {
	      //플레이어 (만약 정답을 못 맞추면): 
//	         "내 이름은 "+answer+"이야, 이름을 맞추지 못했으니 내가 기회를 한번 더 줄게 나와 가위바위보 대결을 해서 이기면 그 때 어디있는지 확실히 알려줄게!\r\n"
//	         +"규칙은 간단해! 총 3판. 이기면 체력 +3, 비기면 기회 차감 없고 체력 유지, 지면 체력 -5."

	   }

   public void scene_chapter_2_1() {
	    try {
	        printMessage("좋아, "+nick+"이제 약속대로 어디있는지 알려줄게. 팀장님은 옆건물 회사에 있을거야.\r\n"
		    		  + " 그런데 그 회사 출입하기 쉽지 않을거야 무서운 경비원이 문 앞을 지키고 있으니, 조심하도록 해! \r\n"
		    		  + "");

	    } catch (InterruptedException e) {
	    	e.printStackTrace();
	    }
   }

   public void scene_chapter_2_2() {
	    try {
	        printMessage(nick+" 네가 졌네! 하지만 나랑 놀면서 시간과 체력을 많이 소비했으니 특별히 어디있는지 알려줄게.\"\r\n"
		    		  + " 팀장님은 옆건물 회사에 있을거야. \r\n"
		    		  + " 그런데 그 회사 출입하기 쉽지 않을거야 무서운 경비원이 문 앞을 지키고 있으니, 조심하도록 해! \r\n"
		    		  + "");

	    } catch (InterruptedException e) {
	        e.printStackTrace();
	    }
   }

   public void scene_chapter_2_3() {
	    try {
	        printMessage(nick+"\" 응 고마워! 그럼 우린 다음에 보자!\"\r\n"
		            + "");

	    } catch (InterruptedException e) {
	        e.printStackTrace();
	    }
   }

   public void scene_chapter_3_intro() {
	    try {
	        printMessage("[STAGE 2: 회사 – 경비원과의 대결]\r\n"
	                + "상황:\r\n"
	                + nick+"은(는) 팀장님이 계신 건물에 도착한다. \r\n"
	                + "하지만 문 앞에 서 있는 경비원이 심상치 않다. \r\n"
	                + "마치 ‘여기서 끝내겠다’는 듯 날카로운 눈빛으로 당신을 노려본다.\r\n"
	                + "경비원과의 대결에서 승리해야만 팀장님이 계신 곳으로 들어갈 수 있다.\r\n"
	                + "\r\n"
	                + "경비원: \"여기 들어갈 수 있는 자격은 나에게 인정받은 사람뿐이다. 싸울 준비 됐나?\"\r\n"
	                + nick+": \"얼마든지요 저는 꼭 팀장님을 찾아야 합니다!\"\r\n"
	                + "\r\n"
	                + "\"좋다, 그럼 시작하지. 내 규칙은 간단하다.\r\n"
	                + "지금 현재 남아있는 네 체력은 "+hp+", 내 체력은 "+(hp-20)+"으로 시작하도록 하지,\r\n"
	                + "내 공격을 피하지 못하면 네 체력에서 10씩 깎인다.\r\n"
	                + "단, 네가 나를 공격해 맞추면 내 체력도 10씩 깎인다.\r\n"
	                + "누구의 체력이 먼저 0이 되느냐가 승부의 열쇠다!\"\r\n"
	                + "");

	    } catch (InterruptedException e) {
	        e.printStackTrace();
	    }
   }


   public void scene_chapter_3_1() {
	    try {
	        printMessage("경비원:"+
	   	         "이럴 수가! 네가 나를 이기다니... 약속은 약속이니까, 들어가도 좋다.\r\n"
		         +nick+
		         ": 팀장님! 제가 여기까지 찾아왔습니다!\r\n"
		         +"팀장님:"+
		         "잘했어. 네 용기와 노력에 감탄한다. 이제 우리 같이 일하자!\r\n"
		         +"내레이션:"+
		         "그 날 이후, 팀장님과 함께 새로운 도전을 시작한 당신. "
		         + "금요일의 모험은 당신의 인생을 완전히 바꿔놓았다.");

	    } catch (InterruptedException e) {
	        e.printStackTrace();
	    }
   }
   public void scene_chapter_3_2() {
	    try {
	        printMessage("경비원:\r\n"
		            + "\"네 체력이 다 떨어졌어. 넌 들어갈 수 없다.\"\r\n"
		            + "내레이션:\r\n"
		            + "\"모든 전투에서 패배한 당신은 결국 팀장님을 찾지 못했다. "
		            + "그 후, 팀장님은 전설처럼 사라졌고, 당신은 아쉬운 마음으로 금요일을 마무리해야 했다.\"");

	    } catch (InterruptedException e) {
	        e.printStackTrace();
	    }
   }

}
