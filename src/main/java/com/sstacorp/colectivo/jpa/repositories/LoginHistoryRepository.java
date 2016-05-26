package com.sstacorp.colectivo.jpa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.sstacorp.colectivo.jpa.entity.LoginHistory;

public interface LoginHistoryRepository extends CrudRepository<LoginHistory, Long> {
	@Query(value = "SELECT LH.* FROM LOGIN_HISTORY LH, SYS_USER SU "
			+"WHERE LH.SYSUSER_ID = SU.ID AND SU.USERNAME = ?1", 
			nativeQuery = true)
	public List<LoginHistory> findByUsername(String username);
}
