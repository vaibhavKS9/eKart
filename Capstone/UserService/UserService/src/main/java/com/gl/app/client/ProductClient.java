package com.gl.app.client;

import com.gl.app.dto.ProductDto;
import com.gl.app.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "product-service", url = "http://localhost:9002")
public interface ProductClient {
    @GetMapping("/api/users/{userId}/products")
    public List<ProductDto> getProducts(@PathVariable("userId") Long userId);

}

