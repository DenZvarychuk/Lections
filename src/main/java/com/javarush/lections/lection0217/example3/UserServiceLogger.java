package com.javarush.lections.lection0217.example3;

class UserServiceLogger implements UserService{
    private final UserService userService;

    UserServiceLogger(UserService userService){
        this.userService = userService;
    }

    @Override
    public User getById(int id) {
        long start = System.currentTimeMillis();
        waitMillis(2_000);
        User user = userService.getById(id);
        long end = System.currentTimeMillis();
        System.out.println("Execute time: " + (end-start) + "ms");
        return user;
    }

    private void waitMillis(int mils) {
        try {
            Thread.sleep(mils);
        } catch (InterruptedException e){}
    }
}
