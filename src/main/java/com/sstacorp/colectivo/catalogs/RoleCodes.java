package com.sstacorp.colectivo.catalogs;

public enum RoleCodes {
	CLIENT("CLNT"),
	ADMIN("ADM"),
	CHEF("CHEF");
	
	private String role;

	private RoleCodes(String role) {
		this.role = role;
	}

	public String getRole() {
		return role;
	}

	public String toString(){
		return role;
	}
	
}
