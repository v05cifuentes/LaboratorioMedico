package com.sophos.laboratory.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sophos.laboratory.model.Affiliate;
import com.sophos.laboratory.model.Appointment;
import com.sophos.laboratory.repository.AffiliateRepository;
import com.sophos.laboratory.repository.AppointmentRepository;


/**
 * Implementación de los servicios de la interfaz de AppointmentService.
 * @author Viviana Cifuentes <viviana.cifuentes05@gmail.com>
 *
 */
@Service
public class AppointmentServiceImp implements AppointmentService {
	
	@Autowired
	AppointmentRepository appointmentRepository;
	AffiliateRepository affiliateRepository;
	
	@Override
	public Appointment createAppointment(Appointment appointment) {
		return appointmentRepository.save(appointment);
	}
		
	@Override
	public List<Appointment> getList() {
		return appointmentRepository.findAll();
	}

	@Override
	public Optional<Appointment> findById(Long idAppointment) {
		return appointmentRepository.findById(idAppointment);
	}

	@Override
	public Optional<Appointment> updateAppointment(Appointment appointment) {
	
		Optional<Appointment> objSet = appointmentRepository.findById(appointment.getIdAppointment());
		
		if (objSet.isPresent()) {
			Appointment objToUpdate = objSet.get();
			objToUpdate.setDateAppointment(appointment.getDateAppointment());
			objToUpdate.setHourAppointment(appointment.getHourAppointment());
			objToUpdate.setFkIdTest(appointment.getFkIdTest()); 	
			objToUpdate.setFkIdAffiliate(appointment.getFkIdAffiliate());
			appointmentRepository.save(objToUpdate);
			return Optional.ofNullable(objToUpdate);
		} else {
			return Optional.empty();
		}
	}

	@Override
	public void deleteAppointmentById(Long idAppointment) {
		appointmentRepository.deleteById(idAppointment);
		
	}

	@Override
	public List<Appointment> findByDateAppointments(LocalDate date) {
		List<Appointment> find = this.appointmentRepository.findByDateAppointment(date);
		return find;
	}
	
	@Override
	public List<Appointment> findByfkIdAffiliate (Affiliate idAffiliate){
		List<Appointment> affiliate = this.appointmentRepository.findByfkIdAffiliate(idAffiliate);
		return affiliate;
	}

	
}
