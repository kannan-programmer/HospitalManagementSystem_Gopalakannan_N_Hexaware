package com.java.main;

import com.java.dao.*;
import com.java.model.Appointment;
import com.java.model.Doctor;
import com.java.model.Patient;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class HMSApp {
    public static void main(String[] args) {
    	IHospitalService service = new HospitalServiceImpl();
        try (Scanner sc = new Scanner(System.in)) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			int choice;
			do {
			    System.out.println("\n===== Hospital Management System =====");
			    System.out.println("1. Create Patient");
			    System.out.println("2. Create Doctor");
			    System.out.println("3. Schedule Appointment");
			    System.out.println("4. Get Appointment by ID");
			    System.out.println("5. Get Appointments for Patient");
			    System.out.println("6. Get Appointments for Doctor");
			    System.out.println("7. Update Appointment");
			    System.out.println("8. Cancel Appointment");
			    System.out.println("9. Delete Patient");
			    System.out.println("10. Delete Doctor");
			    System.out.println("11. Exit");
			    System.out.print("Enter your choice: ");
			     choice = sc.nextInt();

			    try {
			        switch (choice) {
			            case 1:
			                sc.nextLine(); 
			                System.out.print("Enter First Name: ");
			                String fname = sc.nextLine();
			                System.out.print("Enter Last Name: ");
			                String lname = sc.nextLine();
			                System.out.print("Enter DOB (yyyy-MM-dd): ");
			                Date dob = sdf.parse(sc.nextLine());
			                System.out.print("Enter Gender: ");
			                String gender = sc.nextLine();
			                System.out.print("Enter Contact Number: ");
			                String contact = sc.nextLine();
			                System.out.print("Enter Address: ");
			                String addr = sc.nextLine();
			                Patient pat = new Patient(0, fname, lname, dob, gender, contact, addr);
			                boolean patCreate = service.createPatient(pat);
			                System.out.println(patCreate ? "!....Patient Created....!" : "Creation Failed");
			                break;

			            case 2:
			                sc.nextLine(); 
			                System.out.print("Enter First Name: ");
			                String dFname = sc.nextLine();
			                System.out.print("Enter Last Name: ");
			                String dLname = sc.nextLine();
			                System.out.print("Enter Specialization: ");
			                String spec = sc.nextLine();
			                System.out.print("Enter Contact Number: ");
			                String dContact = sc.nextLine();
			                Doctor doc = new Doctor(0, dFname, dLname, spec, dContact);
			                boolean docCreate = service.createDoctor(doc);
			                System.out.println(docCreate ? "!......Doctor Created......!" : "Creation Failed");
			                break;

			            case 3:
			                System.out.print("Enter Patient ID: ");
			                int pid = sc.nextInt();
			                System.out.print("Enter Doctor ID: ");
			                int did = sc.nextInt();
			                sc.nextLine(); 
			                System.out.print("Enter Appointment Date (yyyy-MM-dd): ");
			                Date date = sdf.parse(sc.nextLine());
			                System.out.print("Enter Description: ");
			                String desc = sc.nextLine();
			                Appointment appointment = new Appointment(0, pid, did, date, desc);
			                boolean success = service.scheduleAppointment(appointment);
			                System.out.println(success ? "!.....Appointment Scheduled.....!" : "Failed to Schedule");
			                break;

			            case 4:
			                System.out.print("Enter Appointment ID: ");
			                int aid = sc.nextInt();
			                Appointment a = service.getAppointmentById(aid);
			                System.out.println(a != null ? a : "*** Appointment not found ***");
			                break;

			            case 5:
			                System.out.print("Enter Patient ID: ");
			                int patId = sc.nextInt();
			                List<Appointment> patientApps = service.getAppointmentsForPatient(patId);
			                patientApps.forEach(System.out::println);
			                break;

			            case 6:
			                System.out.print("Enter Doctor ID: ");
			                int docId = sc.nextInt();
			                List<Appointment> doctorApps = service.getAppointmentsForDoctor(docId);
			                doctorApps.forEach(System.out::println);
			                break;

			            case 7:
			                System.out.print("Enter Appointment ID to Update: ");
			                int updateId = sc.nextInt();
			                sc.nextLine(); 
			                System.out.print("Enter New Patient ID: ");
			                int newPid = sc.nextInt();
			                System.out.print("Enter New Doctor ID: ");
			                int newDid = sc.nextInt();
			                sc.nextLine(); 
			                System.out.print("Enter New Appointment Date (yyyy-MM-dd): ");
			                Date newDate = sdf.parse(sc.nextLine());
			                System.out.print("Enter New Description: ");
			                String newDesc = sc.nextLine();
			                Appointment updatedApp = new Appointment(updateId, newPid, newDid, newDate, newDesc);
			                boolean updateStatus = service.updateAppointment(updatedApp);
			                System.out.println(updateStatus ? "!...Appointment Updated...!" : "Update Failed");
			                break;

			            case 8:
			                System.out.print("Enter Appointment ID to Cancel: ");
			                int cancelId = sc.nextInt();
			                boolean cancel = service.cancelAppointment(cancelId);
			                System.out.println(cancel ? "!....Appointment Cancelled....!" : "Cancellation Failed");
			                break;

			            case 9:
			                System.out.print("Enter Patient ID to Delete: ");
			                int delPat = sc.nextInt();
			                boolean patDel = service.deletePatient(delPat);
			                System.out.println(patDel ? "!....Patient Deleted....!" : "Deletion Failed");
			                break;

			            case 10:
			                System.out.print("Enter Doctor ID to Delete: ");
			                int delDoc = sc.nextInt();
			                boolean docDel = service.deleteDoctor(delDoc);
			                System.out.println(docDel ? "!.....Doctor Deleted.....!" : "Deletion Failed");
			                break;

			            case 11:
			                System.out.println("Exiting... \nThankYou...!");
			                System.exit(0);

			            default:
			                System.out.println("**** Invalid choice. Please try again. ***");
			        }
			    } catch (Exception e) {
			        System.out.println("Error: " + e.getMessage());
			    }
			
   }while(choice != 11);
		}
}}
