package com.example.maintaincesystem.Service;

import com.example.maintaincesystem.ApiResponse.ApiException;
import com.example.maintaincesystem.Model.Client;
import com.example.maintaincesystem.Repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {
    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<Client>getAll(){
        return clientRepository.findAll();
    }

    public Client addClint(Client client){

        if (clientRepository.getClintByUserName(client.getUserName())!=null){
            throw new ApiException("user already found");
        }
        if (clientRepository.findClientByEmail(client.getEmail())!=null){
            throw new ApiException("email already found");
        }

        return clientRepository.save(client);


    }

    public Client updateClint(Integer clientId , Client client){
        Client old=clientRepository.getClientbyId(clientId);
        if (old==null){
            throw new ApiException("client not found");
        }
        old.setUserName(client.getUserName());
        old.setName(client.getName());
        old.setEmail(client.getEmail());
        old.setPassword(client.getPassword());
        old.setBalance(client.getBalance());
        old.setPhoneNumber(client.getPhoneNumber());
        return clientRepository.save(old);

    }

    public void deleteClient(Integer clientId){
        Client client =clientRepository.getClientbyId(clientId);
        if (client ==null){
            throw new ApiException("client not found");
        }
        clientRepository.delete(client);

    }



// 1 of 15 end point
    //check pass and user are correct
    public void check(String username, String password) {
        Client client = clientRepository.getClintByUserName(username);
        if (client == null) {
            throw new ApiException("User not found with the username: " + username);
        }
        if (!client.getPassword().equals(password)) {
            throw new ApiException("Incorrect password for username: " + username);
        }}



    //2 of 15
    //transfer for another account

    public String transfer(Integer senderId,Integer receiverId,Double amount){
        if (amount == null || amount <= 0) {
            throw new ApiException("Amount must be greater than zero");
        }
        Client sender=clientRepository.getClientbyId(senderId);
        if (sender==null){
            throw new ApiException(" sender account not found");
        }
        Client receiver=clientRepository.getClientbyId(receiverId);
        if (receiver==null){
            throw new ApiException("receiver not found");
        }

        if (sender.getBalance()<amount){
            throw new ApiException("Insufficient balance");
        }
        sender.setBalance(sender.getBalance()-amount);
        receiver.setBalance(receiver.getBalance()+amount);

        // عشان احفظ التعديلات في قاعدة البيانات
        clientRepository.save(sender);
        clientRepository.save(receiver);
        return "Transfer successful"+sender.getBalance();
    }




}
