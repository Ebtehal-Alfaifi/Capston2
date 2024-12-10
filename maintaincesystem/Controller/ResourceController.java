package com.example.maintaincesystem.Controller;

import com.example.maintaincesystem.ApiResponse.Api;
import com.example.maintaincesystem.Model.Resource;
import com.example.maintaincesystem.Service.ResourceService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/resource")
public class ResourceController {
    private  final ResourceService resourceService;

    public ResourceController(ResourceService resourceService) {
        this.resourceService = resourceService;
    }

    @GetMapping("/get")
    public ResponseEntity getAll(){
        List<Resource>get=resourceService.getAll();
        return ResponseEntity.status(200).body(get);
    }

    //4 of 15 end point
    @PostMapping("/add/{vendorId}")
    public ResponseEntity addNew(@PathVariable Integer vendorId,@RequestBody @Valid Resource resource, Errors errors){
        resourceService.addNew(vendorId,resource);
        return ResponseEntity.status(200).body(new Api("add success"));

    }
@PutMapping("/update/resource-id/{resourceId}/vendor-id/{vendorId}")
    public ResponseEntity update(@PathVariable Integer resourceId,
                                 @PathVariable Integer vendorId ,
                                 @RequestBody @Valid Resource resource){

resourceService.update(resourceId,vendorId,resource);
return ResponseEntity.status(200).body(new Api("update success"));
    }


    @DeleteMapping("/delete/{resourceId}")
    public ResponseEntity delete(@PathVariable Integer resourceId){
        resourceService.delete(resourceId);
        return ResponseEntity.status(200).body(new Api("delete success"));

    }


    @GetMapping("/get-category/{category}")
    public ResponseEntity getByCategory(@PathVariable String category){
        List<Resource>resources=resourceService.getByCategory(category);
        return ResponseEntity.status(200).body(resources);
    }

//discount
    @PostMapping("/resources/discount/{resourceId}/{vendorId}/{discountPercentage}")
    public ResponseEntity applyDiscountToResource( @PathVariable Integer resourceId,
                                                   @PathVariable Integer vendorId,
                                                   @PathVariable Double discountPercentage) {
        resourceService.applyDiscountToResource(resourceId, vendorId, discountPercentage);
        return ResponseEntity.status(200).body(new Api("Discount applied successfully"));
    }

    //list resource by vendor id
    @GetMapping("/merchant/{vendorId}")
    public ResponseEntity getResourcesByMerchant(@PathVariable Integer vendorId) {
        List<Resource> resources = resourceService.getResourcesByMerchant(vendorId);
        return ResponseEntity.status(200).body(resources);
    }

    @GetMapping("/low-stock/{vendorId}")
    public ResponseEntity<List<Resource>> getLowStockResources(@PathVariable Integer vendorId) {
        List<Resource> lowStockResources = resourceService.getLowStockResourcesForVendor(vendorId);
        return ResponseEntity.status(200).body(lowStockResources);
    }

}
