package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.pet.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private PetService petService;

    public Long save(Customer customer, List<Long> petIds) {
        if (petIds != null) {
            petIds.forEach(id -> {
                Optional<Pet> petOptional = this.petService.find(id);
                if (!petOptional.isEmpty()) {
                    customer.getPets().add(petOptional.get());
                }
            });
        }
        return this.customerRepository.save(customer).getId();
    }

    public Optional<Customer> find(Long id) {
        return this.customerRepository.findById(id);
    }

    public List<Customer> findAll() {
        return this.customerRepository.findAll();
    }

    public Optional<Customer> findCustomerByPerId(Long petId) {
        Optional<Pet> optionalPet = this.petService.find(petId);
        if (optionalPet.isPresent()) {
            return this.customerRepository.findById(optionalPet.get().getCustomer().getId());
        }

        return null;
    }
}
