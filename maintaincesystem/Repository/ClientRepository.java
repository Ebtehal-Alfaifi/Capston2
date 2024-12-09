package com.example.maintaincesystem.Repository;

import com.example.maintaincesystem.Model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client,Integer>{
    @Query("select c from Client c where c.clientId=?1")
    Client getClientbyId(Integer clientId);

    Client getClintByUserName(String userName);
    Client findClientByEmail(String email);


}
