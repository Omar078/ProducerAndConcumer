package com.Thread.ThreadLab.snapshot;

import java.util.LinkedList;

public class CareTaker {
    private LinkedList<Memento> mementosList = new LinkedList<Memento>();
    public void add(Memento state) {
        mementosList.add(state);
    }
    public Memento get(int index) {
        return mementosList.get(index);
    }
    public int getSize() {
        return mementosList.size();
    }
}
