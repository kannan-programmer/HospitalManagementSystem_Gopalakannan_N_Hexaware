package com.java.model;
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
public class Doctor {
	   private int doctorId;
	    private String firstName;
	    private String lastName;
	    private String specialization;
	    private String contactNumber;
}
