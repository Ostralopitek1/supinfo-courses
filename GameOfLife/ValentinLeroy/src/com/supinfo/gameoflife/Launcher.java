package com.supinfo.gameoflife;

public class Launcher {

    public static void main(String[] args) {
        // World world = new World(10, 10);
        World world = getWorldFromMap(new int[][]{
                {1,0,1,1,0,1,1,0,1,0},
                {0,1,0,1,0,0,1,0,1,0},
                {1,0,0,0,1,1,0,0,1,0},
                {1,0,0,0,0,0,1,1,1,0},
                {0,0,1,1,0,0,1,0,1,0},
                {0,0,1,0,1,0,0,0,1,0},
                {1,1,1,1,1,1,0,1,1,1},
                {1,0,1,1,0,0,0,0,0,1},
                {1,1,1,1,1,0,0,1,1,0},
                {1,0,0,0,0,0,1,0,1,0}
        });
        run(world, 20);
    }

    // 0 = Dead, 1 = New alive cell, 2 = Old alive cell
    private static World getWorldFromMap(int[][] map) {
        Cell[][] grid = new Cell[map.length][map[0].length];
        for (int r = 0; r < map.length; r++) {
            for (int c = 0; c < map[0].length; c++) {
                grid[r][c] = Cell.getCellFromInt(map[r][c]);
            }
        }
        return new World(grid);
    }

    private static void run(World world, int generations) {
        System.out.println(world);
        for (int i = 0; i < generations; i++) {
            world.newGeneration();
            System.out.println(world);
        }
    }
}
