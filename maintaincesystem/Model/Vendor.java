package com.example.maintaincesystem.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
public class Vendor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer vendorId;

    @NotEmpty(message = "vendor user name can not be null")
    @Size(min = 4,max = 6,message = " vendor user name can not be null ")
    @Column(columnDefinition = "varchar(6) not null unique")
     private String userName;

    @NotEmpty(message = " email can not be null")
    @Email(message = " you should enter valid email")
    @Size(max = 20,message = " email length can not be more than 10")
    @Column(columnDefinition = "varchar(20) not null unique")
    private String email;

    @NotEmpty(message = "password can not be null")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$", message = "Password must contain at least one uppercase letter, one lowercase letter, one digit, and one special character")
    @Size(min = 8,max = 10,message = "password length should be between 8 and 10")
    @Column(columnDefinition = "varchar(10) not null")
    private  String password;

    public Vendor(Integer vendorId, String userName, String email, String password) {
        this.vendorId = vendorId;
        this.userName = userName;
        this.email = email;
        this.password = password;
    }

    public Integer getVendorId() {
        return vendorId;
    }

    public void setVendorId(Integer vendorId) {
        this.vendorId = vendorId;
    }

    public @NotEmpty(message = "vendor user name can not be null") @Size(min = 4, max = 6, message = " vendor user name can not be null ") String getUserName() {
        return userName;
    }

    public void setUserName(@NotEmpty(message = "vendor user name can not be null") @Size(min = 4, max = 6, message = " vendor user name can not be null ") String userName) {
        this.userName = userName;
    }

    public @NotEmpty(message = " email can not be null") @Email(message = " you should enter valid email") @Size(max = 20, message = " email length can not be more than 20") String getEmail() {
        return email;
    }

    public void setEmail(@NotEmpty(message = " email can not be null") @Email(message = " you should enter valid email") @Size(max = 20, message = " email length can not be more than 20") String email) {
        this.email = email;
    }

    public @NotEmpty(message = "password can not be null") @Size(min = 8, max = 10, message = "password length should be between 8 and 10") String getPassword() {
        return password;
    }

    public void setPassword(@NotEmpty(message = "password can not be null") @Size(min = 8, max = 10, message = "password length should be between 8 and 10") String password) {
        this.password = password;
    }

    public Vendor(){

    }
}
