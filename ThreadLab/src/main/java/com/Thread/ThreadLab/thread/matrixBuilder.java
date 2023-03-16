package com.Thread.ThreadLab.thread;

import java.util.LinkedList;
import java.util.Scanner;

import com.Thread.ThreadLab.snapshot.CareTaker;
import com.Thread.ThreadLab.snapshot.Originator;

public class matrixBuilder {
    private Update update = Update.getInstance();
    private LinkedList<Machine> machines;
    private LinkedList<ProductQueue> queues;
   private  Originator originator = new Originator();
    private CareTaker careTaker = new CareTaker();

    public void addProductToFirstQueue(int productId) {
        queues.getFirst().addProduct(productId);
    }
    public void addProducts(int n){
        for (int  i = 0; i < n; i++){
            update.addProduct();
            addProductToFirstQueue(update.getSize());
        }
    }
    public void buildObjects(int[][] adj) {
        originator.setState(adj);
        careTaker.add(originator.saveStateToMemento());

        startSimulation(adj);
    }

    public void startSimulation(int[][] adj) {
        int rows = adj.length;
        int colomns = adj[0].length;
        machines = new LinkedList<Machine>();
        queues = new LinkedList<ProductQueue>();
        for(int i = 0; i<rows;i++) {
            queues.add(new ProductQueue(i+1, new LinkedList<Integer>(), new LinkedList<Machine>()));
        }
        for(int i = 0; i<colomns;i++) {
            machines.add(new Machine(null, i+1));
        }
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j <colomns; j++) {
                if(adj[i][j] == 1) {
                    queues.get(i).addMachine(machines.get(j));
                    machines.get(j).addBeforeQUeue(queues.get(i));
                }else if(adj[i][j] == 2){
                    machines.get(j).setNextQueue(queues.get(i));
                }
            }
        }
        for(int i = 0; i < queues.size(); i++) {
            System.out.println("Queue " + queues.get(i).getQueueId() + " machines:");
            for(int  j = 0; j<queues.get(i).getMachines().size(); j++) {
                System.out.print(" m " + queues.get(i).getMachines().get(j).getMachineId());
            }
            System.out.print("\n");
        }
        for(int i = 0; i < machines.size(); i++) {
            System.out.println("Machine " + machines.get(i).getMachineId() + " next queue: " + machines.get(i).getNextQueue().getQueueId());
        }
        for(int  i = 0; i< machines.size(); i++) {
            System.out.print("m " + machines.get(i).getMachineId());
            for(int j = 0; j< machines.get(i).getBedforeQueues().size(); j++) {
                System.out.print(" q " + machines.get(i).getBedforeQueues().get(j).getQueueId());
            }
            System.out.print("\n");
        }
        update.reset();
        update.setProducts(0);
        update.sendProducts();
        for(int i = 0; i < colomns; i++) {
            machines.get(i).start();
        }
        for(int i = 0;i < rows; i++) {
            queues.get(i).start();
        }

    }
    public LinkedList<String> sendUpdatedList(){
        return update.getProducts();
    }
    public void restart(){
        originator.getStateFromMemento(careTaker.get(careTaker.getSize()-1));
        buildObjects(originator.getState());
    }
}
