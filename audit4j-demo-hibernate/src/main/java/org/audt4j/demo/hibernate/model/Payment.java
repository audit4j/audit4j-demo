package org.audt4j.demo.hibernate.model;

import java.util.ArrayList;
import java.util.List;

import org.audt4j.demo.hibernate.service.Item;

public class Payment {

    private Double totalPrize;
    
    private String user;

    private List<Item> items = new ArrayList<Item>();

    public Payment(String user, Double totalPrize, Item item){
        this.user = user;
        this.totalPrize = totalPrize;
        this.items.add(item);
    }
    
    public Payment(){}

    public Double getTotalPrize() {
        return totalPrize;
    }

    public void setTotalPrize(Double totalPrize) {
        this.totalPrize = totalPrize;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
