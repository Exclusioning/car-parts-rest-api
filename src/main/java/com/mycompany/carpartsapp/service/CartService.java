package com.mycompany.carpartsapp;

import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

@Service
public class CartService{
	private final CartRepository cartRepo;
	private final PartRepository partRepo;

	private static final Logger logger = LoggerFactory.getLogger(CartService.class);

	public CartService(CartRepository cartRepo, PartRepository partRepo){
		this.cartRepo = cartRepo;
		this.partRepo = partRepo;
	}
	
	public 	CartItem addToCart(int partId, int quantity, String sessionId){
		logger.info("Adding to cart: partId: {} quantity: {} sessionId: {}", partId, quantity, sessionId);
		
		CartItem item = cartRepo.findByPartIdAndSessionId(partId, sessionId);
		
		if(item != null){
			item.setQuantity(item.getQuantity() + quantity);
			logger.info("SERVICE: Updated to existing part in {} cart", sessionId);
			return cartRepo.save(item);
		}

		CartItem add = new CartItem(partId, quantity, sessionId);
		logger.info("SERVICE: Cart item created in {} cart", sessionId);
		return cartRepo.save(add);
	}

	public CartItem updateCart(int partId, int quantity, String sessionId){
		logger.info("SERVICE: Updating part: {} with quantity: {}", partId, quantity);

		CartItem item = cartRepo.findByPartIdAndSessionId(partId, sessionId);
		
		if(item == null){
			logger.warn("SERVICE: Part: {} not found", partId);
			return null;
		}
	
		item.setQuantity(quantity);
		return cartRepo.save(item);
	}

	public void deleteFromCart(int partId, String sessionId){
		logger.info("SERVICE: Deleting cart item: {} sessionId: {}", partId, sessionId);
		CartItem item = cartRepo.findByPartIdAndSessionId(partId, sessionId);

		if (item != null){
			logger.info("SERVICE: Deleted cart item");
			cartRepo.delete(item);
		}
	}

	public List<Map<String, Object>> getCart(String sessionId){
		logger.info("SERVICE: Getting cart for sessionId: {}", sessionId);
		List<CartItem> cartItems = cartRepo.findBySessionId(sessionId);
		List<Map<String, Object>> parts = new ArrayList<>();
		
		for(CartItem item : cartItems){
			CarPartInfo part = partRepo.findById(item.getPartId()).orElse(null);
			Map<String, Object> show = new HashMap<>();
			show.put("partId", item.getPartId());
			show.put("quantity", item.getQuantity());

			if(part != null){
				show.put("partName", part.getPartName());
				show.put("partPrice", part.getPartPrice());
			}
		
			parts.add(show);
		
		}

		return parts;
	}
}
	
