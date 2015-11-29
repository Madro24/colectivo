package com.sstacorp.colectivo.jpa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "IMAGE_RELATION")
public class ImageRelation extends EntityTrackProperties{

	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name = "IMAGE_ID")
	private Long imageId;
	
	@Column(name = "IMAGE_TYPE_CODE")
	private String imageTypeCode;
	
	@Column(name = "TARGET_ID")
	private Long targetId;

	public ImageRelation() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getImageId() {
		return imageId;
	}

	public void setImageId(Long imageId) {
		this.imageId = imageId;
	}

	public String getImageTypeCode() {
		return imageTypeCode;
	}

	public void setImageTypeCode(String imageTypeCode) {
		this.imageTypeCode = imageTypeCode;
	}

	public Long getTargetId() {
		return targetId;
	}

	public void setTargetId(Long targetId) {
		this.targetId = targetId;
	}
	
}
