package com.Thread.ThreadLab.Service;

import com.Thread.ThreadLab.thread.matrixBuilder;
import org.springframework.stereotype.Service;

import java.util.LinkedList;

@Service
public class ThreadService {

    private matrixBuilder matrixbuilder = new matrixBuilder();

    public int[][] convertMatrix(String[] list, int nq, int nm){
        int[][] adj = new int [nq][nm];
        for (int i = 0; i< list.length; i++){
            String s = list[i];
            String[] array = s.split(",");
            int source = Integer.parseInt(array[0]) - 10;
            int destination = Integer.parseInt(array[1]) - 10;
            System.out.println("source " + source + " dest" + destination);
            if(source%2 == 1) {
                adj[destination/10 - 1][source/10 - 1] = 2;
                System.out.println("Element " + (destination/10 - 1) + " " + (source/10 - 1) + " = 2");
            }else {
                adj[source/10 - 1][destination/10 - 1] = 1;
                System.out.println("Element " + (source/10 - 1) + " " + (destination/10 - 1) + " = 1");
            }


            System.out.println(source + " " + destination);
        }

        for(int i = 0; i<adj.length;i++) {
            System.out.print("[ " + adj[i][0]);
            for(int j = 1; j < adj[0].length; j++) {
                System.out.print(", " + adj[i][j]);
            }
            System.out.print("]\n");
        }
        return adj;
    }
    public void startSimulation(String[] list, int nq, int nm){
        int[][] adjList = convertMatrix(list, nq, nm);
        matrixbuilder.buildObjects(adjList);
    }
    public LinkedList<String> sendUpdate(){
        return matrixbuilder.sendUpdatedList();
    }
    public void addProduct(int n){
        matrixbuilder.addProducts(n);
    }
    public void restart (){matrixbuilder.restart();}

}
