package com.sophos.laboratory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sophos.laboratory.model.TestE;


/**
 * Repositorio del test.
 * @author Viviana Cifuentes <viviana.cifuentes05@gmail.com>
 *
 */
@Repository
public interface TestRepository extends JpaRepository<TestE, Long>{
	
	
}
