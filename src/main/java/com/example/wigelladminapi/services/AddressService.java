package com.example.wigelladminapi.services;

import com.example.wigelladminapi.entities.Address;
import com.example.wigelladminapi.exceptions.InvalidInputException;
import com.example.wigelladminapi.repositories.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AddressService implements AddressServiceInterface {
    private final AddressRepository addressRepository;

    @Autowired
    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public Address findAdressByStreetAndPostalCodeAndCity(String street, String postalCode, String city) {
        Optional<Address> address = addressRepository.findByStreetAndPostalCodeAndCity(street, postalCode, city);
        return address.orElse(null);
    }

    @Override
    public Address findAddressById(Long id) {
        Optional<Address> address = addressRepository.findById(id);
        return address.orElse(null);
    }

    @Override
    public Address addAdress(Address address) {
      if (address.getStreet() == null || address.getStreet().isEmpty()) {
          throw new InvalidInputException("Address", "street", address.getStreet());
      }
      if (address.getPostalCode() == null || address.getPostalCode().isEmpty()) {
          throw new InvalidInputException("Address", "postalCode", address.getPostalCode());
      }
      if (address.getCity() == null || address.getCity().isEmpty()) {
          throw new InvalidInputException("Address", "city", address.getCity());
      }
      return addressRepository.save(address);
    }
}
