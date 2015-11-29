package com.sstacorp.colectivo.jpa.repositories;

import org.springframework.data.repository.CrudRepository;

import com.sstacorp.colectivo.jpa.entity.CompanyParticipants;

public interface CompanyParticipantsRepository extends
		CrudRepository<CompanyParticipants, Long> {

}
