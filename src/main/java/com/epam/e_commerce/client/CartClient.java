package com.epam.e_commerce.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "CART-MANAGEMENT")
public interface CartClient {
	@DeleteMapping("/cart/{productId}")
	void deleteByProductIdFromCart(@PathVariable("productId") Integer productId);
	@DeleteMapping("/purchases/{productId}")
	void deleteByProductIdFromPurchase(@PathVariable("productId") Integer productId);
}
