package com.sstacorp.colectivo.profile;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.sstacorp.colectivo.catalogs.RoleCodes;
import com.sstacorp.colectivo.catalogs.StatusCodes;
import com.sstacorp.colectivo.dto.ParticipantDTO;
import com.sstacorp.colectivo.dto.ProfileUserDTO;
import com.sstacorp.colectivo.dto.SysUserDTO;
import com.sstacorp.colectivo.errors.ErrorTypes;
import com.sstacorp.colectivo.exceptions.ErrorMessageException;
import com.sstacorp.colectivo.jpa.entity.Participant;
import com.sstacorp.colectivo.jpa.entity.SysUser;
import com.sstacorp.colectivo.jpa.repositories.ParticipantRepository;
import com.sstacorp.colectivo.jpa.repositories.SysUserRepository;
import com.sstacorp.colectivo.services.impl.ProfileServiceImpl;
import com.sstacorp.colectivo.utils.TestUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/WEB-INF/spring/appServlet/servlet-context.xml"})
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class })
public class ProfileServiceTest extends TestCase {
	private final String TEST_USERNAME = "madrido";
	private final String TEST_PASSWORD = "abcd1234";
	private final String TEST_FIRST_NAME_1 = "Omar";
	private final String TEST_LAST_NAME_1 = "Madrid";
	private final String TEST_EMAIL_1 = "omadrid24@gmail.com";
	private final Date TEST_BIRTHDATE_1 = new Date();
	
	@Mock
	SysUserRepository mockedSysUserRepository;
	
	@Mock
	ParticipantRepository mockedParticipantRepository;
	
	@InjectMocks
	ProfileServiceImpl profileService;
	
	@Before
	public void setup(){
		MockitoAnnotations.initMocks(this);
		
	}
	
	@Test
	public void createParticipant_all_values_success(){
		SysUserDTO expectedSysUser = new SysUserDTO(null,TEST_USERNAME,TEST_PASSWORD,null,null);
		ParticipantDTO expectedParticipant = new ParticipantDTO(null,TEST_FIRST_NAME_1,TEST_LAST_NAME_1,TEST_EMAIL_1, TEST_BIRTHDATE_1);
		ProfileUserDTO participantRequest = 
				new ProfileUserDTO(expectedSysUser, expectedParticipant);
		
		//setup mockito
		PowerMockito.when(mockedParticipantRepository.save(any(Participant.class))).thenReturn(populateParticipantEntity(new Participant(), expectedParticipant));
		PowerMockito.when(mockedSysUserRepository.save(any(SysUser.class))).thenReturn(populateSysUserEntity(new SysUser(),expectedSysUser,new Participant()));
		
		ProfileUserDTO profileResult = profileService.createParticipant(participantRequest);
		assertNotNull(profileResult.getSysUser());
		assertNotNull(profileResult.getParticipant());
		assertTrue(this.isExpectedParticipant(expectedParticipant, profileResult.getParticipant()));
		assertTrue(this.isExpectedSysUser(expectedSysUser, profileResult.getSysUser()));
	}

	@Test
	public void createParticipant_username_exists_fail(){
		
		SysUserDTO expectedSysUser = new SysUserDTO(null,TEST_USERNAME,TEST_PASSWORD,null,null);
		ParticipantDTO expectedParticipant = new ParticipantDTO(null,TEST_FIRST_NAME_1,TEST_LAST_NAME_1,TEST_EMAIL_1, TEST_BIRTHDATE_1);
		ProfileUserDTO participantRequest = 
				new ProfileUserDTO(expectedSysUser, expectedParticipant);
				
		//setup mockito
		PowerMockito.when(mockedSysUserRepository.findByUsername(anyString())).thenReturn(new SysUser());
		
		try{
			ProfileUserDTO profileResult = profileService.createParticipant(participantRequest);
		}catch(ErrorMessageException e){
			assertEquals(HttpStatus.BAD_REQUEST, e.getStatus());
			assertEquals(ErrorTypes.ERROR_USERNAME_EXIST.getCode(), e.getErrors().get(0).getCode());
		}
		
	}
	
	@Test
	public void createParticipant_profileUser_empty_fail(){
		
		ProfileUserDTO participantRequest = null;
		
		try{
			ProfileUserDTO profileResult = profileService.createParticipant(participantRequest);
		}catch(ErrorMessageException e){
			assertEquals(HttpStatus.BAD_REQUEST, e.getStatus());
			assertEquals(ErrorTypes.ERROR_PROFILE_USER_EMPTY.getCode(), e.getErrors().get(0).getCode());
		}
		
	}
	
