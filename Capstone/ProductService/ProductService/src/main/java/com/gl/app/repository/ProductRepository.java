package com.gl.app.repository;

import com.gl.app.dto.ProductDto;
import com.gl.app.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;

public interface ProductRepository extends JpaRepository<Product,Long> {
    List<Product> findByUserId(Long userId);
    Optional<Product> findByIdAndUserId(Long id,Long userId);
}
