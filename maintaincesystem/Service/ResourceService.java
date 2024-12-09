package com.example.maintaincesystem.Service;

import com.example.maintaincesystem.ApiResponse.ApiException;
import com.example.maintaincesystem.Model.Resource;
import com.example.maintaincesystem.Repository.ResourceRepository;
import com.example.maintaincesystem.Repository.VendorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResourceService {
    private final ResourceRepository resourceRepository;
    private final VendorRepository vendorRepository;

    public ResourceService(ResourceRepository resourceRepository, VendorRepository vendorRepository) {
        this.resourceRepository = resourceRepository;
        this.vendorRepository = vendorRepository;
    }

    public List<Resource>getAll(){
        return resourceRepository.findAll();
    }


    //********************* End point ********************
    //4 of 15 end point
    //اتحقق ان البائع الي يضيف الموارد
    public Resource addNew(Integer vendorId, Resource resource) {
        if (vendorId == null ) {
            throw new ApiException("Vendor ID is required.");
        }

        // اتحقق اذا البائع موجود في قاعدة البيانات
        boolean vendorExists = vendorRepository.existsById(vendorId);
        if (!vendorExists) {
            throw new ApiException("Vendor not found. Only valid vendors can add resources.");
        }

        if (!vendorId.equals(resource.getVendorId())) {
            throw new ApiException("Vendor ID mismatch. Vendor can only add their own resources.");
        }

        return resourceRepository.save(resource);
    }


    //5 of 15 end point
    // اتحقق انو البائع الي يحدث الموارد
    public Resource update(Integer resourceId, Integer vendorId, Resource resource) {
        Resource oldResource = resourceRepository.findResourceByResourceId(resourceId);

        if (oldResource == null) {
            throw new ApiException("Resource not found");
        }

        if (!vendorId.equals(oldResource.getVendorId())) {
            throw new ApiException("You are not authorized to update this resource. Vendor can only update their own resources.");
        }

        oldResource.setName(resource.getName());
        oldResource.setCategory(resource.getCategory());
        oldResource.setMadeIn(resource.getMadeIn());
        oldResource.setPrice(resource.getPrice());
        oldResource.setStock(resource.getStock());

        return resourceRepository.save(oldResource);
    }



    public void delete(Integer id){
        Resource resource=resourceRepository.findResourceByResourceId(id);
        if (resource==null){
            throw new ApiException("resource not found");
        }
        resourceRepository.delete(resource);
    }

//********************* End point ********************

    //6 of 15
    public List<Resource>getByCategory(String category){
        List<Resource>categories=resourceRepository.getResourceByCategory(category);
        if (categories==null){
            throw new ApiException("categories not found");
        }
        return categories;
    }

//7 of 15 end point

    public void applyDiscountToResource(Integer resourceId, Integer vendorId, Double discountPercentage) {
        // التحقق من المورد
        Resource resource = resourceRepository.findResourceByResourceId(resourceId);
                 new ApiException("Resource not found");

        // التحقق من أن المورد ينتمي للبائع
        if (!resource.getVendorId().equals(vendorId)) {
            throw new ApiException("This resource does not belong to the merchant with ID: " );
        }


        if (discountPercentage < 0 || discountPercentage > 100) {
            throw new ApiException("Discount percentage must be between 0 and 100");
        }

        // حساب السعر الجديد بعد الخصم
        Double newPrice = resource.getPrice() - (resource.getPrice() * discountPercentage / 100);

        // تحديث سعر المورد
        resource.setPrice(newPrice);
        resourceRepository.save(resource);
    }


    //8 of 15

    public List<Resource> getResourcesByMerchant(Integer vendorId) {
        List<Resource> resources = resourceRepository.findAllByVendorId(vendorId);

        if (resources.isEmpty()) {
            throw new ApiException("No resources found for merchant with ID: ");
        }

        return resources;
    }


//end point
    //عطيته الاربعة ك قيمة ثابتة يرجع جميع الموارد التي مخزونها اقل من او يساوي 4 لبائع محدد
    public List<Resource> getLowStockResourcesForVendor(Integer vendorId) {
        List<Resource>resources=resourceRepository.findLowStockResourcesByVendorId(vendorId,4);
        if (resources==null){
            throw new ApiException("there is no resources found ");
        }

        return resources;
    }


}
