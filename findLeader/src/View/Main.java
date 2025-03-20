package View;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import Controller.Controller;
import Model.Friend;
import Model.MemberDTO;
import Model.Ranking;
import oracle.jdbc.rowset.OracleWebRowSetXmlReader;

public class Main {

	public static void main(String[] args) {

		// 공통 필수 유틸 , 변수
		Scanner sc = new Scanner(System.in);
		Random ran = new Random();
		int result = 0;
		
		Controller control = new Controller();
		
		control.Con_gamestart();
		
		control.Con_main();
	}

}