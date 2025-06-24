package com.joe.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.joe.models.Product;
import jakarta.validation.constraints.NotBlank;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseCategoryDTO {

    @NotBlank(message = "Name cannot be blank")
    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;


    @JsonProperty("products")
    private List<ResponseProductDTO> products = new ArrayList<>();

    public ResponseCategoryDTO() {
    }

    public ResponseCategoryDTO(String name, String description, List<ResponseProductDTO> products) {
        this.name = name;
        this.description = description;
        this.products = products;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<ResponseProductDTO> getProducts() {
        return products;
    }

    public void setProducts(List<ResponseProductDTO> products) {
        this.products = products;
    }
}
