package com.example.maintaincesystem.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
//@Check(constraints = "phoneNumber LIKE '05%' AND LENGTH(phoneNumber) = 10") ما اشتغلت معي حق الرقم  ما اشتغلت لا في مودل الفني ولا مودل الكلاينت
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer clientId;

    @NotEmpty(message = "Name cannot be empty")
    @Size(min = 3, max = 10, message = "Name length should be between 3 and 30 characters")
    @Column(columnDefinition = "varchar(10) not null")
    private String name;

    @NotEmpty(message = "User Name cannot be empty")
    @Size(min = 3, max = 10, message = "User Name length should be between 3 and 30 characters")
    @Column(columnDefinition = "varchar(10) not null")
    private String userName;

    @Email(message = "Email should be valid")
    @Column(columnDefinition = "varchar(50) not null unique")
    private String email;

    @NotEmpty(message = "Password cannot be empty")
    @Column(columnDefinition = "varchar(100) not null")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
            message = "Password must contain at least one uppercase letter, one lowercase letter, one digit, and one special character")
    private String password;



    @NotEmpty(message = "phone number can not be empty")
    @Pattern(regexp = "^05[0-9]{8}$",message = "phone number should start with 05," +
            "/n and the length of number must has 10 character only")
    @Column(columnDefinition = "varchar(10) not null unique")
    private String phoneNumber;

   @NotNull(message = "Balance can not be null")
   @Positive(message = "balance should has positive number")
   @Column(columnDefinition = "double not null")
    private Double balance;

    public Client(String userName) {
        this.userName = userName;
    }

    public Client(Integer clientId, String name, String email, String password, String phoneNumber, Double balance) {
        this.clientId = clientId;
        this.name = name;
        this.email = email;
        this.password = password;
        this.balance = balance;
        this.phoneNumber=phoneNumber;
    }


    public @NotEmpty(message = "phone number can not be empty") @Pattern(regexp = "^05[0-9]{8}$", message = "phone number should start with 05," +
            "/n and the length of number must has 10 character only") String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(@NotEmpty(message = "phone number can not be empty") @Pattern(regexp = "^05[0-9]{8}$", message = "phone number should start with 05," +
            "/n and the length of number must has 10 character only") String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public @NotEmpty(message = "User Name cannot be empty")
    @Size(min = 3, max = 10, message = "User Name length should be between 3 and 30 characters")
    String getUserName() {
        return userName;
    }

    public void setUserName(@NotEmpty(message = "User Name cannot be empty") @Size(min = 3, max = 10, message = "User Name length should be between 3 and 30 characters") String userName) {
        this.userName = userName;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public @NotEmpty(message = "Name cannot be empty") @Size(min = 3, max = 30, message = "Name length should be between 3 and 30 characters") String getName() {
        return name;
    }

    public void setName(@NotEmpty(message = "Name cannot be empty") @Size(min = 3, max = 30, message = "Name length should be between 3 and 30 characters") String name) {
        this.name = name;
    }

    public @Email(message = "Email should be valid") String getEmail() {
        return email;
    }

    public void setEmail(@Email(message = "Email should be valid") String email) {
        this.email = email;
    }

    public @NotEmpty(message = "Password cannot be empty") String getPassword() {
        return password;
    }

    public void setPassword(@NotEmpty(message = "Password cannot be empty") String password) {
        this.password = password;
    }

    public @NotNull(message = "Balance cannot be null") @PositiveOrZero(message = "Balance must be zero or positive") Double getBalance() {
        return balance;
    }

    public void setBalance(@NotNull(message = "Balance cannot be null") @PositiveOrZero(message = "Balance must be zero or positive") Double balance) {
        this.balance = balance;
    }


    public Client(){

    }
}
