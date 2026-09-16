package com.mycompany.carpartsapp;

import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

@Service
public class PartService{
	private final PartRepository repo;
	
	private static final Logger logger = LoggerFactory.getLogger(PartService.class);

	public PartService(PartRepository repo){
		this.repo = repo;
	}
	
	public CarPartInfo addPart(CarPartInfo part){
		logger.info("Adding part: {}", part);
		return repo.save(part);	
	}

	public List<CarPartInfo> getParts(){
		logger.info("Fetching parts");
		List<CarPartInfo> parts = new ArrayList<>();
		repo.findAll().forEach(parts::add);
		return parts;
	}

	public CarPartInfo getById(int partId){
		logger.info("Fetting part by id: {}", partId);
		return repo.findById(partId).orElse(null);
	}

	public void deletePart(int partId){
		logger.info("Deleting part: {}", partId);
		repo.deleteById(partId);
	}

	public CarPartInfo updatePart(int partId, int quantity){
		logger.info("Updating part: {} with quantity: {}", partId, quantity);

		CarPartInfo changing = repo.findById(partId).orElse(null);
		if (changing == null){
			logger.warn("Part: {} not found.", partId);
			return null;
		}

		changing.setQuantity(quantity);

		return repo.save(changing);
	}	
}
