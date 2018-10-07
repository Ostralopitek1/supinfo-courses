package com.supinfo.gameoflife;

public class DeadCell implements Cell {
    @Override
    public Cell newGeneration(int nbNeighbors) {
        if (nbNeighbors == 3) {
            return new AliveCell(AliveCell.CellState.NEW);
        }
        return this;
    }

    @Override
    public String getAsString() {
        return "-";
    }

    @Override
    public boolean isAlive() {
        return false;
    }
}
