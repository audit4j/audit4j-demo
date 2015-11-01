package org.audt4j.demo.spring.service;


public class Item {

    private String name;
    
    private Double value;

    public Item(){
        
    }
    
    public Item(String name, Double value){
        this.name=name;
        this.value = value;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }
}
