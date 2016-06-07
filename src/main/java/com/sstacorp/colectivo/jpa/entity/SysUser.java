package com.sstacorp.colectivo.jpa.entity;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import com.sstacorp.colectivo.catalogs.StatusCodes;

@Entity
@Table(name = "SYS_USER")
public class SysUser extends EntityTrackProperties implements UserDetails {

	private static final long serialVersionUID = -1846162336955675938L;

	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name = "USERNAME")
	private String username;
	
	@Column(name = "PASSWORD")
	private String password;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="PARTICIPANT_ID", unique= true, nullable=true, insertable=true, updatable=true)
	private Participant participant;
	
	@Column(name = "STATUS_CODE")
	private String statusCode;
	
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
	public Participant getParticipant() {
		return participant;
	}
	public void setParticipant(Participant participant) {
		this.participant = participant;
	}
	public String getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	// Spring Security UserDetails methods
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return AuthorityUtils.createAuthorityList(participant.getRoleCode());
	}
	@Override
	public boolean isAccountNonExpired() {
		if(!StatusCodes.EXPIRED.toString().equals(statusCode)){
			return true;
		}
		return false;
	}
	@Override
	public boolean isAccountNonLocked() {
		if(!StatusCodes.LOCKED.toString().equals(statusCode)){
			return true;
		}
		return false;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isEnabled() {
		if(StatusCodes.ACTIVE.toString().equals(statusCode)){
			return true;
		}
		else return false;
	}
}
