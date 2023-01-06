package com.sophos.laboratory.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sophos.laboratory.model.TestE;
import com.sophos.laboratory.service.TestServiceImp;

/**
 * Controlador que manipula el flujo de los servicios Test.
 * @author Viviana Cifuentes <viviana.cifuentes05@gmail.com>
 *
 */
@RestController
@RequestMapping("/api/controller/test")
public class TestController {
	
	
	@Autowired 
	private TestServiceImp testServiceImpl;
	
	
	/**
	 * Método que crea un registro dentro de la tabla Test
	 * @param testE
	 * @return la respuesta Http 200-Created si el registro fue creado y 404-Not Found si no.
	 */
	@PostMapping
	public ResponseEntity<?> create(@RequestBody TestE testE){
		
		if(testE.getNameTest() == null || testE.getDescriptionTest() == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		testServiceImpl.createTest(testE);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	
	/**
	 * Método para consultar todos los registros de la tabla test.
	 * @return la respuesta Http 200-Ok si existen los registros y 204-no Content si no.
	 */
	@GetMapping("/")
	public ResponseEntity<?> getTests() {
		List<TestE> testEs = testServiceImpl.getList();
		
		if(testEs.isEmpty()) {
			return ResponseEntity.noContent().build();
		}else {
			return ResponseEntity.status(HttpStatus.OK).body(testEs);
		}
	}
	
	
	/**
	 * Método para realizar búsqueda de tests por Id.
	 * @param id
	 * @return la respuesta Http 200-Ok si el registro existe y 204-no Content si no.
	 */
	@GetMapping("/{id}")
	public ResponseEntity<?> getById(@PathVariable Long id){
		Optional<TestE> optTest = testServiceImpl.findById(id);
		
		if(!optTest.isPresent()) {
			return ResponseEntity.noContent().build();
		}
		
		return ResponseEntity.ok(optTest);	
	}
	
	
	/**
	 * Método para actualizar los datos de la tabla test, realizando búsqueda por id.
	 * @param testDetails 
	 * @param id
	 * @return la respuesta Http 201-Created si el registro fue actualizado y 404-no Found si no.
	 */
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestBody TestE testE, @PathVariable Long id){
		
		if(testE.getNameTest() == null || testE.getDescriptionTest() == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}	
			testServiceImpl.updateTest(testE, id);
			return new ResponseEntity<>(HttpStatus.CREATED);
		
	}
	
	
	/**
	 * Método para eliminar registro de la tabla test.
	 * @param id
	 * @return la respuesta Http 200-Ok si el registro fue eliminado y 204-no Content si no.
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete (@PathVariable Long id){
		try {
			testServiceImpl.deleteTestById(id);
			return ResponseEntity.ok().build();
		}catch(Exception e) {
			return ResponseEntity.noContent().build();
		}
		
	}
				
}
