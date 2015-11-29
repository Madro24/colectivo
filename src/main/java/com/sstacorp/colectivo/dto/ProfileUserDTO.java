package com.sstacorp.colectivo.dto;

public class ProfileUserDTO {
	SysUserDTO sysUser;
	ParticipantDTO participant;
	
	
	public ProfileUserDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ProfileUserDTO(SysUserDTO sysUser, ParticipantDTO participant) {
		super();
		this.sysUser = sysUser;
		this.participant = participant;
	}
	public SysUserDTO getSysUser() {
		return sysUser;
	}
	public void setSysUser(SysUserDTO sysUser) {
		this.sysUser = sysUser;
	}
	public ParticipantDTO getParticipant() {
		return participant;
	}
	public void setParticipant(ParticipantDTO participant) {
		this.participant = participant;
	}
	
	
}
