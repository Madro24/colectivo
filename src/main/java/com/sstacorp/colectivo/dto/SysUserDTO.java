package com.sstacorp.colectivo.dto;


public class SysUserDTO {
	private Long id;
	private String username;
	private String password;
	private String statusCode;
	private Long participantId;

	
	public SysUserDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public SysUserDTO(Long id, String username,String password, String statusCode, Long participantId) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.statusCode = statusCode;
		this.participantId = participantId;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public Long getParticipantId() {
		return participantId;
	}

	public void setParticipantId(Long participantId) {
		this.participantId = participantId;
	}
	
	
}

