package com.joe.service;

import com.joe.dtos.RegisterOccupationDTO;
import com.joe.dtos.ResponseEmployeeDTO;
import com.joe.dtos.ResponseOccupationDTO;
import com.joe.dtos.ResponseRoleDTO;
import com.joe.models.Employee;
import com.joe.models.Occupation;
import com.joe.repository.OccupationRepository;
import com.joe.repository.RoleRepository;
import com.matisse.MtDatabase;
import com.matisse.MtObjectIterator;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OccupationServiceImp implements OccupationRepository {

    private final MtDatabase mtDatabase;
    private final ModelMapper modelMapper;

    public OccupationServiceImp(MtDatabase mtDatabase, ModelMapper modelMapper) {
        this.mtDatabase = mtDatabase;
        this.modelMapper = modelMapper;
    }


    @Override
    public void addOccupation(RegisterOccupationDTO occupation) {

        try{

            if(!mtDatabase.isTransactionInProgress()) {
                mtDatabase.startTransaction();
            } else {
                throw new Exception("Another transaction is already in progress");
            }

            Occupation newOccupation = new Occupation(mtDatabase);

            newOccupation.setName(occupation.getName());
            newOccupation.setDescription(occupation.getDescription());

            mtDatabase.commit();


        }catch (Exception e){
            System.err.println("Error adding occupation: " + e.getMessage());
            try {
                if (mtDatabase.isTransactionInProgress()) {
                    mtDatabase.rollback();
                }
            } catch (Exception rollbackEx) {
                System.err.println("Error during rollback: " + rollbackEx.getMessage());
            }
            throw new RuntimeException("Error adding occupation", e);
        }

    }

    @Override
    public ResponseOccupationDTO getOccupationByName(String name) {

        ResponseOccupationDTO responseOccupationDTO = null;

        if(name==null){
            throw new IllegalArgumentException("Name is null");
        }

        try{

            if(!mtDatabase.isVersionAccessInProgress()) {
                mtDatabase.startVersionAccess();
            }else  {
                throw new Exception("Another transaction is already in progress");
            }

            Occupation findOccupation = Occupation.lookupOccupationName_IDX(mtDatabase, name);
            if(findOccupation==null){
                return  null;
            }

          responseOccupationDTO =  modelMapper.map(findOccupation, ResponseOccupationDTO.class);

         List<ResponseEmployeeDTO> employeeDTOS = new ArrayList<>();
         Employee[] employees =   findOccupation.getEmployees();
         if (employees != null) {
             for (Employee employee : employees) {
                ResponseEmployeeDTO responseEmployeeDTO =  modelMapper.map(employee, ResponseEmployeeDTO.class);
                employeeDTOS.add(responseEmployeeDTO);
             }
         }
            responseOccupationDTO.setEmployees(employeeDTOS);


        }catch (Exception e){
            throw new RuntimeException("Error retrieving occupation by name", e);
        }finally {
            try {
                mtDatabase.endVersionAccess();
            } catch (Exception endEx) {
                System.err.println("Error ending version access: " + endEx.getMessage());
            }
        }


        return responseOccupationDTO;
    }

    @Override
    public List<ResponseOccupationDTO> getAllOccupations() {
       List<ResponseOccupationDTO> list = new ArrayList<>();

       try{

           if(mtDatabase.isVersionAccessInProgress()){
                throw new Exception("Version access is already in progress");
           }else{
                mtDatabase.startVersionAccess();
           }

          MtObjectIterator<Occupation> occupationIterator =  Occupation.instanceIterator(mtDatabase);
           if(!occupationIterator.hasNext()) {
               throw new Exception("No occupations found");
           }

           while(occupationIterator.hasNext()) {
               Occupation occupation = occupationIterator.next();
              ResponseOccupationDTO responseOccupationDTO =  modelMapper.map(occupation, ResponseOccupationDTO.class);
              List<ResponseEmployeeDTO> employeeDTOS = new ArrayList<>();
             Employee[] employees =  occupation.getEmployees();
             if(employees != null) {
                 for (Employee employee : employees) {
                     ResponseEmployeeDTO responseEmployeeDTO = modelMapper.map(employee, ResponseEmployeeDTO.class);
                     employeeDTOS.add(responseEmployeeDTO);
                 }
             }
              responseOccupationDTO.setEmployees(employeeDTOS);
              list.add(responseOccupationDTO);
           }

       } catch (Exception e) {
           throw new RuntimeException("Error retrieving occupations: " + e.getMessage(), e);
       }finally {
           try {
               mtDatabase.endVersionAccess();
           } catch (Exception endEx) {
               System.err.println("Error ending version access: " + endEx.getMessage());
           }
       }

       return list;
    }


}
