package com.joe.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseHomeAddressDTO {

    @JsonProperty("direction_one")
    private String direcctionOne;

    @JsonProperty("direction_two")
    private String direcctionTwo;

    @JsonProperty("city")
    private String city;

    @JsonProperty("postal_code")
    private String postalCode;

    @JsonProperty("state")
    private String state;

    @JsonProperty("country")
    private String country;

    public ResponseHomeAddressDTO() {}

    public ResponseHomeAddressDTO(String direcctionOne, String direcctionTwo, String city, String postalCode, String state, String country) {
        this.direcctionOne = direcctionOne;
        this.direcctionTwo = direcctionTwo;
        this.city = city;
        this.postalCode = postalCode;
        this.state = state;
        this.country = country;
    }


    public String getDirecctionOne() { return direcctionOne; }
    public void setDirecctionOne(String direcctionOne) { this.direcctionOne = direcctionOne; }
    public String getDirecctionTwo() { return direcctionTwo; }
    public void setDirecctionTwo(String direcctionTwo) { this.direcctionTwo = direcctionTwo; }
    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }
    public String getPostalCode() { return postalCode; }
    public void setPostalCode(String postalCode) { this.postalCode = postalCode; }
    public String getState() { return state; }
    public void setState(String state) { this.state = state; }
    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }
}