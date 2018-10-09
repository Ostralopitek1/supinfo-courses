package com.supinfo.gameoflife;

import java.util.Random;

public class World {
    private Cell[][] grid;
    private int generation;

    public World(Cell[][] grid) {
        this.setGrid(grid);
    }

    public World (int nbRows, int nbColumns) {
        this(getRandomGrid(nbRows, nbColumns));
    }

    /**
     * Checks each cell around the specified location and
     * calculate the number of cells alive.
     *
     * @param row   a row index on the world's grid
     * @param col   a column index on the world's grid
     * @return      the number of neighbors
     */
    private int getNeighbors(int row, int col) {
        int maxRow = this.getRows() - 1;
        int startRow = row - 1 >= 0      ? row - 1 : 0;
        int endRow   = row + 1 <= maxRow ? row + 1 : maxRow;
        int maxCol = this.getColumns() - 1;
        int startCol = col - 1 >= 0      ? col - 1 : 0;
        int endCol   = col + 1 <= maxCol ? col + 1 : maxCol;
        int neighbors = 0;
        for (int r = startRow; r <= endRow; r++) {
            for (int c = startCol; c <= endCol; c++) {
                if (r == row && c == col)
                    continue;
                if (this.getCell(r, c).isAlive()) {
                    neighbors++;
                }
            }
        }
        return neighbors;
    }

    /**
     * Go to the next generation of each cell on the grid
     */
    public void newGeneration() {
        Cell[][] nextGrid = new Cell[this.getRows()][this.getColumns()];
        for (int r = 0; r < this.getRows(); r++) {
            for (int c = 0; c < this.getColumns(); c++) {
                int neighbors = this.getNeighbors(r, c);
                nextGrid[r][c] = this.getCell(r, c).newGeneration(neighbors);
            }
        }
        this.setGrid(nextGrid);
        this.generation++;
    }

    /**
     * @return  the number of rows on the grid
     */
    private int getRows() {
        return this.getGrid().length;
    }

    /**
     * @return  the number of columns on the grid
     */
    private int getColumns() {
        return this.getGrid().length > 0 ? this.getGrid()[0].length : 0;
    }

    /**
     * Returns the formatted grid with the current generation
     *
     * @return  the generated String
     */
    @Override
    public String toString() {
        String val = "Generation " + generation + ":\n";
        for (int r = 0; r < this.getRows(); r++) {
            for (int c = 0; c < this.getColumns(); c++) {
                val += this.getCell(r, c).getAsString() + " ";
            }
            val += "\n";
        }
        return val;
    }

    /**
     * Generates a random grid with cells of different
     * types to initialize the world
     *
     * @param rows      the number of rows on the newly created grid
     * @param columns   the number of columns on the newly created grid
     * @return          a new grid with random cells
     */
    private static Cell[][] getRandomGrid(int rows, int columns) {
        Cell[][] grid = new Cell[rows][columns];
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                grid[r][c] = getRandomCell();
            }
        }
        return grid;
    }

    /**
     * Generates a new cell from a random int
     *
     * @return  a classic Cell of unknown type
     */
    private static Cell getRandomCell() {
        Random generator = new Random();
        int rand = generator.nextInt(3);
        Cell cell;
        cell = Cell.getCellFromInt(rand);
        return cell;
    }

    /**
     * Retrieves a Cell from specified indices
     *
     * @param row   the row index of the Cell
     * @param col   the column index of the Cell
     * @return      the Cell at the specified location
     */
    public Cell getCell(int row, int col) {
        return this.getGrid()[row][col];
    }

    public Cell[][] getGrid() {
        return grid;
    }

    public void setGrid(Cell[][] grid) {
        this.grid = grid;
    }
}
