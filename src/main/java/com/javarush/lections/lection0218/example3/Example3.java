package com.javarush.lections.lection0218.example3;

public class Example3 {

    static void main(String[] args) {
    }
}

//set dafault values (min or max or etc)
@Person(live = 90, strength = 10, defence = 99)
class Elf{

}

@interface Person {
    String name() default "";

    int defence();

    int live();

    int strength();
}