package com.sstacorp.colectivo.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sstacorp.colectivo.catalogs.RoleCodes;
import com.sstacorp.colectivo.catalogs.StatusCodes;
import com.sstacorp.colectivo.dto.ParticipantDTO;
import com.sstacorp.colectivo.dto.ProfileUserDTO;
import com.sstacorp.colectivo.dto.SysUserDTO;
import com.sstacorp.colectivo.errors.ErrorTypes;
import com.sstacorp.colectivo.exceptions.ErrorMessage;
import com.sstacorp.colectivo.exceptions.ErrorMessageException;
import com.sstacorp.colectivo.exceptions.ErrorUtil;
import com.sstacorp.colectivo.jpa.entity.Participant;
import com.sstacorp.colectivo.jpa.entity.SysUser;
import com.sstacorp.colectivo.jpa.repositories.ParticipantRepository;
import com.sstacorp.colectivo.jpa.repositories.SysUserRepository;
import com.sstacorp.colectivo.services.ProfileService;

@Service
public class ProfileServiceImpl implements ProfileService {

	@Autowired
	ParticipantRepository participantRepository;
	@Autowired
	SysUserRepository sysUserRepository;

	@Override
	public ProfileUserDTO createParticipant(ProfileUserDTO profileRequest) {
		List<ErrorMessage> errors = new ArrayList<ErrorMessage>();

		validateRequestParticipant(profileRequest, errors);

		ErrorUtil.handleErrors(errors);

		ParticipantDTO participantDTO = profileRequest.getParticipant();

		Participant participant = participantRepository
				.save(populateParticipantEntity(new Participant(),
						participantDTO));

		SysUserDTO sysUserDTO = profileRequest.getSysUser();
		SysUser sysUser = sysUserRepository.save(populateSysUserEntity(
				new SysUser(), sysUserDTO, participant.getId()));

		return new ProfileUserDTO(populateSysUserDTO(sysUser),
				populateParticipantDTO(participant));

	}

	@Override
	public ProfileUserDTO updateParticipant(ProfileUserDTO profileRequest) {
		ParticipantDTO participantDTO = profileRequest.getParticipant();
		Participant participant = participantRepository.findOne(participantDTO
				.getId());
		participant = participantRepository.save(populateParticipantEntity(
				participant, participantDTO));

		SysUserDTO sysUserDTO = profileRequest.getSysUser();
		SysUser sysUser = sysUserRepository.findOne(sysUserDTO.getId());
		sysUser = sysUserRepository.save(populateSysUserEntity(sysUser,
				sysUserDTO, participant.getId()));

		return new ProfileUserDTO(populateSysUserDTO(sysUser),
				populateParticipantDTO(participant));
	}

	@Override
	public ProfileUserDTO getParticipantById(Long participantId) {
		Participant participant = participantRepository.findOne(participantId);
		SysUser sysUser = sysUserRepository.findByParticipantId(participantId);

		return new ProfileUserDTO(populateSysUserDTO(sysUser),
				populateParticipantDTO(participant));
	}

	@Override
	public SysUserDTO changeStatusParticipant(Long participantId,
			String newStatus) {

		sysUserRepository.changeStatusUser(newStatus, participantId);

		SysUser sysUser = sysUserRepository.findByParticipantId(participantId);

		return new SysUserDTO(sysUser.getId(), sysUser.getUsername(),
				sysUser.getPassword(), sysUser.getStatusCode(),
				sysUser.getParticipantId());
	}

	private Participant populateParticipantEntity(Participant participant,
			ParticipantDTO participantDTO) {
		if (participantDTO != null) {
			participant.setFirstName(participantDTO.getFirstName());
			participant.setLastName(participantDTO.getLastName());
			participant.setEmail(participantDTO.getEmail());
			participant.setBirthdate(participantDTO.getBirthdate());
			if (participant.getRoleCode() == null)
				participant.setRoleCode(RoleCodes.CLIENT.toString());
		}
		return participant;
	}

	private SysUser populateSysUserEntity(SysUser sysUser,
			SysUserDTO sysUserDTO, Long participantId) {
		if (sysUserDTO != null) {
			if (sysUserDTO.getUsername() != null)
				sysUser.setUsername(sysUserDTO.getUsername());
			if (sysUserDTO.getPassword() != null)
				sysUser.setPassword(sysUserDTO.getPassword());
			if (sysUser.getStatusCode() == null)
				sysUser.setStatusCode(StatusCodes.NEW.toString());
			if (participantId != null)
				sysUser.setParticipantId(participantId);
		}
		return sysUser;
	}

	private ParticipantDTO populateParticipantDTO(Participant participant) {
		ParticipantDTO participantDTO = new ParticipantDTO();
		if (participant != null) {
			participantDTO.setId(participant.getId());
			participantDTO.setFirstName(participant.getFirstName());
			participantDTO.setLastName(participant.getLastName());
			participantDTO.setEmail(participant.getEmail());
			participantDTO.setBirthdate(participant.getBirthdate());

		}
		return participantDTO;
	}

	private SysUserDTO populateSysUserDTO(SysUser sysUser) {
		return new SysUserDTO(sysUser.getId(), sysUser.getUsername(),
				sysUser.getPassword(), // encrypt password.
				sysUser.getStatusCode(), sysUser.getParticipantId());
	}

	private void validateRequestParticipant(ProfileUserDTO profileUser,
			List<ErrorMessage> errors) {
		if (profileUser == null) {
			errors.add(new ErrorMessage(ErrorTypes.ERROR_PROFILE_USER_EMPTY));
		} else {
			SysUserDTO sysUserDTO = profileUser.getSysUser();

			if (sysUserDTO == null || sysUserDTO.getPassword() == null
					|| sysUserDTO.getUsername() == null) {
				errors.add(new ErrorMessage(ErrorTypes.ERROR_SYSUSER_FIELD_EMPTY));
			}
			// check if username already exists
			else if (sysUserRepository.findByUsername(sysUserDTO.getUsername()) != null) {
				errors.add(new ErrorMessage(ErrorTypes.ERROR_USERNAME_EXIST));
			}

			ParticipantDTO participantDTO = profileUser.getParticipant();
			if (participantDTO == null || participantDTO.getFirstName() == null
					|| participantDTO.getLastName() == null
					|| participantDTO.getEmail() == null
					|| participantDTO.getBirthdate() == null) {
				errors.add(new ErrorMessage(ErrorTypes.ERROR_PARTICIPANT_FIELD_EMPTY));
			}
		}
	}

}
