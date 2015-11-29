package com.sstacorp.colectivo.jpa.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.sstacorp.colectivo.jpa.entity.SysUser;

@Transactional(readOnly = true)
public interface SysUserRepository extends CrudRepository<SysUser, Long> {
	SysUser findByUsername(String username);
	SysUser findByParticipantId(Long participantId);

	@Modifying
	@Transactional
	@Query("UPDATE SysUser SET statusCode = ?  WHERE participantId = ?")
	void changeStatusUser(String statusCode, Long participantId);
}
