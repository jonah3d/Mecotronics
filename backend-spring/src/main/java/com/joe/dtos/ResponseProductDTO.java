package com.joe.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseProductDTO {


        @NotNull(message = "Reference cannot be null")
        @JsonProperty("reference")
        private String reference;

        @NotBlank(message = "Name cannot be blank")
        @JsonProperty("name")
        private String name;

        @NotBlank(message = "Brand cannot be blank")
        @JsonProperty("brand")
        private String brand;


        @JsonProperty("model")
        private String model;

        @JsonProperty("ean")
        private String europeanArticleNumber;

        @JsonProperty("releaseDate")
        private Date releaseDate;

        @JsonProperty("colours")
        List<String> colours = new ArrayList<>();

        @JsonProperty("weight")
        private float weight;

        @JsonProperty("height")
        private float height;

        @JsonProperty("width")
        private float width;

        @JsonProperty("energyEfficiency")
        private String energyEfficiency;

        @JsonProperty("energyConsumption")
        private int energyConsumption;

        @JsonProperty("description")
        private String description;

        @JsonProperty("images")
        private byte[] image;



        // Add other fields from your Product entity that you want to expose
        // Ensure field names match your Product entity's attribute names (or configure ModelMapper)

        public ResponseProductDTO() {}

        public ResponseProductDTO(String reference, String name, String brand, String model) {
            this.reference = reference;
            this.name = name;
            this.brand = brand;
            this.model = model;
        }

        // Getters and Setters for all fields
        public String getReference() {
            return reference;
        }

        public void setReference(String reference) {
            this.reference = reference;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getBrand() {
            return brand;
        }

        public void setBrand(String brand) {
            this.brand = brand;
        }

        public String getModel() {
            return model;
        }

        public void setModel(String model) {
            this.model = model;
        }
}
