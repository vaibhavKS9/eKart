package com.gl.app.service;

import com.gl.app.client.ProductClient;
import com.gl.app.dto.ProductDto;
import com.gl.app.dto.UserDto;
import com.gl.app.entity.User;
import com.gl.app.exception.NotFoundException;
import com.gl.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepo;

    @Autowired
    private ProductClient productClient;

    private UserDto entityToDTO(User user) {
        if (user == null) {
            return null;
        }
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setEmail(user.getEmail());
        userDto.setUsername(user.getUsername());
        userDto.setPassword(user.getPassword());
        return userDto;
    }

    private User dtoToEntity(UserDto userDto) {
        if (userDto == null) {
            return null;
        }
        User user=new User();
        user.setEmail(userDto.getEmail());
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        return user;
    }

    public UserDto addNewUser(UserDto userDto){
        User user=dtoToEntity(userDto);
        User savedUser=userRepo.save(user);
        UserDto userDto1=entityToDTO(savedUser);
        userDto1.setProducts(null);
        return userDto1;



    }
    public UserDto findByUserId(Long id){
        User user = userRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found with ID: " + id));

        UserDto userDto = entityToDTO(user);
        List<ProductDto> products;
        try {
            products = productClient.getProducts(id);
        } catch (feign.FeignException.NotFound e) {
            products = List.of();
        }
        userDto.setProducts(products);
        return userDto;

    }
    public Boolean existsByUserId(Long id){
        return userRepo.existsById(id);

    }
    public String deleteUser(Long id){
        if(userRepo.existsById(id)){
            userRepo.deleteById(id);
            return "User Deleted Successfully with ID: "+id;
        }else{
            throw new NotFoundException("User not found with ID: "+id);
        }
    }

}
