package com.mycompany.carpartsapp;
import java.util.List;
import java.util.Map;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/cart")
public class CartController{
	private static final Logger logger = LoggerFactory.getLogger(CartController.class);
	private CartService cartService;
	public CartController(CartService cartService){
		this.cartService = cartService;
	}

	@PostMapping("/{partId}/{quantity}")
	public CartItem addToCart(@PathVariable int partId, @PathVariable int quantity, @AuthenticationPrincipal OAuth2User user){
		String sessionId = user.getAttribute("login");
		logger.info("POST /cart/{}/{} adding item to cart : User {}", partId, quantity, sessionId);
		return cartService.addToCart(partId, quantity, sessionId);
	}

	@GetMapping
	public List<Map<String, Object>> getCart(@AuthenticationPrincipal OAuth2User user){
		String sessionId = user.getAttribute("login");
		logger.info("GET /cart : User {}", sessionId);
		return cartService.getCart(sessionId);
	}

	@DeleteMapping("/{partId}")
	public void deleteFromCart(@PathVariable int partId, @AuthenticationPrincipal OAuth2User user){
		String sessionId = user.getAttribute("login");
		logger.info("DELETE /cart/{} deleting from cart : User {}", partId, sessionId);
		cartService.deleteFromCart(partId, sessionId);
	}

	@PutMapping("/{partId}/{quantity}")
	public CartItem updateCart(@PathVariable int partId, @PathVariable int quantity, @AuthenticationPrincipal OAuth2User user){
		String sessionId = user.getAttribute("login");
		logger.info("PUT /cart/{}/{} updating item in users cart : User {}", partId, quantity, sessionId);
		return cartService.updateCart(partId, quantity, sessionId);
	}
}
