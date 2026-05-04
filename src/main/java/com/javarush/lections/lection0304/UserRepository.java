package com.javarush.lections.lection0304;

import java.util.Optional;

public interface UserRepository {

    Optional<User> findByEmail(String email);

    void save(User user);
}
