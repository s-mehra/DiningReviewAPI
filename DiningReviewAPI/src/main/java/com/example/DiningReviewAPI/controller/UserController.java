package com.example.DiningReviewAPI.controller;

import com.example.DiningReviewAPI.model.Restaurant;
import com.example.DiningReviewAPI.model.User;
import com.example.DiningReviewAPI.repository.UserRepository;

import org.springframework.http.HttpStatus;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addUser(@RequestBody User user){
        System.out.println(user.getName());
        System.out.println(user.getId());
        System.out.println(user.getCity());
        System.out.println(user.getEggAllergies());

        validateNewUser(user);

        userRepository.save(user);
    }


    @GetMapping("/{name}")
    public User getUser(@PathVariable String name){
        validateUserName(name);

        Optional<User> optionalExistingUser = userRepository.findUserByName(name);
        if(!optionalExistingUser.isPresent()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        User existingUser = optionalExistingUser.get();
        existingUser.setId(null);

        return existingUser;
    }

    @PutMapping("/{name}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateUserInfo(@PathVariable String name, @RequestBody User user){
        validateUserName(name);

        Optional<User> optionalExistingUser = userRepository.findUserByName(name);
        if (optionalExistingUser.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        User existingUser = optionalExistingUser.get();
        copyUserInfoFrom(user, existingUser);

        userRepository.save(existingUser);
    }

    @GetMapping
    public Iterable<User> getAllUsers(){
        return userRepository.findAll();
    }

    private void copyUserInfoFrom(User newUser, User existingUser){
        if (ObjectUtils.isEmpty(newUser.getName())) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);
        }

        if (!ObjectUtils.isEmpty(newUser.getCity())) {
            existingUser.setCity(newUser.getCity());
        }

        if (!ObjectUtils.isEmpty(newUser.getState())) {
            existingUser.setState(newUser.getState());
        }

        if (!ObjectUtils.isEmpty(newUser.getZipcode())) {
            existingUser.setZipcode(newUser.getZipcode());
        }

        if (!ObjectUtils.isEmpty(newUser.getPeanutAllergies())) {
            existingUser.setPeanutAllergies(newUser.getPeanutAllergies());
        }

        if (!ObjectUtils.isEmpty(newUser.getDairyAllergies())) {
            existingUser.setDairyAllergies(newUser.getDairyAllergies());
        }

        if (!ObjectUtils.isEmpty(newUser.getEggAllergies())) {
            existingUser.setEggAllergies(newUser.getEggAllergies());
        }
    }

    private void validateNewUser(User user) {
        if (ObjectUtils.isEmpty(user.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User name cannot be empty");
        }

        // You can add more validation checks for other user fields if needed

        Optional<User> existingUser = userRepository.findUserByName(user.getName());
        if (existingUser.isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "User with the same name already exists");
        }
    }


    private void validateUser(User user){
        validateUserName(user.getName());

        Optional<User> existingUser = userRepository.findUserByName(user.getName());

        if(existingUser.isPresent()){
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }
    }

    private void validateUserName(String userName){
        if (ObjectUtils.isEmpty(userName)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

}
