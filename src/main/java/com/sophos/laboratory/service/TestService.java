package com.sophos.laboratory.service;

import java.util.List;
import java.util.Optional;

import com.sophos.laboratory.model.TestE;

/**
 * Interface que contiene los métodos de lógica de negocio de la tabla Test.
 * @author Viviana Cifuentes <viviana.cifuentes05@gmail.com>
 *
 */
public interface TestService {
	
	
	/**
	 * Método que permite consultar listado de Tests. 
	 * @return listado de tests.
	 * 
	 */
	public List<TestE> getList();
	
	
	/**
	 * Consulta un registro dentro de la tabla Test por el Id.
	 * @param idTest
	 * @return registro de la tabla test.
	 * 
	 */
	public Optional<TestE> findById(Long idTest);
	
	
	/**
	 * Crear un registro dentro de la tabla Test.
	 * @param testE
	 * @return registro creado con todos sus parámetros.
	 * 
	 */
	public TestE createTest(TestE testE);
	
	
	/**
	 * Actualiza un registro dentro de la tabla Test.	
	 * @param testE
	 * @return registro de la tabla test con el o los campos actualizados.
	 * 
	 */
	public TestE updateTest(TestE testE, Long id);
	
	/**
	 * Elimina un registro de la tabla Test dado el Id como parámetro.
	 * @param idTest
	 * 
	 */
	public void deleteTestById(Long idTest);

}
