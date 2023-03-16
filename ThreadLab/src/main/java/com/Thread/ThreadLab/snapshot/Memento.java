package com.Thread.ThreadLab.snapshot;

public class Memento {
    private int[][] state;
    public Memento(int[][] state){
        this.state = state;
    }
    public int[][] getState() {
        return state;
    }
}
