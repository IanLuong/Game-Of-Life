package life;
import java.util.Scanner;
import java.util.Random;
import java.lang.Thread;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int inputSize = scanner.nextInt();

        Random random = new Random();
        Universe universe = new Universe(inputSize);

        GameOfLife playBoard = new GameOfLife();

        runGameOfLife(playBoard, universe, random);
    }

    public static void runGameOfLife(GameOfLife game, Universe universe, Random random) {
        int currentGeneration = 1;

        populateGrid(universe, random);
        assignNeighbours(universe);
        game.initBoard(universe);
        game.setLabels(currentGeneration, universe.getNoOfCells());

        while (true) {
            game.repaint();
            if(game.isReset()) {
                game.setReset(false);
                currentGeneration = 1;
                universe = new Universe(universe.getSize());
                random = new Random();

                populateGrid(universe, random);
                assignNeighbours(universe);
                game.setLabels(currentGeneration, universe.getNoOfCells());
                game.setBoard(universe);
            }

            if(game.isPlaying()) {
                //System.out.println("2");
                currentGeneration++;
                universe = processNextGeneration(universe);
                game.setLabels(currentGeneration, universe.getNoOfCells());
                game.setBoard(universe);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println("I was interrupted");
                }
            }
        }
    }

    public static void populateGrid(Universe board, Random randomizer) {
        for (int i = 0; i < board.getSize(); i++) {
            for (int j = 0; j < board.getSize(); j++) {
                char startingValue = randomizer.nextBoolean() ? 'O' : ' ';
                board.setCellAtPos(i, j, startingValue);
            }
        }
    }

    public static void assignNeighbours (Universe board){
        for (int i = 0; i < board.getSize(); i++) {
            for (int j = 0; j < board.getSize(); j++) {
                board.setCellNeighbours(i, j);
            }
        }
    }

    public static Universe processNextGeneration (Universe board) {
        Universe newBoard = new Universe(board.getSize());
        for (int i = 0; i < board.getSize(); i++) {
            for (int j = 0; j < board.getSize(); j++) {
                Cell currentCell = board.getCellAtPos(i, j);
                int currentCellNeighbours = currentCell.getNoOfNeighbours();

                if (currentCellNeighbours < 2 || currentCellNeighbours > 3) {
                    newBoard.setCellAtPos(i, j, ' ');
                } else if(currentCellNeighbours == 3) {
                    newBoard.setCellAtPos(i, j, 'O');
                } else {
                    newBoard.setCellAtPos(i, j, currentCell.getValue());
                }
            }
        }

        assignNeighbours(newBoard);
        return newBoard;
    }
}
