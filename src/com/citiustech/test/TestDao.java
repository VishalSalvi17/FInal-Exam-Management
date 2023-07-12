package com.citiustech.test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.citiustech.student.ConnectionProvider;

public class TestDao {

	public static Map displayAllQuesionsWithoutAns(int examID1) {
		// TODO Auto-generated method stub
		boolean  testQuestion = false;
		int count = 0;
		Map<Integer,Ques> question = new HashMap<>();
		
		try{
			// jdbc code..
			Connection con = ConnectionProvider.createConnection();
			String displayAllQuestionsQuery = "select questionId, question,examID ,opt1,opt2,opt3,correctAns from questions where examID = '"+examID1+"'";
			
			Statement stmt = con.createStatement();
			ResultSet set = stmt.executeQuery(displayAllQuestionsQuery);
			
			while(set.next()){
				Ques ques = new Ques(set.getString(2),set.getInt(3),set.getString(4),set.getString(5),set.getString(6),set.getString(7));
				question.put(count,ques);
				count++;
//				int questionId = set.getInt(1);
//				String question = set.getString(2);
//				String examID = set.getString(3);
//				String opt1 = set.getString(4);
//				String opt2 = set.getString(5);
//				String opt3 = set.getString(6);
//				
//				System.out.println("questionId : "+questionId);
//				System.out.println("question : "+question);
//				System.out.println("examID : "+examID);
//				System.out.println("opt1 : "+opt1);
//				System.out.println("opt2 : "+opt2);
//				System.out.println("opt3 : "+opt3);
			}
//			System.out.println(question);
			testQuestion = true;
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return question;
	}

	public static String correctAns(int quesId) {
		
		// TODO Auto-generated method stub
		
		String checkCorrectAns= null;
		try{
			Connection con = ConnectionProvider.createConnection();
			String corrtAns = "select correctAns from questions where questionId = '"+quesId+"'";
			
			Statement stmt = con.createStatement();
			ResultSet set = stmt.executeQuery(corrtAns);
			
			while(set.next()){
				checkCorrectAns = set.getString(1);
				
				System.out.println("correctAns : "+checkCorrectAns);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return checkCorrectAns; 
	}
}
