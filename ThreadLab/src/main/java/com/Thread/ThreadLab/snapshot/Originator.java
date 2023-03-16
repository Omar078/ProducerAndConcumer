package com.Thread.ThreadLab.snapshot;

public class Originator {
    private int[][] state;

    public void setState(int[][] state){
        this.state = state;
    }
    public int[][] getState() {
        return state;
    }
    public Memento saveStateToMemento() {
        return new Memento(state);
    }
    public void getStateFromMemento(Memento memento){
        state = memento.getState();
    }
}
