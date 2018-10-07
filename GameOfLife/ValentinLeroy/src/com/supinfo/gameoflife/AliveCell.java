package com.supinfo.gameoflife;

public class AliveCell implements Cell {
    public static enum CellState {
        NEW,
        OLD
    }
    private CellState state;

    public AliveCell() {
        this(CellState.NEW);
    }

    public AliveCell(CellState state) {
        this.setState(state);
    }

    public CellState getState() {
        return state;
    }

    public void setState(CellState state) {
        this.state = state;
    }

    @Override
    public Cell newGeneration(int nbNeighbors) {
        if (nbNeighbors < 2 || nbNeighbors > 3) {
            return new DeadCell();
        }
        this.setState(CellState.OLD);
        return this;
    }

    @Override
    public String getAsString() {
        return this.state == CellState.NEW ? "0" : "+";
    }

    @Override
    public boolean isAlive() {
        return true;
    }
}
