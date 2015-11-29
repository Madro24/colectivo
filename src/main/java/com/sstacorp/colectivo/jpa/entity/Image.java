package com.sstacorp.colectivo.jpa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "IMAGE")
public class Image extends EntityTrackProperties {
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name = "IMAGE")
	private byte[] image;
	
	@Column(name = "CONTENT_TYPE")
	private String contentType;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public byte[] getImage() {
		return image;
	}
	public void setImage(byte[] image) {
		this.image = image;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	
	
}
