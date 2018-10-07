package com.supinfo.gameoflife;

public interface Cell {
    Cell newGeneration(int nbNeighbors);
    String getAsString();
    boolean isAlive();

    // 0 = Dead, 1 = New alive cell, 2 = Old alive cell
    public static Cell getCellFromInt(int value) {
        Cell cell;
        switch (value) {
            case 0:
                cell = new DeadCell();
                return cell;
            case 1:
                cell = new AliveCell(AliveCell.CellState.NEW);
                break;
            default:
                cell = new AliveCell(AliveCell.CellState.OLD);
        }
        return cell;
    }
}
