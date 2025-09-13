package com.gl.app.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service", url = "http://localhost:9000")
public interface UserClient {
    @GetMapping("/api/users/{id}/exists")
    public Boolean existsByUserId(@PathVariable("id") Long userId);
}

