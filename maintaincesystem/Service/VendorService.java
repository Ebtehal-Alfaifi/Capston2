package com.example.maintaincesystem.Service;

import com.example.maintaincesystem.ApiResponse.ApiException;
import com.example.maintaincesystem.Model.Vendor;
import com.example.maintaincesystem.Repository.ResourceRepository;
import com.example.maintaincesystem.Repository.VendorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendorService {
    private final VendorRepository vendorRepository;
    private final ResourceRepository resourceRepository;


    public VendorService(VendorRepository vendorRepository, ResourceRepository resourceRepository) {
        this.vendorRepository = vendorRepository;
        this.resourceRepository = resourceRepository;
    }

    public List<Vendor>getAll(){
        return vendorRepository.findAll();
    }


    public Vendor addNew(Vendor vendor){
        if (vendorRepository.getVendorByEmail(vendor.getEmail())!=null){
            throw new  ApiException("email already existing");
        }
        if (vendorRepository.findVendorByUserName(vendor.getUserName())!=null){
            throw new ApiException(" user name already existing");
        }
        return vendorRepository.save(vendor);
    }

    public Vendor update(Integer id,Vendor vendor){
        Vendor oldVendor=vendorRepository.findVendorByVendorId(id);
        if (oldVendor==null){
            throw new ApiException("id not found");
        }

        oldVendor.setUserName(vendor.getUserName());
        oldVendor.setEmail(vendor.getEmail());
        oldVendor.setPassword(vendor.getPassword());
        return vendorRepository.save(oldVendor);

    }

    public void delete(Integer id){
        Vendor vendor=vendorRepository.findVendorByVendorId(id);
        if (vendor==null){
            throw new ApiException("id not fount");
        }

        vendorRepository.delete(vendor);
    }





    //8 of 15 end point




}
