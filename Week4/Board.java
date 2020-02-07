package Week4;

import java.util.Iterator;

public class Board {
    final int[][] tilesFinal = {
            { 1, 2, 3 },
            { 4, 5, 6 },
            { 7, 8, -1 }


    };
    private int n = 3;
    private int[][] tiles = new int[3][3];


    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    public Board(int[][] tiles) {
        // this.tiles = tiles;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                this.tiles[i][j] = tiles[i][j];

            }
        }


    }

    // unit testing (not graded)
    public static void main(String[] args) {
        int tiles[][] = {
                { 8, 1, 3 },
                { 4, -1, 2 },
                { 7, 6, 5 },

                };

        Board board = new Board(tiles);
        System.out.println(board);
        System.out.println("Manhattan Distance: " + board.manhattan());

        int tilesY[][] = {
                { 8, 1, 3 },
                { 4, -1, 2 },
                { 7, 6, 5 },

                };
        Board boardY = new Board(tilesY);
        if (board.equals(boardY)) System.out.println("boards are equal");
        else System.out.println("boards are NOT equal");

        if (board.isGoal()) System.out.println("goal board recahed");
        else System.out.println("goals board not reached");

        Board twinBoard = board.twin();

        System.out.println("twin board:\n" + twinBoard);
        Iterator<Board> boardIterator = board.neighbors().iterator();
        while (boardIterator.hasNext()) {
            System.out.println(boardIterator.next());
        }

    }

    // sum of Manhattan distances between tiles and goal
    public int manhattan() {
        int manhattanDistance = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int num = tiles[i][j];
                manhattanDistance += getManhattanDistance(i, j, num);
            }
        }
        return manhattanDistance;
    }

    // does this board equal y?
    public boolean equals(Object y) {
        return this.toString().equals(y.toString());
    }

    // string representation of this board
    public String toString() {
        String boardStr = "";
        System.out.println(tiles.length);
        for (int[] tileA : tiles) {
            for (int tile : tileA) {
                boardStr += tile + " ";
            }
            boardStr += "\n";
        }
        return boardStr;
    }

    // is this board the goal board?
    public boolean isGoal() {
        return this.equals(tilesFinal);

    }

    // a board that is obtained by exchanging any pair of tiles
    public Board twin() {
        Board twinBoard = new Board(tiles);
        int i1 = 0, j1 = 0, i2 = 0, j2 = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int num = tiles[i][j];
                if (num != -1) {
                    if (tiles[i][j] != 0) {
                        i1 = i;
                        j1 = j;
                    }
                    else {
                        i2 = i;
                        j2 = j;
                        break;
                    }
                }
            }
        }

        twinBoard.swap(i1, j1, i2, j2);
        return twinBoard;

    }

    // all neighboring boards
    public Iterable<Board> neighbors() {
        Stack<Board> boardStack = new Stack<>();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int num = tiles[i][j];
                if (num == -1) {
                    int i1 = 0, j1 = 0;

                    i1 = i - 1;
                    j1 = j;
                    Board board = swapWithEmpty(i, j, i1, j1);
                    if (board != null)
                        boardStack.push(board);

                    i1 = i + 1;
                    j1 = j;
                    Board board1 = swapWithEmpty(i, j, i1, j1);
                    if (board1 != null)
                        boardStack.push(board1);

                    i1 = i;
                    j1 = j - 1;
                    Board board2 = swapWithEmpty(i, j, i1, j1);
                    if (board2 != null)
                        boardStack.push(board2);

                    i1 = i;
                    j1 = j + 1;
                    Board board3 = swapWithEmpty(i, j, i1, j1);
                    if (board3 != null)
                        boardStack.push(board3);
                }
            }
        }
        return boardStack;
    }

    private int getManhattanDistance(int i, int j, int num) {
        int manhattanDistance = 0;
        int row = (num - 1) / 3;
        int col = (num - 1) % 3;
        if (num != -1) {
            if (row > i) {
                manhattanDistance += row - i;
            }
            else {
                manhattanDistance += i - row;
            }

            if (col > j) {
                manhattanDistance += col - j;
            }
            else {
                manhattanDistance += j - col;
            }
        }
        return manhattanDistance;
    }

    private void swap(int i1, int j1, int i2, int j2) {
        int temp = 0;
        temp = tiles[i1][j1];
        tiles[i1][j1] = tiles[i2][j2];
        tiles[i2][j2] = temp;

    }

    private Board swapWithEmpty(int i, int j, int i1, int j1) {
        if ((i1 >= 0 && i1 < n) && (j1 >= 0 && j1 < n)) {
            Board neighbourBoard = new Board(tiles);
            neighbourBoard.swap(i, j, i1, j1);
            return neighbourBoard;
        }
        return null;
    }

    // board dimension n
    public int dimension() {
        return 0;
    }

    // number of tiles out of place
    public int hamming() {


        return 0;
    }
}