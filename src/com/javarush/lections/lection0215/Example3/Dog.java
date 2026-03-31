package com.javarush.lections.lection0215.Example3;

// Dog realization
class Dog extends Animal{
    private final int weight;

    protected Dog(Builder builder){
        super(builder);
        this.weight = builder.weight;
    }

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder extends Animal.Builder<Builder>{
        private int weight;

        protected Builder(){
        }

        public Builder withWeight(int weight){
            this.weight = weight;
            return self();
        }

        @Override
        public Dog build(){
            return new Dog(this);
        }
    }

}

//class Labrador extends Dog{
//    private final int value;
//
//    private Labrador(Builder builder){
//        super(builder);
//        this.value = builder.value;
//    }
//
//    public static class Builder extends Dog.Builder<Builder>{
//        private int value;
//    }
//
//}------[[[[[[[
