package com.mycompany.carpartsapp;

import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

@RestController
@RequestMapping("/api/parts")
public class PartController{
	private final PartService service;
	
	private static final Logger logger = LoggerFactory.getLogger(PartController.class);

	public PartController(PartService service){
		this.service = service;
	}
	
	@PostMapping
	public void addPart(@RequestParam String name, @RequestParam String price, @RequestParam int quantity){
		logger.info("Post /parts: name={}, price={}, quantity={}", name, price, quantity);
		CarPartInfo part = new CarPartInfo(name, price, quantity, 0);
		service.addPart(part);
	}

	@GetMapping
	public List<CarPartInfo> getParts(){
		logger.info("GET /parts");
		return service.getParts();
	}

	@GetMapping("/{partId}")
        public CarPartInfo partById(@PathVariable int partId){
                logger.info("GET /parts/{}", partId);
		return service.getById(partId);
        }

	@PutMapping("/{partId}/{quantity}")
	public void updatePart(@PathVariable int partId, @PathVariable int quantity){
		logger.info("PUT /parts/{}: updating quantity to {}", partId, quantity);
		service.updatePart(partId, quantity);
	}

	@DeleteMapping("/{partId}")
	public void deletePart(@PathVariable int partId){
		logger.info("DELETE /parts/{} deleting part.", partId);
		service.deletePart(partId);
	}
}
