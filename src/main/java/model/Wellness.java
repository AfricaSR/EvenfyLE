package model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="detalle")
public class Wellness {
	
	public enum typeWellness {
		
		Alérgenos, 
		Diversidad
		
	}
	
	private typeWellness type;
	
	private String name;
	
	public typeWellness getType() {
		
		return type;
		
	}
	
	@XmlElement(name = "tipo")
	public void setType(typeWellness type) {
		
		this.type = type;
		
	}
	
	public String getName() {
		
		return name;
		
	}
	
	@XmlElement(name = "nombre")
	public void setName(String name) {
		
		this.name = name;
		
	}
	
	public Wellness() {}
}
