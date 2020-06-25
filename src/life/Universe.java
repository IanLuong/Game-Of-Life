package life;

class Universe {

    private int size;
    private Cell[][] matrix;

    public Universe(int size) {
        this.size = size;
        matrix = new Cell[size][size];
    }

    public int getSize() {
        return size;
    }


    public Cell getCellAtPos(int row, int col) {
        return matrix[row][col];
    }

    public String getMatrix() {
        StringBuilder builder = new StringBuilder();
        for (int row = 0; row < size; row++) {
            for (Cell cell : matrix[row]) {
                builder.append(cell.getValue());
            }
            builder.append(System.getProperty("line.separator"));
        }
        return builder.toString();
    }

    public int getNoOfCells() {
        int cellCount = 0;

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (matrix[i][j].getValue() == 'O') {
                    cellCount++;
                }
            }
        }

        return cellCount;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setCellAtPos(int row, int col, char value) {
        matrix[row][col] = new Cell(value);
    }

    public void setCellNeighbours(int row, int col) {
        int neighbourCount = 0;

        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                if (i == 0 && j == 0) { //ensures that cell does not count itself as a neighbour
                    continue;
                } else if (matrix[(((row + i) % size) + size) % size][(((col + j) % size) + size) % size].getValue() == 'O') {
                    neighbourCount++;
                }
            }
        }
        matrix[row][col].setNoOfNeighbours(neighbourCount);
    }
}
