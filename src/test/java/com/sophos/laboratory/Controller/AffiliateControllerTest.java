package com.sophos.laboratory.Controller;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

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

import com.sophos.laboratory.controller.AffiliateController;
import com.sophos.laboratory.model.Affiliate;
import com.sophos.laboratory.service.AffiliateServiceImp;

/**
 * Pruebas unitarias realizadas para cada uno de los métodos del AffiliateController.
 * @author Viviana Cifuentes <viviana.cifuentes05@gmail.com>
 * 
 */

@ExtendWith(MockitoExtension.class)
class AffiliateControllerTest {
	
	@InjectMocks
	AffiliateController affiliateController;
	
	@Mock
	AffiliateServiceImp affiliateServiceMock;
	
	
	/**
	 * Pureba unitaria que verifica que el afiliado se cree correctamente.
	 */
	@Test
	void testCreateAffiliateOk() {
		var affiliate = new Affiliate(2L, "Alberto", 46, "alberto@gmail.com");
		when(affiliateServiceMock.createAffiliate(affiliate)).thenReturn(affiliate);
		var response = affiliateController.create(affiliate);
		Assertions.assertEquals(HttpStatusCode.valueOf(201), response.getStatusCode());
	}
	
	
	/**
	 * Prueba unitaria que verifica la respuesta correcta cuando el afiliado no se puede crear.
	 */
	@Test
	void testCreateAffiliateError() {
		var affiliate = new Affiliate();
		var response = affiliateController.create(affiliate);
		Assertions.assertEquals(HttpStatusCode.valueOf(404), response.getStatusCode());			
	}
	
	
	/**
	 * Prueba unitaria que verifica si la consulta imprime todos los afiliados correctamente.
	 */
	@Test
	void testGetAllAffiliatesOk() {
		List<Affiliate> affiliate = new ArrayList<>();
		affiliate.add(new Affiliate(2L, "Alberto", 46, "alberto@gmail.com"));
		when(affiliateServiceMock.getList()).thenReturn(affiliate);
		var response = affiliateController.getAffiliates();
		Assertions.assertEquals(HttpStatusCode.valueOf(200), response.getStatusCode());
	}
	
	
	/**
	 *  Prueba unitaria que verifica la respuesta correta cuando la consulta no imprime los afiliados.
	 */
	@Test
	void testGetAllAffiliatesError() {
		when(affiliateServiceMock.getList()).thenReturn(Collections.emptyList());
		var response = affiliateController.getAffiliates();
		Assertions.assertEquals(HttpStatusCode.valueOf(204), response.getStatusCode());
	}
	
	
	/**
	 * Prueba unitaria que verifica si la consulta imprime el afiliado filtrado por Id correctamente.
	 */
	@Test
	void testGetAffiliatesByIdOk() { 
		var affiliate = new Affiliate(2L, "Alberto", 46, "alberto@gmail.com");
		when(affiliateServiceMock.findById(affiliate.getIdAffiliate())).thenReturn(Optional.of(affiliate));
		var response = affiliateController.getById(affiliate.getIdAffiliate());
		Assertions.assertEquals(HttpStatusCode.valueOf(200), response.getStatusCode());
	}
	
	
	/**
	 *  Prueba unitaria que verifica la respuesta correta cuando la consulta no imprime el afiliado filtrado por Id.
	 */
	@Test
	void testGetAffiliatesByIdError() {
		var affiliate = new Affiliate();
		when(affiliateServiceMock.findById(affiliate.getIdAffiliate())).thenReturn(Optional.ofNullable(null));
		var response = affiliateController.getById(affiliate.getIdAffiliate());
		Assertions.assertEquals(HttpStatusCode.valueOf(204), response.getStatusCode());
	}
	
	
	/**
	 *  Prueba unitaria que verifica si el afiliado filtrado por Id actualiza correctamente.
	 */
	@Test
	public void testUpdateAffiliatesOk() {
		var affiliate = new Affiliate(2L, "Alberto", 46, "alberto@gmail.com");
		var response = affiliateController.update(affiliate, affiliate.getIdAffiliate());
		Assertions.assertEquals(HttpStatusCode.valueOf(201), response.getStatusCode());
	}
	
	
	/**
	 *  Prueba unitaria que verifica la respuesta correcta cuando el afiliado no se pueda actualizar. 
	 */
	@Test
	public void testUpdateAffiliatesError() {
		var affiliate = new Affiliate();
		var response = affiliateController.update(affiliate, affiliate.getIdAffiliate());
		Assertions.assertEquals(HttpStatusCode.valueOf(404), response.getStatusCode());
	}
	
	
	/**
	 *  Prueba unitaria que verifica si el afiliado filtrado por Id se elimina correctamente.
	*/  
	@Test
	public void testDeleteAffiliatesOk() {
		doThrow(new RuntimeException()).when(affiliateServiceMock).deleteAffiliateById(anyLong());
		var response = affiliateController.delete(anyLong());
		Assertions.assertEquals(HttpStatus.NO_CONTENT , response.getStatusCode());
	}
	
	
	/**
	 *  Prueba unitaria que verifica la respuesta correcta cuando el afiliado no puede ser eliminado.
	*/ 
	@Test
	public void testDeleteAffiliatesError() {
		doThrow(new RuntimeException()).when(affiliateServiceMock).deleteAffiliateById(anyLong());
		var response = affiliateController.delete(anyLong());
		Assertions.assertEquals(HttpStatus.NO_CONTENT , response.getStatusCode());
	}
}
