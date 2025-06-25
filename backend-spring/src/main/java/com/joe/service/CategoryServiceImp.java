package com.joe.service;

import com.joe.dtos.RegisterCategoryDTO;
import com.joe.dtos.ResponseCategoryDTO;
import com.joe.dtos.ResponseProductDTO;
import com.joe.models.Category;
import com.joe.models.Product;
import com.joe.repository.CategoryRepository;
import com.matisse.MtDatabase;
import com.matisse.MtObjectIterator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.joe.models.Category.lookupName_IDX;

@Service
public class CategoryServiceImp implements CategoryRepository {

    private final MtDatabase mtDatabase;
    private final ModelMapper modelMapper;

    public CategoryServiceImp(MtDatabase mtDatabase, ModelMapper modelMapper) {
        this.mtDatabase = mtDatabase;
        this.modelMapper = modelMapper;
    }

    @Override
    public void addCategory(RegisterCategoryDTO registerCategoryDTO) {


        try {

            if(!mtDatabase.isTransactionInProgress()){
                mtDatabase.startTransaction();
            }else {
                throw new Exception("Another transaction is already in progress");
            }


            if (registerCategoryDTO == null) {
                throw new IllegalArgumentException("Category DTO cannot be null");
            }

            Category category = new Category(mtDatabase);
            modelMapper.map(registerCategoryDTO, category);

            mtDatabase.commit();
        } catch (Exception e) {
            System.err.println("Error adding category: " + e.getMessage());
            try {
                if (mtDatabase.isTransactionInProgress()) {
                    mtDatabase.rollback();
                }
            } catch (Exception rollbackEx) {
                System.err.println("Error during rollback: " + rollbackEx.getMessage());
            }
            throw new RuntimeException("Error adding category", e);
        }
    }

    @Override
    public List<ResponseCategoryDTO> getCategories() {
        List<ResponseCategoryDTO> responseCategories = new ArrayList<>();
        try {
            mtDatabase.startVersionAccess();

            MtObjectIterator<Category> categoryIterator = Category.instanceIterator(mtDatabase);
            if( !categoryIterator.hasNext()) {
                System.out.println("No categories found.");
                return responseCategories;
            }

            while (categoryIterator.hasNext()) {
                Category category = categoryIterator.next();

                ResponseCategoryDTO responseDTO = modelMapper.map(category, ResponseCategoryDTO.class);

                List<ResponseProductDTO> productDTOs = new ArrayList<>();
                Product[] products = category.getProducts();
                if (products != null) {
                    for (Product product : products) {
                        productDTOs.add(modelMapper.map(product, ResponseProductDTO.class));
                    }
                }
                responseDTO.setProducts(productDTOs);

                responseCategories.add(responseDTO);
            }

        } catch (Exception e) {
            System.err.println("Error getting categories: " + e.getMessage());
            e.printStackTrace();

        } finally {
            try {
                mtDatabase.endVersionAccess();
            } catch (Exception endEx) {
                System.err.println("Error ending version access: " + endEx.getMessage()); // Use System.err
            }
        }
        return responseCategories;
    }


    @Override
    public ResponseCategoryDTO getCategoryByName(String name) {

        ResponseCategoryDTO responseDTO = null;

        if(name == null) {
            throw new IllegalArgumentException("Category name cannot be null");
        }

        try {
            mtDatabase.startVersionAccess();

           Category cat = Category.lookupName_IDX(mtDatabase, name);
            if (cat == null) {
                System.out.println("Category not found with name: " + name);
                return null;
            }

            responseDTO =   modelMapper.map(cat, ResponseCategoryDTO.class);
            List<ResponseProductDTO> productDTOs = new ArrayList<>();
          Product[] products =   cat.getProducts();
            if (products != null) {
                for (Product product : products) {
                    productDTOs.add(modelMapper.map(product, ResponseProductDTO.class));
                }
            }
            responseDTO.setProducts(productDTOs);

        } catch (Exception e) {
            System.err.println("Error getting category by name: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                mtDatabase.endVersionAccess();
            } catch (Exception endEx) {
                System.err.println("Error ending version access: " + endEx.getMessage());
            }
        }
        return responseDTO;

    }
}

