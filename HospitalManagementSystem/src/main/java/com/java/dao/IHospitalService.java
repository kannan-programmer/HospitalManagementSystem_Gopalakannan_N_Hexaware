package com.java.dao;

import java.util.List;

import com.java.exceptions.DoctorNotFoundException;
import com.java.exceptions.PatientNotFoundException;
import com.java.exceptions.AppointmentNotFoundException;
import com.java.model.Appointment;
import com.java.model.Doctor;
import com.java.model.Patient;

public interface IHospitalService {
	 Appointment getAppointmentById(int appointmentId);
	    List<Appointment> getAppointmentsForPatient(int patientId) throws PatientNotFoundException;
	    List<Appointment> getAppointmentsForDoctor(int doctorId) throws DoctorNotFoundException;
	    boolean scheduleAppointment(Appointment appointment) throws ClassNotFoundException;
	    boolean updateAppointment(Appointment appointment) throws AppointmentNotFoundException; 
	    boolean cancelAppointment(int appointmentId) throws AppointmentNotFoundException;
	    boolean createPatient(Patient patient) throws ClassNotFoundException;
	    boolean deletePatient(int patientId) throws ClassNotFoundException;
	    boolean createDoctor(Doctor doctor) throws ClassNotFoundException;
	    boolean deleteDoctor(int doctorId) throws ClassNotFoundException;
}
