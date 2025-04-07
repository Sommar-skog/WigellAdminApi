package com.example.wigelladminapi.repositories;

import com.example.wigelladminapi.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

    Optional<Address> findByStreetAndPostalCodeAndCity(String street, String postalCode, String city);

}
