package Com.java.test;

import org.junit.jupiter.api.Test;

import com.java.model.Doctor;

import static org.junit.jupiter.api.Assertions.*;

public class DoctorTest {

    @Test
    public void testAllArgsConstructor() {
        Doctor doctor = new Doctor(0, "Arjun", "Mehta", "Cardiology", "9999999999");

        assertEquals(0, doctor.getDoctorId());
        assertEquals("Arjun", doctor.getFirstName());
        assertEquals("Mehta", doctor.getLastName());
        assertEquals("Cardiology", doctor.getSpecialization());
        assertEquals("9999999999", doctor.getContactNumber());
    }

    @Test
    public void testNoArgsConstructor() {
        Doctor doctor = new Doctor();

        assertNotNull(doctor); // Object should be created
    }

    @Test
    public void testSettersAndGetters() {
        Doctor doctor = new Doctor();

        doctor.setDoctorId(10);
        doctor.setFirstName("Kiran");
        doctor.setLastName("Rao");
        doctor.setSpecialization("Dermatology");
        doctor.setContactNumber("8888888888");

        assertEquals(10, doctor.getDoctorId());
        assertEquals("Kiran", doctor.getFirstName());
        assertEquals("Rao", doctor.getLastName());
        assertEquals("Dermatology", doctor.getSpecialization());
        assertEquals("8888888888", doctor.getContactNumber());
    }

    @Test
    public void testToStringMethod() {
        Doctor doctor = new Doctor(0, "Sneha", "Patel", "Orthopedics", "7777777777");

        String output = doctor.toString();

        assertTrue(output.contains("Sneha"));
        assertTrue(output.contains("Patel"));
        assertTrue(output.contains("Orthopedics"));
        assertTrue(output.contains("7777777777"));
    }
}
