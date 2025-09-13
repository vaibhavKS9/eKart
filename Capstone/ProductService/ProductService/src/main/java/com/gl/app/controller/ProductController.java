package com.gl.app.controller;

import com.gl.app.dto.ProductDto;
import com.gl.app.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users/{userId}/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<ProductDto> addNewProduct(@PathVariable("userId") Long userId, @Valid @RequestBody ProductDto productDto){

        return new ResponseEntity<>(productService.addNewProduct(userId,productDto),HttpStatus.CREATED);

    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> getByUserId(@PathVariable("userId") Long userId){
        return new ResponseEntity<>(productService.getByUserId(userId),HttpStatus.OK);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductDto> getByUserIdAndProductId(@PathVariable("userId") Long userId, @PathVariable("productId") Long productId){
        return new ResponseEntity<>(productService.getByIdAndUserId(userId,productId),HttpStatus.OK);
    }



}

