package com.mycompany.carpartsapp;

import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface CartRepository extends CrudRepository<CartItem, Integer>{
	
	List<CartItem> findBySessionId(String session);
	CartItem findByPartIdAndSessionId(int partId, String sessionId);
}
