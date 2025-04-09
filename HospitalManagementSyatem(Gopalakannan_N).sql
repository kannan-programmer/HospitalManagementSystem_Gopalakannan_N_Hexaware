create database if not exists HospitalMS ;
use HospitalMS;

--  patient table
create table patient (
    patientid int auto_increment primary key,
    firstname varchar(50) not null,
    lastname varchar(50) not null,
    dateofbirth date not null,
    gender varchar(10) not null,
    contactnumber varchar(20) not null,
    address varchar(100) not null
);

-- doctor table
create table doctor (
    doctorid int auto_increment primary key,
    firstname varchar(50) not null,
    lastname varchar(50) not null,
    specialization varchar(100) not null,
    contactnumber varchar(20) not null
);

--  appointment table
create table appointment (
    appointmentid   int auto_increment primary key,
    patientid int,
    doctorid int,
    appointmentdate date not null,
    description Varchar(100) not null,
    foreign key (patientid) references patient(patientid),
    foreign key (doctorid) references doctor(doctorid)
);

--  10 sample patients
insert into patient (firstname, lastname, dateofbirth, gender, contactnumber, address) values
('Arun', 'Kumar', '1990-05-12', 'Male', '9876543210', 'Chennai, Tamil Nadu'),
('Divya', 'Sree', '1988-03-22', 'Female', '9123456780', 'Hyderabad, Telangana'),
('Rajesh', 'Menon', '1992-07-18', 'Male', '9988776655', 'Kochi, Kerala'),
('Meena', 'Lakshmi', '1995-11-05', 'Female', '9090909090', 'Bangalore, Karnataka'),
('Suresh', 'Ravi', '1985-04-10', 'Male', '9000112233', 'Coimbatore, Tamil Nadu'),
('Priya', 'Devi', '1993-09-30', 'Female', '9111223344', 'Madurai, Tamil Nadu'),
('Vignesh', 'Anand', '1991-12-20', 'Male', '9222333445', 'Trichy, Tamil Nadu'),
('Kavya', 'Mohan', '1994-06-15', 'Female', '9333444555', 'Vijayawada, Andhra Pradesh'),
('Hari', 'Krishna', '1989-08-25', 'Male', '9444555666', 'Visakhapatnam, Andhra Pradesh'),
('Anitha', 'Ramesh', '1996-02-14', 'Female', '9555666777', 'Tirupati, Andhra Pradesh');

--  10 sample doctors
insert into doctor (firstname, lastname, specialization, contactnumber) values
('Sundar', 'Rajan', 'Cardiologist', '9000011122'),
('Priya', 'Varshini', 'Dermatologist', '9000022233'),
('Karthik', 'Suresh', 'Orthopedic', '9000033344'),
('Revathi', 'Balaji', 'Gynecologist', '9000044455'),
('Mohan', 'Das', 'Pediatrician', '9000055566'),
('Anand', 'Shankar', 'Neurologist', '9000066677'),
('Swathi', 'Rao', 'ENT Specialist', '9000077788'),
('Girish', 'Naidu', 'Urologist', '9000088899'),
('Bhavani', 'Murthy', 'Psychiatrist', '9000099900'),
('Harish', 'Iyer', 'General Physician', '9000100011');

--  10 sample appointments
insert into appointment (patientid, doctorid, appointmentdate, description) values
(1, 1, '2025-04-10', 'Routine heart checkup'),
(2, 2, '2025-04-11', 'Skin allergy consultation'),
(3, 3, '2025-04-12', 'Knee pain treatment'),
(4, 4, '2025-04-13', 'Pregnancy checkup'),
(5, 5, '2025-04-14', 'Child fever consultation'),
(6, 6, '2025-04-15', 'Migraine issues'),
(7, 7, '2025-04-16', 'Ear infection'),
(8, 8, '2025-04-17', 'Kidney stone pain'),
(9, 9, '2025-04-18', 'Counseling session'),
(10, 10, '2025-04-19', 'General health checkup');

select * from patient;
select * from doctor;
select * from appointment;