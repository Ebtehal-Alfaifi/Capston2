package com.example.maintaincesystem.Repository;

import com.example.maintaincesystem.Model.Technician;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TechnicianRepository extends JpaRepository<Technician,Integer>{

    Technician findAllByTechnicianId(Integer technicianId );
    Technician findByUserName(String userName);
    @Query("select t from Technician t where t.email=?1")
    Technician getByEmail(String email);

    @Query("select t from Technician t where t.phoneNumber=?1")
    Technician getByPhone(String phoneNumber);
}
