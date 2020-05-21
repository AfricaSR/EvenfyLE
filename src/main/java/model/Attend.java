package model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
@XmlRootElement(name="invitacion")
@XmlType(propOrder={"code", "name", "surname", "confirmed", "wellness", "responses"})
public class Attend {
	
	private String code;
	
	private String name;
	
	private String surname;
	
	private boolean confirmed;
	
	private Detail wellness;
	
	private Responses responses;
	
	public String getCode() {
		
		return code;
		
	}
	
	@XmlElement(name = "codigo-acceso")
	public void setCode(String code) {
		
		this.code = code;
		
	}
	
	public String getName() {
		
		return name;
		
	}
	
	@XmlElement(name = "nombre")
	public void setName(String name) {
		
		this.name = name;
		
	}
	
	public String getSurname() {
		
		return surname;
		
	}
	
	@XmlElement(name = "apellidos")
	public void setSurname(String surname) {
		
		this.surname = surname;
		
	}
	
	public boolean isConfirmed() {
		
		return confirmed;
		
	}
	
	@XmlElement(name = "confirmado")
	public void setConfirmed(boolean confirmed) {
		
		this.confirmed = confirmed;
		
	}

	public Responses getResponses() {
		
		return responses;
		
	}
	
	@XmlElement(name = "comunicaciones")
	public void setResponses(Responses responses) {
		
		this.responses = responses;
		
	}
	
	public Detail getWellness() {
		
		return wellness;
		
	}
	
	@XmlElement(name = "bienestar")
	public void setWellness(Detail wellness) {
		
		this.wellness = wellness;
		
	}
	
	public Attend() {}

}
