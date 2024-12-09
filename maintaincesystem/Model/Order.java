package com.example.maintaincesystem.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.hibernate.annotations.Check;

import java.time.LocalDateTime;

@Entity
//@Check(constraints = "orderType in ('Resource', 'Tech')")
//@Check(constraints ="status in ('Pending','Assigned','Completed')" )

@Table(name = "order_table")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderId;

    @NotNull(message = "CLIENT ID CAN NOT BE NULL")
    @Column(columnDefinition = "int not null")
    private Integer clientId;

    // i do not use not empty because maybe clint chose to buy resource only
    //or to chose tech to solve the problem
    @Column(columnDefinition = "int ")
    private Integer technicianId;


    // i do not use not empty because maybe clint chose to buy resource only
    //or to chose tech to solve the problem
    @Column(columnDefinition = "int ")
    private Integer resourceId;

    //// i do not use not empty because if client only ask for resource only
    @Size(min = 5, max = 500,message = "issue description length between 5 and 500 ")
    @Column(columnDefinition = "varchar(500) ")
    private String issueDescription; // وصف المشكلة الي يواجهها العميل

    // يحدد نوع الطلب اذا كان يبي موارد فقط او اذا كان يبي فني للاصلاح
    @NotEmpty(message = "Order Type cannot be null")
    @Pattern(regexp = "^(Resource|Tech)$", message = "Order Type must be either 'Resource' or 'Tech'")
    @Column(columnDefinition = "varchar(8) ")
    private String orderType;


    @NotEmpty(message = "Status cannot be null")
    @Pattern(regexp = "^(Pending|Assigned|Completed)$", message = "Status must be one of the following: Pending, Completed, Assigned")
    @Column(columnDefinition = "varchar(20) not null default 'Pending'")
    private String status = "Pending"; // by default


    // بخليها فارغة في حالة طلبت الفني فقط
//    @NotNull(message = "quantity can not be null")
    @Positive(message = "quantity should has positive number")
    @Column(columnDefinition = "int ")
    private Integer quantity;


    //عطيتهاقيمة افتراضية 0 عشان اجمعها بعدين اذا طلبت الموارد او الفني
    @NotNull(message = "total price can not be null")
    @PositiveOrZero(message = "total price should have positive number or zero")
    @Column(columnDefinition = "double not null default 0.0")
      private Double totalPrice = 0.0;


    @Column(columnDefinition = "double")
    private Double proposedPrice;  /// احسب فيها تكلفة الفني /

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt = LocalDateTime.now();

    public Order(Double proposedPrice) {
        this.proposedPrice = proposedPrice;
    }

    public Order(Integer orderId, Integer clientId, Integer technicianId, Integer resourceId, String issueDescription, String orderType, String status, Integer quantity, Double totalPrice, LocalDateTime createdAt) {
        this.orderId = orderId;
        this.clientId = clientId;
        this.technicianId = technicianId;
        this.resourceId = resourceId;
        this.issueDescription = issueDescription;
        this.orderType = orderType;
        this.status = status;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.createdAt = createdAt;
    }

    public Order() {

    }

    public Double getProposedPrice() {
        return proposedPrice;
    }

    public void setProposedPrice(Double proposedPrice) {
        this.proposedPrice = proposedPrice;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public @NotNull(message = "CLIENT ID CAN NOT BE NULL") Integer getClientId() {
        return clientId;
    }

    public void setClientId(@NotNull(message = "CLIENT ID CAN NOT BE NULL") Integer clientId) {
        this.clientId = clientId;
    }

    public Integer getTechnicianId() {
        return technicianId;
    }

    public void setTechnicianId(Integer technicianId) {
        this.technicianId = technicianId;
    }

    public Integer getResourceId() {
        return resourceId;
    }

    public void setResourceId(Integer resourceId) {
        this.resourceId = resourceId;
    }

    public @Size(min = 5, max = 500, message = "issue description length between 5 and 500 ") String getIssueDescription() {
        return issueDescription;
    }

    public void setIssueDescription(@Size(min = 5, max = 500, message = "issue description length between 5 and 500 ") String issueDescription) {
        this.issueDescription = issueDescription;
    }

    public @NotEmpty(message = "Order Type cannot be null") @Pattern(regexp = "^(Resource|Tech)$", message = "Order Type must be either 'Resource' or 'Tech'") String getOrderType() {
        return orderType;
    }

    public void setOrderType(@NotEmpty(message = "Order Type cannot be null") @Pattern(regexp = "^(Resource|Tech)$", message = "Order Type must be either 'Resource' or 'Tech'") String orderType) {
        this.orderType = orderType;
    }

    public @NotEmpty(message = "Status cannot be null") @Pattern(regexp = "^(Pending|Completed|Assigned)$", message = "Status must be one of the following: Pending, Completed, Assigned") String getStatus() {
        return status;
    }

    public void setStatus(@NotEmpty(message = "Status cannot be null") @Pattern(regexp = "^(Pending|Completed|Assigned)$", message = "Status must be one of the following: Pending, Completed, Assigned") String status) {
        this.status = status;
    }


    public @Positive(message = "quantity should has positive number") Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(@Positive(message = "quantity should has positive number") Integer quantity) {
        this.quantity = quantity;
    }

    public @NotNull(message = "total price can not be null") @PositiveOrZero(message = " total price should has positive number ") Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(@NotNull(message = "total price can not be null") @PositiveOrZero(message = " total price should has positive number ") Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
