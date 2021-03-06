package com.sstacorp.colectivo.jpa.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.sstacorp.colectivo.jpa.entity.ImageRelation;

public interface ImageRelationRepository extends
		CrudRepository<ImageRelation, Long> {
	
	List<ImageRelation> findByTargetIdAndImageTypeCodeIn(Long targetId, List<String> imageTypeCodeList);

}
