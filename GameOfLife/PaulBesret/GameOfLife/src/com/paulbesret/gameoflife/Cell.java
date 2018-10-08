package com.paulbesret.gameoflife;

public class Cell {

    private CellType type = CellType.Unpopulated;
    private int x;
    private int y;

    Cell(int x, int y){
        this.x = x;
        this.y = y;
    }

    CellType getType() {
        return type;
    }

    void setType(CellType type) {
        this.type = type;
    }

    int checkNeighbors(Cell[][] grid){
        int populatedNeighbors = 0;
        Cell[] neighbors = {
                grid[x-1][y-1], grid[x-1][y], grid[x-1][y+1],
                grid[x][y-1],/* grid[x][y] */ grid[x][y+1],
                grid[x+1][y-1], grid[x+1][y], grid[x+1][y+1]
        };
        for(Cell neighbor : neighbors){
            populatedNeighbors += neighbor.getType() == CellType.Populated ? 1 : 0;
        }
        return populatedNeighbors;
    }
}
