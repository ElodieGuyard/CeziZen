package com.example.cesizen.repository;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

import com.example.cesizen.model.Resource;
import org.springframework.data.repository.CrudRepository;

public interface RessourceRepository extends CrudRepository<Resource, Integer> {

}
