package com.gl.app.service;

import com.gl.app.client.UserClient;
import com.gl.app.dto.ProductDto;
import com.gl.app.entity.Product;
import com.gl.app.exception.NotFoundException;
import com.gl.app.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class ProductService  {
    @Autowired
    private ProductRepository prodRepo;

    @Autowired
    private UserClient userClient;

    private ProductDto entityToDTO(Product product) {
        if(product==null){
            return null;
        }
        ProductDto productDto=new ProductDto();
        productDto.setName(product.getName());
        productDto.setId(product.getId());
        productDto.setCategory(product.getCategory());
        productDto.setDescription(product.getDescription());
        productDto.setPrice(product.getPrice());
        return productDto;

    }

    private Product dtoToEntity(ProductDto productDto) {
        if(productDto==null){
            return null;
        }
        Product product=new Product();
        product.setName(productDto.getName());
        product.setCategory(productDto.getCategory());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        return product;
    }

    public ProductDto addNewProduct(Long userId, ProductDto productDto){
        Boolean userExists=userClient.existsByUserId(userId);
        if(!userExists){
            throw new NotFoundException("User not found with ID: " + userId);
        }
        Product product=dtoToEntity(productDto);
        product.setUserId(userId);
        Product savedProduct=prodRepo.save(product);

        return entityToDTO(savedProduct);

    }
    public ProductDto getByIdAndUserId(Long userId,Long productId){
        return prodRepo.findByIdAndUserId(productId, userId)
                .map(this::entityToDTO)
                .orElseGet(() -> {
                    Boolean userExists=userClient.existsByUserId(userId);
                    if(!userExists){
                        throw new NotFoundException("User not found with ID: " + userId);
                    }else if(prodRepo.existsById(productId)){
                        throw new NotFoundException("You are not authorized to access this product");
                    }else{
                        throw new NotFoundException("Product not found with id : " + productId);
                    }

                });
    }
    public List<ProductDto> getByUserId(Long userId){


        Boolean userExists=userClient.existsByUserId(userId);
        if(!userExists){
            throw new NotFoundException("User not found with ID: " + userId);
        }

        return prodRepo.findByUserId(userId)
                .stream()
                .map(this::entityToDTO)
                .toList();
    }
}


