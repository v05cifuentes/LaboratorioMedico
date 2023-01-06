package com.sophos.laboratory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sophos.laboratory.model.Affiliate;
/**
 * Repositorio del Afiliado.
 * @author Viviana Cifuentes <viviana.cifuentes05@gmail.com>
 *
 */
@Repository
public interface AffiliateRepository extends JpaRepository<Affiliate, Long>{
	
}
