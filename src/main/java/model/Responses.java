package model;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
@XmlRootElement(name = "comunicaciones")
@XmlAccessorType (XmlAccessType.FIELD)
public class Responses {
	
	@XmlElement(name = "comunicacion")
	private ArrayList<Response> responses;
	
	public ArrayList<Response> getResponses() {
		
		return responses;
		
	}
	
	public void setResponses(ArrayList<Response> responses) {
		
		this.responses = responses;
		
	}

}
