package com.sophos.laboratory.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sophos.laboratory.model.TestE;
import com.sophos.laboratory.repository.TestRepository;

/**
 * Implementación de los servicios de la interfaz de AppointmentService.
 * @author Viviana Cifuentes <viviana.cifuentes05@gmail.com>
 *
 */
@Service
public class TestServiceImp implements TestService {
	
	@Autowired
	TestRepository testRepository;
	
	@Override
	public TestE createTest(TestE testE) {
		return testRepository.save(testE);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<TestE> getList() {
		return testRepository.findAll();
	}
	
	@Override
	@Transactional(readOnly = true)
	public Optional<TestE> findById(Long idTest) {
		return testRepository.findById(idTest);
	}
	
	@Override
	public TestE updateTest(TestE request, Long id) {
		TestE test = testRepository.findById(id).get();
	
	    if(Objects.nonNull(request.getNameTest())) {
	        test.setNameTest(request.getNameTest());
	    }
	
	    if(Objects.nonNull(request.getDescriptionTest())) {
	    	test.setDescriptionTest(request.getDescriptionTest());
	    }
	
	
	    return testRepository.save(test);
	}
	
	@Override
	public void deleteTestById(Long idTest) {
		this.testRepository.deleteById(idTest);
	}
	
}
