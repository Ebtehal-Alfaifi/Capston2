package com.example.maintaincesystem.Controller;

import com.example.maintaincesystem.ApiResponse.Api;
import com.example.maintaincesystem.ApiResponse.ApiException;
import com.example.maintaincesystem.Model.Order;
import com.example.maintaincesystem.Service.OrderService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/order")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/get")
    public ResponseEntity getAllOrders() {
        List<Order> orders = orderService.getAllOrders();
        return ResponseEntity.status(200).body(orders);
    }

    // إنشاء طلب جديد
    @PostMapping("/creat-order")
    public ResponseEntity createOrder(@RequestBody @Valid Order order) {
        orderService.createOrder(order);
        return ResponseEntity.status(200).body(new Api("order add success"));
    }

    @PutMapping("/update/{orderId}")
    public ResponseEntity updateOrder(@PathVariable Integer orderId,@RequestBody @Valid Order order){


        orderService.updateOrder(orderId,order);
        return ResponseEntity.status(200).body(new Api("update success"));
    }

    @DeleteMapping("/delete/{orderId}")
    public ResponseEntity delete(@PathVariable Integer orderId){
        orderService.deleteOrder(orderId);
        return ResponseEntity.status(200).body(new Api("update Success"));
    }

    //end point
    // اتمام عملية الطلب للموارد وتغيير الحاله
    @PostMapping("/purchase/{clientId}/{orderId}/{resourceId}/{quantity}")
    public ResponseEntity purchaseResource(
            @PathVariable Integer clientId,
            @PathVariable Integer orderId,
            @PathVariable Integer resourceId,
            @PathVariable Integer quantity) {
        orderService.purchaseResource(clientId,orderId, resourceId, quantity);
        return ResponseEntity.status(200).body(new Api("purchaseResource success"));
    }


// احدد فني للمشكلة العميل
     @PostMapping("/select-technician/{orderId}/{technicianId}/{proposedPrice}")
     public ResponseEntity selectTechnicianForOrder( @PathVariable Integer orderId,
                                                     @PathVariable Integer technicianId,
                                                     @PathVariable Double proposedPrice) {
        orderService.selectTechnicianForOrder(orderId, technicianId, proposedPrice);
        return ResponseEntity.status(200).body(new Api("Technician selection success"));
    }


    // اعرض جميع الفنيين الذين اختارو مشكلة العميل لكي يقوم العميل بعدها بب اختيار فني محدد
   @GetMapping("/{orderId}/proposed-technicians")
   public ResponseEntity getProposedTechniciansForOrder(@PathVariable Integer orderId) {
        List<Order> proposedTechnicians = orderService.getProposedTechniciansForOrder(orderId);
        return ResponseEntity.status(200).body(proposedTechnicians); }


// العميل يقبل الفني
    @PostMapping("/{orderId}/accept-technician/technical-id/{technicianId}/client-id/{clientId}")
    public ResponseEntity acceptTechnicianForOrder( @PathVariable Integer orderId,
                                                    @PathVariable Integer technicianId,
                                                    @PathVariable Integer clientId) {
        orderService.acceptTechnicianForOrder(orderId, technicianId, clientId);
        return ResponseEntity.status(200).body(new Api("Technician accepted successfully"));
    }


    // العميل يرفض الفني

    @PostMapping("/{orderId}/reject-technician")
    public ResponseEntity rejectTechnicianForOrder( @PathVariable Integer orderId,
                                                    @PathVariable Integer clientId) {
        orderService.rejectTechnicianForOrder(orderId, clientId);
        return ResponseEntity.status(200).body(new Api("Technician rejected successfully")); }






// الفني بعد ان يكمل حل المشكلة يحولها الى مكتمل
    @PutMapping("/orders/complete/{orderId}/{technicianId}")
    public ResponseEntity completeOrder( @PathVariable Integer orderId
            , @PathVariable Integer technicianId) {
        orderService.completeOrder(orderId, technicianId);
        return ResponseEntity.status(200).body(new Api("Order completed successfully"));
    }

}
