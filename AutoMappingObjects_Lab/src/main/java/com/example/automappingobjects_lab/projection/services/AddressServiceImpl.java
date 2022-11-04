package com.example.automappingobjects_lab.projection.services;

import com.example.automappingobjects_lab.projection.entity.Address;
import com.example.automappingobjects_lab.projection.exceptions.NonExistingEntityException;
import com.example.automappingobjects_lab.projection.repositories.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class AddressServiceImpl implements AddressService {
    private final AddressRepository addressRepository;

    @Autowired
    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public Address findById(long id) {
        return addressRepository.findById(id).orElseThrow(
                ()-> new NonExistingEntityException(
                        String.format("Address with id = %s does not exist.",id)
                )
        );
    }

    @Override
    public List<Address> findAll() {
        return addressRepository.findAll();
    }

    @Override
    @Transactional
    public Address addAddress(Address address) {
        address.setId(null);
        return addressRepository.save(address);
    }

    @Override
    @Transactional
    public Address updateAddress(Address address) {
       findById(address.getId());
        return addressRepository.save(address);
    }

    @Override
    @Transactional
    public Address deleteAddressById(long id) {
        Address deleted = findById(id);
        addressRepository.deleteById(id);
        return deleted;
    }

    @Override
    public long getAddressCount() {
        return addressRepository.count();
    }
}
