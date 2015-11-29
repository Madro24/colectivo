package com.sstacorp.colectivo.jpa.entity;

import java.util.Date;

import javax.persistence.Column;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

public class EntityTrackProperties {
	@CreatedDate
	@Column(name = "CREATE_DATE")
	private Date createDate;
	
	@CreatedBy
	@Column(name = "CREATE_USER")
	private String createUser;
	
	@LastModifiedDate
	@Column(name = "UPDATE_DATE")
	private Date updateDate;
	
	@LastModifiedBy
	@Column(name = "UPDATE_USER")
	private String updateUser;
	
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public String getUpdateUser() {
		return updateUser;
	}
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
	
	
}
