package com.joe.controller;

import com.joe.dtos.RegisterOccupationDTO;
import com.joe.dtos.ResponseOccupationDTO;
import com.joe.models.Occupation;
import com.joe.repository.OccupationRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shop/occupations")
@Validated
public class OccupationController {

    @Autowired
    private OccupationRepository occupationRepository;


    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addOccupation(@Valid @RequestBody RegisterOccupationDTO registerOccupationDTO) {
        try{

            if(registerOccupationDTO == null) {
                return ResponseEntity.badRequest().body("Occupation DATA cannot be null");
            }

            occupationRepository.addOccupation(registerOccupationDTO);
            return ResponseEntity.ok("Occupation added successfully");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error adding occupation: " + e.getMessage());
        }
    }
    @GetMapping()
    public ResponseEntity<?> getOccupations() {
        try {
            List<ResponseOccupationDTO> occupations = occupationRepository.getAllOccupations();
            if (occupations.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No occupations found");
            }
            return ResponseEntity.ok(occupations);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error retrieving occupations: " + e.getMessage());
        }
    }

    @GetMapping("/get/{name}")
    public ResponseEntity<?> getOccupationByName(@PathVariable String name) {
        try{
            if(name == null) {
                return ResponseEntity.badRequest().body("Name cannot be null");
            }
          ResponseOccupationDTO responseOccupationDTO =   occupationRepository.getOccupationByName(name.toUpperCase());
            if(responseOccupationDTO == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Occupation not found");
            }

            return  ResponseEntity.ok(responseOccupationDTO);

        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error retrieving occupation: " + e.getMessage());
        }
    }
}
