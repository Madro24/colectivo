package com.sstacorp.colectivo.services;

import com.sstacorp.colectivo.dto.ProfileUserDTO;
import com.sstacorp.colectivo.dto.SysUserDTO;

public interface ProfileService{
	ProfileUserDTO createParticipant(ProfileUserDTO participantRequest );
	ProfileUserDTO updateParticipant(ProfileUserDTO participantRequest );
	ProfileUserDTO getParticipantById(Long participantId );
	SysUserDTO changeStatusParticipant(Long participantId, String newStatus);
}
