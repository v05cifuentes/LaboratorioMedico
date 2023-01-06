package com.sophos.laboratory.model;

import java.time.LocalDate;
import java.time.LocalTime;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * Entidad de la cita.
 * @author Viviana Cifuentes <viviana.cifuentes05@gmail.com>
 *
 */

@Entity
@Table(name = "appointment")
public class Appointment {
	/**
	 * Id de la cita.
	 */
	@Id
	@Column(name = "id_appointment")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idAppointment;
	
	/**
	 * Fecha de la cita.
	 */
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@JsonFormat(pattern = "dd/MM/yyyy")
	@Column(name = "date_appointment")
	private LocalDate dateAppointment;
	
	/**
	 * Hora exacta de la cita en formato "HH:mm".
	 */
	@DateTimeFormat(pattern = "HH:mm")
	@JsonFormat(pattern = "HH:mm")
	@Column(name = "hour_appointment")
	private LocalTime hourAppointment;
	
	/**
	 * Relación bidireccional entre las tablas test y appointment por medio del Id del Test.
	 */
	@ManyToOne
	@OnDelete(action = OnDeleteAction.CASCADE)
	@Basic(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_test")
	private TestE fkIdTest;
	
	/**
	 * Relación bidireccional entre las tablas affiliate y appointment por medio del Id del Afiliado.
	 */
	@ManyToOne
	@OnDelete(action = OnDeleteAction.CASCADE)
	@Basic(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_affiliate")
	private Affiliate fkIdAffiliate;

	public Appointment() {
		
	}

	public Appointment(Long idAppointment, LocalDate dateAppointment, LocalTime hourAppointment, TestE fkIdTest,
			Affiliate fkIdAffiliate) {
		this.idAppointment = idAppointment;
		this.dateAppointment = dateAppointment;
		this.hourAppointment = hourAppointment;
		this.fkIdTest = fkIdTest;
		this.fkIdAffiliate = fkIdAffiliate;
	}

	public Long getIdAppointment() {
		return idAppointment;
	}

	public void setIdAppointment(Long idAppointment) {
		this.idAppointment = idAppointment;
	}

	public LocalDate getDateAppointment() {
		return dateAppointment;
	}

	public void setDateAppointment(LocalDate dateAppointment) {
		this.dateAppointment = dateAppointment;
	}

	public LocalTime getHourAppointment() {
		return hourAppointment;
	}

	public void setHourAppointment(LocalTime hourAppointment) {
		this.hourAppointment = hourAppointment;
	}

	public TestE getFkIdTest() {
		return fkIdTest;
	}

	public void setFkIdTest(TestE fkIdTest) {
		this.fkIdTest = fkIdTest;
	}

	public Affiliate getFkIdAffiliate() {
		return fkIdAffiliate;
	}

	public void setFkIdAffiliate(Affiliate fkIdAffiliate) {
		this.fkIdAffiliate = fkIdAffiliate;
	}

	@Override
	public String toString() {
		return "Appointment [idAppointment=" + idAppointment + ", dateAppointment=" + dateAppointment
				+ ", hourAppointment=" + hourAppointment + ", fkIdTest=" + fkIdTest + ", fkIdAffiliate=" + fkIdAffiliate
				+ "]";
	}
	
	
}