	@Test
	public void createParticipant_participant_empty_fail(){
		SysUserDTO expectedSysUser = new SysUserDTO(null,TEST_USERNAME,TEST_PASSWORD,null,null);
		ParticipantDTO expectedParticipant = null;
		ProfileUserDTO participantRequest = 
				new ProfileUserDTO(expectedSysUser, expectedParticipant);
		
		
		try{
			ProfileUserDTO profileResult = profileService.createParticipant(participantRequest);
		}catch(ErrorMessageException e){
			assertEquals(HttpStatus.BAD_REQUEST, e.getStatus());
			assertEquals(ErrorTypes.ERROR_PARTICIPANT_FIELD_EMPTY.getCode(), e.getErrors().get(0).getCode());
		}
		
	}
	
	@Test
	public void createParticipant_sysUser_empty_fail(){
		SysUserDTO expectedSysUser = null;
		ParticipantDTO expectedParticipant = new ParticipantDTO(null,TEST_FIRST_NAME_1,TEST_LAST_NAME_1,TEST_EMAIL_1, TEST_BIRTHDATE_1);
		ProfileUserDTO participantRequest = 
				new ProfileUserDTO(expectedSysUser, expectedParticipant);		
		
		try{
			ProfileUserDTO profileResult = profileService.createParticipant(participantRequest);
		}catch(ErrorMessageException e){
			assertEquals(HttpStatus.BAD_REQUEST, e.getStatus());
			assertEquals(ErrorTypes.ERROR_SYSUSER_FIELD_EMPTY.getCode(), e.getErrors().get(0).getCode());
		}
		
	}
	
	@Test
	public void createParticipant_sysUser_and_participant_empty_fail(){
		List<String> expectedErrors = Arrays.asList(ErrorTypes.ERROR_PARTICIPANT_FIELD_EMPTY.getCode(),
													ErrorTypes.ERROR_SYSUSER_FIELD_EMPTY.getCode());	
		SysUserDTO expectedSysUser = null;
		ParticipantDTO expectedParticipant = null;
		ProfileUserDTO participantRequest = 
				new ProfileUserDTO(expectedSysUser, expectedParticipant);		
		
		try{
			ProfileUserDTO profileResult = profileService.createParticipant(participantRequest);
		}catch(ErrorMessageException e){
			assertEquals(HttpStatus.BAD_REQUEST, e.getStatus());
			TestUtil.assertValidationErrors(expectedErrors, e);
		}
		
	}
	private boolean isExpectedParticipant(ParticipantDTO expected,ParticipantDTO actual){
		if(expected == null || actual ==null) return false;
		if(actual.getId() == null) return false;
		if(!expected.getFirstName().equals(actual.getFirstName())) return false;
		if(!expected.getLastName().equals(actual.getLastName())) return false;
		if(!expected.getEmail().equals(actual.getEmail())) return false;
		if(!expected.getBirthdate().equals(actual.getBirthdate())) return false;
		return true;
	}
	
	private boolean isExpectedSysUser(SysUserDTO expected, SysUserDTO actual){
		if(expected == null || actual == null) return false;
		if(actual.getId() == null) return false;
		if(expected.getId() != null && expected.getId() != actual.getId()) return false;
		if(expected.getPassword() != null && !expected.getPassword().equals(actual.getPassword())) return false;
		if(expected.getStatusCode() != null && !expected.getStatusCode().equals(actual.getStatusCode())) return false;
		if(expected.getUsername() != null && !expected.getUsername().equals(actual.getUsername())) return false;
		if(expected.getParticipantId() != null && !expected.getParticipantId().equals(actual.getParticipantId())) return false;
		
		return true;
	}
	
	private Participant populateParticipantEntity(Participant participant ,ParticipantDTO participantDTO) {
		if (participantDTO != null) {
			participant.setId(1L);
			participant.setFirstName(participantDTO.getFirstName());
			participant.setLastName(participantDTO.getLastName());
			participant.setEmail(participantDTO.getEmail());
			participant.setBirthdate(participantDTO.getBirthdate());
			if(participant.getRoleCode() == null)
				participant.setRoleCode(RoleCodes.CLIENT.toString());
		}
		return participant;
	}
	
	private SysUser populateSysUserEntity(SysUser sysUser,SysUserDTO sysUserDTO,Participant participant) {
		if (sysUserDTO != null) {
			sysUser.setId(1L);
			if(sysUserDTO.getUsername() != null)
				sysUser.setUsername(sysUserDTO.getUsername());
			if(sysUserDTO.getPassword() != null)
				sysUser.setPassword(sysUserDTO.getPassword());
			if(sysUser.getStatusCode() == null)
				sysUser.setStatusCode(StatusCodes.NEW.toString());
			if(participant != null)
				sysUser.setParticipant(participant);
		}
		return sysUser;
	}

}
