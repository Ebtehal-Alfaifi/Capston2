package com.example.maintaincesystem.Controller;

import com.example.maintaincesystem.ApiResponse.Api;
import com.example.maintaincesystem.Model.Vendor;
import com.example.maintaincesystem.Service.VendorService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/vendor")
public class VendorController {
    private final VendorService vendorService;

    public VendorController(VendorService vendorService) {
        this.vendorService = vendorService;
    }

    @GetMapping("/get")
    public ResponseEntity getAll() {
        List<Vendor>vendors=vendorService.getAll();
        return ResponseEntity.status(200).body(vendors);
    }

    @PostMapping("/add")
    public ResponseEntity addNew(@RequestBody @Valid Vendor vendor) {

        vendorService.addNew(vendor);
        return ResponseEntity.status(200).body(new Api("add success"));
    }

@PutMapping("/update/{vendorId}")
    public ResponseEntity update(@PathVariable Integer vendorId, @RequestBody @Valid Vendor vendor) {


        vendorService.update(vendorId, vendor);
        return ResponseEntity.status(200).body(new Api(" update success"));
    }

@DeleteMapping("/delete/{vendorId}")
    public ResponseEntity delete(@PathVariable Integer vendorId){
        vendorService.delete(vendorId);
        return ResponseEntity.status(200).body(new Api("delete success"));
    }


}