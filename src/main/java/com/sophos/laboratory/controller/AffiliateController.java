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

import com.sophos.laboratory.model.Affiliate;
import com.sophos.laboratory.service.AffiliateServiceImp;

/**
 * Controlador que manipula el flujo de los servicios Affiliate.
 * @author Viviana Cifuentes <viviana.cifuentes05@gmail.com>
 *
 */

@RestController
@RequestMapping("/api/controller/affiliate")
public class AffiliateController {
	
	@Autowired 
	private AffiliateServiceImp affiliateService;
	
	/**
	 * Crea un registro dentro de la tabla Affiliate.
	 * @param affiliate
	 * @return la respuesta Http 200-Created si el registro fue creado y 404-Not Found si no.
	 */
	@PostMapping
	public ResponseEntity<?> create(@RequestBody Affiliate affiliate){
		
		if(affiliate.getNameAffiliate() == null || affiliate.getMailAffiliate() == null || affiliate.getAgeAffiliate() == 0) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		affiliateService.createAffiliate(affiliate);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	/**
	 * Método que retorna todos los registros de la tabla Affiliate.
	 * @return la respuesta Http 200-Ok si existen los registros y 204-no Content si no.
	 */
	@GetMapping("/")
	public ResponseEntity<List<Affiliate>> getAffiliates() {
		List<Affiliate> affiliate = affiliateService.getList();
		
		if(affiliate.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		
		return ResponseEntity.ok(affiliate);	
	}
	
	/**
	 * Método para realizar búsqueda de afiliados por Id.
	 * @param id
	 * @return la respuesta Http 200-Ok si el registro existe y 204-no Content si no.
	 */
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getById(@PathVariable Long id){
		Optional<Affiliate> optAffiliate = affiliateService.findById(id);
		
		if(!optAffiliate.isPresent()) {
			return ResponseEntity.noContent().build();
		}
		
		return ResponseEntity.ok(optAffiliate);	
	}
	
	/**
	 * Método para actualizar los datos de la tabla Affiliate, realizando búsqueda por id.
	 * @param testDetails 
	 * @param id
	 * @return la respuesta Http 201-Created si el registro fue actualizado y 404-no Found si no.
	 */
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestBody Affiliate affiliate, @PathVariable Long id){
		
		if(affiliate.getNameAffiliate() == null || affiliate.getMailAffiliate() == null || affiliate.getAgeAffiliate() == 0) {
			return ResponseEntity.notFound().build();
		}
			affiliateService.updateAffiliate(affiliate, id);
			return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	/**
	 * Método para eliminar registro de la tabla Affiliate.
	 * @param id
	 * @return la respuesta Http 200-Ok si el registro fue eliminado y 204-no Content si no.
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id){

		try {
			affiliateService.deleteAffiliateById(id);
			return ResponseEntity.ok().build();
			
		}catch(Exception e) {
			return ResponseEntity.noContent().build();
		}
		
		
	}
}
