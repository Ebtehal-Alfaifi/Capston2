package com.example.maintaincesystem.Controller;

import com.example.maintaincesystem.ApiResponse.Api;
import com.example.maintaincesystem.Model.Client;
import com.example.maintaincesystem.Service.ClientService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/clients")


public class ClientController {
    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }


    @GetMapping("/getAll")
    public ResponseEntity getAllClients() {
        List<Client> clients = clientService.getAll();
        return ResponseEntity.status(200).body(clients);
    }


    @PostMapping("/add")
    public ResponseEntity addClient(@RequestBody @Valid Client client) {
         clientService.addClint(client);
        return  ResponseEntity.status(200).body(new Api("add success"));
    }


    @PutMapping("/update/{clientId}")
    public ResponseEntity updateClient(@PathVariable Integer clientId, @RequestBody @Valid Client client) {
        clientService.updateClint(clientId, client);
        return ResponseEntity.status(200).body(new Api("update success"));
    }

    @DeleteMapping("/delete/{clientId}")
    public ResponseEntity deleteClient(@PathVariable Integer clientId) {
        clientService.deleteClient(clientId);
        return  ResponseEntity.status(200).body(new Api("Client deleted successfully"));
    }

    @PostMapping("/check/user-name/{username}/password/{password}")
    public ResponseEntity checkUserCredentials(@PathVariable String username, @PathVariable String password) {
        clientService.check(username, password);
        return ResponseEntity.status(200).body("Credentials are correct");

    }

    //2 of 15
    @PostMapping("/transfer/Sender/{senderId}/receiver/{receiverId}/amount/{amount}")
    public ResponseEntity<String> transferAmount(
            @PathVariable Integer senderId,
            @PathVariable Integer receiverId,
            @PathVariable Double amount) {
        String result = clientService.transfer(senderId, receiverId, amount);

        return ResponseEntity.ok(result);
    }


}