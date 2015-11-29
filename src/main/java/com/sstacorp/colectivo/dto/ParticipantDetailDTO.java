package com.sstacorp.colectivo.dto;

import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;
import com.sstacorp.colectivo.util.dto.EntityTrackDTO;

public class ParticipantDetailDTO extends EntityTrackDTO {
	private ParticipantDTO participantBase;
	
	public ParticipantDetailDTO(){
		participantBase = new ParticipantDTO();
	}

	public Long getId(){
		return participantBase.getId();
	}
	public void setId(Long id){
		participantBase.setId(id);
	}
	public String getFirstName() {
		return participantBase.getFirstName();
	}
	public void setFirstName(String firstName) {
		participantBase.setFirstName(firstName);
	}
	public String getLastName() {
		return participantBase.getLastName();
	}
	public void setLastName(String lastName) {
		participantBase.setLastName(lastName);
	}
	public String getEmail() {
		return participantBase.getEmail();
	}
	public void setEmail(String email) {
		participantBase.setEmail(email);
	}
	@JsonSerialize(using=DateSerializer.class)
	public Date getBirthdate() {
		return participantBase.getBirthdate();
	}
	public void setBirthdate(Date birthdate) {
		participantBase.setBirthdate(birthdate);
	}
}
