package com.javarush.lections.lection0217.example3;

class UserServiceImpl implements UserService {

    @Override
    public User getById(int id) {
        return new User(id);
    }
}
