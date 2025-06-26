package com.joe.repository;

import com.joe.dtos.RegisterOccupationDTO;
import com.joe.dtos.ResponseOccupationDTO;
import com.joe.dtos.ResponseRoleDTO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OccupationRepository {

    void addOccupation(RegisterOccupationDTO occupation);
    ResponseOccupationDTO getOccupationByName(String name);
    List<ResponseOccupationDTO> getAllOccupations();
}
