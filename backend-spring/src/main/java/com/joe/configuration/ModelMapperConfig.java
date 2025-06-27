package com.joe.configuration;

import com.joe.dtos.*;
import com.joe.models.*;
import org.modelmapper.AbstractConverter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Date;
import java.util.GregorianCalendar;


@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);


        modelMapper.addConverter(ctx -> {
            GregorianCalendar cal = ctx.getSource();
            return cal != null ? cal.getTime() : null;
        }, GregorianCalendar.class, Date.class);

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



        modelMapper.addMappings(new PropertyMap<Role, ResponseRoleDTO>() {
            @Override
            protected void configure() {

            }
        });


        modelMapper.addMappings(new PropertyMap<Role, ResponseRoleSlimDTO>() {
            @Override
            protected void configure() {

            }
        });

        modelMapper.addMappings(new PropertyMap<Occupation, ResponseOccupationDTO>() {
            @Override
            protected void configure() {

            }
        });

        modelMapper.addMappings(new PropertyMap<Occupation, ResponseOccupationSlimDTO>() {
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

        modelMapper.addMappings(new PropertyMap<Employee, ResponseEmployeeFullDTO>() {
            @Override
            protected void configure() {

            }

        });



        modelMapper.addMappings(new PropertyMap<Address, ResponseHomeAddressDTO>() {
            @Override
            protected void configure() {

            }
        });

        modelMapper.addMappings(new PropertyMap<EmployeeLogin, ResponseEmployeeLoginDTO>() {
            @Override
            protected void configure() {

            }
        });

        return modelMapper;
    }
}

