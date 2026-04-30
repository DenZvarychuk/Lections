package com.javarush.lections.lection0215.Example3;

// Animal realization
class Animal{
    private final String name;

    protected Animal(Builder builder){
        this.name = builder.name;
    }

    public static Builder builder() {
        return new Builder();
    }



    // nested class Builder
    public static class Builder<SELF extends Builder<SELF>>{
        private String name;

        protected Builder(){
        }

        public SELF withName(String name){
            this.name = name;
            return self();
        }
        @SuppressWarnings("unchecked")
        public SELF self(){
            return (SELF) this;
        }

        public Animal build(){
            return new Animal(this);
        }
    }
}
