package model;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
@XmlRootElement(name = "bienestar")
@XmlAccessorType (XmlAccessType.FIELD)
public class Detail {
	
	@XmlElement(name = "detalle")
	private ArrayList<Wellness> wellness;
	
	public ArrayList<Wellness> getWellness() {
		
		return wellness;
		
	}
	
	public void setWellness(ArrayList<Wellness> wellness) {
		
		this.wellness = wellness;
		
	}
	
}
