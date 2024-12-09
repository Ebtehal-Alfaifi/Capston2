package com.example.maintaincesystem.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.Check;

@Entity
@Check(constraints = "category in ('Electrical', 'Plumbing')")

public class Resource {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer resourceId;

    @NotNull(message = "Name cannot be null")
    @Size(min = 3, max = 100, message = "Name must be between 3 and 100 characters")
    @Column(columnDefinition = "varchar(100)not null")
    private String name;

    @NotNull(message = "Price cannot be null")
    @Column(columnDefinition = "double not null")
    private Double price;

    @NotNull(message = "Stock cannot be null")
    @PositiveOrZero(message =" stock can not be negative number" )
    @Column(columnDefinition = "int not null")
    private Integer stock;


    @NotNull(message = " made in can not be null")
    @Size(min = 3,max = 10,message = " city name should be between 3 and 10")
    @Column(columnDefinition = "varchar(10) not null")
    private String madeIn;

    @NotNull(message = "Category cannot be null")
    @Pattern(regexp = "^(Electrical|Plumbing)$", message = "Category must be either 'Electrical' or 'Plumbing'")
    @Column(columnDefinition = "varchar(50) not null")
    private String category;

    @NotNull(message = "Seller ID cannot be null")
    @Column(columnDefinition = "int not null")
    private Integer vendorId;

    public Resource(Integer resourceId, String name, Double price, Integer stock, String madeIn, String category, Integer vendorId) {
        this.resourceId = resourceId;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.madeIn = madeIn;
        this.category = category;
        this.vendorId = vendorId;
    }

    public Resource() {

    }

    public Integer getResourceId() {
        return resourceId;
    }

    public void setResourceId(Integer resourceId) {
        this.resourceId = resourceId;
    }

    public @NotNull(message = "Name cannot be null") @Size(min = 3, max = 100, message = "Name must be between 3 and 100 characters") String getName() {
        return name;
    }

    public void setName(@NotNull(message = "Name cannot be null") @Size(min = 3, max = 100, message = "Name must be between 3 and 100 characters") String name) {
        this.name = name;
    }

    public @NotNull(message = "Price cannot be null") Double getPrice() {
        return price;
    }

    public void setPrice(@NotNull(message = "Price cannot be null") Double price) {
        this.price = price;
    }

    public @NotNull(message = "Stock cannot be null") @PositiveOrZero(message = " stock can not be negative number") Integer getStock() {
        return stock;
    }

    public void setStock(@NotNull(message = "Stock cannot be null") @PositiveOrZero(message = " stock can not be negative number") Integer stock) {
        this.stock = stock;
    }

    public @NotNull(message = " made in can not be null") @Size(min = 3, max = 10, message = " city name should be between 3 and 10") String getMadeIn() {
        return madeIn;
    }

    public void setMadeIn(@NotNull(message = " made in can not be null") @Size(min = 3, max = 10, message = " city name should be between 3 and 10") String madeIn) {
        this.madeIn = madeIn;
    }

    public @NotNull(message = "Category cannot be null") @Pattern(regexp = "^(Electrical|Plumbing)$", message = "Category must be either 'Electrical' or 'Plumbing'") String getCategory() {
        return category;
    }

    public void setCategory(@NotNull(message = "Category cannot be null") @Pattern(regexp = "^(Electrical|Plumbing)$", message = "Category must be either 'Electrical' or 'Plumbing'") String category) {
        this.category = category;
    }

    public @NotNull(message = "Seller ID cannot be null") Integer getVendorId() {
        return vendorId;
    }

    public void setVendorId(@NotNull(message = "Seller ID cannot be null") Integer vendorId) {
        this.vendorId = vendorId;
    }
}
