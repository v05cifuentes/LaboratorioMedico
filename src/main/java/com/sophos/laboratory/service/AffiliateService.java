package com.sophos.laboratory.service;

import java.util.List;
import java.util.Optional;

import com.sophos.laboratory.model.Affiliate;


/**
 * Interface que contiene los métodos de lógica de negocio de la tabla Afiliados.
 * @author Viviana Cifuentes <viviana.cifuentes05@gmail.com>
 *
 */
public interface AffiliateService {
	
	
	/**
	 * Método que permite consultar listado de Afiliados. 
	 * @return listado de afiliados.
	 * 
	 */
	public List<Affiliate> getList();
	
	
	/**
	 * Consulta un registro dentro de la tabla Affiliate por el Id.
	 * @param idAffiliate
	 * @return registro de la tabla affiliate.
	 * 
	 */
	public Optional<Affiliate> findById(Long idAffiliate);
	
	
	/**
	 * Crear un registro dentro de la tabla Affiliate.
	 * @param affiliate
	 * @return el registro creado con todos sus parámetros.
	 * 
	 */
	public Affiliate createAffiliate(Affiliate affiliate);
	
	
	/**
	 * Actualiza un registro dentro de la tabla Affiliate.	
	 * @param affiliate
	 * @return registro de la tabla affiliate con el o los campos actualizados.
	 * 
	 */
	public Affiliate updateAffiliate(Affiliate request, Long id);
	
	
	/**
	 * Elimina un registro de la tabla Affiliate dado el Id como parámetro.
	 * @param idAffiliate
	 * 
	 */
	public void deleteAffiliateById(Long idAffiliate);

	
}
