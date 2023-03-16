package com.Thread.ThreadLab.thread;

import java.util.LinkedList;

public class ProductQueue extends Thread{
    private int queueId;
    private LinkedList<Integer> products = new LinkedList<Integer>();
    LinkedList<Machine> machines;
    public ProductQueue(int id, LinkedList<Integer> products, LinkedList<Machine> machines) {
        this.queueId = id;
        this.products = products;
        this.machines = machines;
    }
    public void addMachine(Machine machine) {
        this.machines.add(machine);
    }
    public void print() {
        if(products.isEmpty()) {
            System.out.println("[]");
            return;
        }
        try {
            System.out.print("[" + products.get(0));
            for (int i = 1; i < products.size(); i++) {
                System.out.print(", " + products.get(i));
            }
            System.out.print("]\n");
        } catch (Exception e) {
            System.out.println("[]");
            return;
        }
    }
    public void run() {
        synchronized (this) {
            while(true) {
                if(!this.products.isEmpty()) {
                    for (Machine machine : this.machines) {
                        if (machine.isReady()) {
                            synchronized (machine) {
                                System.out.println("Queue " + this.queueId + " sent product " + products.getFirst() + " to machine " + machine.getMachineId());
                                print();
                                if(machine.getCurrentProductId() == -1){
                                    machine.setIsReady(false);
                                    machine.serCurrentProduct(this.products.getFirst());
                                    this.products.removeFirst();
                                }else {
                                    System.out.println("Machine stolen");
                                    continue;
                                }
                                print();
                                machine.notifyAll();

                                if(products.isEmpty())
                                    break;

                            }

                        }
                    }
                }
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public int getQueueId() {
        return queueId;
    }
    public void setId(int id) {
        this.queueId = id;
    }
    public LinkedList<Integer> getProducts() {
        return products;
    }
    public void setProducts(LinkedList<Integer> products) {
        this.products = products;
    }
    public void removeProduct(int productId) {
        for(int i = 0; i<products.size(); i++) {
            if(products.get(i) == productId) {
                products.remove(i);
                return;
            }
        }
    }
    public LinkedList<Machine> getMachines() {
        return machines;
    }
    public void addProduct(int productId) {
        products.add(productId);
        synchronized (this) {
            this.notify();
        }
    }
}
