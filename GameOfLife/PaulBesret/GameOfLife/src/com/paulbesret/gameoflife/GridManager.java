package com.paulbesret.gameoflife;

public final class GridManager {

    private static int rows = 10;
    private static int columns = 10;
    private static Cell[][] grid = initialize();

    public static void run(){
        Cell[][] tempGrid = duplicate(grid);
        for (int i = 1; i < rows - 1; i++) {
            for (int j = 1; j < columns - 1; j++){
                int populatedNeighbors = grid[i][j].checkNeighbors(grid);
                if(grid[i][j].getType() == CellType.Populated){
                    if(populatedNeighbors == 1 || populatedNeighbors == 0){
                        // die
                        tempGrid[i][j].setType(CellType.Unpopulated);
                    } else if (populatedNeighbors >= 4){
                        // die
                        tempGrid[i][j].setType(CellType.Unpopulated);
                    } else if (populatedNeighbors == 2 || populatedNeighbors == 3){
                        // Survive
                        tempGrid[i][j].setType(CellType.Populated);
                    }
                } else if(populatedNeighbors == 3){
                    // born
                    tempGrid[i][j].setType(CellType.Populated);
                }
            }
        }
        System.out.println();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e){
            System.err.println(e);
        }
        grid = tempGrid;
        print(grid);
    }

    // Initialize a grid of cells
    private static Cell[][] initialize(){
        Cell[][] grid = new Cell[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++){
                grid[i][j] = new Cell(i,j);
            }
        }

        GridManager.setCellContent(grid,4,5, CellType.Populated);
        GridManager.setCellContent(grid,5,5, CellType.Populated);
        GridManager.setCellContent(grid,5,6, CellType.Populated);
        GridManager.setCellContent(grid,5,4, CellType.Populated);
        GridManager.setCellContent(grid,6,5, CellType.Populated);
        print(grid);
        return grid;
    }

    // Duplicate a grid
    private static Cell[][] duplicate(Cell[][] grid){
        Cell[][] duplicatedGrid = new Cell[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++){
                duplicatedGrid[i][j] = new Cell(i,j);
                duplicatedGrid[i][j].setType(grid[i][j].getType());
            }
        }
        return duplicatedGrid;
    }

    // Print the content of a grid
    private static void print(Cell[][] grid){
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++){
                System.out.print(grid[i][j].getType() == CellType.Populated ? "O" : "X");
            }
            System.out.println();
        }
    }

    // Setup content of a given cell
    private static void setCellContent(Cell[][] grid, int x, int y, CellType type){
        grid[x][y].setType(type);
    }
}
