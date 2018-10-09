package com.supinfo.gameoflife;

public interface Cell {
    /**
     * Go to the next generation and returns a Cell
     * depending on the provided number of neighbors
     *
     * @param nbNeighbors   the number of neighbors
     * @return  a Cell with an unknown type
     */
    Cell newGeneration(int nbNeighbors);

    /**
     * Returns the String representing the Cell depending on its type
     *
     * @return a String containing a single character
     */
    String getAsString();

    /**
     * @return  a boolean representing the Cell's state
     */
    boolean isAlive();

    /**
     * Creates a new Cell depending on the specified integer.
     * Here are the available values
     * 0 = Dead, 1 = New alive cell, 2 = Old alive cell
     *
     * @param value the integer representing the Cell Type
     * @return      a new Cell
     */
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
