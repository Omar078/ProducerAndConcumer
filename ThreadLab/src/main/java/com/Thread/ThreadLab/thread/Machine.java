package com.Thread.ThreadLab.thread;

import java.util.LinkedList;
import java.util.Random;

public class Machine extends Thread{
    private int machineId;
    private ProductQueue nextQueue;
    private LinkedList<ProductQueue> beforeQueues = new LinkedList<ProductQueue>();
    private int currentProductId = -1;
    private boolean isReady = true;
    private Random random = new Random();
    private Update update;

    public Machine(ProductQueue nextQueue, int machienId) {
        this.nextQueue = nextQueue;
        this.machineId = machienId;
        update = Update.getInstance();
    }

    public void run() {
        synchronized (this) {
            while(true) {
                try {
                    System.out.println("Waiting" + machineId);
                    this.wait();
                    System.out.println("awaken");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    System.out.println("Executing machine " + machineId + "For product " + currentProductId);
                    update.updateProduct(currentProductId, getMachineId(), "M");
                    Thread.sleep(random.nextInt(10000) + 100);
                    System.out.println("Finished machine " + machineId);
                } catch (InterruptedException e) {}
                System.out.println("machine " + machineId + " added product " + currentProductId + " to queue " + nextQueue.getQueueId());
                update.updateProduct(currentProductId, nextQueue.getQueueId(), "Q");
                nextQueue.addProduct(currentProductId);
                currentProductId = -1;
                System.out.println("Next queue " + nextQueue.getQueueId() + ":");
                nextQueue.print();
                setIsReady(true);
                notifyQueues();
            }
        }
    }
    public void addBeforeQUeue(ProductQueue queue) {
        this.beforeQueues.add(queue);
    }
    public void notifyQueues() {
        for (ProductQueue queue : beforeQueues) {
            System.out.println("Machine " + machineId + " activating queue" + queue.getQueueId());
            synchronized (queue) {
                queue.notify();
            }
        }
    }
    public boolean isReady() {
        System.out.println("machine" + machineId + " " + isReady );
        return this.isReady;
    }
    public void setIsReady(boolean status) {
        this.isReady = status;
    }
    public void serCurrentProduct(int productId) {
        this.currentProductId = productId;
    }

    public int getMachineId() {
        return machineId;
    }
    public LinkedList<ProductQueue> getBedforeQueues(){
        return beforeQueues;
    }
    public ProductQueue getNextQueue() {
        return nextQueue;
    }
    public void setNextQueue(ProductQueue queue) {
        this.nextQueue = queue;
    }
    public int getCurrentProductId() {
        return currentProductId;
    }
}
