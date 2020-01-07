public abstract class Piece {

    // row and column positions
    private int row;
    private int col;

    // Constructor
    public Piece(int row, int col) {
        // set positions
        this.row = row;
        this.col = col;
    }

    // Getters and setters
    public int getRow() {
        return this.row;
    }
    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return this.col;
    }
    public void setCol(int col) {
        this.col = col;
    }

}
