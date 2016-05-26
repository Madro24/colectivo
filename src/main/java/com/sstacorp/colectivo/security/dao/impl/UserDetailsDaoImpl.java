package com.sstacorp.colectivo.security.dao.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.LockedException;
import org.springframework.stereotype.Repository;

import com.sstacorp.colectivo.catalogs.StatusCodes;
import com.sstacorp.colectivo.jpa.entity.LoginHistory;
import com.sstacorp.colectivo.jpa.entity.SysUser;
import com.sstacorp.colectivo.jpa.repositories.LoginHistoryRepository;
import com.sstacorp.colectivo.jpa.repositories.SysUserRepository;
import com.sstacorp.colectivo.security.dao.UserDetailsDao;

@Repository
public class UserDetailsDaoImpl implements UserDetailsDao {

	@Autowired
	private SysUserRepository sysUserRepository;

	@Autowired
	private LoginHistoryRepository loginHistoryRepository;

	private static final int MAX_ATTEMPTS = 3;

	@Override
	public void updateFailAttempts(String username) {

		SysUser user = sysUserRepository.findByUsername(username);

		if (user != null) {

			LoginHistory loginHistory = getUserAttempts(username);

			if (loginHistory == null) {
				// if no record, insert a new
				loginHistory = new LoginHistory();
				loginHistory.setSysUserId(user.getId());
				loginHistory.setAttempts(1);
				loginHistory.setLastLogin(new Date());

				loginHistoryRepository.save(loginHistory);

			} else {

				// update attempts count, +1
				loginHistory.setAttempts(loginHistory.getAttempts() + 1);
				loginHistory.setLastLogin(new Date());
				loginHistoryRepository.save(loginHistory);

				if (loginHistory.getAttempts() >= MAX_ATTEMPTS) {

					user.setStatusCode(StatusCodes.LOCKED.toString());
					sysUserRepository.save(user);

					throw new LockedException("User Account is locked!");
				}

			}
		}
	}

	@Override
	public void resetFailAttempts(String username) {
		
		List<LoginHistory> loginHistories = loginHistoryRepository.findByUsername(username);
		
		// There is only one history per user.
		if(!CollectionUtils.isEmpty(loginHistories)){
			LoginHistory loginHistory = loginHistories.get(0);
			loginHistory.setAttempts(0);
			loginHistory.setLastLogin(null);
			
			loginHistoryRepository.save(loginHistory);
		}
	}

	@Override
	public LoginHistory getUserAttempts(String username) {

		List<LoginHistory> loginHistory = loginHistoryRepository.findByUsername(username);
		
		if(!CollectionUtils.isEmpty(loginHistory)){
			return loginHistory.get(0);
		}
		else{
			return null;
		}
		
	}

}
