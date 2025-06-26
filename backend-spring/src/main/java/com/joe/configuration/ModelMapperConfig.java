package com.joe.configuration;

import com.joe.dtos.*;
import com.joe.models.*;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {

        ModelMapper modelMapper = new ModelMapper();

     /*   modelMapper.addMappings(new PropertyMap<RegisterCategoryDTO, Category>() {
            @Override
            protected void configure() {
            }
        });*/
        
        modelMapper.addMappings(new PropertyMap<Category, ResponseCategoryDTO>() {
            @Override
            protected void configure() {

            }
        });

        modelMapper.addMappings(new PropertyMap<Product, ResponseProductDTO>() {
            @Override
            protected void configure() {

            }
        });

        // We cant map RegisterRoleDTO to Role directly because of final setters in Role class

   /*     modelMapper.addMappings(new PropertyMap<RegisterRoleDTO, Role>() {
            @Override
            protected void configure() {

                map(source.getName(), destination.getName());
                map(source.getDescription(), destination.getDescription());
                map(source.getAccessLevel(), destination.getAccessLevel());

            }
        });
*/
        modelMapper.addMappings(new PropertyMap<Role, ResponseRoleDTO>() {
            @Override
            protected void configure() {

            }
        });

        modelMapper.addMappings(new PropertyMap<Occupation, ResponseOccupationDTO>() {
            @Override
            protected void configure() {

            }
        });

        modelMapper.addMappings(new PropertyMap<Department, ResponseDepartmentDTO>() {
            @Override
            protected void configure() {

            }
        });

        modelMapper.addMappings(new PropertyMap<Employee, ResponseEmployeeDTO>() {
            @Override
            protected void configure() {

            }
        });

        return modelMapper;
    }
}