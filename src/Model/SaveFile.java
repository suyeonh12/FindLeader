package Model;

import java.sql.SQLException;

public class SaveFile extends MemberDAO {

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

	public void setSave(String nick, int stgNum, int hp) {
		dto.setNick(nick);
		this.setStageNum(stgNum);
		this.setSaveScore(hp);

	}

	// 세이브
	public void saveData() {
		// while (true) {
		System.out.println(
				"닉네임: " + this.dto.getNick() + "\t끝낸 스테이지: " + this.getStageNum() + "\tHP: " + this.getSaveScore());
		System.out.print("저장하시겠습니까? [1]네 [2]아니오 ");
		int input = sc.nextInt();

		if (input == 1) { // 저장 
			String nick = this.dto.getNick(); 
			int stgNum = this.getStageNum();
			int hp = this.getSaveScore();

			getConn(); // DB접근
			
			//세이브 데이터 있는지 확인
			String sql = "SELECT * FROM TB_SAVE WHERE NICK=?";
			try {
				MemberDTO dto = new MemberDTO();
				psmt = conn.prepareStatement(sql);
				psmt.setString(1, nick);
				rs = psmt.executeQuery();

				if (rs.next()) {
					//세이브데이터 있으면 update로 덮어쓰기
					sql = "UPDATE TB_SAVE SET STAGE_NUM=?,SCORE=? WHERE NICK=?";
					try {
						psmt = conn.prepareStatement(sql);
						psmt.setInt(1, stgNum);
						psmt.setInt(2, hp);
						psmt.setString(3, nick);
						result = psmt.executeUpdate();
						if(result > 0) {
							System.out.println("데이터가 저장되었습니다.");
							System.out.println(
									"닉네임: " + nick + "\t끝낸 스테이지: " + stgNum + "\tHP: " + hp);
							
						}else {
							System.out.println("데이터 저장 실패!");
						}
					} catch (SQLException e) {
						e.printStackTrace();
					} finally {
						close();
					}
				} else {
					//세이브데이터 없으면 insert로 추가하기
					sql = "INSERT INTO TB_SAVE VALUES (?,?,?)";
					try {
						psmt = conn.prepareStatement(sql);
						psmt.setString(1, nick);
						psmt.setInt(2, stgNum);
						psmt.setInt(3, hp);
						result = psmt.executeUpdate();
						if(result > 0) {
							System.out.println("데이터가 저장되었습니다.");
							System.out.println(
									"닉네임: " + nick + "\t끝낸 스테이지: " + stgNum + "\tHP: " + hp);
							
						}else {
							System.out.println("데이터 저장 실패!");
						}
					} catch (SQLException e) {
						e.printStackTrace();
					} finally {
						close();
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close();
			}

		} else if (input == 2) {

		} else {
			System.out.println("잘못된 입력입니다. 다시 입력해주세요.");
		}
	}

	public void loadData() {
		getConn();
		
		MemberDAO dao = new MemberDAO();
		dao.login(dto);
		
		String sql = "SELECT * FROM TB_SAVE WHERE NICK=?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, dto.getNick());
			rs = psmt.executeQuery();
			if(rs.next()) {
				System.out.println(
						"닉네임: " + rs.getString("nick") + "\t끝낸 스테이지: " + rs.getInt("stage_num") + "\tHP: " + rs.getInt("score"));
				System.out.println("세이브 데이터가 있습니다, 게임을 이어서 하시겠습니까? [1]네 [2]아니오");
				int input = sc.nextInt();
				
				if(input == 1) { //이어서 하기 선택
					if( rs.getInt("stage_num") == 1 ) {
						Con_stage2(rs.getInt("score"));
					}else {
						Con_stage3(rs.getInt("score"));
					}
				}

			}else {
				System.out.println("세이브 데이터가 없습니다!");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	

}
