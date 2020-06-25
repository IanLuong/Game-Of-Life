package life;

class Cell {

    private char value;
    private int noOfNeighbours;

    public Cell(char value) {
        this.value = value;
    }

    public char getValue() {
        return value;
    }

    public void setValue(char value) {
        this.value = value;
    }

    public int getNoOfNeighbours() {
        return noOfNeighbours;
    }

    public void setNoOfNeighbours(int neighbours) {
        this.noOfNeighbours = neighbours;
    }

}
