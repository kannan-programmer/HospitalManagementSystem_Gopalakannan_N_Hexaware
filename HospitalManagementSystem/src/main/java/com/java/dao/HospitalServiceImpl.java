package com.java.dao;

import com.java.exceptions.AppointmentNotFoundException;
import com.java.exceptions.DoctorNotFoundException;
import com.java.exceptions.PatientNotFoundException;
import com.java.model.*;
import com.java.util.ConnectionHelper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HospitalServiceImpl implements IHospitalService {

    @Override
    public Appointment getAppointmentById(int appointmentId) {
        Appointment appointment = null;
        String query = "SELECT * FROM appointment WHERE appointmentid = ?";
        try (Connection conn = ConnectionHelper.getConnection()) {
        
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, appointmentId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                appointment = new Appointment(
                    rs.getInt("appointmentid"),
                    rs.getInt("patientid"),
                    rs.getInt("doctorid"),
                    rs.getDate("appointmentdate"),
                    rs.getString("description")
                );
            }else {
                throw new AppointmentNotFoundException("Appointment ID " + appointment + " not found.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return appointment;
    }

    @Override
    public List<Appointment> getAppointmentsForPatient(int patientId) throws PatientNotFoundException {
        List<Appointment> list = new ArrayList<>();
        try (Connection conn = ConnectionHelper.getConnection()) {
            String query = "SELECT * FROM appointment WHERE patientid = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, patientId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                list.add(new Appointment(
                    rs.getInt("appointmentid"),
                    rs.getInt("patientid"),
                    rs.getInt("doctorid"),
                    rs.getDate("appointmentdate"),
                    rs.getString("description")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } if (list.isEmpty()) {
            throw new PatientNotFoundException("No appointments found for patient ID: " + patientId);
        }
        return list;
    }

    @Override
    public List<Appointment> getAppointmentsForDoctor(int doctorId) throws DoctorNotFoundException {
        List<Appointment> list = new ArrayList<>();
        String query = "SELECT * FROM appointment WHERE doctorid = ?";
        try (Connection conn = ConnectionHelper.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, doctorId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                list.add(new Appointment(
                    rs.getInt("appointmentid"),
                    rs.getInt("patientid"),
                    rs.getInt("doctorid"),
                    rs.getDate("appointmentdate"),
                    rs.getString("description")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }if (list.isEmpty()) {
            throw new DoctorNotFoundException("No appointments found for doctor ID: " + doctorId);
        }
        return list;
    }

    @Override
    public boolean scheduleAppointment(Appointment appointment) throws ClassNotFoundException {
    	String sql = "INSERT INTO appointment (patientid, doctorid, appointmentdate, description) VALUES (?, ?, ?, ?)";
        try (Connection con = ConnectionHelper.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, appointment.getPatientId());
            ps.setInt(2, appointment.getDoctorId());
            ps.setDate(3, new java.sql.Date(appointment.getAppointmentDate().getTime()));
            ps.setString(4, appointment.getDescription());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateAppointment(Appointment appointment) throws AppointmentNotFoundException {
        try (Connection conn = ConnectionHelper.getConnection()) {
            String query = "UPDATE appointment SET patientid=?, doctorid=?, appointmentdate=?, description=? WHERE appointmentid=?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, appointment.getPatientId());
            stmt.setInt(2, appointment.getDoctorId());
            stmt.setDate(3, new java.sql.Date(appointment.getAppointmentDate().getTime()));
            stmt.setString(4, appointment.getDescription());
            stmt.setInt(5, appointment.getAppointmentId());
            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean cancelAppointment(int appointmentId) throws AppointmentNotFoundException {
        try (Connection conn = ConnectionHelper.getConnection()) {
            String query = "DELETE FROM appointment WHERE appointmentid = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, appointmentId);
            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean createPatient(Patient patient) throws ClassNotFoundException {
    	String sql = "INSERT INTO patient (firstname, lastname, dateofbirth, gender, contactnumber, address) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection con = ConnectionHelper.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, patient.getFirstName());
            ps.setString(2, patient.getLastName());
            ps.setDate(3, new java.sql.Date(patient.getDateOfBirth().getTime()));
            ps.setString(4, patient.getGender());
            ps.setString(5, patient.getContactNumber());
            ps.setString(6, patient.getAddress());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deletePatient(int patientId) throws ClassNotFoundException {
    	try (Connection con = ConnectionHelper.getConnection()) {
            con.setAutoCommit(false);

            try (PreparedStatement deleteAppointments = con.prepareStatement("DELETE FROM appointment WHERE patientid = ?");
                 PreparedStatement deletePatient = con.prepareStatement("DELETE FROM patient WHERE patientid = ?")) {

                deleteAppointments.setInt(1, patientId);
                deleteAppointments.executeUpdate();

                deletePatient.setInt(1, patientId);
                boolean result = deletePatient.executeUpdate() > 0;

                con.commit();
                return result;
            } catch (SQLException e) {
                con.rollback();
                e.printStackTrace();
                return false;
            } finally {
                con.setAutoCommit(true);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean createDoctor(Doctor doctor) throws ClassNotFoundException {
    	String sql = "INSERT INTO doctor (firstname, lastname, specialization, contactnumber) VALUES (?, ?, ?, ?)";
        try (Connection con = ConnectionHelper.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, doctor.getFirstName());
            ps.setString(2, doctor.getLastName());
            ps.setString(3, doctor.getSpecialization());
            ps.setString(4, doctor.getContactNumber());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteDoctor(int doctorId) throws ClassNotFoundException {
    	try (Connection con = ConnectionHelper.getConnection()) {
            con.setAutoCommit(false);

            try (PreparedStatement deleteAppointments = con.prepareStatement("DELETE FROM appointment WHERE doctorid = ?");
                 PreparedStatement deleteDoctor = con.prepareStatement("DELETE FROM doctor WHERE doctorid = ?")) {

                deleteAppointments.setInt(1, doctorId);
                deleteAppointments.executeUpdate();

                deleteDoctor.setInt(1, doctorId);
                boolean result = deleteDoctor.executeUpdate() > 0;

                con.commit();
                return result;
            } catch (SQLException e) {
                con.rollback();
                e.printStackTrace();
                return false;
            } finally {
                con.setAutoCommit(true);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
}

	
}
