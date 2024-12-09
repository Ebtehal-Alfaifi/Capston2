package com.example.maintaincesystem.Repository;

import com.example.maintaincesystem.Model.Resource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResourceRepository extends JpaRepository<Resource,Integer> {

    Resource findResourceByResourceId(Integer resourceId);

    //6 of 15 endpoint
    @Query("select r from Resource r where r.category=?1")
    List<Resource>getResourceByCategory(String category);


    @Query("select v from Resource v where v.vendorId=?1")
    Resource getVendorId(Integer vendorId);

    // 8 of 15 end point
    List<Resource> findAllByVendorId(Integer merchantId);


    //end point ما ادري كم رقمها
    @Query("SELECT r FROM Resource r WHERE r.vendorId = ?1 AND r.stock <= ?2")
    List<Resource> findLowStockResourcesByVendorId(Integer vendorId, Integer stock);






}
