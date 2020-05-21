package model;

import java.util.ArrayList;

public class User {
	
	private int idUser;
	
	private String name;
	
	private String surname;
	
	private String email;
	
	private String token;
	
	private ArrayList<Event> Events;
	
	public int getIdUser() {
		
		return idUser;
		
	}
	
	public void setIdUser(int idUser) {
		
		this.idUser = idUser;
		
	}
	
	public String getName() {
		
		return name;
		
	}
	
	public void setName(String name) {
		
		this.name = name;
		
	}
	
	public String getSurname() {
		
		return surname;
		
	}
	
	public void setSurname(String surname) {
		
		this.surname = surname;
		
	}
	
	public String getEmail() {
		
		return email;
		
	}
	
	public void setEmail(String email) {
		
		this.email = email;
		
	}
	
	public ArrayList<Event> getEvents() {
		
		return Events;
		
	}
	
	public void setEvents(ArrayList<Event> events) {
		
		Events = events;
		
	}
	
	public String getToken() {
		
		return token;
		
	}
	
	public void setToken(String token) {
		
		this.token = token;
		
	}
	
	public User() {}

}
