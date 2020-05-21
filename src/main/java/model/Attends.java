package model;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
@XmlRootElement(name = "invitaciones")
@XmlAccessorType (XmlAccessType.FIELD)
public class Attends {
	
	@XmlElement(name = "invitacion")
	private ArrayList<Attend> invitations = null;

	public ArrayList<Attend> getInvitations() {
		
		return invitations;
		
	}

	public void setInvitations(ArrayList<Attend> invitations) {
		
		this.invitations = invitations;
		
	}
	
	

}
