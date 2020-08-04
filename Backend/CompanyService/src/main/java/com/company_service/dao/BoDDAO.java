package com.company_service.dao;

import java.util.ArrayList;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.company_service.model.BOD;
import com.company_service.model.Company;

@Repository
@Transactional
public interface BoDDAO extends CrudRepository<BOD, Integer>{
	@Query(nativeQuery = true, value = "Select * from bod where company_id = :id")
	ArrayList<BOD> findByCompanyId(int id);
	
	@Modifying
	@Query(nativeQuery = true, value = "Delete from bod where company_id = :id")
	void deleteByCompanyId(int id);
}
