package com.example.automappingobjects_lab.projection.services;

import com.example.automappingobjects_lab.projection.entity.Address;

import java.util.List;

public interface AddressService {
    Address findById(long id);
    List<Address> findAll();
    Address addAddress(Address address);
    Address updateAddress(Address address);
    Address deleteAddressById(long id);
    long getAddressCount();
}
