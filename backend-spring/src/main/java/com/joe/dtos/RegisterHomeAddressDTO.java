package com.joe.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RegisterHomeAddressDTO {

    @NotBlank(message = "street cannot be blank")
    @JsonProperty("street")
    private String directionOne;

    @NotBlank(message = "floor/door cannot be blank")
    @JsonProperty("floor_door")
    private String directionTwo;

    @NotBlank(message = "city cannot be blank")
    @JsonProperty("city")
    private String city;

    @NotBlank(message = "postal code cannot be blank")
    @JsonProperty("postal_code")
    private String postalCode;

    @NotBlank(message = "country cannot be blank")
    @JsonProperty("country")
    private String country;

    @JsonProperty("state")
    private String state;


    public RegisterHomeAddressDTO(String directionOne, String directionTwo, String city, String postalCode, String country, String state) {
        this.directionOne = directionOne;
        this.directionTwo = directionTwo;
        this.city = city;
        this.postalCode = postalCode;
        this.country = country;
        this.state = state;
    }

    public RegisterHomeAddressDTO() {
    }

    public String getDirectionOne() {
        return directionOne;
    }

    public void setDirectionOne(String directionOne) {
        this.directionOne = directionOne;
    }

    public String getDirectionTwo() {
        return directionTwo;
    }

    public void setDirectionTwo(String directionTwo) {
        this.directionTwo = directionTwo;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
