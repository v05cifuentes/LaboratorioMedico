package com.sophos.laboratory.Controller;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatusCode;

import com.sophos.laboratory.controller.AppointmentController;
import com.sophos.laboratory.model.Affiliate;
import com.sophos.laboratory.model.Appointment;
import com.sophos.laboratory.model.TestE;
import com.sophos.laboratory.service.AppointmentServiceImp;

@ExtendWith(MockitoExtension.class)
class AppointmentControllerTest {
	
	@InjectMocks
	AppointmentController appointmentController = new AppointmentController();
	
	@Mock 
	AppointmentServiceImp appointmentServiceMock;
	
	
	/**
	 * Pureba unitaria que verifica que la cita se cree correctamente.
	 */
	@Test
	void testCreateAppointmentOk() {
		var test = new TestE();
		var affiliate = new Affiliate();
		LocalTime ahora = LocalTime.now();
		LocalDate hoy = LocalDate.now();
		var appointment = new Appointment(1L, hoy, ahora, test, affiliate);
		when(appointmentServiceMock.createAppointment(appointment)).thenReturn(appointment);
		var response = appointmentController.create(appointment);
		Assertions.assertEquals(HttpStatusCode.valueOf(201), response.getStatusCode());
	}
	
	/**
	 * Prueba unitaria que verifica la respuesta correcta cuando la cita no se puede crear.
	 */
	@Test
	void testCreateAppointmentError() {
		var appointment = new Appointment();
		var response = appointmentController.create(appointment);
		Assertions.assertEquals(HttpStatusCode.valueOf(404), response.getStatusCode());			
	}
	
	/**
	 * Prueba unitaria que verifica si la consulta imprime todos las citas correctamente.
	 */
	@Test
	void testGetAllAppointmentsOk() {
		var list = new ArrayList<Appointment>();
		list.add(new Appointment());
		when(appointmentServiceMock.getList()).thenReturn(list);
		var response = appointmentController.getAppointments();
		Assertions.assertEquals(HttpStatusCode.valueOf(200), response.getStatusCode());
	}
	
	/**
	 *  Prueba unitaria que verifica la respuesta correta cuando la consulta no imprime las citas.
	 */
	@Test
	void testGetAllAppointmentsError() {
		when(appointmentServiceMock.getList()).thenReturn(Collections.emptyList());
		var response = appointmentController.getAppointments();
		Assertions.assertEquals(HttpStatusCode.valueOf(204), response.getStatusCode());
	}
	
	/**
	 * Prueba unitaria que verifica si la consulta imprime la cita filtrada por Id correctamente.
	 */
	@Test
	void testGetAppointmentsByIdOk() {
		var test = new TestE();
		var affiliate = new Affiliate();
		LocalTime ahora = LocalTime.now();
		LocalDate hoy = LocalDate.now();
		var appointment = new Appointment(1L, hoy, ahora, test, affiliate);
		when(appointmentServiceMock.findById(appointment.getIdAppointment())).thenReturn(Optional.of(appointment));
		var response = appointmentController.getById(appointment.getIdAppointment());
		Assertions.assertEquals(HttpStatusCode.valueOf(200), response.getStatusCode());
	}
	
	/**
	 *  Prueba unitaria que verifica la respuesta correta cuando la consulta no imprime la cita filtrada por Id.
	 */
	@Test
	void testGetAppointmentsByIdError() {
		var appointment = new Appointment();
		when(appointmentServiceMock.findById(appointment.getIdAppointment())).thenReturn(Optional.ofNullable(null));
		var response = appointmentController.getById(appointment.getIdAppointment());
		Assertions.assertEquals(HttpStatusCode.valueOf(204), response.getStatusCode());
	}
	
	/**
	 *  Prueba unitaria que verifica si la cita filtrada por Id actualiza correctamente.
	 */
	@Test
	void testUpdateAppointmentsOk() {
		var appointment = new Appointment();
		when(appointmentServiceMock.updateAppointment(appointment)).thenReturn(Optional.of(appointment));
		var response = appointmentController.update(appointment);
		Assertions.assertEquals(HttpStatusCode.valueOf(201), response.getStatusCode());
	}
	
	/**
	 *  Prueba unitaria que verifica la respuesta correcta cuando la cita no se pueda actualizar. 
	 */
	@Test
	void testUpdateAppointmentsError() {
		var appointment = new Appointment();
		when(appointmentServiceMock.updateAppointment(appointment)).thenReturn(Optional.empty());
		var response = appointmentController.update(appointment);
		Assertions.assertEquals(HttpStatusCode.valueOf(404), response.getStatusCode());
	}
	
	/**
	 *  Prueba unitaria que verifica si la cita filtrada por Id se elimina correctamente.
	 *
	 **/
	@Test
	void testDeleteAppointmentOk() {
		doNothing().when(appointmentServiceMock).deleteAppointmentById(anyLong());
		var response = appointmentController.delete(anyLong());
		Assertions.assertEquals(HttpStatusCode.valueOf(200), response.getStatusCode());
	}
	
	/**
	  Prueba unitaria que verifica la respuesta correcta cuando la cita no puede ser eliminada.
	**/ 
	@Test
	void testDeleteAppointmentError() {
		doThrow(new RuntimeException()).when(appointmentServiceMock).deleteAppointmentById(anyLong());
		var response = appointmentController.delete(anyLong());
		Assertions.assertEquals(HttpStatusCode.valueOf(204), response.getStatusCode());
	}
	
	
	/**
	 * Prueba unitaria que verifica si la consulta imprime la cita filtrada por fecha correctamente.
	 */
	@Test
	void testGetAppointmentsByDateOk() {
		var test = new TestE();
		var affiliate = new Affiliate();
		LocalTime ahora = LocalTime.now();
		String date = "24/11/1998";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate localDate = LocalDate.parse(date, formatter);
		var list = new ArrayList<Appointment>();
		list.add(new Appointment(1L, localDate, ahora, test, affiliate));
		var response = appointmentController.getByDateAppointments(date);
		Assertions.assertEquals(HttpStatusCode.valueOf(200), response.getStatusCode());
	}
	
	/**
	 * Prueba unitaria que verifica si la consulta imprime la cita filtrada por afiliado correctamente.
	 */
	@Test
	void testGetAppointmentsByIdAffiliateOk() {
		var test = new TestE();
		var affiliate = new Affiliate();
		LocalTime ahora = LocalTime.now();
		LocalDate hoy = LocalDate.now();
		var list = new ArrayList<Appointment>();
		list.add(new Appointment(1L, hoy, ahora, test, affiliate));
		var response = appointmentController.getByIdAffiliate(affiliate);
		Assertions.assertEquals(HttpStatusCode.valueOf(200), response.getStatusCode());
	}
}