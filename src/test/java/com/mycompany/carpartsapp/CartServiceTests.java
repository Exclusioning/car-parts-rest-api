package com.mycompany.carpartsapp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;
import org.springframework.transaction.annotation.Transactional;
import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class CartServiceTest {
	
	@Autowired
	private CartService cartService;

	@Autowired
	private CartRepository cartRepository;

	@Test
	void testAddToCart(){
		CartItem item = cartService.addToCart(1, 2, "MillsZack1");
		assertNotNull(item);
		assertEquals(2, item.getQuantity());
	}

	@Test
	void testDuplicateItemAdd(){
		cartService.addToCart(1, 2, "MillsZack2");
		CartItem newItem = cartService.addToCart(1, 65, "MillsZack2");
                assertEquals(67, newItem.getQuantity());
	}
	
	@Test
	void testUpdateItem(){
		cartService.addToCart(1, 2, "MillsZack3");
                CartItem updatedItem = cartService.addToCart(1, 65, "MillsZack3");
                assertNotNull(updatedItem);
		assertEquals(67, updatedItem.getQuantity());
	}

	@Test
	void testDeleteFromCart(){
		cartService.addToCart(1, 2, "MillsZack4");
		cartService.deleteFromCart(1, "MillsZack4");
		List<Map<String, Object>> cart = cartService.getCart("MillsZack4");
		assertEquals(0, cart.size());
	}

	@Test
	void testDeleteMissingItem(){
		cartService.deleteFromCart(912340, "MillsZack5");
		assertTrue(true);
	}

	@Test
	void testUpdateMissingCartItem(){
		CartItem nonexistent = cartService.updateCart(923242, 92, "MillsZack6");
		assertNull(nonexistent);
	}

	@Test
	void testGetEmptyCart(){
	List<Map<String, Object>> cart = cartService.getCart("nonexistent");
	assertEquals(0, cart.size());
	}
}
