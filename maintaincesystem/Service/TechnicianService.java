package com.example.maintaincesystem.Service;

import com.example.maintaincesystem.ApiResponse.ApiException;
import com.example.maintaincesystem.Model.Technician;
import com.example.maintaincesystem.Repository.TechnicianRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TechnicianService {
    private final TechnicianRepository technicianRepository;

    public TechnicianService(TechnicianRepository technicianRepository) {
        this.technicianRepository = technicianRepository;
    }

    public List<Technician> getAll() {
        return technicianRepository.findAll();
    }

    public Technician addTechnician(Technician technician) {
        if (technicianRepository.findByUserName(technician.getUserName()) != null) {

                throw new ApiException("Username already exists");
            }
            if (technicianRepository.getByEmail(technician.getEmail()) != null) {
                throw new ApiException("Email already exists");
            }
            if (technicianRepository.getByPhone(technician.getPhoneNumber()) != null) {
                throw new ApiException("Phone number already exists");
            }
            return technicianRepository.save(technician);
        }



    public Technician updateTechnician(Integer technicianId, Technician technician) {
        Technician oldTechnician = technicianRepository.findAllByTechnicianId(technicianId);
        if (oldTechnician == null) {
            throw new ApiException("Technician not found");
        }

        oldTechnician.setUserName(technician.getUserName());
        oldTechnician.setName(technician.getName());
        oldTechnician.setEmail(technician.getEmail());
        oldTechnician.setPassword(technician.getPassword());
        oldTechnician.setPhoneNumber(technician.getPhoneNumber());
        oldTechnician.setSpecialization(technician.getSpecialization());
        oldTechnician.setYearsOfExperience(technician.getYearsOfExperience());

        return technicianRepository.save(oldTechnician);
    }


    public void deleteTechnician(Integer technicianId) {
        Technician technician = technicianRepository.findAllByTechnicianId(technicianId);
        if (technician == null) {
            throw new ApiException("Technician not found");
        }
        technicianRepository.delete(technician);
    }



    }




