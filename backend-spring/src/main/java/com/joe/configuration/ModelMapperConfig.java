package com.joe.configuration;

import com.joe.dtos.*;
import com.joe.models.Category;
import com.joe.models.Product;
import com.joe.models.Role;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {

        ModelMapper modelMapper = new ModelMapper();

        modelMapper.addMappings(new PropertyMap<RegisterCategoryDTO, Category>() {
            @Override
            protected void configure() {
            }
        });
        
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

        modelMapper.addMappings(new PropertyMap<RegisterRoleDTO, Role>() {
            @Override
            protected void configure() {

            }
        });

        modelMapper.addMappings(new PropertyMap<Role, ResponseRoleDTO>() {
            @Override
            protected void configure() {

            }
        });


        return modelMapper;
    }
}