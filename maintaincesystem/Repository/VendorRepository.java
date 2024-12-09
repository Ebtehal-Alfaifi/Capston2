package com.example.maintaincesystem.Repository;

import com.example.maintaincesystem.Model.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface VendorRepository extends JpaRepository<Vendor,Integer>{
    Vendor findVendorByVendorId(Integer vendorId);
    @Query("select v from Vendor v where v.email=?1 ")
    Vendor getVendorByEmail(String email);


    //8 of 15 end point
    Vendor findVendorByUserName(String userName);

}
