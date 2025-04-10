package com.example.wigelladminapi.services;

import com.example.wigelladminapi.entities.Address;

public interface AddressService {

    Address findAdressByStreetAndPostalCodeAndCity(String street, String postalCode, String city);
    Address findAddressById(Long id);
    Address addAdress(Address address);

}
