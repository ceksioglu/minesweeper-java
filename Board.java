package minesweeper;

import java.util.Random;

public class Board {

    private int height, width;
    private int [][] board;
    private boolean[][] mines;
    private boolean[][] flagged;
    private boolean [][] cleared;

    public Board(int size){
        this.height = size;
        this.width = size;
        this.board = new int[width][height];
        this.mines = new boolean[width][height];
        this.flagged = new boolean[width][height];
        this.cleared = new boolean[width][height];
        boardInit();
        placeMines();
    }

    private void placeMines() {
        Random random = new Random();
        int mineCount = (int) (width * height * 0.20);
    }

    private void boardInit() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                mines[i][j] = false;
                flagged[i][j] = false;
                cleared[i][j] = false;
                board[i][j] = 0;
            }
        }


    }
    public static void main (String[]args){

    }
}
