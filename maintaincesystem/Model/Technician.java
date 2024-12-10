package com.example.maintaincesystem.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.hibernate.annotations.Check;

@Entity
@Check(constraints = "specialization IN ('Electrician', 'Plumber')")


public class Technician {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer technicianId;

    @NotEmpty(message = "Technician name can not be null")
    @Size(min = 3,max = 20,message = "Technician name should be between 3 and 10")
    @Column(columnDefinition = "varchar(20) not null")
    private String name;


    @NotEmpty(message = "user name can not be null")
    @Size(min = 4,max = 10,message = " user name length should be between 4 and 10")
    @Column(columnDefinition = "varchar(10) not null unique")
    private String userName;

    @NotEmpty(message = "email can not be null")
    @Email(message = "you should enter valid email")
    @Size(max =20,message = " email can not be more than 20 character")
    @Column(columnDefinition = "varchar(20) not null unique")
    private String email;

    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$"
            , message = "Password must contain at least one uppercase letter, one lowercase letter, one digit, and one special character")
    @Size(min = 8,message = "password at least should has 8 character")
    @NotEmpty(message = "password can not be empty")
    private String password;

    @NotEmpty(message = "phone number can not be null")
    @Pattern(regexp = "^05[0-9]{8}$",message = "phone number should start with 05," +
            "/n and the length of number must has 10 character only")
    @Column(columnDefinition = "varchar(10) not null unique")
    private String phoneNumber;

    @NotEmpty(message ="specialization can not be null" )
    @Pattern(
            regexp = "^(Electrician|Plumber)$",
            message = "Specialty must be either 'Electrician' or 'Plumber'"
    )
    @Column(columnDefinition = "varchar(11) not null")
    private String specialization;


    @NotNull(message = "years of experience can not be null")
    @Min(value = 1,message = "at least you should has 1 years of experience ")
    public Integer yearsOfExperience;

    public Technician(Integer technicianId, String name, String userName, String email, String password, String phoneNumber, String specialization, Integer yearsOfExperience) {
        this.technicianId = technicianId;
        this.name = name;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.specialization = specialization;
        this.yearsOfExperience = yearsOfExperience;
    }

    public Integer getTechnicianId() {
        return technicianId;
    }

    public void setTechnicianId(Integer technicianId) {
        this.technicianId = technicianId;
    }

    public @NotEmpty(message = "Technician name can not be null") @Size(min = 3, max = 10, message = "Technician name should me between 3 and 10") String getName() {
        return name;
    }

    public void setName(@NotEmpty(message = "Technician name can not be null") @Size(min = 3, max = 10, message = "Technician name should me between 3 and 10") String name) {
        this.name = name;
    }

    public @NotEmpty(message = "user name can not be null") @Size(min = 4, max = 10, message = " user name length should be between 4 and 10") String getUserName() {
        return userName;
    }

    public void setUserName(@NotEmpty(message = "user name can not be null") @Size(min = 4, max = 10, message = " user name length should be between 4 and 10") String userName) {
        this.userName = userName;
    }

    public @NotEmpty(message = "email can not be null") @Email(message = "you should enter valid email") @Size(max = 20, message = " email can not be more than 20 character") String getEmail() {
        return email;
    }

    public void setEmail(@NotEmpty(message = "email can not be null") @Email(message = "you should enter valid email") @Size(max = 20, message = " email can not be more than 20 character") String email) {
        this.email = email;
    }

    public @NotEmpty(message = "password can not be empty") String getPassword() {
        return password;
    }

    public void setPassword(@NotEmpty(message = "password can not be empty") String password) {
        this.password = password;
    }

    public @NotEmpty(message = "phone number can not be null") @Pattern(regexp = "^05[0-9]{8}$", message = "phone number should start with 05," +
            "/n and the length of number must has 10 character only") String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(@NotEmpty(message = "phone number can not be null") @Pattern(regexp = "^05[0-9]{8}$", message = "phone number should start with 05," +
            "/n and the length of number must has 10 character only") String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public @NotEmpty(message = "specialization can not be null") @Pattern(
            regexp = "^(Electrician|Plumber)$",
            message = "Specialty must be either 'Electrician' or 'Plumber'"
    ) String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(@NotEmpty(message = "specialization can not be null") @Pattern(
            regexp = "^(Electrician|Plumber)$",
            message = "Specialty must be either 'Electrician' or 'Plumber'"
    ) String specialization) {
        this.specialization = specialization;
    }

    public @NotNull(message = "years of experience can not be null") @Min(value = 1, message = "at least you should has 1 years of experience ") Integer getYearsOfExperience() {
        return yearsOfExperience;
    }

    public void setYearsOfExperience(@NotNull(message = "years of experience can not be null") @Min(value = 1, message = "at least you should has 1 years of experience ") Integer yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }

    public Technician(){

    }
}
