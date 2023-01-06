package com.sophos.laboratory.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sophos.laboratory.model.Affiliate;
import com.sophos.laboratory.repository.AffiliateRepository;
/**
 * Implementación de los servicios de la interfaz de AffiliateService.
 * @author Viviana Cifuentes <viviana.cifuentes05@gmail.com>
 *
 */
@Service
public class AffiliateServiceImp implements AffiliateService {
	
	@Autowired
	AffiliateRepository affiliateRepository;
	
	@Override
	@Transactional(readOnly = true)
	public List<Affiliate> getList() {
		return affiliateRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Affiliate> findById(Long idAffiliate) {
		return affiliateRepository.findById(idAffiliate);
	}

	@Override
	public Affiliate createAffiliate(Affiliate affiliate) {
		return affiliateRepository.save(affiliate);
	}

	@Override
	public Affiliate updateAffiliate(Affiliate request, Long id) {
		Affiliate affiliate = affiliateRepository.findById(id).get();
		
		if(Objects.nonNull(request.getNameAffiliate())) {
			affiliate.setNameAffiliate(request.getNameAffiliate());
		}
		
		if(Objects.nonNull(request.getAgeAffiliate())) {
			affiliate.setAgeAffiliate(request.getAgeAffiliate());
		}
		
		if(Objects.nonNull(request.getMailAffiliate())) {
			affiliate.setMailAffiliate(request.getMailAffiliate());
		}
		
		return affiliateRepository.save(affiliate);
	}

	@Override
	public void deleteAffiliateById(Long idAffiliate) {
		affiliateRepository.deleteById(idAffiliate);
	}

}
