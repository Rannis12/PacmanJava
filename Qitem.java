public class Qitem {
    public  int row;
    public  int col;
    public int dist;

    public Qitem(int row, int col, int dist) {
        this.row = row;
        this.col = col;
        this.dist = dist;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public int getDist() {
        return dist;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public void setDist(int dist) {
        this.dist = dist;
    }
}
