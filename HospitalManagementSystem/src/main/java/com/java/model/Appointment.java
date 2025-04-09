package com.java.model;



import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Appointment {
	  private int appointmentId;
	    private int patientId;
	    private int doctorId;
	    private Date appointmentDate;
	    private String description;
}
