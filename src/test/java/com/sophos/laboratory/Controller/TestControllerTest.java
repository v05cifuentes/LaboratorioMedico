package com.sophos.laboratory.Controller;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.anyLong;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import com.sophos.laboratory.controller.TestController;
import com.sophos.laboratory.model.TestE;
import com.sophos.laboratory.service.TestServiceImp;

/**
 * Pruebas unitarias realizadas para cada uno de los métodos del TestController.
 * @author Viviana Cifuentes <viviana.cifuentes05@gmail.com>
 * 
 */

@ExtendWith(MockitoExtension.class)
class TestControllerTest {
	
	@InjectMocks
	TestController testController = new TestController();
	
	@Mock
	TestServiceImp TestServiceMock;
	
	
	/**
	 * Pureba unitaria que verifica que el test se cree correctamente.
	 */
	@Test
	public void testCreateTestOK() {
		var test = new TestE(2L, "Sangre", "Embarazo");
		when(TestServiceMock.createTest(test)).thenReturn(test);
		var response = testController.create(test);
		Assertions.assertEquals(HttpStatusCode.valueOf(201), response.getStatusCode());
	}
	
	
	/**
	 * Prueba unitaria que verifica la respuesta correcta cuando el test no se puede crear.
	 */
	@Test
	public void testCreateTestError() {
		var test = new TestE();
		var response = testController.create(test);
		Assertions.assertEquals(HttpStatusCode.valueOf(404), response.getStatusCode());
	}
	
	/**
	 * Prueba unitaria que verifica si la consulta imprime todos los tests correctamente.
	 */
	@Test
	public void testGetAllTestsOK() {
		List<TestE> test = new ArrayList<>();
		test.add(new TestE(1L, "Sangre", "Bilirrubina"));
		when(TestServiceMock.getList()).thenReturn(test);
		var response = testController.getTests();
		Assertions.assertEquals(HttpStatusCode.valueOf(200), response.getStatusCode());
	}
	
	
	/**
	 *  Prueba unitaria que verifica la respuesta correta cuando la consulta no imprime los tests.
	 */
	@Test
	public void testGetAllTestsError() {
		when(TestServiceMock.getList()).thenReturn(Collections.emptyList());
		var response = testController.getTests();
		Assertions.assertEquals(HttpStatusCode.valueOf(204), response.getStatusCode());
	}
	
	
	/**
	 * Prueba unitaria que verifica si la consulta imprime el test filtrado por Id correctamente.
	 */
	@Test
	public void testGetTestByIdOK() {
		var test = new TestE(3L, "Audiovisual", "Astigmatismo");
		when(TestServiceMock.findById(test.getIdTest())).thenReturn(Optional.of(test));
		var response = testController.getById(test.getIdTest());
		Assertions.assertEquals(HttpStatusCode.valueOf(200), response.getStatusCode());
	}
	
	
	/**
	 *  Prueba unitaria que verifica la respuesta correta cuando la consulta no imprime el test filtrado por Id.
	 */
	@Test
	public void testGetTestByIdError() {
		var test = new TestE();
		when(TestServiceMock.findById(test.getIdTest())).thenReturn(Optional.ofNullable(null));
		var response = testController.getById(test.getIdTest());
		Assertions.assertEquals(HttpStatusCode.valueOf(204), response.getStatusCode());
	}
	
	
	/**
	 *  Prueba unitaria que verifica si el test filtrado por Id actualiza correctamente.
	 */
	@Test
	public void testUpdateTestOK() {
		var test = new TestE(2L, "Sangre", "Embarazo");
		var response = testController.update(test, test.getIdTest());
		Assertions.assertEquals(HttpStatusCode.valueOf(201), response.getStatusCode());
	}
	
	
	/**
	 *  Prueba unitaria que verifica la respuesta correcta cuando el test no se pueda actualizar. 
	 */
	@Test
	public void testUpdateTestError() {
		var test = new TestE();
		var response = testController.update(test, test.getIdTest());
		Assertions.assertEquals(HttpStatusCode.valueOf(404), response.getStatusCode());
	}
	
	
	/**
	 *  Prueba unitaria que verifica si el test filtrado por Id se elimina correctamente.
	 *
	 **/
	@Test
	public void testDeleteTestOK() {
		doThrow(new RuntimeException()).when(TestServiceMock).deleteTestById(anyLong());
		var response = testController.delete(anyLong());
		Assertions.assertEquals(HttpStatus.NO_CONTENT , response.getStatusCode());
	}
	

	/**
	  Prueba unitaria que verifica la respuesta correcta cuando el test no puede ser eliminado.
	**/ 
	@Test
	public void testDeleteTestError() {
		doNothing().when(TestServiceMock).deleteTestById(anyLong());
		var response = testController.delete(anyLong());
		Assertions.assertEquals(HttpStatus.OK , response.getStatusCode());
		
	}
}
