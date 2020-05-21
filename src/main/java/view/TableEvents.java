package view;

import model.Event;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

//Clase de refuerzo para generar filas de información más legibles sobre cada evento
public class TableEvents {

	private String codeEvent;

	private String titleEvent;

	private String dateEvent;

	private int attendsEvent;

	public String getCodeEvent() {

		return codeEvent;

	}

	public void setCodeEvent(String codeEvent) {

		this.codeEvent = codeEvent;

	}

	public String getTitleEvent() {

		return titleEvent;

	}

	public void setTitleEvent(String titleEvent) {

		this.titleEvent = titleEvent;

	}

	public String getDateEvent() {

		return dateEvent;

	}

	public void setDateEvent(String dateEvent) {

		this.dateEvent = dateEvent;

	}

	public int getAttendsEvent() {

		return attendsEvent;

	}

	public void setAttendsEvent(int attendsEvent) {

		this.attendsEvent = attendsEvent;

	}

	public TableEvents(Event e) throws ParseException {

		setCodeEvent(e.getCode());

		setTitleEvent(e.getTitle());

		DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.ENGLISH);

		DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm", Locale.ENGLISH);

		LocalDateTime date = LocalDateTime.parse(e.getDate(), inputFormatter);

		String formattedDate = outputFormatter.format(date);

		setDateEvent(formattedDate);

		setAttendsEvent(e.getInvitations().getInvitations().size());

	}

}
