package Com.java.test;

import com.java.dao.HospitalServiceImpl;
import com.java.model.Appointment;
import com.java.model.Doctor;
import com.java.model.Patient;
import org.junit.jupiter.api.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class HMSAppTest {

    static HospitalServiceImpl service;
    static int patientId = 0;
    static int doctorId = 0;
    static int appointmentId = 0;

    static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    @BeforeAll
    static void setup() {
        service = new HospitalServiceImpl();
    }

    @Test
    @Order(1)
    void testCreatePatient() throws Exception {
        Date dob = sdf.parse("2000-01-01");
        Patient patient = new Patient(0, "Test", "User", dob, "Male", "1234567890", "Test Address");
        boolean result = service.createPatient(patient);
        assertTrue(result);
        patientId = 1; 
    }

    @Test
    @Order(2)
    void testCreateDoctor() throws Exception {
        Doctor doctor = new Doctor(0, "Doc", "Test", "Cardiology", "9876543210");
        boolean result = service.createDoctor(doctor);
        assertTrue(result);
        doctorId = 1; 
    }

    @Test
    @Order(3)
    void testScheduleAppointment() throws Exception {
        Date date = sdf.parse("2025-04-15");
        Appointment appointment = new Appointment(0, 3, 4, date, "Routine Checkup");
        boolean result = service.scheduleAppointment(appointment);
        assertTrue(result);
    }

    @Test
    @Order(4)
    void testGetAppointmentsForPatient() throws Exception {
    	int patientId =5;
        List<Appointment> appointments = service.getAppointmentsForPatient(patientId);
        assertNotNull(appointments);
        assertFalse(appointments.isEmpty());
        appointmentId = appointments.get(0).getAppointmentId();
    }

    @Test
    @Order(5)
    void testGetAppointmentsForDoctor() throws Exception {
    	int doctorId = 3;
        List<Appointment> appointments = service.getAppointmentsForDoctor(doctorId);
        assertNotNull(appointments);
        assertFalse(appointments.isEmpty());
    }

    @Test
    @Order(6)
    void testGetAppointmentById() throws Exception {
        Appointment appointment = service.getAppointmentById(appointmentId);
        assertNotNull(appointment);
        assertEquals("Child fever consultation", appointment.getDescription());
    }

    @Test
    @Order(7)
    void testUpdateAppointment() throws Exception {
        Date newDate = sdf.parse("2025-04-20");
        Appointment updated = new Appointment(11, 4,8, newDate, "Regular Checkup");
        boolean result = service.updateAppointment(updated);
        assertTrue(result);
    }

    @Test
    @Order(8)
    void testCancelAppointment() throws Exception { 
    	int appointmentId =7;
        boolean result = service.cancelAppointment(appointmentId);
        assertTrue(result);
    }

    @Test
    @Order(9)
    void testDeletePatient() throws Exception {
    	int patientId =7;
        boolean result = service.deletePatient(patientId);
        assertTrue(result);
    }

    @Test
    @Order(10)
    void testDeleteDoctor() throws Exception {
    	int doctorId =7;
        boolean result = service.deleteDoctor(doctorId);
        assertTrue(result);
    }
}
