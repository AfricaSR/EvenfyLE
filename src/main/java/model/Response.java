package model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "comunicacion")
public class Response {
	
	private String question;
	
	private String response;
	
	public String getQuestion() {
		
		return question;
		
	}
	
	@XmlElement(name = "pregunta")
	public void setQuestion(String question) {
		
		this.question = question;
		
	}
	
	public String getResponse() {
		
		return response;
		
	}
	
	@XmlElement(name = "respuesta")
	public void setResponse(String response) {
		
		this.response = response;
		
	}
	
	public Response() {}
	
	

}
