package com.sophos.laboratory.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sophos.laboratory.model.Affiliate;
import com.sophos.laboratory.model.Appointment;

/**
 * Repositorio de la cita.
 * @author Viviana Cifuentes <viviana.cifuentes05@gmail.com>
 *
 */
@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
	
	/**
	 * Query que permite consultar citas por fecha.
	 * @param dateAppointment
	 * @return
	 */
	@Query(value = "SELECT * FROM appointment WHERE appointment.date_appointment = :dateAppointment",
			nativeQuery = true)
	List<Appointment> findByDateAppointment(@Param("dateAppointment") LocalDate dateAppointment);
	
	/**
	 * Query que permite consultar citas por el Id del Afiliado.
	 * @param idAffiliate
	 * @return
	 */
	List<Appointment> findByfkIdAffiliate(Affiliate idAffiliate);
}
