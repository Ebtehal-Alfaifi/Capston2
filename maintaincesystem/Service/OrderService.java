package com.example.maintaincesystem.Service;

import com.example.maintaincesystem.ApiResponse.ApiException;
import com.example.maintaincesystem.Model.Client;
import com.example.maintaincesystem.Model.Order;
import com.example.maintaincesystem.Model.Resource;
import com.example.maintaincesystem.Repository.ClientRepository;
import com.example.maintaincesystem.Repository.OrderRepository;
import com.example.maintaincesystem.Repository.ResourceRepository;
import com.example.maintaincesystem.Repository.TechnicianRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final ClientRepository clientRepository;
    private final ResourceRepository resourceRepository;
    private final TechnicianRepository technicianRepository;

    public OrderService(OrderRepository orderRepository, ClientRepository clientRepository, ResourceRepository resourceRepository, TechnicianRepository technicianRepository) {
        this.orderRepository = orderRepository;
        this.clientRepository = clientRepository;
        this.resourceRepository = resourceRepository;
        this.technicianRepository = technicianRepository;
    }

    //get order
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    //add order
    public Order createOrder(Order order) {
        order.setCreatedAt(LocalDateTime.now());
        return orderRepository.save(order);
    }


    //update

    public Order updateOrder(Integer orderId, Order updatedOrder) {
        Order existingOrder = orderRepository.findOrderByOrderId(orderId);
        if (existingOrder==null){
            throw new ApiException("order does not existing");
        }
        existingOrder.setClientId(updatedOrder.getClientId());
        existingOrder.setTechnicianId(updatedOrder.getTechnicianId());
        existingOrder.setResourceId(updatedOrder.getResourceId());
        existingOrder.setIssueDescription(updatedOrder.getIssueDescription());
        existingOrder.setOrderType(updatedOrder.getOrderType());
        existingOrder.setStatus(updatedOrder.getStatus());
        existingOrder.setQuantity(updatedOrder.getQuantity());
        existingOrder.setTotalPrice(updatedOrder.getTotalPrice());
        return orderRepository.save(existingOrder);
    }

    public void deleteOrder(Integer orderId) {
        Order order=orderRepository.findOrderByOrderId(orderId);
        if (order==null){

            throw new ApiException("Order not found");
        }
        orderRepository.delete(order);
    }



    //******************end point *********************
    //9 of 15
    public Order purchaseResource(Integer clientId, Integer orderId,Integer resourceId, Integer quantity) {
        // التحقق من وجود المورد
        Resource resource = resourceRepository.findResourceByResourceId(resourceId);
        if (resource == null) {
            throw new ApiException("Resource not found");
        }

        // التحقق من الكمية المطلوبة
        if (resource.getStock() < quantity) {
            throw new RuntimeException("Not enough stock available");
        }

        // حساب السعر الإجمالي
        Double totalPrice = resource.getPrice() * quantity;

        Order order=orderRepository.findOrderByOrderId(orderId);
        if (order==null){
            throw new ApiException("order not found");
        }

        Client client = clientRepository.getClientbyId(clientId);
        if (client == null) {
            throw new ApiException("Client not found");
        }

        // اتحقق من الرصيد
        if (client.getBalance() < totalPrice) {
            throw new ApiException("Insufficient balance");
        }

        // اخصم المبلغ من حساب العميل
        client.setBalance(client.getBalance() - totalPrice);
        clientRepository.save(client);

        // انقص الكمية من المخزون
        resource.setStock(resource.getStock() - quantity);
        resourceRepository.save(resource);

        // إنشاء الطلب
         order = new Order();
        order.setOrderId(orderId);
        order.setClientId(clientId);
        order.setResourceId(resourceId);
        order.setOrderType("Resource");
        order.setStatus("Completed");
        order.setQuantity(quantity);
        order.setTotalPrice(totalPrice);
        order.setCreatedAt(LocalDateTime.now());


//        Order savedOrder = orderRepository.save(order);
//        savedOrder.setOrderId(savedOrder.getOrderId());
//        return savedOrder;

        return orderRepository.save(order);
    }




    //10 of 15
// عينتها ك قيمة ثابتة لتحسب سعر اي فني
    private static final double HOURLY_RATE = 50.0;


    public void assignTechnicianToOrder(Integer orderId, Integer technicianId, Integer hoursWorked) {
        Order order = orderRepository.findOrderByOrderId(orderId);
        if (order==null) {
           throw new ApiException ("Order not found with ID: " );
        }

        // اتحقق اذا الحالة احد اخذها او لا
        if (order.getStatus().equals("Assigned")) {
            throw new ApiException("Order is already assigned to a technician.");
        }


        Client client = clientRepository.getClientbyId(order.getClientId());
        if (client==null) {

            throw new ApiException("Client not found with ID: " );
        }
        // حساب تكلفة الفني بناءً على الساعات
        Double technicianFee = hoursWorked * HOURLY_RATE;

        // التحقق من وجود رصيد كافٍ لدى العميل
        if (client.getBalance() < technicianFee) {
            throw new ApiException("Client does not have enough balance to pay for the technician.");
        }

        // خصم تكلفة الفني من رصيد العميل
        client.setBalance(client.getBalance() - technicianFee);
        clientRepository.save(client);

        // تحديث الطلب لتعيين الفني
        order.setOrderType("Tech");
        order.setTechnicianId(technicianId);
        order.setStatus("Assigned");

        Double updatedTotalPrice = order.getTotalPrice()+ technicianFee;
        order.setTotalPrice(updatedTotalPrice);

        orderRepository.save(order);
    }


    //11 of 15
// change status of order to complete
public void completeOrder(Integer orderId, Integer technicianId) {
    Order order = orderRepository.findOrderByOrderId(orderId);
    if (order == null) {
        throw new ApiException("Order not found with ID: ");
    }
    if (!"Assigned".equals(order.getStatus())) {
        throw new ApiException("Order status must be 'Assigned' to complete.");
    }
    if (!order.getTechnicianId().equals(technicianId)) {
        throw new ApiException("Only the assigned technician can complete this order.");
    }
    order.setStatus("Completed");
    orderRepository.save(order);


}





}
