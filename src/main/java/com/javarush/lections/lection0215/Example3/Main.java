package com.javarush.lections.lection0215.Example3;

public class Main {
    public static void main(String[] args) {
        Animal.builder().withName("default animal").build();

        Dog defaultDog = Dog.builder().withWeight(2)
                .withName("default dog").build();
    }
}
