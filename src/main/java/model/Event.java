package model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name="evento")
@XmlType(propOrder={"idEvent", "date", "host", "code", "title", "description", "location", "street", "postalCode", "closed", "invitations"})
@XmlSeeAlso({Attends.class})
public class Event {
	
	private int idEvent;
	
	private int host;
	
	private String title;
	
	private String description;
	
	private String code;
	
	private String date;
	
	private String location;
	
	private String street;
	
	private String postalCode;
	
	private boolean closed;
	
	private Attends invitations;
	
	public int getIdEvent() {
		
		return idEvent;
		
	}
	
	@XmlElement(name="id-evento")
	public void setIdEvent(int idEvent) {
		
		this.idEvent = idEvent;
		
	}
	
	public int getHost() {
		
		return host;
		
	}
	
	@XmlElement(name="id-anfitrion")
	public void setHost(int host) {
		
		this.host = host;
		
	}
	
	public String getTitle() {
		
		return title;
		
	}
	
	@XmlElement(name="titulo")
	public void setTitle(String title) {
		
		this.title = title;
		
	}
	
	public String getDescription() {
		
		return description;
		
	}
	
	@XmlElement(name="descripcion")
	public void setDescription(String description) {
		
		this.description = description;
		
	}
	
	public String getCode() {
		
		return code;
		
	}
	
	@XmlElement(name="codigo")
	public void setCode(String code) {
		
		this.code = code;
		
	}
	
	public String getDate() {
		
		return date;
		
	}
	
	@XmlElement(name="fecha")
	public void setDate(String date) {
		
		this.date = date;
		
	}
	
	public String getLocation() {
		
		return location;
		
	}
	
	@XmlElement(name="localidad")
	public void setLocation(String location) {
		
		this.location = location;
		
	}
	
	public String getStreet() {
		
		return street;
		
	}
	
	@XmlElement(name="direccion")
	public void setStreet(String street) {
		
		this.street = street;
		
	}
	
	public String getPostalCode() {
		
		return postalCode;
		
	}
	
	@XmlElement(name="cod-postal")
	public void setPostalCode(String postalCode) {
		
		this.postalCode = postalCode;
		
	}
	
	public boolean isClosed() {
		
		return closed;
		
	}
	
	@XmlElement(name="cerrado")
	public void setClosed(boolean closed) {
		
		this.closed = closed;
		
	}
	
	public Attends getInvitations() {
		
		return invitations;
		
	}
	
	@XmlElement(name = "invitaciones")
	public void setInvitations(Attends invitations) {
		
		this.invitations = invitations;
		
	}
	
	public Event() {}

}
