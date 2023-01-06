package com.sophos.laboratory.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sophos.laboratory.model.Affiliate;
import com.sophos.laboratory.model.Appointment;
import com.sophos.laboratory.service.AppointmentServiceImp;


@RestController
@RequestMapping("/api/controller/appointment")
public class AppointmentController {
	
	@Autowired
	private AppointmentServiceImp appointmentService;

		
	/**
	 * Método que crea un registro dentro de la tabla Appointment
	 * @param appointment
	 * @return la respuesta Http 200-Created si el registro fue creado y 404-Not Found si no.
	 */
	@PostMapping
	public ResponseEntity<?> create(@RequestBody Appointment appointment){
		
		if(appointment.getDateAppointment() == null || appointment.getHourAppointment() == null || appointment.getFkIdAffiliate() == null || appointment.getFkIdTest() == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		appointmentService.createAppointment(appointment);
		return new ResponseEntity<>(HttpStatus.CREATED);
		
	}
	
	/**
	 * Método para consultar todos los registros de la tabla appointment.
	 * @return la respuesta Http 200-Ok si existen los registros y 204-no Content si no.
	 */
	@GetMapping("/")
	public ResponseEntity<List<Appointment>> getAppointments() {
		List<Appointment> appointment = appointmentService.getList();
		if(appointment.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		
			return ResponseEntity.ok(appointment);
		
	}
	
	/**
	 * Método para realizar búsqueda de appointments por Id.
	 * @param id
	 * @return la respuesta Http 200-Ok si el registro existe y 204-no Content si no.
	 */
	@GetMapping("/{id}")
	public ResponseEntity<?> getById(@PathVariable Long id){
		Optional<Appointment> optAppointment = appointmentService.findById(id);
		
		if(!optAppointment.isPresent()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(optAppointment);	
	}
	

	/**
	 * Método para actualizar los datos de la tabla Appopinment, realizando búsqueda por id.
	 * @param idAppoinment
	 * @param id
	 * @return la respuesta Http 201-Created si el registro fue actualizado y 404-no Found si no.
	 */
	@PutMapping
	public ResponseEntity<?> update(@RequestBody Appointment appointment){
		
		Optional<Appointment> appoUpdated = this.appointmentService.updateAppointment(appointment);
		if (appoUpdated.isEmpty()) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.status(HttpStatus.CREATED).body(appoUpdated);
		}
	}
	
	
	/**
	 * Método para eliminar registro de la tabla Appointment.
	 * @param id
	 * @return la respuesta Http 200-Ok si el registro fue eliminado y 204-no Content si no.
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete (@PathVariable Long id){
		try {
			appointmentService.deleteAppointmentById(id);
			return ResponseEntity.ok().build();
		}catch(Exception e) {
			return ResponseEntity.noContent().build();
		}
	}
	
	/**
	 * Método para consultar los registros de la tabla Appointment filtrados por fecha.
	 * @param date
	 * @return la respuesta Http 200-Ok si la fecha existe.
	 */
	@GetMapping
	public ResponseEntity<?> getByDateAppointments(@RequestParam String date) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		//convert String to LocalDate
		LocalDate localDate = LocalDate.parse(date, formatter);
		List<Appointment> appointment = appointmentService.findByDateAppointments(localDate);
		return ResponseEntity.ok(appointment);
	}
	
	/**
	 * Método para consultar los registros de la tabla Appointment filtrados por el Id del Afiliado.
	 * @param date
	 * @return la respuesta Http 200-Ok si el Id del Afiliado existe.
	 */
	@GetMapping("/affiliate/{id}")
	public ResponseEntity<?> getByIdAffiliate(@PathVariable Affiliate id) {
		List<Appointment> optAppointment = this.appointmentService.findByfkIdAffiliate(id);
		return ResponseEntity.ok(optAppointment);
	}
	
}
