package com.javarush.lections.lection0303;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {

    private final UserService us = new UserService();

    @Test
    void findByIdShouldReturnUser() {
        User actual = us.findById(123);
        User expected = new User("John Doe2");
        // check all field in separate method
        //assertEqualsUser(expected, actual);

        //if exist, use equals() + hashCode() from User
        assertEquals(expected, actual);
    }

    void assertEqualsUser(User expected, User actual) {
        assertEquals(expected.getName(), actual.getName());
    }

}