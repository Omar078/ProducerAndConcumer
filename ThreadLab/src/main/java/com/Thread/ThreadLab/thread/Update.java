package com.Thread.ThreadLab.thread;

import java.util.LinkedList;

public class Update {
    private LinkedList<String> products;
    private static Update UPDATE = null;

    public static Update getInstance() {
        if(UPDATE == null) {
            UPDATE = new Update();
        }
        return UPDATE;
    }
    public void reset() {
        products = new LinkedList();
    }
    public void addProduct() {
        products.add("Q1");
    }
    public void setProducts(int n) {
        for(int i = 0; i<n; i++) {
            products.add(i, "Q1");
        }
    }
    public void updateProduct(int productId, int destination,String type) {
        type += destination;
        products.set(productId - 1, type);
        sendProducts();
    }
    public void sendProducts() {
        if(products.isEmpty()) {
            System.out.println("****************[]****************");
            return;
        }
        System.out.print("****************[" + products.get(0));
        for (int i = 1; i < products.size(); i++) {
            System.out.print(", " + products.get(i));
        }
        System.out.print("]****************\n");
    }
    public int getSize() {
        return products.size();
    }
    public LinkedList<String> getProducts(){
        return products;
    }
}
