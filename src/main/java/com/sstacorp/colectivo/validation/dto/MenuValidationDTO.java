package com.sstacorp.colectivo.validation.dto;

import org.springframework.http.HttpMethod;

import com.sstacorp.colectivo.dto.MenuDTO;

public class MenuValidationDTO {
	private MenuDTO menu;
	private Long companyId;
	private Long menuId;
	private HttpMethod requestType;
	
	public MenuValidationDTO(Long companyId, HttpMethod requestType) {
		this.companyId = companyId;
		this.requestType = requestType;
	}
	public MenuValidationDTO(Long companyId, Long menuId,HttpMethod requestType) {
		this.companyId = companyId;
		this.menuId = menuId;
		this.requestType = requestType;
	}
	
	public MenuValidationDTO(MenuDTO menu, Long companyId, HttpMethod requestType) {
		this.companyId = companyId;
		this.menu = menu;
		this.requestType = requestType;
	}
	
	public MenuValidationDTO(MenuDTO menu, Long companyId, Long menuId,HttpMethod requestType) {
		this.companyId = companyId;
		this.menuId = menuId;
		this.menu = menu;
		this.requestType = requestType;
	}
	public MenuDTO getMenu() {
		return menu;
	}
	public void setMenu(MenuDTO menu) {
		this.menu = menu;
	}
	public Long getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}
	public Long getMenuId() {
		return menuId;
	}
	public void setMenuId(Long menuId) {
		this.menuId = menuId;
	}
	public HttpMethod getRequestType() {
		return requestType;
	}
	public void setRequestType(HttpMethod requestType) {
		this.requestType = requestType;
	}
	
	
}
