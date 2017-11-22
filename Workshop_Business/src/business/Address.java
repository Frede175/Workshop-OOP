/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import common.IAddress;

/**
 *
 * @author andreasmolgaard-andersen
 */
public class Address implements IAddress {
    
    private String street;
    private String number;
    private String zipCode;
    private String country;

    
    public Address(String street, String number, String zipCode, String country) {
        this.street = street;
        this.number = number;
        this.zipCode = zipCode;
        this.country = country;
    }
    
    @Override
    public String getStreetName() {
        return street;
    }

    @Override
    public String getStreetNumber() {
        return number;
    }

    @Override
    public String getZipCode() {
        return zipCode;
    }

    @Override
    public String getCountry() {
        return country;
    }
    
    @Override
    public String toString() {
        return street + " " + number + " " + zipCode + " " + country;

    }
    
    
    
}
