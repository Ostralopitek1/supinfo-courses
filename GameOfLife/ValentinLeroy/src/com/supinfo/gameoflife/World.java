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

    private int getRows() {
        return this.getGrid().length;
    }

    private int getColumns() {
        return this.getGrid().length > 0 ? this.getGrid()[0].length : 0;
    }

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

    private static Cell[][] getRandomGrid(int rows, int columns) {
        Cell[][] grid = new Cell[rows][columns];
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                grid[r][c] = getRandomCell();
            }
        }
        return grid;
    }

    private static Cell getRandomCell() {
        Random generator = new Random();
        int rand = generator.nextInt(3);
        Cell cell;
        cell = Cell.getCellFromInt(rand);
        return cell;
    }

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
