package com.example.maintaincesystem.Controller;

import com.example.maintaincesystem.ApiResponse.Api;
import com.example.maintaincesystem.Model.Technician;
import com.example.maintaincesystem.Service.TechnicianService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/technicians")
public class TechnicianController {
    private final TechnicianService technicianService;

    public TechnicianController(TechnicianService technicianService) {
        this.technicianService = technicianService;
    }

    @GetMapping("/getAll")
    public ResponseEntity getAllTechnicians() {
        List<Technician> technicians = technicianService.getAll();
        return ResponseEntity.status(200).body(technicians);
    }

    @PostMapping("/add")
    public ResponseEntity addTechnician(@RequestBody @Valid Technician technician) {

        technicianService.addTechnician(technician);
        return ResponseEntity.status(200).body(new Api("Technician added successfully"));
    }

    @PutMapping("/update/{technicianId}")
    public ResponseEntity updateTechnician(@PathVariable Integer technicianId, @RequestBody @Valid Technician technician) {

        technicianService.updateTechnician(technicianId, technician);
        return ResponseEntity.status(200).body(new Api("Technician updated successfully"));
    }

    @DeleteMapping("/delete/{technicianId}")
    public ResponseEntity deleteTechnician(@PathVariable Integer technicianId) {
        technicianService.deleteTechnician(technicianId);
        return ResponseEntity.status(200).body(new Api("Technician deleted successfully"));
    }

}
