package com.javarush.lections.lection0304;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

public class UserService {

    private static final Logger log = LogManager.getLogger(UserService.class);

    private final UserRepository userRepository;
    private final UserValidator userValidator;
    private final UserConvertor userConvertor;

    public UserService(UserRepository userRepository, UserValidator userValidator, UserConvertor userConvertor) {
        this.userRepository = userRepository;
        this.userValidator = userValidator;
        this.userConvertor = userConvertor;
    }

    public User findById(int id) {

        return new User("John Doe");
    }

    public void register(String email, String password) {
        log.debug("user register");

        userValidator.validate(email, password);
        Optional<User> byEmail = userRepository.findByEmail(email);
        if (byEmail.isPresent()) {
            log.info("User with email already exists");
            throw new IllegalArgumentException("User with email already exists");
        }

        User user = new User("Alex");
        user.setEmail(email);
        user.setPassword(password);

        User userToSave = userConvertor.convert(user);

        userRepository.save(userToSave);

    }

    public User findByEmail(String email) {
        return new User("John Doe");
    }

}