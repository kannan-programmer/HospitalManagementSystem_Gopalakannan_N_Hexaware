package Com.java.test;

import org.junit.jupiter.api.Test;

import com.java.model.Appointment;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class AppointmentTest {

    @Test
    void testNoArgsConstructor() {
        Appointment appointment = new Appointment();
        assertNotNull(appointment);
    }

    @SuppressWarnings("deprecation")
	@Test
    void testAllArgsConstructor() {
        Date date = new Date(125, 3, 10); 
        Appointment appointment = new Appointment(1, 1001, 2001, date, "General check-up");

        assertEquals(1, appointment.getAppointmentId());
        assertEquals(1001, appointment.getPatientId());
        assertEquals(2001, appointment.getDoctorId());
        assertEquals(date, appointment.getAppointmentDate());
        assertEquals("General check-up", appointment.getDescription());
    }

    @SuppressWarnings("deprecation")
	@Test
    void testSetters() {
        Appointment appointment = new Appointment();
        Date date = new Date(125, 5, 15); 

        appointment.setAppointmentId(10);
        appointment.setPatientId(101);
        appointment.setDoctorId(201);
        appointment.setAppointmentDate(date);
        appointment.setDescription("Dental check-up");

        assertEquals(10, appointment.getAppointmentId());
        assertEquals(101, appointment.getPatientId());
        assertEquals(201, appointment.getDoctorId());
        assertEquals(date, appointment.getAppointmentDate());
        assertEquals("Dental check-up", appointment.getDescription());
    }

    @SuppressWarnings("deprecation")
	@Test
    void testGetters() {
        Date date = new Date(125, 6, 1); 
        Appointment appointment = new Appointment(5, 105, 205, date, "Eye test");

        int appointmentId = appointment.getAppointmentId();
        int patientId = appointment.getPatientId();
        int doctorId = appointment.getDoctorId();
        Date appointmentDate = appointment.getAppointmentDate();
        String description = appointment.getDescription();

        assertEquals(5, appointmentId);
        assertEquals(105, patientId);
        assertEquals(205, doctorId);
        assertEquals(date, appointmentDate);
        assertEquals("Eye test", description);
    }

    @SuppressWarnings("deprecation")
	@Test
    void testToString() {
        Date date = new Date(125, 6, 1); 
        Appointment appointment = new Appointment(5, 105, 205, date, "Eye test");

        String result = appointment.toString();
        assertTrue(result.contains("5"));
        assertTrue(result.contains("105"));
        assertTrue(result.contains("205"));
        assertTrue(result.contains("Eye test"));
    }
}
