package com.sstacorp.colectivo.security.dao;

import com.sstacorp.colectivo.jpa.entity.LoginHistory;

public interface UserDetailsDao {
	void updateFailAttempts(String username);
	void resetFailAttempts(String username);
	LoginHistory getUserAttempts(String username);
}
