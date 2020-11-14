package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.user.Customer;
import com.udacity.jdnd.course3.critter.user.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PetService {
    @Autowired
    private PetRepository petRepository;
    @Autowired
    private CustomerService customerService;

    public Long save(Pet pet, Long ownerId) {
        if (ownerId != null) {
            Optional<Customer> customer = this.customerService.find(ownerId);
            if (customer.isPresent()) {
                pet.setCustomer(customer.get());
            }
        }
        return this.petRepository.save(pet).getId();
    }

    public Optional<Pet> find(Long id) {
        return this.petRepository.findById(id);
    }

    public List<Pet> findAll() {
        return this.petRepository.findAll();
    }

    public List<Pet> findAllByOwnerId(Long ownerId) {
        Optional<Customer> customer = this.customerService.find(ownerId);
        if (customer.isPresent()) {
            return this.petRepository.getAllByCustomerId(ownerId);
        }

        return null;
    }
}
