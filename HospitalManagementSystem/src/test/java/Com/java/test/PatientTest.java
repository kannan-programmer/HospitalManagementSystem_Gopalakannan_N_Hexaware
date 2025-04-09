package Com.java.test;

import com.java.model.Patient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class PatientTest {

    private Patient patient;
    private Date dob;

    @SuppressWarnings("deprecation")
	@BeforeEach
    public void setUp() {
        dob = new Date(95, 4, 20); 
        patient = new Patient(101, "Gopal", "Krishnan", dob,
                "Male", "9876543210", "Chennai, India");
    }

    @Test
    public void testConstructor() {
        assertEquals(101, patient.getPatientId());
        assertEquals("Gopal", patient.getFirstName());
        assertEquals("Krishnan", patient.getLastName());
        assertEquals(dob, patient.getDateOfBirth());
        assertEquals("Male", patient.getGender());
        assertEquals("9876543210", patient.getContactNumber());
        assertEquals("Chennai, India", patient.getAddress());
    }

    @SuppressWarnings("deprecation")
	@Test
    public void testSettersAndGetters() {
        Date newDob = new Date(100, 10, 15); 

        patient.setPatientId(202);
        patient.setFirstName("Swetha");
        patient.setLastName("Raj");
        patient.setDateOfBirth(newDob);
        patient.setGender("Female");
        patient.setContactNumber("1234567890");
        patient.setAddress("Hyderabad");

        assertEquals(202, patient.getPatientId());
        assertEquals("Swetha", patient.getFirstName());
        assertEquals("Raj", patient.getLastName());
        assertEquals(newDob, patient.getDateOfBirth());
        assertEquals("Female", patient.getGender());
        assertEquals("1234567890", patient.getContactNumber());
        assertEquals("Hyderabad", patient.getAddress());
    }

    @Test
    public void testNoArgsConstructor() {
        Patient newPatient = new Patient();
        assertNotNull(newPatient);
        assertEquals(0, newPatient.getPatientId());
        assertNull(newPatient.getFirstName());
        assertNull(newPatient.getLastName());
        assertNull(newPatient.getDateOfBirth());
        assertNull(newPatient.getGender());
        assertNull(newPatient.getContactNumber());
        assertNull(newPatient.getAddress());
    }

    @Test
    public void testToStringContainsData() {
        String str = patient.toString();
        assertTrue(str.contains("Gopal"));
        assertTrue(str.contains("Krishnan"));
        assertTrue(str.contains("Male"));
        assertTrue(str.contains("9876543210"));
        assertTrue(str.contains("Chennai"));
    }
}
