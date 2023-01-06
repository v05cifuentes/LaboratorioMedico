package com.sophos.laboratory.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.sophos.laboratory.model.Affiliate;
import com.sophos.laboratory.model.Appointment;

public interface AppointmentService {
	
	
	/**
	 * Método que permite consultar listado de Appointment. 
	 * @return listado de appointments.
	 * 
	 */
	public List<Appointment> getList();
	
	
	/**
	 * Consulta un registro dentro de la tabla Appointmet por el Id.
	 * @param idAppoinment
	 * @return registro de la tabla appointment.
	 * 
	 */
	public Optional<Appointment> findById(Long idAppoinment);
			
	
	/**
	 * Crear un registro dentro de la tabla Appointment.
	 * @param appointment
	 * @return registro creado con todos sus parámetros.
	 * 
	 */
	public Appointment createAppointment(Appointment appointment);

	
	/**
	 * Actualiza un registro dentro de la tabla Appointment.	
	 * @param appointment
	 * @return registro de la tabla appointment con el o los campos actualizados.
	 * 
	 */
	public Optional<Appointment> updateAppointment(Appointment appointment);
	
	
	/**
	 * Elimina un registro de la tabla Appointment dado el Id como parámetro.
	 * @param idAppointment
	 * 
	 */
	public void deleteAppointmentById(Long idAppointment);
	
	
	/**
	 * Filtra registros por la fecha de las citas.
	 * @param date
	 * @return los registros que contengan la fecha dada.
	 */
	public List<Appointment> findByDateAppointments(LocalDate date);

	
	/**
	 * Filtra registros por el Id de los afiliados.
	 * @param idAffiliate
	 * @return registros que contengan el id dado.
	 */
	public List<Appointment> findByfkIdAffiliate(Affiliate idAffiliate);

}
