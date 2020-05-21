package model;

import java.util.ArrayList;

public class Question {
	
	private String question;
	
	private ArrayList<Response> responses;
	
	public String getQuestion() {
		
		return question;
		
	}
	
	public void setQuestion(String question) {
		
		this.question = question;
		
	}
	
	public ArrayList<Response> getResponses() {
		
		return responses;
		
	}
	
	public void setResponses(ArrayList<Response> responses) {
		
		this.responses = responses;
		
	}
	
	public Question() {}

}
