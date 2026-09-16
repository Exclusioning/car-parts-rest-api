package com.mycompany.carpartsapp;

import org.springframework.data.repository.CrudRepository;

public interface PartRepository extends CrudRepository<CarPartInfo, Integer>{
}
