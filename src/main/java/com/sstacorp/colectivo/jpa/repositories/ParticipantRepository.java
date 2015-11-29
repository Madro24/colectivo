package com.sstacorp.colectivo.jpa.repositories;

import org.springframework.data.repository.CrudRepository;

import com.sstacorp.colectivo.jpa.entity.Participant;

public interface ParticipantRepository extends
		CrudRepository<Participant, Long> {

}
