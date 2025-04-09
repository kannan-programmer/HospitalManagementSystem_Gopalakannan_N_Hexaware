package Com.java.test;

import com.java.dao.HospitalServiceImpl;
import com.java.exceptions.DoctorNotFoundException;
import com.java.exceptions.PatientNotFoundException;
import com.java.model.Appointment;
import com.java.model.Doctor;
import com.java.model.Patient;
import org.junit.jupiter.api.*;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class HospitalServiceTest {
    static Appointment appointment;
    private static final HospitalServiceImpl service = new HospitalServiceImpl();
    @Test
    @Order(1)
    void testCreatePatient() throws Exception {
        Patient patient = new Patient(0, "Gopal", "Krishna", new Date(), "Male", "9999999999", "Chennai");
        boolean created = service.createPatient(patient);
        assertTrue(created); 

    }
    @Test
    @Order(2)
    void testCreateDoctor() throws Exception {
        Doctor doctor = new Doctor(0, "Vishnu", "Das", "Cardiologist", "8888888888");
        boolean created = service.createDoctor(doctor);
        assertTrue(created); }

    @Test
    @Order(3)
    void testScheduleAppointment() throws Exception {
    	appointment = new Appointment(0,2,3,new Date(),"Generalcheckup");
        boolean scheduled = service.scheduleAppointment(appointment);
        assertTrue(scheduled);
    }

    @Test
    @Order(4)
    void testGetAppointmentsForDoctor() throws Exception {
    	int doctorId =3;
        List<Appointment> appointments = service.getAppointmentsForDoctor(doctorId);
        assertNotNull(appointments);
        assertFalse(appointments.isEmpty());
        appointments.get(0).getAppointmentId();
    }

    @Test
    @Order(5)
    void testGetAppointmentsForPatient() throws Exception {
    	int patientId = 5;
        List<Appointment> appointments = service.getAppointmentsForPatient(patientId);
        assertNotNull(appointments);
        assertFalse(appointments.isEmpty());
    }

    @Test
    @Order(6)
    void testUpdateAppointment() throws Exception {
        Appointment appointment = new Appointment(0, 1, 4, new Date(), "general check");
        boolean updated = service.updateAppointment(appointment);
        assertTrue(updated);
    }

    @Test
    @Order(7)
    void testGetAppointmentById() throws Exception {
    	int appointmentId = 7;
        Appointment appointment = service.getAppointmentById(appointmentId);
        assertNotNull(appointment);
        assertEquals("Ear infection", appointment.getDescription());
    }

    @Test
    @Order(8)
    void testCancelAppointment() throws Exception {
    	int appointmentId = 8;
        boolean deleted = service.cancelAppointment(appointmentId);
        assertTrue(deleted);
    }

    @Test
    @Order(9)
    void testDeletePatient() throws Exception {
    	int patientId =10;
        boolean deleted = service.deletePatient(patientId);
        assertTrue(deleted);
    }

    @Test
    @Order(10)
    void testDeleteDoctor() throws Exception {
    	int doctorId =10;
        boolean deleted = service.deleteDoctor(doctorId);
        assertTrue(deleted);
    }
    
   

    @Test
    void testGetAppointmentsForInvalidPatientThrowsException() {
        assertThrows(PatientNotFoundException.class, () -> {
            service.getAppointmentsForPatient(9999);
        });
    }

    @Test
    void testGetAppointmentsForInvalidDoctorThrowsException() {
        assertThrows(DoctorNotFoundException.class, () -> {
            service.getAppointmentsForDoctor(9999);
        });
    }

    @Test
    void testUpdateNonExistentAppointment() {
        Appointment fakeAppointment = new Appointment(9999, 1, 1, new Date(), "Fake update");
        boolean result = assertDoesNotThrow(() -> service.updateAppointment(fakeAppointment));
        assertFalse(result);
    }

    @Test
    void testCancelNonExistentAppointment() {
        boolean result = assertDoesNotThrow(() -> service.cancelAppointment(9999));
        assertFalse(result);
    }

    @Test
    void testDeleteNonExistentPatient() {
        boolean result = assertDoesNotThrow(() -> service.deletePatient(9999));
        assertFalse(result);
    }

    @Test
    void testDeleteNonExistentDoctor() {
        boolean result = assertDoesNotThrow(() -> service.deleteDoctor(9999));
        assertFalse(result);
    }
}
