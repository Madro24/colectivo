package com.sstacorp.colectivo.jpa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "MENU_CONTENT")
public class MenuContent extends EntityTrackProperties {
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name = "MENU_ID")
	private Long menuId;
	
	@Column(name = "PRODUCT_ID")
	private Long productId;
	
	@Column(name = "STATUS_CODE")
	private String statusCode;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getMenuId() {
		return menuId;
	}
	public void setMenuId(Long menuId) {
		this.menuId = menuId;
	}
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public String getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	
}
